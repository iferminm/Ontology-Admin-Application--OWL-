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
<script language="JavaScript" type="text/javascript" src="js/class_instance_chainning.js"></script>
<title>Strigi Ontology Manager</title>
</head>
<body>
<%
OntologyManager manager = new OntologyManager();
TreeSet<Statement> classes = manager.getClasses();

Iterator<Statement> classesIterator = classes.iterator();

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
			<table width="80%">
				<tr>
					<td>Class Name:</td>
					<td>
						<select name="class" onchange="getClassInstances(this.value)">
							<option value="noselect">Select a class</option>
							<option value="invalid">--------------</option>
							<%
							while (classesIterator.hasNext()) {
								Statement currentClass = classesIterator.next();
								if (!currentClass.getCleanStatement().equalsIgnoreCase("resource")) {
									String option = "<option value=\"" + currentClass.getStatement() + "\">";
									option += currentClass.getCleanStatement() + "</option>";
									out.println(option);
								}
							}
							%>
						</select>
					</td>
				</tr>
			</table>
			<div id="result_annotations">
			
			</div>
		</div>
	</div>
</body>
</html>