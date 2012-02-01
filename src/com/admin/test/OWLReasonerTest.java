package com.admin.test;


import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Test;

import com.admin.owlmanager.OWLReasoner;

public class OWLReasonerTest {
	
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
		OWLReasoner reasoner = new OWLReasoner();
		boolean res = reasoner.generateReasonedModel(
				"http://localhost/ontologies/ThesisOntology.owl", 
				"/var/www/ontologies/reasoned_unit_testing.owl");
		boolean success = new File("/var/www/ontologies/reasoned_unit_testing.owl").exists();
		assertTrue("Error creating the reasoned file", res && success);
	}
}
