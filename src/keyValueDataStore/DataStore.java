package keyValueDataStore;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

import org.json.simple.parser.JSONParser;
import com.google.gson.Gson;

public class DataStore {

	static JSONObject currentFileData;
	static Scanner in = new Scanner(System.in);
	private static FileWriter file;

	public static void main(String[] args) {

		String defaultFilePath = "defaultFile.json";
		String inputFilePath = "";

		System.out.println("Welcome to key-value data store !");
		boolean bool = true;
		while (true) {
			System.out.println("Enter a file path or enter 'default' for default file path");

			inputFilePath = in.nextLine();

			if (inputFilePath.equals("Default") || inputFilePath.equals("default") || inputFilePath.equals("DEFAULT")) {
				inputFilePath = defaultFilePath;
				break;
			} else {
				File f = new File(inputFilePath);

				if (!f.exists()) {
					JSONObject jsonObject = new JSONObject();
					System.out.println(f);
					try {
						file = new FileWriter(inputFilePath);
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
					break;

				} else {
					System.out.println("File already exists!");
					System.out.println("Do you want to perform operations in the given file? Y/N");
					String flag = in.nextLine();
					if (!flag.equals("y") || !flag.equals("Y")) {
						System.out.println("Try with different file name again");

					} else {
						break;
					}
				}

			}
		}
               //functionality to be performed is chosen
		while (true) {
			String key = "";
			String value = "";
			System.out.println("Choose your desired functionality to perform");
			System.out.println(
					" 1. Create a new Key-Value pair\n 2. Read a specific key\n 3. Delete a specific key\n 4. Exit ");
			String choice = in.nextLine();
			switch (choice) {
			case "1":
				System.out.println("Enter the key");
				key = in.nextLine();
				System.out.println("Enter the value");
				value = in.nextLine();
				read(inputFilePath);
				create(key, value, inputFilePath);
				break;
			case "2":
				System.out.println("Enter the desired key to read its value");
				key = in.nextLine();
				read(inputFilePath);
				readKey(key, inputFilePath);
				break;
			case "3":
				System.out.println("Enter the desired key to delete the key-value pair");
				key = in.nextLine();
				read(inputFilePath);
				deleteKey(key, inputFilePath);
				break;
			case "4":
				System.out.println("Thank you! Come again.");
				System.exit(0);
				break;
			default:
				System.out.println("There is no such functionality. Please choose from the given options");
				break;
			}
		}
	}
        //reads the file
	public static void read(String inputFilePath) {
		try {
			FileReader fileReader = new FileReader(inputFilePath);
			JSONParser jsonParser = new JSONParser();
			Object object = jsonParser.parse(fileReader);
			Gson gsonObject = new Gson();
			String sampleString = gsonObject.toJson(object);
			JSONParser parser = new JSONParser();
			currentFileData = (JSONObject) parser.parse(sampleString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        //reads the value of the desired key 
	static void readKey(String key, String inputFilePath) {

		if (currentFileData.containsKey(key)) {
			try {
				String value = (String) currentFileData.get(key);
				System.out.println("The requested Key : " + key + ", Value : " + value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Sorry! The key does not exist.");
		}
	}
        //creates a new key-value pair
	@SuppressWarnings("unchecked")
	static void create(String key, String value, String inputFilePath) {

		if (!currentFileData.containsKey(key)) {
			currentFileData.put(key, value);
			try {
				file = new FileWriter(inputFilePath);
				file.write(currentFileData.toJSONString());
				System.out.println("\n	DATA INSERTED USING THE GIVEN KEY\n");
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
		} else {
			System.out.println("Sorry! The key already exists.");
		}
	}
        //deletes the value of the desired key
	static void deleteKey(String key, String inputFilePath) {

		if (currentFileData.containsKey(key)) {
			currentFileData.remove(key);

			try {
				file = new FileWriter(inputFilePath);
				file.write(currentFileData.toJSONString());
				System.out.println("\n	DATA DELETED USING THE GIVEN KEY	\n");
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
		} else {
			System.out.println("Sorry! The key does not exist.");
		}
	}
}
