

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@WebServlet("/CreateJson")
public class CreateJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 JSONArray arr=new JSONArray();
	 
    public CreateJson() {
        super();
        // TODO Auto-generated constructor stub
    }
   

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("add"))
		{
			JSONObject object=new JSONObject();
			String name=request.getParameter("name");
			String id=request.getParameter("id");
		       object.put("name",name);
			   object.put("id",id);
			   //System.out.println(object);
		       arr.add(object);
		       JSONObject main = new JSONObject();
		       main.put("cities",arr);
		       System.out.println(arr);
		       if(arr.size()<=10) {
				  try(FileWriter fwrite=new FileWriter("/home/sapient/Documents/workspace-sts-3.9.2.RELEASE/WeatherApplication/WebContent/myFile.json")) 
				    {
				    	fwrite.write(main.toString());
				    	fwrite.flush();
				    } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}else {
			System.out.println("size exceeded");
		}
		}
		
		if(action.equalsIgnoreCase("view"))
		{
			System.out.println("in view Favourites");
			JSONParser parser=new JSONParser();
			try {
				JSONObject main = new JSONObject();
				main = (JSONObject) parser.parse(new FileReader("/home/sapient/Documents/workspace-sts-3.9.2.RELEASE/WeatherApplication/WebContent/myFile.json"));
				 response.setContentType("application/json");
			    	response.getWriter().write(main.toString());
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		}
		
		if(action.equalsIgnoreCase("remove")) {
			String name=request.getParameter("name");
			
			
			JSONParser parser=new JSONParser();
			try {
				JSONObject main = new JSONObject();
				main = (JSONObject) parser.parse(new FileReader("/home/sapient/Documents/workspace-sts-3.9.2.RELEASE/WeatherApplication/WebContent/myFile.json"));

				for(int i=0;i<main.size();i++) {
					Object data=main.get("cities");
					if(data.toString().contains(name)) {
						
					}
				}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
			
		}
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
