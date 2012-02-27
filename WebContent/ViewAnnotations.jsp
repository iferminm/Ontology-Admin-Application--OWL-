<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.admin.owlmanager.OntologyManager" %>
<%@ page import="com.admin.domain.*" %>
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
<%
OntologyManager manager = new OntologyManager();
TreeSet<Statement> classes = manager.getClasses();

Iterator<Statement> iter = classes.iterator();

%>
	<div id="wrapper">
		<div id="header">
			<p> Este es el header</p>
		</div>
		<div id="menu">
			<div id="menuitem">
				<p>View Ontology</p>
			</div>
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
			<h2>Available Annotations</h2>
			<br />
			<%
			while (iter.hasNext()) {
				Statement currentClass = iter.next();
				if (currentClass.getCleanStatement().equalsIgnoreCase("resource")) {
					System.out.println("Es Recurso, no hago nada");
					continue;
				} else {
					String printClass = "<p><b>On class: " + currentClass.getCleanStatement() + ":</b></p>";
					out.println(printClass);
					TreeSet<Statement> classAnnotations = manager.getClassInstances(currentClass.getStatement());
					Iterator<Statement> iterator = classAnnotations.iterator();
					out.println("<ul>");
					while (iterator.hasNext()) {
						Statement currentInstance = iterator.next();
						String printInstance = "<li><a href=\"EditAnnotation.jsp?annot=" + currentInstance.getStatement() + "\">";
						printInstance += currentInstance.getCleanStatement() + "</a></li>";
						out.println(printInstance);
					}
					out.println("</ul>");
				}
			}
			%>
			
		</div>
	</div>
</body>
</html>