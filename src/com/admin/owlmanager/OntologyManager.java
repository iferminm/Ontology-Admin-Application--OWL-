package com.admin.owlmanager;


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
		return VirtuosoImporter.getTheInstance().deleteGraph(graphName);
	}
	
	public void loadOntology() {
		OWLReasoner reasoner = new OWLReasoner();
		boolean result = reasoner.generateReasonedModel(sourcePath, targetPath);
	}
	
	public void getTriplets() {
		
	}
	
	public void addResource() {
		
	}
	
	public void removeResource() {
		
	}
}
