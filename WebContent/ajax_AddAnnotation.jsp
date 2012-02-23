<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.admin.owlmanager.OntologyManager" %>
<%@ page import="com.admin.domain.*" %>
<%@ page import="java.util.TreeSet" %>
<%@ page import="java.util.Iterator" %>

<%

OntologyManager manager = new OntologyManager();

String className = request.getParameter("class");

out.println("<p>" + className + "</p>");

out.println(className);


%>