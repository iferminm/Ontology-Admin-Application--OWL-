var xmlHttp;

/**
 * Class instances getting callback function
 * 
 * @param className target class
 */
function getClassInstances(className) {
	xmlHttp = new XMLHttpRequest();
	if (xmlHttp == null) {
		alert("You must activate JavaScript on your Browser");
		return;
	}
	
	var url = "ajax_AddResource.jsp";
	url = url + "?className=" + className;
	
	xmlHttp.onreadystatechange = result_instances;
	xmlHttp.open("GET", url, true);
	xmlHttp.send(null);
}

/**
 * updated the document
 */
function result_instances() {
	if (xmlHttp.readyState == 4) {
		document.getElementById("instance").innerHTML = xmlHttp.responseText;
	}
}