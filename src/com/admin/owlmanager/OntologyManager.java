package com.admin.owlmanager;

import com.admin.config.ConfigManager;
import com.hp.hpl.jena.query.ResultSet;


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
		return VirtuosoActor.getTheInstance().deleteGraph(graphName);
	}
	
	public boolean loadOntology() {
		OWLActor reasoner = new OWLActor();
		ConfigManager cm = ConfigManager.getInstance();
		String sourcePath = cm.getProperty("baseModelPath");
		String targetPath = cm.getProperty("reasonedModelPath");
		String graphName = cm.getProperty("graphName");
		String graphLabel = cm.getProperty("graphLabel");
		
		boolean result = reasoner.generateReasonedModel(sourcePath, targetPath);
		result = result && VirtuosoActor.getTheInstance().toVirtuoso(targetPath, graphName, graphLabel);

		return result;
	}
	
	private String[][][] populate(ResultSet rs) {
		String[][][] result = null;
		
		return result;
	}
	
	public String[][][] getModelAsString() {
		String[][][] res = null;
		ResultSet rs = VirtuosoActor.getTheInstance()
				.getAllModel(ConfigManager.getInstance()
						.getProperty("graphName"));
		res = this.populate(rs);
		
		return res;
	}
	
	public void getTriplets() {
		
	}
	
	public void addResource() {
		
	}
	
	public void removeResource() {
		
	}
}
