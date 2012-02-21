package com.admin.owlmanager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import com.admin.config.ConfigManager;
import com.admin.domain.Statement;
import com.admin.domain.Triplet;
import com.hp.hpl.jena.ontology.OntClass;
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
	
	public void addTriplet() {
		
	}

	public void deleteTriplet() {
		
	}
	
	public boolean deleteOntology(String graphName) {
		return VirtuosoActor.getTheInstance().deleteGraph(graphName);
	}
	
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
	
	public ArrayList<Triplet> getModelAsString() {
		ResultSet res = VirtuosoActor.getTheInstance()
							.getAllModel(ConfigManager
							.getInstance().getProperty("graphName"));
		
		ArrayList<Triplet> result = this.populate(res);
		
		return result;
	}
	
	public void getTriplets() {
		
	}
	
	public TreeSet<Statement> getClasses() {
		TreeSet<Statement> result = new TreeSet<Statement>();
		ExtendedIterator<OntClass> iter = new OWLActor().getClasses(ConfigManager.getInstance().getProperty("baseModelPath"));
		
		while (iter.hasNext()) {
			OntClass c = iter.next();
			result.add(new Statement(c.toString()));
		}
		
		return result;
	}
	
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
	
	private ArrayList<String> getVarNames (QuerySolution sol) {
		Iterator<String> iter = sol.varNames();
		ArrayList<String> result = new ArrayList<String>();
		while (iter.hasNext()) {
			String current = iter.next();
			result.add(current);
		}
		return result;
	}
	
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
	
	public TreeSet<Statement> oneResultQuery(String select, String whereConditions) {
		String graphName = ConfigManager.getInstance().getProperty("graphName");
		
		String query = "SELECT " + select + " FROM <" + graphName + "> WHERE { " + whereConditions + " }";
		
		ResultSet res = VirtuosoActor.getTheInstance().executeOneResultQuery(graphName, query);
		
		return this.populateOneResult(res);
	}
	
	public void addResource() {
		
	}
	
	public void removeResource() {
		
	}
}
