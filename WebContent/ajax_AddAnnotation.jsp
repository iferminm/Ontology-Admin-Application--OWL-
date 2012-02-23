<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.admin.owlmanager.OntologyManager" %>
<%@ page import="com.admin.domain.*" %>
<%@ page import="java.util.TreeSet" %>
<%@ page import="java.util.Iterator" %>

<%

final String thesisPrefix = "http://localhost/ontologies/ThesisOntology.owl#";

OntologyManager manager = new OntologyManager();

String className = request.getParameter("class");
Statement statement = new Statement(className);

out.println("<table width=\"80%\">");

/**************************************************************************************************************

                                Load disciplines for new knowledge area

***************************************************************************************************************/
if (statement.getCleanStatement().equalsIgnoreCase("KnowledgeArea")) {
	TreeSet<Statement> disciplines = manager.getClassInstances(thesisPrefix + "Discipline");
	Iterator<Statement> disciplinesIterator = disciplines.iterator();
	out.println("<tr>");
	out.println("<td>in-discipline</td>");
	out.println("<td>");
	out.println("<select name=\"discipline\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (disciplinesIterator.hasNext()) {
		Statement current = disciplinesIterator.next();
		String value = current.getStatement();
		String cleanValue = current.getCleanStatement();
		out.println("<option value='" + value + "'>" + cleanValue +"</option>");
	}
	
	out.println("</select>");
	out.println("</td>");
	out.println("</tr>");
	
/**************************************************************************************************************

                                   Load Knowledge Areas for new Unit

***************************************************************************************************************/
} else if (statement.getCleanStatement().equalsIgnoreCase("unit")) {
	TreeSet<Statement> knowledgeAreas = manager.getClassInstances(thesisPrefix + "KnowledgeArea");
	Iterator<Statement> knowledgeAreasIterator = knowledgeAreas.iterator();
	out.println("<tr>");
	out.println("<td>in-knowledge-area</td>");
	out.println("<td>");
	out.println("<select name=\"knowledgearea\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (knowledgeAreasIterator.hasNext()) {
		Statement current = knowledgeAreasIterator.next();
		String value = current.getStatement();
		String cleanValue = current.getCleanStatement();
		out.println("<option value='" + value + "'>" + cleanValue +"</option>");
	}
	
	out.println("</select>");
	out.println("</td>");
	out.println("</tr>");
	
/**************************************************************************************************************

    								 Load Units for new Topic

***************************************************************************************************************/
} else if (statement.getCleanStatement().equalsIgnoreCase("topic")) {
	TreeSet<Statement> units = manager.getClassInstances(thesisPrefix + "Unit");
	Iterator<Statement> unitsIterator = units.iterator();
	out.println("<tr>");
	out.println("<td>in-unit</td>");
	out.println("<td>");
	out.println("<select name=\"unit\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (unitsIterator.hasNext()) {
		Statement current = unitsIterator.next();
		String value = current.getStatement();
		String cleanValue = current.getCleanStatement();
		out.println("<option value='" + value + "'>" + cleanValue +"</option>");
	}
	
	out.println("</select>");
	out.println("</td>");
	out.println("</tr>");
	
/**************************************************************************************************************

									 Load topics for new concepts

***************************************************************************************************************/
} else if (statement.getCleanStatement().equalsIgnoreCase("concept")) {
	TreeSet<Statement> topics = manager.getClassInstances(thesisPrefix + "Topic");
	TreeSet<Statement> concepts = manager.getClassInstances(thesisPrefix + "Concept");
	Iterator<Statement> topicsIterator = topics.iterator();
	out.println("<tr>");
	out.println("<td>in-topic</td>");
	out.println("<td>");
	out.println("<select name=\"topic\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (topicsIterator.hasNext()) {
		Statement current = topicsIterator.next();
		String value = current.getStatement();
		String cleanValue = current.getCleanStatement();
		out.println("<option value='" + value + "'>" + cleanValue +"</option>");
	}
	
	out.println("</select>");
	out.println("</td>");
	out.println("</tr>");

/**************************************************************************************************************

							Load Concepts for concept relations

***************************************************************************************************************/
	Iterator<Statement> conceptsIterator = concepts.iterator();
	out.println("<tr>");
	out.println("<td><select name=\"conceptrelation\">");
	out.println("<option value=\"related-to\">related-to</option>");
	out.println("<option value=\"preceded-by\">preceded-by</option>");
	out.println("</td>");
	out.println("<td>");
	out.println("<select multiple name=\"related\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (conceptsIterator.hasNext()) {
		Statement current = conceptsIterator.next();
		String value = current.getStatement();
		String cleanValue = current.getCleanStatement();
		out.println("<option value='" + value + "'>" + cleanValue +"</option>");
	}
	
	out.println("</select>");
	out.println("</td>");
	out.println("</tr>");
} else if (statement.getCleanStatement().equalsIgnoreCase("tool")) {
	out.println("Herramienta");
} else if (statement.getCleanStatement().equalsIgnoreCase("enterprise")) {
	out.println("Empresa");
} else if (statement.getCleanStatement().equalsIgnoreCase("person")) {
	out.println("Persona");
} else if (statement.getCleanStatement().equalsIgnoreCase("team")) {
	out.println("Team");
}

out.println("</table>");


%>