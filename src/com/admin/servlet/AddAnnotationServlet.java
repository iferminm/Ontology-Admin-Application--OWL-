package com.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddAnnotationServlet
 */
@WebServlet("/AddAnnotationServlet")
public class AddAnnotationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String THESIS_PREFIX = "http://localhost/ontologies/ThesisOntology.owl#";
	private static final String RDF_PREFIX = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAnnotationServlet() {
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
		Map<String, String[]> map = request.getParameterMap();
		
		Set<String> keys = map.keySet();
		Iterator<String> keysIterator = keys.iterator();

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		while (keysIterator.hasNext()) {
			String current = keysIterator.next();
			String[] value = map.get(current);
			writer.print(current + "--->" + " ");
			for (int i = 0; i < value.length; i++) {
				writer.print(value[i] + "  ");
			}
			writer.println('\n');
		}
	}

}
