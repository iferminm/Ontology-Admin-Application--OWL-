<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Stylesheet" href="css/GeneralStyle.css" type="text/css" />
<title>Striggi Ontology Manager</title>
</head>
<body>
<%
	Map<String, String[]> parameterMap = request.getParameterMap();
	String knowledgeHref = "AddResource.jsp?add=knowledge";
	String toolHref = "AddResource.jsp?add=tool";
	String enterpriseHref = "AddResource.jsp?add=enterprise";
	String personHref = "AddResource.jsp?add=person";
	String teamHref = "AddResource.jsp?add=team";
	if (!parameterMap.isEmpty()) {
		String resource = request.getParameter("resource");
		knowledgeHref += "&resource=" + resource;
		toolHref += "&resource=" + resource;
		enterpriseHref += "&resource=" + resource;
		personHref += "&resource=" + resource;
		teamHref += "&resource=" + resource;
	} else {
		knowledgeHref += "&resource=new";
		toolHref += "&resource=new";
		enterpriseHref += "&resource=new";
		personHref += "&resource=new";
		teamHref += "&resource=new";
	}
%>
	<div id="wrapper">
		<div id="header">

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
				<p><a href="AddAnnotation.jsp">View Annotations</a></p>			
			</div>
		</div>
		<div id="contentwrapper">
			<h2>Please, select what kind of resource you want to add:</h2>
			<p><a href="<%=knowledgeHref%>">Knowledge classes</a></p>
			<p><a href="<%=toolHref%>">Tool class</a></p>
			<p><a href="<%=enterpriseHref%>">Enterprise class</a></p>
			<p><a href="<%=personHref%>">Person class</a></p>
			<p><a href="<%=teamHref%>">Team class</a></p>
		</div>
	</div>

</body>
</html>