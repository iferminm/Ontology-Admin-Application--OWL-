package com.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.config.ConfigManager;
import com.admin.owlmanager.OWLReasoner;
import com.admin.owlmanager.VirtuosoImporter;

/**
 * Servlet implementation class OntologyServlet
 */
@WebServlet("/OntologyServlet")
public class OntologyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OntologyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OWLReasoner reasoner = new OWLReasoner();
		ConfigManager cm = ConfigManager.getInstance();
		String sourcePath = "http://localhost/ontologies/ThesisOntology.owl";//cm.getProperty("baseModelPath");
		String targetPath = "/var/www/ontologies/reasoned.owl";//cm.getProperty("reasonedModelPath");
		String graphName = "thesis:model"; //cm.getProperty("graphName");
		String graphLabel = "http://localhost/ontologies/virt"; //cm.getProperty("graphLabel");
		
		boolean res = reasoner.generateReasonedModel(sourcePath, targetPath);
		res = res && VirtuosoImporter.getTheInstance().toVirtuoso(targetPath, graphName, graphLabel);
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		if (res) {
			writer.write("<h1>OK</h1>");
		} else {
			writer.write("<h1>ERROR</h1>");
		}
	}
}
