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

<script language="JavaScript" type="text/javascript" src="js/resource_selectboxes_chainning.js"></script>

<title>Striggi Ontology Manager</title>
</head>
<body>
<%
	OntologyManager om = new OntologyManager();
	TreeSet<MyStatement> classes = om.getClasses();
	Iterator<MyStatement> iter = null;
	String resource = request.getParameter("resource");
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
		<form method="POST" action="AddResourceServlet">
			<table width="80%">
				<tr>
					<td><b>Resource URI:</b></td>
					<td>
					<%
						if (resource.equals("new")) { // if creating a resource
					%>
						<input class="text" type="text" name="uri" size="60" />
					<%
						} else { // if editing a resource
					%>
						<input class="text" type="text" name="uri" size="60" value="<%=resource%>" readonly="readonly" />
					<%
						}
					%>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<p align="center">Annotations</p>
					</td>
				</tr>
				<%
					String contentType = request.getParameter("add");
							if (contentType.equalsIgnoreCase("knowledge")) {
				%>
				<tr>
					<td>Discipline</td>
					<td>
						<select multiple name="discipline" size="15" onchange="getKnowledgeAreas(this.value)">
						<%
							// Filling the discipline select box 
											out.write("<option value=\"noselect\">No Selection</option>");
											out.write("<option value=\"invalid\">------------</option>");
											iter = classes.iterator();
											MyStatement dis = null;
											while (iter.hasNext()) {
												dis = iter.next();
												if (dis.getCleanStatement().equalsIgnoreCase("discipline")) {
													break;
												}
											}
											TreeSet<MyStatement> disciplines = om.getClassInstances(dis.getStatement());
											iter = disciplines.iterator();
											while (iter.hasNext()) {
												MyStatement current = iter.next();
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
				<%
					} else {    // End adding knowledge and begin adding other
				%>
					<%
						if (contentType.equalsIgnoreCase("tool")) { // Begin if adding a tool
					%>
					<tr>
						<td>Tool</td>
						<td>
							<select multiple name="tool" size="15">
							<%
								// Filling the tool select box 
												out.write("<option value=\"noselect\">No Selection</option>");
												out.write("<option value=\"invalid\">------------</option>");
												iter = classes.iterator();
												MyStatement tool = null;
												while (iter.hasNext()) {
													tool = iter.next();
													if (tool.getCleanStatement().equalsIgnoreCase("tool")) {
														break;
													}
												}
												TreeSet<MyStatement> allTools = om.getClassInstances(tool.getStatement());
												iter = allTools.iterator();
												while (iter.hasNext()) {
													MyStatement current = iter.next();
													out.println("<option value=\"" + current.getStatement() + "\">" + 
																current.getCleanStatement() + "</option>");
												
												}
							%>
							</select>
						</td>
					</tr>
					<%
						} else if(contentType.equalsIgnoreCase("enterprise")) { // End of adding a tool and begin enterprise
					%>
					<tr>
						<td>Enterprise</td>
						<td>
							<select multiple name="enterprise" size="15">
							<%
								// Filling the enterprise select box 
												out.write("<option value=\"noselect\">No Selection</option>");
												out.write("<option value=\"invalid\">------------</option>");
												iter = classes.iterator();
												MyStatement enterprise = null;
												while (iter.hasNext()) {
													enterprise = iter.next();
													if (enterprise.getCleanStatement().equalsIgnoreCase("enterprise")) {
														break;
													}
												}
												TreeSet<MyStatement> allEnterprises = om.getClassInstances(enterprise.getStatement());
												iter = allEnterprises.iterator();
												while (iter.hasNext()) {
													MyStatement current = iter.next();
													out.println("<option value=\"" + current.getStatement() + "\">" + 
																current.getCleanStatement() + "</option>");
												
												}
							%>
							</select>
						</td>
					</tr>
					<%
						} else if (contentType.equalsIgnoreCase("person")) { // End of adding an enterprise and begin person
					%>
					<tr>
						<td>Person</td>
						<td>
							<select multiple name="person" size="15">
							<%
								// Filling the person select box 
												out.write("<option value=\"noselect\">No Selection</option>");
												out.write("<option value=\"invalid\">------------</option>");
												iter = classes.iterator();
												MyStatement person = null;
												while (iter.hasNext()) {
													person = iter.next();
													if (person.getCleanStatement().equalsIgnoreCase("person")) {
														break;
													}
												}
												TreeSet<MyStatement> allPersons = om.getClassInstances(person.getStatement());
												iter = allPersons.iterator();
												while (iter.hasNext()) {
													MyStatement current = iter.next();
													out.println("<option value=\"" + current.getStatement() + "\">" + 
																current.getCleanStatement() + "</option>");
												
												}
							%>
							</select>
						</td>
					</tr>
					<%
						} else if (contentType.equalsIgnoreCase("team")) { // End of person an enterprise and begin team
					%>
					<tr>
						<td>Team</td>
						<td>
							<select multiple name="team" size="15">
							<%
								// Filling the person select box 
												out.write("<option value=\"noselect\">No Selection</option>");
												out.write("<option value=\"invalid\">------------</option>");
												iter = classes.iterator();
												MyStatement team = null;
												while (iter.hasNext()) {
													team = iter.next();
													if (team.getCleanStatement().equalsIgnoreCase("team")) {
														break;
													}
												}
												TreeSet<MyStatement> allTeams = om.getClassInstances(team.getStatement());
												iter = allTeams.iterator();
												while (iter.hasNext()) {
													MyStatement current = iter.next();
													out.println("<option value=\"" + current.getStatement() + "\">" + 
																current.getCleanStatement() + "</option>");
												
												}
							%>
							</select>
						</td>
					</tr>
					<% 
							} // end adding team 
						} // end adding other
					%>
				<tr>
					<td colspan="2"><input type="submit" name="submit" value="Submit" /></td>
				</tr>
			</table>
		</form>
		</div>
	</div>
</body>
</html>