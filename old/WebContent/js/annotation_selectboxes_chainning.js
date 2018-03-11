var xmlHttp;

function getCleanStatement (statement) {
	tokens = className.split("#");
	if (tokens.length == 2) {
		return tokens[1].toLowerCase();
	}
	return statement;
}

function showClassProperties(className) {
	xmlHttp = new XMLHttpRequest();

	if (xmlHttp == null) {
		alert("You must enable Javascript on your browser");
	}
	
	var today = new Date();
	id = Math.abs(Math.sin(today.getTime()));
	
	url = "ajax_AddAnnotation.jsp?class=" + escape(className);
	url = url + "&id=" + id;
	
//	cleanStatement = getCleanStatement(className);
	
	
	xmlHttp.onreadystatechange = result_class;
	
	xmlHttp.open("GET", url, true);
	xmlHttp.send(null);
}

function result_class() {
	if (xmlHttp.readyState == 4) {
		document.getElementById("result_class").innerHTML = xmlHttp.responseText;
	}
}