package com.admin.owlmanager;

import java.io.FileInputStream;

import com.hp.hpl.jena.rdf.model.Model;

import virtuoso.jena.driver.VirtModel;

/**
 * This singleton class imports an OWL model 
 * into a Virtuoso stored Graph 
 * 
 * @author israelord
 */
public class VirtuosoImporter {
	
	private static VirtuosoImporter instance = new VirtuosoImporter();
	
	private String connection;
	private String user;
	private String pwd;
	
	private VirtuosoImporter() {
		//TODO read from properties
		this.connection = "jdbc:virtuoso://localhost:1111";
		this.user = "dba";
		this.pwd = "virtuoso";
	}
	
	public static VirtuosoImporter getTheInstance() {
		return instance;
	}
	
	/**
	 * Imports an OWL model into a Virtuoso
	 * stored Graph
	 * @param path OWL model location
	 * @param graphName given name for the graph we're going to store
	 *        This name will also be used for SPARQL Queries
	 * @param label a label for the graph, usually on an URL form
	 * 
	 * @return
	 */
	public boolean toVirtuoso(String path, String graphName, String label) {
		boolean res = false;
		try {
			Model virtModel = VirtModel.openDatabaseModel(graphName, connection, user, pwd);
			virtModel.read(new FileInputStream(path), label);
			res = true;
		} catch (Exception ex) {
			// TODO: log4j
			ex.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Permanently deletes a stored graph
	 * @param graphName the name of the graph we want to delete
	 * @return true if everything went OK
	 */
	public boolean deleteGraph(String graphName) {
		boolean res = false;
		try {
			Model virtModel = VirtModel.openDatabaseModel(graphName, connection, user, pwd);
			virtModel.removeAll();
			res = true;
		} catch(Exception ex) {
			// TODO: log4j
			ex.printStackTrace();
		}
		return res;
	}
}
