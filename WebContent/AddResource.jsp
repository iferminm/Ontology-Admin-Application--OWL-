<%@ page language="java" import="com.admin.owlmanager.OntologyManager,
								java.util.TreeSet,
								com.admin.domain.*,
								java.util.Iterator" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<form>
			<table width="80%">
				<tr>
					<td><b>Resource URI:</b></td>
					<td>
						<input class="text" type="text" name="uri" size="60" />
					</td>
				</tr>
				<tr>
					<td>Class:</td>
					<td>
						<select name="classes">
							<option value="noselect">Select a class:</option>
							<option value="invalid">---------------</option>
							<%
								OntologyManager om = new OntologyManager();
								TreeSet<Statement> classes = om.getClasses();
								Iterator<Statement> iter = classes.iterator();

								while (iter.hasNext()) {
									Statement current = iter.next();
									out.println("<option value=\"" + 
												current.toString() + "\">" +
												current.getCleanStatement() + "</option>");
								}
							%>
						</select>
					</td>
				</tr>
				<tr>
					<td>Instances:</td>
					<td>
						<select name="instances">
							<option value="noselect">Select an instance</option>
							<option value="invalid">------------------</option>
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