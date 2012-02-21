<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.TreeSet" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.admin.owlmanager.*" %>
<%@ page import="com.admin.domain.*" %>

<%

OntologyManager om = new OntologyManager();
String type = request.getParameter("type");
String name = request.getParameter("name");
String relation = request.getParameter("relation");
String next = request.getParameter("next");

String rdfPrefix = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
String thesisPrefix = "http://localhost/ontologies/ThesisOntology.owl#";

TreeSet<Statement> classes = om.getClasses();
Statement targetClass = null;
Iterator<Statement> classIterator = classes.iterator();

while (classIterator.hasNext()) {
	targetClass = classIterator.next();
	if (targetClass.getCleanStatement().equalsIgnoreCase(name)) {
		break;
	}
}

String conditions = "?s <" + rdfPrefix +  "type> <" + thesisPrefix + type + "> . " +
					"?s <" + thesisPrefix + relation + "> <" + name + ">";

TreeSet<Statement> options = om.oneResultQuery("?s", conditions);
					
out.println("<select multiple name=\"" + type.toLowerCase() + "\" size=\"15\" onchange=\"" + next + "(this.value)\">");
 
if (options.size() > 0) {
	Iterator<Statement> iter = options.iterator();
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (iter.hasNext()) {
		Statement current = iter.next();
		String value = current.getStatement();
		String cleanValue = current.getCleanStatement();
		out.println("<option value='" + value + "'>" + cleanValue +"</option>");
	}
} else {
	out.println("<option value=\"null\">The selected individual has no contents</option>");
}

out.println("</select>");

%>
