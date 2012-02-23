<%@ page language="java" contentType="text/html; charset=UTF-8"
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
				<p><a href="PreAddResource.jsp">Add Resource</a></p>			
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
			<h2>Please, select what kind of resource you want to add:</h2>
			<p><a href="AddResource.jsp?add=knowledge">Knowledge classes</a></p>
			<p><a href="AddResource.jsp?add=other">Entity or tool classes</a></p>
		</div>
	</div>

</body>
</html>