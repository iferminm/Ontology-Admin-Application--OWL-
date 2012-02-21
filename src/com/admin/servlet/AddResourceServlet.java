package com.admin.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddResourceServlet
 */
@WebServlet("/AddResourceServlet")
public class AddResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		Iterator<String> keysIterator = keys.iterator();
		
		while (keysIterator.hasNext()) {
			String currentKey = keysIterator.next();
			System.out.print(currentKey + " --> ");
			String[] currentValue = map.get(currentKey);
			for (int i = 0; i < currentValue.length; i++) {
				System.out.print(currentValue[i]);
			}
			System.out.println(". ");
		}
	}

}
