<%@ page language="java" %>
<%@ page import="com.admin.owlmanager.OntologyManager" %>
<%@ page import="java.util.TreeSet" %>
<%@ page import="com.admin.domain.*" %>
<%@ page import="java.util.Iterator" %> 
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="Stylesheet" href="css/GeneralStyle.css" type="text/css" />

<script language="JavaScript" type="text/javascript" src="js/selectbox_chainning.js"></script>

<title>Strigi Ontology Manager</title>
</head>
<body>
<%
	OntologyManager om = new OntologyManager();
	TreeSet<Statement> classes = om.getClasses();
	Iterator<Statement> iter = null;
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
				<p>Add Resource</p>
			</div>
			<div id="menuitem">
				<p>View Resource</p>			
			</div>
			<div id="menuitem">
				<p>Add Annotation</p>			
			</div>
			<div id="menuitem">
				<p>View Annotations</p>			
			</div>
		</div>
		<div id="contentwrapper">
		<form method="POST" action="AddResourceServlet">
			<table width="80%">
				<tr>
					<td><b>Resource URI:</b></td>
					<td>
						<input class="text" type="text" name="uri" size="60" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<p align="center">Annotations</p>
					</td>
				</tr>
				<tr>
					<td>Tool</td>
					<td>
						<select multiple name="tool" size="15">
						</select>
					</td>
				</tr>
				<tr>
					<td>Entity</td>
					<td>
						<select multiple name="entity" size="15">
						</select>
					</td>
				</tr>
				<tr>
					<td>Discipline</td>
					<td>
						<select multiple name="discipline" size="15" onchange="getKnowledgeAreas(this.value)">
						<%
							// Filling the discipline select box 
							out.write("<option value=\"noselect\">No Selection</option>");
							iter = classes.iterator();
							Statement dis = null;
							while (iter.hasNext()) {
								dis = iter.next();
								if (dis.getCleanStatement().equalsIgnoreCase("discipline")) {
									break;
								}
							}
							TreeSet<Statement> disciplines = om.getClassInstances(dis.getStatement());
							iter = disciplines.iterator();
							while (iter.hasNext()) {
								Statement current = iter.next();
								out.println("<option value=\"" + current.getStatement() + "\">" + 
											current.getCleanStatement() + "</option>");
							
							}
						%>
						</select>
					</td>
				</tr>
				<tr>
					<td>Knowledge Area</td>
					<td id="result_knowledge_area">
						<select multiple name="knowledgearea" size="15" onchange="get_units(this.value)">
						</select>
					</td>
				</tr>
				<tr>
					<td>Unit</td>
					<td id="result_unit">
						<select multiple name="unit" size="15" onchange="get_topics(this.value)">
						</select>
					</td>
				</tr>
				<tr>
					<td>Topic</td>
					<td id="result_topic">
						<select multiple name="topic" size="15" onchange="get_concepts(this.value)">
						</select>
					</td>
				</tr>
				<tr>
					<td>Concept</td>
					<td id="result_concept">
						<select multiple name="concept" size="15">
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" name="submit" value="Submit" /></td>
				</tr>
			</table>
		</form>
		</div>
	</div>
</body>
</html>