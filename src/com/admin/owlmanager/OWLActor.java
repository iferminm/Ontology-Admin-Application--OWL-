package com.admin.owlmanager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;



/**
 * This class, generates a complete reasoned OWL file
 * from a base model
 * 
 * @author israelord
 */
public class OWLActor {

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
	
	public ExtendedIterator<OntClass> getClasses(String modelUrl) {
		OntModel model = ModelFactory.createOntologyModel();
		model.read(modelUrl);

		ExtendedIterator<OntClass> classes = model.listClasses();

		return classes;
	}
	
	public ExtendedIterator<?> getInstances(String modelUrl, String className) {
		OntModel model = ModelFactory.createOntologyModel();
		model.read(modelUrl);
		OntClass c = model.getOntClass(className);
		ExtendedIterator<?> iter = c.listInstances();

		return iter;
	}
}
