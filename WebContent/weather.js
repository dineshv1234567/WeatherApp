//port number on which tomcat server is running
portNumber = 8082;
// openWeathermapApi details
apiSite = "http://api.openweathermap.org/data/2.5/weather?q=";
key = "&unit=metrics&appid=c949e999511b1499fcda272cc09b2fdf";

function getData() {
	document.getElementById("name").innerHTML = "";
	document.getElementById("id").innerHTML = "";
	document.getElementById("show").innerHTML = "";
	// making ajax call
	var xmlHttp = new XMLHttpRequest();
	var word = document.getElementById("city").value;
	// call to opemweathermap.org api
	var url = apiSite + word + key;
	xmlHttp.open("GET", url, true);
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {
		// if user enters null value
		console.log(word);
		if (word === "" && xmlHttp.readyState == 2) {
			alert("Please enter value");
		} else {
			// if request finished, response is ready and status is ok
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var myArr = JSON.parse(xmlHttp.responseText);
				document.getElementById("name").innerHTML = myArr.name;
				document.getElementById("id").innerHTML = myArr.id;
				document.getElementById("show").innerHTML = "Weather Condition : "
						+ "<img src='http://openweathermap.org/img/w/"
						+ myArr.weather[0].icon
						+ ".png'>"
						+ myArr.weather[0].description
						+ "<br>"
						+ "Max-Temp : "
						+ myArr.main.temp_max
						+ "<br>"
						+ "Min-Temp : "
						+ myArr.main.temp_min
						+ "<br>"
						+ "Humidity : "
						+ myArr.main.humidity + "<br>";
			}// user enters wrong input, it will be handled in the first
			// attempt
			else if (xmlHttp.status == 404 && xmlHttp.readyState == 2) {
				console.log(xmlHttp.status + "  " + xmlHttp.readyState);
				alert("Enter proper value");
			}
		}
	};
}

// function executes when add to favourites button is clicked
function add() {
	var name = document.getElementById("name").innerHTML;
	var id = document.getElementById("id").innerHTML;
	if (!(name === "")) {
		console.log("name : " + name + " | id : " + id);
		var xmlHttp = new XMLHttpRequest();
		var urlFav = "http://localhost:" + portNumber
				+ "/WeatherApplication/addCity?name=" + name + "&id=" + id;
		xmlHttp.open("POST", urlFav, true);
		xmlHttp.send();
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				alert("Data successfully added");
			}
//			else{
//				alert("Data contains already");
//			}
		}
	} else {
		 alert("Please enter value");
	}
}

// function executes when show favourites button is clicked
function showFav() {
	var xmlHttp = new XMLHttpRequest();
	var urlFav = "http://localhost:" + portNumber
			+ "/WeatherApplication/viewFav";
	xmlHttp.open("GET", urlFav, true);
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			document.getElementById("name").innerHTML = "";
			document.getElementById("id").innerHTML = "";
			// parsing the retrieved json object
			if(JSON.parse(xmlHttp.responseText == "")){
				alert("Data not found");
			}
			else{
			var data = JSON.parse(xmlHttp.responseText);
			var len = data.cities;
			var arrSize = len.length;
			var displayData;
				for (let i = 0; i < arrSize; i++) {
					if (i == 0) {
						displayData = "Name : " + data.cities[i].name;
					} else {
						displayData += "Name : " + data.cities[i].name;
					}
					displayData += '&emsp;<button  class="btn btn-secondary" onclick="remove(\''
							+ data.cities[i].name
							+ '\'\)">Remove</button>'
							+ "<br><hr>";
				}
				document.getElementById("show").innerHTML = displayData;
		
		}
		}

	};
}

// function executes when show remove button is clicked
function remove(data) {
	var xmlHttp = new XMLHttpRequest();
	var urlFav = "http://localhost:" + portNumber
			+ "/WeatherApplication/removeCity?name=" + data;
	xmlHttp.open("GET", urlFav, true);
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert("Data removed");
			document.getElementById("show").innerHTML = "";
		}
	}
}
