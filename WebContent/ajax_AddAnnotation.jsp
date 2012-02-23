<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.admin.owlmanager.OntologyManager" %>
<%@ page import="com.admin.domain.*" %>
<%@ page import="java.util.TreeSet" %>
<%@ page import="java.util.Iterator" %>

<%

OntologyManager manager = new OntologyManager();

String className = request.getParameter("class");
Statement statement = new Statement(className);

if (statement.getCleanStatement().equalsIgnoreCase("Discipline")) {
	out.println("Disciplina");
} else if (statement.getCleanStatement().equalsIgnoreCase("KnowledgeArea")) {
	out.println("Area de conocimiento");
} else if (statement.getCleanStatement().equalsIgnoreCase("unit")) {
	out.println("Unidad");
} else if (statement.getCleanStatement().equalsIgnoreCase("topic")) {
	out.println("Topico");
} else if (statement.getCleanStatement().equalsIgnoreCase("concept")) {
	out.println("Concepto");
} else if (statement.getCleanStatement().equalsIgnoreCase("tool")) {
	out.println("Herramienta");
} else if (statement.getCleanStatement().equalsIgnoreCase("enterprise")) {
	out.println("Empresa");
} else if (statement.getCleanStatement().equalsIgnoreCase("person")) {
	out.println("Persona");
} else if (statement.getCleanStatement().equalsIgnoreCase("team")) {
	out.println("Team");
}

%>