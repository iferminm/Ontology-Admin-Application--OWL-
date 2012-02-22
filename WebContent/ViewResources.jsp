<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.admin.domain.*" %>
<%@ page import="com.admin.owlmanager.OntologyManager" %>
<%@ page import="java.util.TreeSet" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Stylesheet" href="css/GeneralStyle.css" type="text/css" />
<title>Strigi Ontology Manager</title>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<p> Este es el header</p>
		</div>
		<div id="menu">
			<div id="menuitem">
				<p>View Ontology</p>
			</div>
			<div id="menuitem">
				<p><a href="PreAddResource.html">Add Resource</a></p>			
			</div>
			<div id="menuitem">
				<p><a href="ViewResources.jsp">View Resources</a></p>			
			</div>
			<div id="menuitem">
				<p>Add Annotation</p>			
			</div>
			<div id="menuitem">
				<p>View Annotations</p>			
			</div>
		</div>
		<div id="contentwrapper">
			<h2 align="center">Available Resources</h2>
			<br />
			<%
				final String RESOURCE_CLASS = "http://localhost/ontologies/ThesisOntology.owl#Resource";
				OntologyManager om = new OntologyManager();
				TreeSet<Statement> resources = om.getClassInstances(RESOURCE_CLASS);
				Iterator<Statement> iter = resources.iterator();
				out.write("<table width=\"100%\">");
				while (iter.hasNext()) {
					String text = iter.next().toString();
					out.write("<tr>");
					out.write("<td>");
					out.write("<p align=\"center\"><a href=\"EditResource.jsp?id=" + text + "\">" + text + "</a></p>");
					out.write("</td>");
					out.write("</tr>");
				}
				out.write("</table>");
			%>
		</div>
	</div>

</body>
</html>