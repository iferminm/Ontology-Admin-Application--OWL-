var xmlHttp;

function showClassProperties(className) {
	xmlHttp = new XMLHttpRequest();
	
	if (xmlHttp == null) {
		alert("You must enable Javascript on your browser");
	}
	
	var today = new Date();
	id = Math.abs(Math.sin(today.getTime()));
	
	url = "ajax_AddAnnotation.jsp?class=" + className;
	url = url + "&id=" + id;
	
	xmlHttp.onreadystatechange = result_class;
	
	xmlHttp.open("GET", url, true);
	xmlHttp.send(null);
}

function result_class() {
	if (xmlHttp.readyState == 4) {
		document.getElementById("colocar la id...").innerHTML = xmlHttp.responseText;
	}
}