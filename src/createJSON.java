import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

public class createJSON {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    JSONObject obj = new JSONObject();
    obj.put("name", "neha");
     
     
    obj.put("location", "sangrur");
    
    //JSONArray....add
    
    try(FileWriter fwrite=new FileWriter("myFile.json",true)) 
    {
    	fwrite.write(obj.toString());
    	fwrite.flush();
    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    System.out.println(obj);
	}
}
