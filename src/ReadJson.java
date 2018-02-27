import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class ReadJson {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
	JSONParser parser=new JSONParser();
	try {
		Object obj = parser.parse(new FileReader("myFile.json"));
		JSONObject jsonObject=(JSONObject) obj;
		System.out.println(jsonObject.size());
//		String name=(String) jsonObject.get("name");
//		System.out.println(name);
//		String loc=(String) jsonObject.get("location");
//		System.out.println(loc);
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
