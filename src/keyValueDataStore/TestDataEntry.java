package keyValueDataStore;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TestDataEntry {

	private static FileWriter file;

	@SuppressWarnings("unchecked")
	public static void main(String args[]) {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("UserID", "303");
		jsonObject.put("First Name", "Damon");
		jsonObject.put("Last Name", "Salvatore");
		jsonObject.put("Date Of Birth", "16-03-1997");
		jsonObject.put("Gender", "Male");
		jsonObject.put("Qualification", "BE");
		jsonObject.put("Marital Status", "Single");
		jsonObject.put("Phone Number", "9543786");
		jsonObject.put("Email ID", "damon97@email.com");
		jsonObject.put("City", "Bangalore");
		jsonObject.put("State", "Karnataka");
		jsonObject.put("Nationality", "Indian");
		jsonObject.put("Pincode", "647030");

		JSONArray hobbies = new JSONArray();

		hobbies.add("Reading Books");
		hobbies.add("Sketching");
		hobbies.add("Travelling");
		jsonObject.put("Hobbies", hobbies);

		try {
			file = new FileWriter("defaultFile.json");
			file.write(jsonObject.toJSONString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				file.flush();
				file.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println(jsonObject);

	}
}
