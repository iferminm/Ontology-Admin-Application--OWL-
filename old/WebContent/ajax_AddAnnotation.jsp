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
MyStatement statement = new MyStatement(className);

out.println("<table width=\"80%\">");

/**************************************************************************************************************

                                Load disciplines for new knowledge area

***************************************************************************************************************/
if (statement.getCleanStatement().equalsIgnoreCase("KnowledgeArea")) {
	TreeSet<MyStatement> disciplines = manager.getClassInstances(thesisPrefix + "Discipline");
	Iterator<MyStatement> disciplinesIterator = disciplines.iterator();
	out.println("<tr>");
	out.println("<td>in-discipline</td>");
	out.println("<td>");
	out.println("<select name=\"in-discipline\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (disciplinesIterator.hasNext()) {
		MyStatement current = disciplinesIterator.next();
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
	TreeSet<MyStatement> knowledgeAreas = manager.getClassInstances(thesisPrefix + "KnowledgeArea");
	Iterator<MyStatement> knowledgeAreasIterator = knowledgeAreas.iterator();
	out.println("<tr>");
	out.println("<td>in-knowledge-area</td>");
	out.println("<td>");
	out.println("<select name=\"in-nowledge-area\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (knowledgeAreasIterator.hasNext()) {
		MyStatement current = knowledgeAreasIterator.next();
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
	TreeSet<MyStatement> units = manager.getClassInstances(thesisPrefix + "Unit");
	Iterator<MyStatement> unitsIterator = units.iterator();
	out.println("<tr>");
	out.println("<td>in-unit</td>");
	out.println("<td>");
	out.println("<select name=\"in-unit\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (unitsIterator.hasNext()) {
		MyStatement current = unitsIterator.next();
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
	TreeSet<MyStatement> topics = manager.getClassInstances(thesisPrefix + "Topic");
	TreeSet<MyStatement> concepts = manager.getClassInstances(thesisPrefix + "Concept");
	Iterator<MyStatement> topicsIterator = topics.iterator();
	out.println("<tr>");
	out.println("<td>in-topic</td>");
	out.println("<td>");
	out.println("<select name=\"in-topic\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (topicsIterator.hasNext()) {
		MyStatement current = topicsIterator.next();
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
	Iterator<MyStatement> conceptsIterator = concepts.iterator();
	out.println("<tr>");
	out.println("<td><select name=\"conceptrelation\">");
	out.println("<option value=\"noselect\">Select a relation type</option>");
	out.println("<option value=\"related-to\">related-to</option>");
	out.println("<option value=\"preceded-by\">preceded-by</option>");
	out.println("</td>");
	out.println("<td>");
	out.println("<select multiple name=\"related\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (conceptsIterator.hasNext()) {
		MyStatement current = conceptsIterator.next();
		String value = current.getStatement();
		String cleanValue = current.getCleanStatement();
		out.println("<option value='" + value + "'>" + cleanValue +"</option>");
	}
	
	out.println("</select>");
	out.println("</td>");
	out.println("</tr>");
	
/**************************************************************************************************************

    							Loading Entities to select who developed the tool

***************************************************************************************************************/
} else if (statement.getCleanStatement().equalsIgnoreCase("tool")) {
	TreeSet<MyStatement> entities = manager.getClassInstances(thesisPrefix + "Entity");
	TreeSet<MyStatement> concepts = manager.getClassInstances(thesisPrefix + "Concept");
	Iterator<MyStatement> entitiesIterator = entities.iterator();
	out.println("<tr>");
	out.println("<td>developed-by</td>");
	out.println("<td>");
	out.println("<select name=\"developed-by\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (entitiesIterator.hasNext()) {
		MyStatement current = entitiesIterator.next();
		String value = current.getStatement();
		String cleanValue = current.getCleanStatement();
		out.println("<option value='" + value + "'>" + cleanValue +"</option>");
	}
	
	out.println("</select>");
	out.println("</td>");
	out.println("</tr>");
	
	
/**************************************************************************************************************

				Load concepts for the current tool to implement

***************************************************************************************************************/
	out.println("<tr>");
	out.println("<td>implements</td>");
	out.println("<td>");
	out.println("<select name=\"implements\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	Iterator<MyStatement> conceptsIterator = concepts.iterator();
	while (conceptsIterator.hasNext()) {
		MyStatement current = conceptsIterator.next();
		String value = current.getStatement();
		String cleanValue = current.getCleanStatement();
		out.println("<option value='" + value + "'>" + cleanValue +"</option>");
	}
	
	out.println("</select>");
	out.println("</td>");
	out.println("</tr>");
	
/**************************************************************************************************************

		Load Tools for the Enterprise to develop

***************************************************************************************************************/
} else if (statement.getCleanStatement().equalsIgnoreCase("enterprise")) {
	TreeSet<MyStatement> tools = manager.getClassInstances(thesisPrefix + "Tool");
	TreeSet<MyStatement> concepts = manager.getClassInstances(thesisPrefix + "Concept");
	Iterator<MyStatement> toolsIterator = tools.iterator();
	out.println("<tr>");
	out.println("<td>develops</td>");
	out.println("<td>");
	out.println("<select name=\"develops\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (toolsIterator.hasNext()) {
		MyStatement current = toolsIterator.next();
		String value = current.getStatement();
		String cleanValue = current.getCleanStatement();
		out.println("<option value='" + value + "'>" + cleanValue +"</option>");
	}
	
	out.println("</select>");
	out.println("</td>");
	out.println("</tr>");
/**************************************************************************************************************

			Load concepts for the enterprise to state

***************************************************************************************************************/
	Iterator<MyStatement> conceptsIterator = concepts.iterator();
	out.println("<tr>");
	out.println("<td>states</td>");
	out.println("<td>");
	out.println("<select name=\"states\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (conceptsIterator.hasNext()) {
		MyStatement current = conceptsIterator.next();
		String value = current.getStatement();
		String cleanValue = current.getCleanStatement();
		out.println("<option value='" + value + "'>" + cleanValue +"</option>");
	}
	
	out.println("</select>");
	out.println("</td>");
	out.println("</tr>");

/**************************************************************************************************************

				Load tools for the person to develop

***************************************************************************************************************/
} else if (statement.getCleanStatement().equalsIgnoreCase("person")) {
	TreeSet<MyStatement> tools = manager.getClassInstances(thesisPrefix + "Tool");
	TreeSet<MyStatement> concepts = manager.getClassInstances(thesisPrefix + "Concept");
	TreeSet<MyStatement> knowledgeAreas = manager.getClassInstances(thesisPrefix + "KnowledgeArea");
	Iterator<MyStatement> toolsIterator = tools.iterator();
	out.println("<tr>");
	out.println("<td>develops</td>");
	out.println("<td>");
	out.println("<select name=\"develops\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (toolsIterator.hasNext()) {
		MyStatement current = toolsIterator.next();
		String value = current.getStatement();
		String cleanValue = current.getCleanStatement();
		out.println("<option value='" + value + "'>" + cleanValue +"</option>");
	}
	
	out.println("</select>");
	out.println("</td>");
	out.println("</tr>");
/**************************************************************************************************************

			Load concepts for the person to state

***************************************************************************************************************/
	Iterator<MyStatement> conceptsIterator = concepts.iterator();
	out.println("<tr>");
	out.println("<td>states</td>");
	out.println("<td>");
	out.println("<select name=\"states\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (conceptsIterator.hasNext()) {
		MyStatement current = conceptsIterator.next();
		String value = current.getStatement();
		String cleanValue = current.getCleanStatement();
		out.println("<option value='" + value + "'>" + cleanValue +"</option>");
	}
	
	out.println("</select>");
	out.println("</td>");
	out.println("</tr>");

/**************************************************************************************************************

		Load knowledge areas for the person to be reference in

***************************************************************************************************************/
Iterator<MyStatement> knowledgeAreasIterator = knowledgeAreas.iterator();
out.println("<tr>");
out.println("<td>is-reference-in</td>");
out.println("<td>");
out.println("<select name=\"is-reference-in\" size=\"15\">");
out.println("<option selected value=\"noselect\">No Selection</option>");
out.println("<option value=\"invalid\">-------------------</option>");
while (knowledgeAreasIterator.hasNext()) {
MyStatement current = knowledgeAreasIterator.next();
String value = current.getStatement();
String cleanValue = current.getCleanStatement();
out.println("<option value='" + value + "'>" + cleanValue +"</option>");
}

out.println("</select>");
out.println("</td>");
out.println("</tr>");

	
/**************************************************************************************************************

				Load concepts for the team to state

***************************************************************************************************************/
} else if (statement.getCleanStatement().equalsIgnoreCase("team")) {
	TreeSet<MyStatement> tools = manager.getClassInstances(thesisPrefix + "Tool");
	TreeSet<MyStatement> concepts = manager.getClassInstances(thesisPrefix + "Concept");
	Iterator<MyStatement> toolsIterator = tools.iterator();
	out.println("<tr>");
	out.println("<td>develops</td>");
	out.println("<td>");
	out.println("<select name=\"develops\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (toolsIterator.hasNext()) {
		MyStatement current = toolsIterator.next();
		String value = current.getStatement();
		String cleanValue = current.getCleanStatement();
		out.println("<option value='" + value + "'>" + cleanValue +"</option>");
	}
	
	out.println("</select>");
	out.println("</td>");
	out.println("</tr>");
	
/**************************************************************************************************************

			Load concepts for the team to state

***************************************************************************************************************/
	Iterator<MyStatement> conceptsIterator = concepts.iterator();
	out.println("<tr>");
	out.println("<td>states</td>");
	out.println("<td>");
	out.println("<select name=\"states\" size=\"15\">");
	out.println("<option selected value=\"noselect\">No Selection</option>");
	out.println("<option value=\"invalid\">-------------------</option>");
	while (conceptsIterator.hasNext()) {
		MyStatement current = conceptsIterator.next();
		String value = current.getStatement();
		String cleanValue = current.getCleanStatement();
		out.println("<option value='" + value + "'>" + cleanValue +"</option>");
	}
	
	out.println("</select>");
	out.println("</td>");
	out.println("</tr>");

}

out.println("</table>");
%>