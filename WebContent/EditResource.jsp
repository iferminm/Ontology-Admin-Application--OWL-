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
	String resource = request.getParameter("id");
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
		<%
				String conditions = "<" + resource + "> <http://localhost/ontologies/ThesisOntology.owl#has-annotation> ?o";
				OntologyManager om = new OntologyManager();
				
				TreeSet<Statement> annotations = om.oneResultQuery("*", conditions);
				Iterator<Statement> iter = annotations.iterator();
				
		%>
			<form action="EditResourceServlet" method="POST">
				<h2>Editing Resource: <i><a href="<%=resource%>" target="_blank"><%=resource%></a></i></h2>
				<br />
				<p><b>Select an action:</b></p>
				<input type="radio" name="action" value="delete_resource" />Delete current resource <br />
				<input type="radio" name="action" value="delete_annotations" />Delete selected annotations <br />
				<input type="radio" name="action" value="add_annotations" />Add annotations <br />
				<input type="hidden" name="resource" value="<%=resource%>" />
				<br />
				<p><b>Annotations:</b></p>
				<%
					while (iter.hasNext()) {
						Statement current = iter.next();
						out.println("<input type=\"checkbox\" name=\"" + current.getStatement() + "\">" + current.getCleanStatement());
						out.println("<br />");
					}
				%>
				<input type="submit" value="Submit" />
			</form>
		</div>
	</div>

</body>
</html>