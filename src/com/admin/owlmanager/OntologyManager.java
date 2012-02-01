package com.admin.owlmanager;

import com.admin.config.ConfigManager;


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
	
	public boolean loadOntology() {
		OWLReasoner reasoner = new OWLReasoner();
		ConfigManager cm = ConfigManager.getInstance();
		String sourcePath = cm.getProperty("baseModelPath");
		String targetPath = cm.getProperty("reasonedModelPath");
		String graphName = cm.getProperty("graphName");
		String graphLabel = cm.getProperty("graphLabel");
		
		boolean result = reasoner.generateReasonedModel(sourcePath, targetPath);
		result = result && VirtuosoImporter.getTheInstance().toVirtuoso(targetPath, graphName, graphLabel);

		return result;
	}
	
	public void getTriplets() {
		
	}
	
	public void addResource() {
		
	}
	
	public void removeResource() {
		
	}
}
