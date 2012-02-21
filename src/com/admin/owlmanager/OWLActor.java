package com.admin.owlmanager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.RDF;



/**
 * This class, generates a complete reasoned OWL file
 * from a base model
 * 
 * @author israelord
 */
public class OWLActor {
	private static final String NAMED_INDIVIDUAL = "http://www.w3.org/2002/07/owl#NamedIndividual";

	/**
	 * Writes an OWL model to a file on the given path
	 * 
	 * @param model Model to be written
	 * @param path Path to the file where the model will be written
	 * @throws FileNotFoundException if the route to the file is not valid
	 */
	private void writeModel(InfModel model, String path) throws FileNotFoundException {
		FileOutputStream out = new FileOutputStream(path);
		model.write(out);
	}
	
	/**
	 * Applies forward chaining reasoning through pellet to a 
	 * given model
	 * @param path route to the model
	 * @return a complete reasoned model
	 */
	private InfModel reasonOverModel(String path) {
		Model emptyModel = ModelFactory.createDefaultModel();
		
		//Pellet Instance
		Reasoner reasoner = PelletReasonerFactory.theInstance().create();
		
		InfModel model = ModelFactory.createInfModel(reasoner, emptyModel);
		InputStream in = FileManager.get().open(path);
		model.read(in, "");
		
		return model;
	}
	
	/**
	 * TODO: read locations from properties files
	 * 
	 * @param sourcePath Original model to be reasoned
	 * @param targetPath Target file to store the reasoned model
	 * @return true if everything went OK
	 */
	public boolean generateReasonedModel(String sourcePath, String targetPath) {
		InfModel model = this.reasonOverModel(sourcePath);
		boolean res = false;
		try {
			this.writeModel(model, targetPath);
			res = true;
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			// TODO log4j
		} catch (Exception ex) {
			ex.printStackTrace();
			// TODO log4j
		}
		return res;
	}

	/**
	 * Gets all classes available on the model
	 * @param modelUrl the location of the owl model
	 * @return a list of classes
	 */
	public ExtendedIterator<OntClass> getClasses(String modelUrl) {
		OntModel model = ModelFactory.createOntologyModel();
		model.read(modelUrl);

		ExtendedIterator<OntClass> classes = model.listClasses();

		return classes;
	}
	
	/**
	 * Gets all available instances of a given class
	 * 
	 * @param modelUrl the location of the owl model
	 * @param className the name of the class
	 * @return the list of instances for the given class
	 */
	public ExtendedIterator<?> getInstances(String modelUrl, String className) {
		OntModel model = ModelFactory.createOntologyModel();
		model.read(modelUrl);
		OntClass c = model.getOntClass(className);
		ExtendedIterator<?> iter = c.listInstances();

		return iter;
	}
	
	public boolean addIndividual(String modelPath, String modelUrl, String uri, String className) {
		OntModel model = ModelFactory.createOntologyModel();
		model.read(modelUrl);
		OntClass c = model.getOntClass(className);
	
		model.createIndividual(uri, c);	
		
		try {
			this.writeModel(model, modelPath);
		} catch (FileNotFoundException e) {
			// TODO: log4j
			e.printStackTrace();
			return false;
		} finally {
			model.close();
		}
		
		return true;
	}
	
	public ExtendedIterator<OntProperty> getProperties(String modelURL) {
		OntModel model = ModelFactory.createOntologyModel();
		model.read(modelURL);
		
		ExtendedIterator<OntProperty> result = model.listAllOntProperties();
		
		return result;
	}
	
	public boolean addTripleStore(String modelPath, String modelUrl, String subject, String predicate, String object) {
		OntModel model = ModelFactory.createOntologyModel();
		model.read(modelUrl);
		
//		System.out.println("SUBJECT: " + subject);
		
		Resource resource = model.getResource(subject);
		Property property = model.getOntProperty(predicate);
		RDFNode node = model.getIndividual(object);
		
//		System.out.println("STATEMENT: " + resource + "   " + property + "   " + node);
		
//		resource.addProperty(property, node);
		
//		StmtIterator iter = resource.listProperties();
		
//		System.out.println(iter.toList().size());
		
//		while (iter.hasNext()) {
//			Statement current = iter.next();
//			System.out.println("entré acá");
//			System.out.println(current.toString());
//		}
		
//		System.out.println("salí del while o nunca entré");
		
		Statement statement = ResourceFactory.createStatement(resource, property, node);
		
		model.add(statement);
		
//		model.add(statement);
		
		try {
			this.writeModel(model, modelPath);
		} catch (FileNotFoundException e) {
			// TODO log4j
			e.printStackTrace();
			return false;
		} finally {
			model.close();
		}
		
		return true;
	}
	
}
