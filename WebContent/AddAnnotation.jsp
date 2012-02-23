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
<script language="JavaScript" type="text/javascript" src="js/annotation_selectboxes_chainning.js"></script>
<title>Strigi Ontology Manager</title>
</head>
<body>
	<%
		OntologyManager om = new OntologyManager();
		TreeSet<Statement> classes = om.getClasses();
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
			<h2>Add Annotation</h2>
			<br />
			<form action="AddAnnotationServlet" method="POST">
				<table width="80%">
					<tr>
						<td>Annotation name</td>
						<td>
							<input class="text" type="text" size="20" name="annotation" />
						</td>
					</tr>
					<tr>
						<td>Annotation class</td>
						<td>
							<select name="class" onchange="showClassProperties(this.value)">
								<option value="noselect">Select a class</option>
								<option value="invalid">--------------</option>
								<%
									while (iter.hasNext()) {
										Statement current = iter.next();
										if ( (!current.getCleanStatement().equalsIgnoreCase("Resource")) && 
											(!current.getCleanStatement().equalsIgnoreCase("Entity")) ) {
										
											out.println("<option value=\"" + current.getStatement() + "\">" + 
														current.getCleanStatement() + "</option>");
										}
									}
								%>
							</select>
						</td>
					</tr>
				</table>
				<div id="result_class">
				</div>
			</form>
		</div>
	</div>
</body>
</html>