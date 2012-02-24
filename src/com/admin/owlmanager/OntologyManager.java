package com.admin.owlmanager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import com.admin.config.ConfigManager;
import com.admin.domain.Statement;
import com.admin.domain.Triplet;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

/**
 * This class manages all that has to do with the ontology
 * 
 * @author israelord
 */
public class OntologyManager {

	/**
	 * Deletes the given graph from Virtuoso
	 * @param graphName given graph
	 * @return True if everything went ok
	 */
	public boolean deleteOntology() {
		String graphName = ConfigManager.getInstance().getProperty("graphName");
		return VirtuosoActor.getTheInstance().deleteGraph(graphName);
	}
	
	/**
	 * Reads the base model, applies reasoning through the 
	 * corresponding method and imports the resulting reasoned
	 * model into Virtuoso
	 * @return True if everything went ok
	 */
	public boolean loadOntology() {
		OWLActor reasoner = new OWLActor();
		ConfigManager cm = ConfigManager.getInstance();
		String sourcePath = cm.getProperty("baseModelPath");
		String targetPath = cm.getProperty("reasonedModelPath");
		String graphName = cm.getProperty("graphName");
		String graphLabel = cm.getProperty("graphLabel");
		
		boolean result = reasoner.generateReasonedModel(sourcePath, targetPath);
		result = result && VirtuosoActor.getTheInstance().toVirtuoso(targetPath, graphName, graphLabel);

		return result;
	}
	
	/**
	 * Takes a ResultSet with it's three variables and 
	 * exports all the statements into an ArrayList<Triplet>
	 * to manage them easier in the presentation view
	 * @param res the ResultSet we want to export
	 * @return The ResultSet's statements as ArrayList<Triplet>
	 */
	private ArrayList<Triplet> populate(ResultSet res) {
		ArrayList<Triplet> result = new ArrayList<Triplet>();
		
		while (res.hasNext()) {
			QuerySolution qs = res.nextSolution();
			RDFNode subject = qs.get("s");
			RDFNode predicate = qs.get("p");
			RDFNode object = qs.get("o");
			
			Triplet t = new Triplet(subject.toString(), predicate.toString(), object.toString());
			result.add(t);
		}
		
		return result;
	}
	
	/**
	 * Gets all triplets of the reasoned model that
	 * persists in Virtuoso
	 * @return All the model as ArrayList<Triplet>
	 */
	public ArrayList<Triplet> getModelAsString() {
		ResultSet res = VirtuosoActor.getTheInstance()
							.getAllModel(ConfigManager
							.getInstance().getProperty("graphName"));
		
		ArrayList<Triplet> result = this.populate(res);
		
		return result;
	}
		
	/**
	 * Gets all the classes of the owls base model
	 * @return All classes as a TreeSet<Statement>
	 */
	public TreeSet<Statement> getClasses() {
		TreeSet<Statement> result = new TreeSet<Statement>();
		ExtendedIterator<OntClass> iter = new OWLActor().getClasses(ConfigManager.getInstance().getProperty("baseModelPath"));
		
		while (iter.hasNext()) {
			OntClass c = iter.next();
			result.add(new Statement(c.toString()));
		}
		
		return result;
	}
	
	/**
	 * Gets all instances or named individuals from a 
	 * given class
	 * @param className the complete class' name
	 * @return all the class' instances
	 */
	public TreeSet<Statement> getClassInstances(String className) {
		TreeSet<Statement> result = new TreeSet<Statement>();
		ExtendedIterator<?> iter = new OWLActor().getInstances(ConfigManager.getInstance()
													.getProperty("baseModelPath"), className);
		while (iter.hasNext()) {
			Object i = iter.next();
			result.add(new Statement(i.toString()));
		}

		return result;
	}
	
	/**
	 * Gets the variable names from a QuerySolution object
	 * those variable names are the ones on the select ?x ?y ?z
	 * @param sol QuerySolution with variable names
	 * @return an ArrayList<String> with the requested variable names to be evaluated
	 */
	private ArrayList<String> getVarNames (QuerySolution sol) {
		Iterator<String> iter = sol.varNames();
		ArrayList<String> result = new ArrayList<String>();
		while (iter.hasNext()) {
			String current = iter.next();
			result.add(current);
		}
		return result;
	}
	
	/**
	 * Populates a TreeSet with the resulting statements
	 * of a SELECT ?s query type
	 * @param rs The ResultSet object
	 * @return All the ResultSet's Stataments as TreeSet<Statement>
	 */
	private TreeSet<Statement> populateOneResult(ResultSet rs) {
		TreeSet<Statement> result = new TreeSet<Statement>();
		
		while (rs.hasNext()) {
			QuerySolution solution = rs.nextSolution();
			String varName = this.getVarNames(solution).get(1);
			RDFNode node = solution.get(varName);
			result.add(new Statement(node.toString()));
		}
		
		return result;
	}
	
	/**
	 * Builds a query of the form SELECT ?s FROM and returns
	 * the result set as a TreeSet<Statement> to manage it
	 * easier in the presentation layer
	 * @param select variable name
	 * @param whereConditions the "ready to use" conditions to be satisfied by the WHERE clause
	 * @return The ResultSet as a TreeSet<Statement>
	 */
	public TreeSet<Statement> oneResultQuery(String select, String whereConditions) {
		String graphName = ConfigManager.getInstance().getProperty("graphName");
		
		String query = "SELECT " + select + " FROM <" + graphName + "> WHERE { " + whereConditions + " }";
		
		ResultSet res = VirtuosoActor.getTheInstance().executeOneResultQuery(graphName, query);
	
		return this.populateOneResult(res);
	}
	
	/**
	 * Search a requested class and returns the
	 * matching Statement instance
	 * @param requestedClass name of the requested class
	 * @return the requested class' statement
	 */
	public Statement getSingleClass(String requestedClass) {
		TreeSet<Statement> classes = this.getClasses();
		Iterator<Statement> iter = classes.iterator();
		
		while (iter.hasNext()) {
			Statement current = iter.next();
			if (current.getStatement().equalsIgnoreCase(requestedClass)) {
				return current;
			}
		}
		
		return null;
	}
	
	/**
	 * Gets a class throgh its clean name
	 * @param requestedClassClean clean name for the requested class (with no prefix)
	 * @return the requested statement (with prefix)
	 */
	public Statement getSingleClassClean(String requestedClassClean) {
		TreeSet<Statement> classes = this.getClasses();
		Iterator<Statement> iter = classes.iterator();
		
		while (iter.hasNext()) {
			Statement current = iter.next();
			if (current.getCleanStatement().equalsIgnoreCase(requestedClassClean)) {
				return current;
			}
		}
		
		return null;
	}
	
	/**
	 * Gets all properties on the model
	 * @return All the properties as TreeSet<Statement>
	 */
	private TreeSet<Statement> getAllProperties() {
		TreeSet<Statement> result = new TreeSet<Statement>();
		OWLActor owl = new OWLActor();
		ExtendedIterator<OntProperty> iter = owl.getProperties(ConfigManager.getInstance().getProperty("baseModelPath"));
		
		while (iter.hasNext()) {
			OntProperty current = iter.next();
			result.add(new Statement(current.toString()));
		}
		
		return result;
	}
	
	/**
	 * Gets a single property through its clean name 
	 * @param cleanName name we're looking for
	 * @return the requested statement
	 */
	private Statement getSinglePropertyClean(String cleanName) {
		TreeSet<Statement> properties = this.getAllProperties();
		Statement result = null;
		Iterator<Statement> iter = properties.iterator();
		
		while (iter.hasNext()) {
			result = iter.next();
			if (result.getCleanStatement().equals(cleanName)) {
				break;
			}
		}
		
		return result;
	}
		
	/**
	 * Adds a resource and its annotations to the OWL model
	 * @param resource resource to be added
	 * @param anotations selected annotations
	 * @return True if everything went ok
	 */
	public boolean addResource(String resource, String[] anotations) {
		String writeModelPath = ConfigManager.getInstance().getProperty("baseModelLocation");
		String modelURL = ConfigManager.getInstance().getProperty("baseModelPath");
		OWLActor owl = new OWLActor();
		Statement cls = this.getSingleClassClean("resource");
		Statement relation = this.getSinglePropertyClean("has-annotation");
		
		boolean resultIndividual = owl.addIndividual(writeModelPath, modelURL, resource, cls.getStatement());
		boolean resultTriples = true;
		
		
		for (int i = 0; i < anotations.length; i++) {
			resultTriples = resultTriples && owl.addTripleStore(writeModelPath, modelURL, resource, relation.getStatement(), anotations[i]);
		}
		
		return resultIndividual && resultTriples;
	}
	
	/**
	 * Deletes a resource through its URI 
	 * @param resourceURI target resource URI
	 * @return True if everything went ok
	 */
	public boolean deleteResource(String resourceURI) {
		boolean result = false;
		OWLActor owl = new OWLActor();
		String modelPath = ConfigManager.getInstance().getProperty("baseModelLocation");
		String modelUrl = ConfigManager.getInstance().getProperty("baseModelPath");
		result = owl.deleteIndividual(modelPath, modelUrl, resourceURI);
		return result;
	}
	
	/**
	 * Gets all annotations for a given resource URI
	 * @param resource URI
	 * @return all the resource's properties as a TreeSet<Statement>
	 */
	public TreeSet<Statement> getResourceAnnotations(String resource) {
		TreeSet<Statement> result = new TreeSet<Statement>();
		OWLActor owl = new OWLActor();
		ArrayList<RDFNode> values = owl.getIndividualPropertyValues(ConfigManager.getInstance().getProperty("baseModelPath"), 
									resource, "http://localhost/ontologies/ThesisOntology.owl#has-annotation");
		
		Iterator<RDFNode> iter = values.iterator();
		
		while (iter.hasNext()) {
			RDFNode current = iter.next();
			result.add(new Statement(current.toString()));
		}
		
		return result;
	}
	
	/**
	 * Deletes selected annotations from a given resource
	 * @param resourceURI the target resource
	 * @param annotations the selected annotations
	 * @return True if everything went ok
	 */
	public boolean deleteResourceAnnotations(String resourceURI, ArrayList<String> annotations) {
		boolean result = false;
		String prefix = "http://localhost/ontologies/ThesisOntology.owl#";
		String relation = "has-annotation";
		
		String modelURL = ConfigManager.getInstance().getProperty("baseModelPath");
		String modelPath = ConfigManager.getInstance().getProperty("baseModelLocation");
		
		OWLActor owl = new OWLActor();
		result = owl.deleteIndividualObjects(modelURL, modelPath, resourceURI, annotations, prefix, relation);
		
		return result;
	}
	
	public boolean addTriplet(String subject, String predicate, String object) {
		boolean result = false;
		
		
		return result;
	}
}
