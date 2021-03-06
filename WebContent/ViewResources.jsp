<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.admin.domain.*" %>
<%@ page import="com.admin.owlmanager.OntologyManager" %>
<%@ page import="java.util.TreeSet" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.net.URLEncoder" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Stylesheet" href="css/GeneralStyle.css" type="text/css" />
<title>Striggi Ontology Manager</title>
</head>
<body>
	<div id="wrapper">
		<div id="header">
		
		</div>
		<div id="menu">
			<div id="menuitem">
				<p><a href="PreAddResource.jsp">Add Resource</a></p>			
			</div>
			<div id="menuitem">
				<p><a href="ViewResources.jsp">View Resources</a></p>			
			</div>
			<div id="menuitem">
				<p><a href="AddAnnotation.jsp">Add Annotation</a></p>			
			</div>
			<div id="menuitem">
				<p><a href="ViewAnnotations.jsp">View Annotations</a></p>			
			</div>
		</div>
		<div id="contentwrapper">
			<h2 align="center">Available Resources</h2>
			<br />
			<%
				final String RESOURCE_CLASS = "http://localhost/ontologies/ThesisOntology.owl#Resource";
					OntologyManager om = new OntologyManager();
					TreeSet<MyStatement> resources = om.getClassInstances(RESOURCE_CLASS);
					Iterator<MyStatement> iter = resources.iterator();
					out.write("<table width=\"100%\">");
					while (iter.hasNext()) {
						String text = iter.next().toString();
						String URLText = URLEncoder.encode(text, "UTF-8");
						out.write("<tr>");
						out.write("<td>");
						out.write("<p align=\"center\"><a href=\"EditResource.jsp?id=" + URLText + "\">" + text + "</a></p>");
						out.write("</td>");
						out.write("</tr>");
					}
					out.write("</table>");
			%>
		</div>
	</div>

</body>
</html>