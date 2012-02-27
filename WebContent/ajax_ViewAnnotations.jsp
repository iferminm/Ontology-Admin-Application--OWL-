<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.admin.owlmanager.OntologyManager" %>
<%@ page import="com.admin.domain.*" %>
<%@ page import="java.util.TreeSet" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.net.URLEncoder" %>

<%
	String className = request.getParameter("className");


if ( (className.equalsIgnoreCase("noselect")) || (className.equalsIgnoreCase("invalid")) ) {
	out.println("<h4>You must select a valid class<h4>");
} else {
	OntologyManager manager = new OntologyManager();
	TreeSet<MyStatement> classInstances = manager.getClassInstances(className);
	Iterator<MyStatement> iterator = classInstances.iterator();

	while (iterator.hasNext()) {
		MyStatement currentInstance = iterator.next();
		String printInstance = "<li><a href=\"EditAnnotation.jsp?annot=" + URLEncoder.encode(currentInstance.getStatement(), "UTF-8") + "\">";
		printInstance += currentInstance.getCleanStatement() + "</a></li>";
		out.println(printInstance);
	}
}
%>