

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

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
		String path="/home/sapient/Documents/workspace-sts-3.9.2.RELEASE/WeatherApplication/WebContent/myFile.json";
		if(action.equalsIgnoreCase("add"))
		{		
			JSONObject main = new JSONObject();
			JSONArray  cities = new JSONArray();
			JSONObject city = new JSONObject();
			
			 File f = new File("/home/sapient/Documents/workspace-sts-3.9.2.RELEASE/WeatherApplication/WebContent/myFile.json");
	    	   if(!(f.exists())) { 
	    		   System.out.println("File not exists");
					JSONObject object=new JSONObject();
					String name=request.getParameter("name");
					String id=request.getParameter("id");
					
			       object.put("name",name);
					object.put("id",id);
				       cities.add(object);
				       main.put("cities",cities);
				       System.out.println("---"+main);
				
						  try(FileWriter fwrite=new FileWriter(path)) 
					    {
					    	fwrite.write(main.toString());
					    	fwrite.flush();
					    } catch (IOException e) {
							e.printStackTrace();
						}
	    	   }
	    	   else if(f.exists()){
	    		   System.out.println("File exists");
			int count = 0;
				JSONParser parser = new JSONParser();
			    try {
					main = (JSONObject) parser.parse(new FileReader(path));
					System.out.println(main.get("cities"));
					cities=(JSONArray) main.get("cities");
					count=cities.size();
					System.out.println("++++++++"+count);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			try {
				count++;
				if(count>10)
				{
					System.out.println("size exceeded");
				}
				else {
					JSONObject object=new JSONObject();
					String name=request.getParameter("name");
					String id=request.getParameter("id");
			       object.put("name",name);
					object.put("id",id);
				       cities.add(0,object);
				       main.put("cities",cities);
				       System.out.println(main);
				       try(FileWriter fwrite=new FileWriter(path)) 
					    {
					    	fwrite.write(main.toString());
					    	fwrite.flush();
					    } catch (IOException e) {
							e.printStackTrace();
						}
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
			} 	
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
				JSONArray arr=new JSONArray();
				arr=(JSONArray) main.get("cities");
				for(int i=0;i<arr.size();i++) {
					JSONObject temp=new JSONObject();
					temp=(JSONObject) arr.get(i);
					if(temp.get("name").equals(name)) {
						arr.remove(i);
						System.out.println(arr);
						JSONObject data=new JSONObject();
						data.put("cities", arr);
						try(FileWriter fwrite=new FileWriter(path)) 
					    {
					    	fwrite.write(main.toString());
					    	fwrite.flush();
					    } catch (IOException e) {
							e.printStackTrace();
						}
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
