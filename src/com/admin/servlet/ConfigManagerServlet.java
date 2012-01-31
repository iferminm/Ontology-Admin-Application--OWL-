package com.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.config.ConfigManager;

/**
 * Servlet implementation class ConfigManagerServlet
 */
@WebServlet("/ConfigManagerServlet")
public class ConfigManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String filename = "ServerConfig.properties";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfigManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getServletContext().getRequestDispatcher("/WebContent/ConfigManager.jsp").forward(request, response);
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		HashMap<String, String> map = this.unpackRequest(request);
		
		boolean setOk = this.setProperties(map);
		
		if (setOk) {
			writer.write("<h2>Configuration Saved</h2>");
		} else {
			writer.write("<h2>There were errors seting the configuration</h2>");
		}
		writer.close();
	}

	private boolean setProperties(HashMap<String, String> parameters) {
		boolean result = false;
		Iterator<String> keyIterator = parameters.keySet().iterator();
		ConfigManager cm = new ConfigManager(filename);
		while (keyIterator.hasNext()) {
			String currentKey = keyIterator.next();
			String currentValue = parameters.get(currentKey);
			result = cm.setProperty(currentKey, currentValue);
			if (!result) {
				return false;
			}
		}
		return true;
	}
	
	private HashMap<String, String> unpackRequest(HttpServletRequest request) {
		HashMap<String, String> parameters = new HashMap<String, String>();
		
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String currentName = (String) paramNames.nextElement();
			String currentValue = request.getParameter(currentName);
			parameters.put(currentName, currentValue);
		}
		
		return parameters;
		
		
	}
	
	private static void addContentToTable(PrintWriter writer, String key, String value) {
		startTableRow(writer);
		addTableColumn(writer, key);
		addTableColumn(writer, value);
		closeTableRow(writer);
	}

	private static void startTableRow(PrintWriter writer) {
		writer.write("<tr>");
	}
	
	private static void closeTableRow(PrintWriter writer) {
		writer.write("</tr>");
	}
	
	private static void addTableColumn(PrintWriter writer, String value) {
		writer.write("<td>" + value + "</td>");
	}
}
