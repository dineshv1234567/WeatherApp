function getData(){
var xmlHttp = new XMLHttpRequest();
var word=document.getElementById("city").value;
//document.write(word);
var url ="http://api.openweathermap.org/data/2.5/weather?q="+word+"&appid=4f51cc3581658734467f957800c7625d";
xmlHttp.open("GET",url, true);
xmlHttp.send();
xmlHttp.onreadystatechange = function() {
   if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
       var myArr= JSON.parse(xmlHttp.responseText);
      // var dataObj= JSON.stringify(myArr);
       document.getElementById("name").innerHTML=myArr.name+"<br>";
       document.getElementById("id").innerHTML=myArr.id+"<br>";
       document.getElementById("show").innerHTML =
    	   "Weather Condition : "+"<img src='http://openweathermap.org/img/w/"+
    	   myArr.weather[0].icon+".png'>"+myArr.weather[0].description+"<br>"+
    	   "Max-Temp : "+myArr.main.temp_max+"<br>"+
    	   "Min-Temp : "+myArr.main.temp_min+"<br>"+
    	   "Humidity : "+myArr.main.humidity+"<br>" ;
   }
};
}
