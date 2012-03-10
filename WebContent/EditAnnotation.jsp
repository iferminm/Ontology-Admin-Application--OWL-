<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.admin.owlmanager.OntologyManager" %>
<%@ page import="com.admin.domain.*" %>
<%@ page import="java.util.TreeSet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Stylesheet" href="css/GeneralStyle.css" type="text/css" />
<title>Strigi Ontology Manager</title>
</head>
<body>
<%
String annotationName = request.getParameter("annot");
OntologyManager manager = new OntologyManager();
ArrayList<Triplet> relations = manager.getAnnotationProperties(annotationName);
Iterator<Triplet> iterator = relations.iterator();
final String THESIS_PREFIX = "http://localhost/ontologies/ThesisOntology.owl";
%>
	<div id="wrapper">
		<div id="header">
			<p> Este es el header</p>
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
			<form action="EditAnnotationServlet" method="post">
				<h2>Editing Annotation: <%=annotationName %></h2>
				<br />
				<p><b>Select an action:</b></p>
				<input type="radio" checked name="action" value="delete_annotation" />Delete current annotation <br />
				<input type="radio" name="action" value="delete_properties" />Delete selected properties <br />
				<input type="radio" name="action" value="add_properties" />Add properties <br />
				<input type="hidden" name="annotation" value="<%=annotationName%>" />
				<br />
				<p><b>Properties:</b></p>
				<%
				while (iterator.hasNext()) {
					Triplet currentTriplet = iterator.next();
					if (currentTriplet.getPredicate().startsWith(THESIS_PREFIX)) {
						String predicate = currentTriplet.getPredicate();
						String object = currentTriplet.getObject();
						String simplePredicate = currentTriplet.getSimplePredicate();
						String simpleObject = currentTriplet.getSimpleObject();
						
						String property = "<input type=\"checkbox\" name=\"" + predicate + "\" value=\"" + object + "\" />";
						property += simplePredicate + ": " + simpleObject + "<br />";
						
						out.println(property);
						
					}
				}
				%>
				<br />
				<input type="submit" value="Submit" />
			</form>
		</div>
	</div>
</body>
</html>