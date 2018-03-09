package servlets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

@WebServlet("/removeCity")
public class RemoveCity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoveCity() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// on clicking remove button
		String path = "./myFile.json";
		String name = request.getParameter("name");
		JSONParser parser = new JSONParser();
		try {
			JSONObject main = new JSONObject();
			main = (JSONObject) parser.parse(new FileReader(path));
			JSONArray arr = new JSONArray();
			arr = (JSONArray) main.get("cities");
			for (int i = 0; i < arr.size(); i++) {
				JSONObject temp = new JSONObject();
				temp = (JSONObject) arr.get(i);
				if (temp.get("name").equals(name)) {
					arr.remove(i);
					System.out.println(arr);
					JSONObject data = new JSONObject();
					data.put("cities", arr);
					try (FileWriter fwrite = new FileWriter(path)) {
						fwrite.write(main.toString());
						fwrite.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
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
