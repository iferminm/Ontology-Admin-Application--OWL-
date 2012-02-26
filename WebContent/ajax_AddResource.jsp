<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.TreeSet" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.admin.owlmanager.*" %>
<%@ page import="com.admin.domain.*" %>

<%

OntologyManager om = new OntologyManager();

/*
	Type is the target ontology class we're
	asking for their instances
*/
String type = request.getParameter("type");
/*
	Name is the name of the superior class instance
*/
String name = request.getParameter("name");
/*
	Relation we want to query
*/
String relation = request.getParameter("relation");
/*
	Name of the method to get data for the next select
*/
String next = request.getParameter("next");

String rdfPrefix = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
String thesisPrefix = "http://localhost/ontologies/ThesisOntology.owl#";

TreeSet<Statement> classes = om.getClasses();
Statement targetClass = null;
Iterator<Statement> classIterator = classes.iterator();

// We get the complete name for the target class we want instances from
while (classIterator.hasNext()) {
	targetClass = classIterator.next();
	if (targetClass.getCleanStatement().equalsIgnoreCase(name)) {
		break;
	}
}

// Building the query conditions
String conditions = "?s <" + rdfPrefix +  "type> <" + thesisPrefix + type + "> . " +
					"?s <" + thesisPrefix + relation + "> <" + name + ">";

					
// Getting the ResultSet as a TreeSet<Statement>
TreeSet<Statement> options = om.oneResultQuery("?s", conditions);


String select = "<select multiple name=\"" + type.toLowerCase() + "\" size=\"15\"";

if (!next.equalsIgnoreCase("none")) {
	select += "onchange=\"" + next + "(this.value)\"";
}

select += ">";

out.println(select);
// Filling the select with the query result set 
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
