package com.admin.test;


import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Test;

import com.admin.config.ConfigManager;
import com.admin.owlmanager.OWLActor;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class OWLActorTest {
	
	@After
	public void deleteGenFile() {
		File file = new File("/var/www/ontologies/reasoned_unit_testing.owl");
		if ( file.exists() ) {
			boolean success = file.delete();
			if (!success) {
				fail("Problems restoring development environment");
			}
		}
	}

	@Test
	public void testGenerateReasonedModel() {
		OWLActor reasoner = new OWLActor();
		boolean res = reasoner.generateReasonedModel(
				"http://localhost/ontologies/ThesisOntology.owl", 
				"/var/www/ontologies/reasoned_unit_testing.owl");
		boolean success = new File("/var/www/ontologies/reasoned_unit_testing.owl").exists();
		assertTrue("Error creating the reasoned file", res && success);
	}
	
	@Test
	public void getClassesTest() {
		OWLActor owl = new OWLActor();
		ExtendedIterator<OntClass> classes = owl.getClasses(ConfigManager.getInstance().getProperty("baseModelPath"));
		assertTrue("Something went wrong",  classes.toList().size() == 11);
	}
	
	@Test
	public void getInstancesTest() {
		OWLActor owl = new OWLActor();
		String className = "http://localhost/ontologies/ThesisOntology.owl#KnowledgeArea";
		ExtendedIterator<?> iter = owl.getInstances(ConfigManager.getInstance().getProperty("baseModelPath"), className);
		
		assertTrue("ERROR",  iter.toList().size() == 14);
	}
}
