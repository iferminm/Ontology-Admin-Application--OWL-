var xmlHttp;

/**
 * This function gets the knowledge areas
 * for a given Discipline
 * @param disciplineName
 */
function getKnowledgeAreas(disciplineName) {
	xmlHttp = new XMLHttpRequest();
	
	if (xmlHttp == null) {
		alert("You must enable javascritp on your browser");
	}
	
	today = new Date();
	id = Math.abs(Math.sin(today.getTime()));
	
	var url = "ajax_AddResource.jsp?type=KnowledgeArea";
	url = url + "&name=" + escape(disciplineName);
	url = url + "&relation=" + escape("in-discipline");
	url = url + "&next=" + escape("get_units");
	url = url + "&id=" + id;
	
	xmlHttp.onreadystatechange = result_knowledge_areas;
	
	xmlHttp.open("GET", url, true);
	xmlHttp.send(null);
}

function result_knowledge_areas() {
	if (xmlHttp.readyState == 4) {
		document.getElementById("result_knowledge_area").innerHTML = xmlHttp.responseText;
	}
}

/**
 * This function gets the units for a given
 * Knowledge Area
 * @param knowledgeAreaName
 */
function get_units(knowledgeAreaName) {

	xmlHttp = new XMLHttpRequest();
	
	if (xmlHttp == null) {
		alert("You Must enable javascript");
	}
	
	today = new Date();
	id = Math.abs(Math.sin(today.getTime));
	
	var url = "ajax_AddResource.jsp?type=Unit";
	url = url + "&name=" + escape(knowledgeAreaName);
	url = url + "&relation=" + escape("in-knowledge-area");
	url = url + "&next=" + escape("get_topics");
	url = url + "&id=" + id;
	
	xmlHttp.onreadystatechange = result_unit;
	
	xmlHttp.open("GET", url, true);
	xmlHttp.send(null);
}

function result_unit() {
	if (xmlHttp.readyState == 4) {
		document.getElementById("result_unit").innerHTML = xmlHttp.responseText;
	}
}


/**
 * This function gets the topics for a given
 * Unit
 * @param unitName
 */
function get_topics(unitName) {
	xmlHttp = new XMLHttpRequest();
	
	if (xmlHttp == null) {
		alert("You Must enable javascript");
	}
	
	today = new Date();
	id = Math.abs(Math.sin(today.getTime));
	
	var url = "ajax_AddResource.jsp?type=Topic";
	url = url + "&name=" + escape(unitName);
	url = url + "&relation=" + escape("in-unit");
	url = url + "&next=" + escape("get_concepts");
	url = url + "&id=" + id;
	
	xmlHttp.onreadystatechange = result_topic;
	
	xmlHttp.open("GET", url, true);
	xmlHttp.send(null);
}

function result_topic() {
	if (xmlHttp.readyState == 4) {
		document.getElementById("result_topic").innerHTML = xmlHttp.responseText;
	}
}	

/**
 * This function gets the concepts
 * for a given topic
 * @param topicName
 */
function get_concepts(topicName) {
	xmlHttp = new XMLHttpRequest();
	
	if (xmlHttp == null) {
		alert("You Must enable javascript");
	}
	
	today = new Date();
	id = Math.abs(Math.sin(today.getTime));
	
	var url = "ajax_AddResource.jsp?type=Concept";
	url = url + "&name=" + escape(topicName);
	url = url + "&relation=" + escape("in-topic");
	url = url + "&next=" + escape("none");
	url = url + "&id=" + id;
	
	xmlHttp.onreadystatechange = result_concept;
	
	xmlHttp.open("GET", url, true);
	xmlHttp.send(null);
}

function result_concept() {
	if (xmlHttp.readyState == 4) {
		document.getElementById("result_concept").innerHTML = xmlHttp.responseText;
	}
}