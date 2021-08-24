package models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.javafx.tk.Toolkit;

public class FileManager {
	
	private static final String FINE_FILE_PATH = "src/resources/persistance/fine.txt";
	private static final String PLACE_TAG = "place";
	private static final String DESCRIPTION_TAG = "description";
	private static final String NAME_TAG = "name";
	private static final String FINE_LIST_TAG = "fineList";
	private static final String _TAG = "cedula";
	private static final String PERSON_TAG = "person";

	public FileManager() {
	}
	
	public File fineToJson(Fine fine) throws IOException {
		JSONObject json = new JSONObject();
		JSONObject jsonFine = new JSONObject();
		jsonFine.put(_TAG, fine.getCedulaPerson());
		jsonFine.put(NAME_TAG, fine.getNamePerson());
		jsonFine.put(DESCRIPTION_TAG, fine.getDescription());
		jsonFine.put(PLACE_TAG, fine.getPlace());
		json.put("fine", jsonFine);
		File file = new File(FINE_FILE_PATH);
		FileWriter fileWriter = new FileWriter(file,false);
		fileWriter.write(json.toString(4));
		fileWriter.flush();
		return file;
	}

	public Person readPersonFile(File personFile) throws IOException {
		if (personFile!=null) {
			String text = String.join(" ", Files.readAllLines(Paths.get(personFile.getPath())));
			JSONObject personJson = new JSONObject(text);
			JSONObject person = personJson.getJSONObject(PERSON_TAG);
			Person p = new Person(((JSONObject) (person)).get(_TAG).toString(),
					((JSONObject) (person)).get(NAME_TAG).toString());
			JSONArray fineList = ((JSONObject) (person)).getJSONArray(FINE_LIST_TAG);
			for (int j = 0; j < fineList.length(); j++) {
				p.addFine(new Fine(((JSONObject) (fineList.get(j))).get(DESCRIPTION_TAG).toString(),
						((JSONObject) (fineList.get(j))).get(PLACE_TAG).toString()));
			}
			return p;
		}
		return null;
	}
}
