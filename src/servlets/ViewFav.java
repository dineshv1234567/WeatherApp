package servlets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@WebServlet("/viewFav")
public class ViewFav extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ViewFav() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// on clicking view button
		String path = "./myFile.json";
		JSONParser parser = new JSONParser();
		try {
			JSONObject main = new JSONObject();
			main = (JSONObject) parser.parse(new FileReader(path));
			JSONArray cities = (JSONArray) main.get("cities");
			if(cities.size()!=0) {
			response.setContentType("application/json");
			response.getWriter().write(main.toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
