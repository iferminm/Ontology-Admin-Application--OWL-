package com.admin.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.admin.owlmanager.OWLReasoner;
import com.admin.owlmanager.VirtuosoImporter;

public class VirtuosoImporterTest {

	@Before
	public void setUp() {
		OWLReasoner reasoner = new OWLReasoner();
		boolean success = reasoner.generateReasonedModel(
				"/var/www/ontologies/ThesisOntology.owl", 
				"/var/www/ontologies/reasoned_unit_testing.owl");
		if (!success) {
			fail("There were problems setting up this test");
		}
	}
	
	@Test
	public void test() {
		boolean res = VirtuosoImporter.getTheInstance().toVirtuoso(
				"/var/www/ontologies/reasoned_unit_testing.owl",
				"Thesis:UnitTest",
				"http://localhost/thesis/virt_test");
		assertTrue("Operation not finished successfully", res);
	}
	
	@After
	public void deleteGenFile() {
		new File("/var/www/ontologies/reasoned_unit_testing.owl").delete();
		VirtuosoImporter.getTheInstance().deleteGraph("Thesis:UnitTest");
	}

}
