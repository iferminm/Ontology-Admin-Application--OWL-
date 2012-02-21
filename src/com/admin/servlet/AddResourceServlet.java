package com.admin.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.domain.Statement;
import com.admin.owlmanager.OntologyManager;

/**
 * Servlet implementation class AddResourceServlet
 */
@WebServlet("/AddResourceServlet")
public class AddResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String CONCEPT = "concept";
	private static final String TOPIC = "topic";
	private static final String UNIT = "unit";
	private static final String KNOWLEDGE_AREA = "knowledgearea";
	private static final String DISCIPLINE = "discipline";
	private static final String ENTITY = "entity";
	private static final String TOOL = "tool";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddResourceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> map = request.getParameterMap();
		Set<String> keys = map.keySet();
		String classToAdd = null;
		
		if ((keys.contains(CONCEPT)) && !(map.get(CONCEPT)[0].equalsIgnoreCase("invalid") || (map.get(CONCEPT)[0].equalsIgnoreCase("noselect")))) {
			classToAdd = "Concept";
		} else if ((keys.contains(TOPIC)) && !(map.get(TOPIC)[0].equalsIgnoreCase("invalid") || (map.get(TOPIC)[0].equalsIgnoreCase("noselect")))) {
			classToAdd = "Topic";
		} else if ((keys.contains(UNIT)) && !(map.get(UNIT)[0].equalsIgnoreCase("invalid") || (map.get(UNIT)[0].equalsIgnoreCase("noselect"))))  {
			classToAdd = "Unit";
		} else if ((keys.contains(KNOWLEDGE_AREA)) && !(map.get(KNOWLEDGE_AREA)[0].equalsIgnoreCase("invalid") || (map.get(KNOWLEDGE_AREA)[0].equalsIgnoreCase("noselect")))) {
			classToAdd = "Knowledge Area";
		} else if ((keys.contains(DISCIPLINE)) && !(map.get(DISCIPLINE)[0].equalsIgnoreCase("invalid") || (map.get(DISCIPLINE)[0].equalsIgnoreCase("noselect")))) {
			classToAdd = "Discipline";
		} else {
			if ((keys.contains(ENTITY)) && !(map.get(ENTITY)[0].equalsIgnoreCase("invalid") || (map.get(ENTITY)[0].equalsIgnoreCase("noselect")))) {
				classToAdd = "Entity";
			} else if ((keys.contains(TOOL)) && !(map.get(TOOL)[0].equalsIgnoreCase("invalid") || (map.get(TOOL)[0].equalsIgnoreCase("noselect")))) {
				classToAdd = "Tool";
			} else {
				classToAdd = "Levantamos un error";
			}
		}
		
		System.out.println("Clase: " + classToAdd);
		
		/*
		Iterator<String> keysIterator = keys.iterator();
		
		while (keysIterator.hasNext()) {
			String currentKey = keysIterator.next();
			System.out.print(currentKey + " --> ");
			String[] currentValue = map.get(currentKey);
			for (int i = 0; i < currentValue.length; i++) {
				System.out.print(currentValue[i]);
			}
			System.out.println(". ");
		}*/
	}

}
