package com.admin.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.admin.config.ConfigManager;
import com.admin.owlmanager.OWLActor;
import com.admin.owlmanager.VirtuosoActor;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class VirtuosoActorTest {

	@Before
	public void setUp() {
		OWLActor reasoner = new OWLActor();
		boolean success = reasoner.generateReasonedModel(
				"/var/www/ontologies/ThesisOntology.owl", 
				"/var/www/ontologies/reasoned_unit_testing.owl");
		if (!success) {
			fail("There were problems setting up this test");
		}
	}
	
	@Test
	public void test() {
		boolean res = VirtuosoActor.getTheInstance().toVirtuoso(
				"/var/www/ontologies/reasoned_unit_testing.owl",
				"Thesis:UnitTest",
				"http://localhost/thesis/virt_test");
		assertTrue("Operation not finished successfully", res);
	}
	
	@Test
	public void getAllModelTest() {
		ResultSet res = VirtuosoActor.getTheInstance().getAllModel(ConfigManager.getInstance().getProperty("graphName"));
		assertTrue("Troubles getting model: ", res != null);
	}
	
	@Test
	public void executeOneResultQueryTest() {
		String query = "SELECT * FROM <tesis:model> WHERE " + 
				"{?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/ontologies/ThesisOntology.owl#KnowledgeArea> . " +
				"?s <http://localhost/ontologies/ThesisOntology.owl#in-discipline> <http://localhost/ontologies/ThesisOntology.owl#ComputerScience>} ";
		ResultSet res = VirtuosoActor.getTheInstance().executeOneResultQuery(ConfigManager.getInstance().getProperty("graphName"), query);
		
		assertTrue("Query Error", res != null);
	}
	
	
	@After
	public void deleteGenFile() {
		new File("/var/www/ontologies/reasoned_unit_testing.owl").delete();
		VirtuosoActor.getTheInstance().deleteGraph("Thesis:UnitTest");
	}

}
