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
		
		System.out.println(classes);
		
		assertTrue("ERROR:", classes.size() == 11);
	}

}
