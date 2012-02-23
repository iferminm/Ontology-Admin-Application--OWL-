var xmlHttp;

function cleanString(text) {
	tokens = text.split("#");
	alert(tokens.length);
	if (tokens.length == 2) {
	//	alert(tokens[0]);
	//	alert(tokens[1]);
		return tokens[1].toLowerCase();
	}
	
	return text.toLowerCase();
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
	
	xmlHttp.onreadystatechange = result_class;
	
	xmlHttp.open("GET", url, true);
	xmlHttp.send(null);
}

function result_class() {
	if (xmlHttp.readyState == 4) {
		document.getElementById("result_tool").innerHTML = xmlHttp.responseText;
	}
}