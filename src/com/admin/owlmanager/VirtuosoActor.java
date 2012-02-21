package com.admin.owlmanager;

import java.io.FileInputStream;

import com.admin.config.ConfigManager;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtModel;
import virtuoso.jena.driver.VirtuosoQueryExecution;
import virtuoso.jena.driver.VirtuosoQueryExecutionFactory;

/**
 * This singleton class imports an OWL model 
 * into a Virtuoso stored Graph 
 * 
 * @author israelord
 */
public class VirtuosoActor {
	
	private static VirtuosoActor instance = new VirtuosoActor();
	
	private String connection;
	private String user;
	private String pwd;
	
	private VirtuosoActor() {
		this.connection = ConfigManager.getInstance().getProperty("virtuosoConnectionString");
		this.user = ConfigManager.getInstance().getProperty("virtuosoUser");
		this.pwd = ConfigManager.getInstance().getProperty("virtuosoPassword");
	}
	
	public static VirtuosoActor getTheInstance() {
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
	 * @return True is everything went ok
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
	
	/**
	 * Gets a connection through Virtuoso
	 * @param graphName
	 * @return a VirtGraph connection resource
	 */
	private VirtGraph connect(String graphName) {
		return new VirtGraph (graphName, connection, user, pwd);
	}
	
	/**
	 * Gets all the triplets of the model
	 * 
	 * @param graphName the graph's given name
	 * @return All the statements
	 */
	public ResultSet getAllModel(String graphName) {
		String query = "SELECT * FROM <" + graphName + "> " + 
				"WHERE { ?s ?p ?o }";
		
		Query sparql = QueryFactory.create(query);
		
		VirtGraph graph = this.connect(graphName);
		
		VirtuosoQueryExecution vqe = VirtuosoQueryExecutionFactory.create(sparql, graph);
		ResultSet res = vqe.execSelect();
		
		return res;
	}
	
	/**
	 * Executes a query which returns only one variable of the triplet, a select 
	 * with the following.
	 * @param graphName 
	 * @param query
	 * @return the query's ResultSet
	 */
	public ResultSet executeOneResultQuery(String graphName, String query) {
		Query sparql = QueryFactory.create(query);
		VirtGraph graph = this.connect(graphName);
		
		VirtuosoQueryExecution vqe = VirtuosoQueryExecutionFactory.create(sparql, graph);
		
		ResultSet res = vqe.execSelect();
		
		return res;
	}
}
