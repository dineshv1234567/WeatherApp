function getData(){
var xmlHttp = new XMLHttpRequest();
var word=document.getElementById("city").value;
//document.write(word);
var url ="http://api.openweathermap.org/data/2.5/weather?q="+word+"&unit=metrics&appid=4f51cc3581658734467f957800c7625d";
xmlHttp.open("GET",url, true);
xmlHttp.send();
xmlHttp.onreadystatechange = function() {
   if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
       var myArr= JSON.parse(xmlHttp.responseText);
      // var dataObj= JSON.stringify(myArr);
       document.getElementById("name").innerHTML=myArr.name;
       document.getElementById("id").innerHTML=myArr.id;
       document.getElementById("show").innerHTML =
    	   "Weather Condition : "+"<img src='http://openweathermap.org/img/w/"+
    	   myArr.weather[0].icon+".png'>"+myArr.weather[0].description+"<br>"+
    	   "Max-Temp : "+myArr.main.temp_max+"<br>"+
    	   "Min-Temp : "+myArr.main.temp_min+"<br>"+
    	   "Humidity : "+myArr.main.humidity+"<br>" ;
   }
};
}

function add(){
	var name=document.getElementById("name").innerHTML;
	var id=document.getElementById("id").innerHTML;
	console.log("name : "+name+" | id : "+id);
	 var xmlHttp = new XMLHttpRequest();
	 var urlFav= "http://localhost:8082/WeatherApplication/CreateJson?name="+name+"&id="+id +"&action=add";
	// console.log(urlFav);
	 xmlHttp.open("GET",urlFav,true);
	 xmlHttp.send();
}

function showFav(){
	//alert("button clicked");
	 var xmlHttp = new XMLHttpRequest();
	 var urlFav= "http://localhost:8082/WeatherApplication/CreateJson?action=view";
		 xmlHttp.open("GET",urlFav,true);
		 xmlHttp.send();	
		 
		 xmlHttp.onreadystatechange = function(){
			if(xmlHttp.readyState == 4 && xmlHttp.status==200){
				document.getElementById("name").innerHTML="";
				document.getElementById("id").innerHTML="";
				var data=JSON.parse(xmlHttp.responseText);
				var len = data.cities;
				var arrSize=len.length;
				var displayData;
				//"<input type='button' value='Remove' onclick='remove(" + data.cities[i].name + ")'
			for(let i=0;i<arrSize;i++){
					if(i==0){
						displayData ="Name : "+data.cities[i].name;
					}
					else{
					displayData +="Name : "+data.cities[i].name;
					}
					displayData +='<button  class="btn btn-primary" onclick="remove(\''+data.cities[i].name+'\'\)">Remove</button>'+"<br><hr>";
				}
				document.getElementById("show").innerHTML = displayData;
			}
	};
}

function remove(data){
	 var xmlHttp = new XMLHttpRequest();
	 var urlFav= "http://localhost:8082/WeatherApplication/CreateJson?name="+data+"&action=remove";
		 xmlHttp.open("GET",urlFav,true);
		 xmlHttp.send();
	
	
}


