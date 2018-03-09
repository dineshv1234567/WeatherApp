package servlets;

import java.io.File;
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

@WebServlet("/addCity")
public class AddCity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddCity() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "./myFile.json";
		JSONObject main = new JSONObject();
		JSONArray cities = new JSONArray();
		File f = new File(path);
		if (!(f.exists())) {
			System.out.println("File not exists");
			JSONObject object = new JSONObject();
			String name = request.getParameter("name");
			String id = request.getParameter("id");
			object.put("name", name);
			object.put("id", id);
			cities.add(object);
			main.put("cities", cities);
			System.out.println(main);
			// wiriting to file
			try (FileWriter fwrite = new FileWriter(path)) {
				fwrite.write(main.toString());
				fwrite.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (f.exists()) {
			// if file exists, then read from file, make change and write back to it
			System.out.println("File exists");
			int count = 0;
			JSONParser parser = new JSONParser();
			try {
				main = (JSONObject) parser.parse(new FileReader(path));
				cities = (JSONArray) main.get("cities");
				count = cities.size();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				count++;
				if (count > 10) {
					System.out.println("size exceeded");
				} else {
					int flag=0;
					JSONObject object = new JSONObject();
					String name = request.getParameter("name");
					String id = request.getParameter("id");
//					for (int i = 0; i < cities.size(); i++) {
//						JSONObject temp = new JSONObject();
//						temp = (JSONObject) cities.get(i);
//						if (temp.get("name").equals(name)) {
//							flag=1;
//						}
//					}
				//	if(flag==0) {
						object.put("name", name);
						object.put("id", id);
						cities.add(0, object);
						main.put("cities", cities);
						System.out.println(main);
						System.out.println("flag"+flag);
						try (FileWriter fwrite = new FileWriter(path)) {
							fwrite.write(main.toString());
							fwrite.flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
			//	}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

}
