package com.admin.test;

import static org.junit.Assert.*;

import java.util.TreeSet;


import org.junit.Test;

import com.admin.domain.Statement;
import com.admin.owlmanager.OntologyManager;

public class OntologyManagerTest {

	@Test
	public final void getClassesTest() {
		OntologyManager om = new OntologyManager();
		TreeSet<Statement> classes = om.getClasses();
		
		assertTrue("ERROR:", classes.size() == 11);
	}
	
	@Test
	public final void oneResultQueryTest() {
		OntologyManager om = new OntologyManager();
		String conditions = "?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/ontologies/ThesisOntology.owl#KnowledgeArea> . " +
							"?s <http://localhost/ontologies/ThesisOntology.owl#in-discipline> <http://localhost/ontologies/ThesisOntology.owl#ComputerScience>";
		TreeSet<Statement> result = om.oneResultQuery("?s", conditions);
		
		assertTrue("Query Error", result.size() == 14);
	}

}
