package models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONObject;

public class FileManager {

	private static final String EVIDENCY_TAG = "evidency";

	private static final String PERSON_TAG = "person";

	private static final String PERSON_FILE_PATH = "src/resources/persistance/person.txt";

	private static final String PEOPLE_LIST_TAG = "peopleList";

	private static final String PEOPLE_PATH = "src/resources/persistance/people.txt";

	private static final String IMAGES_PATH = "src/resources/images/";

	private static final String FINE_LIST_TAG = "fineList";

	private static final String PLACE_TAG = "place";

	private static final String DESCRIPTION_TAG = "description";

	private static final String NAME_TAG = "name";

	private static final String CEDULA_TAG = "cedula";

	private static final URL JSON_PATH = FileManager.class.getResource("/resources/persistance/people.txt");

	private ArrayList<Person> people;

	public FileManager() {
		people = new ArrayList<Person>();
		try {
			readPeople();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showPeople() {
		for (int i = 0; i < people.size(); i++) {
			System.out.println(people.get(i));
		}
		System.out.println(people.size());
	}
	
	public ArrayList<Person> getPeople() {
		return people;
	}


	public void setPeople(ArrayList<Person> people) {
		this.people = people;
	}


	public void writeJson() throws IOException {
		sortList();
		JSONObject json = new JSONObject();
		JSONArray peopleList = new JSONArray();
		for (Person person : people) {
			JSONObject jsonPerson = new JSONObject();
			jsonPerson.put(CEDULA_TAG, person.getCedula());
			jsonPerson.put(NAME_TAG, person.getName());
			JSONArray fineList = new JSONArray();
			for (Fine fine : person.getFines()) {
				JSONObject jsonFine = new JSONObject();
				jsonFine.put(DESCRIPTION_TAG, fine.getDescription());
				jsonFine.put(PLACE_TAG, fine.getPlace());
				jsonFine.put(EVIDENCY_TAG, fine.getEvidencyName());
				fineList.put(jsonFine);
			}
			jsonPerson.put(FINE_LIST_TAG, fineList);
			peopleList.put(jsonPerson);
		}
		json.put(PEOPLE_LIST_TAG, peopleList);
		FileWriter file = new FileWriter(PEOPLE_PATH);
		file.write(json.toString(4));
		file.flush();
	}

	private void readPeople() throws IOException {
		String text = String.join(" ", Files.readAllLines(Paths.get(JSON_PATH.getPath().substring(1))));
		JSONObject json = new JSONObject(text);
		JSONArray peopleList = json.getJSONArray(PEOPLE_LIST_TAG);
		for (int i = 0; i < peopleList.length(); i++) {
			Person p = new Person(((JSONObject) (peopleList.get(i))).get(CEDULA_TAG).toString(),
					((JSONObject) (peopleList.get(i))).get(NAME_TAG).toString());
			JSONArray fineList = ((JSONObject) (peopleList.get(i))).getJSONArray(FINE_LIST_TAG);
			for (int j = 0; j < fineList.length(); j++) {
				p.addFine(new Fine(((JSONObject) (fineList.get(j))).get(DESCRIPTION_TAG).toString(),
						((JSONObject) (fineList.get(j))).get(PLACE_TAG).toString(),
						(IMAGES_PATH+((JSONObject) (fineList.get(j))).get(EVIDENCY_TAG).toString())));
			}
			this.people.add(p);
		}

	}

	public void sortList() {
		Collections.sort(this.people, Comparator.comparing(Person::getCedula));
	}

	public File PersonToJson(Person person) throws IOException {
		JSONObject json = new JSONObject();
		JSONObject jsonPerson = new JSONObject();
		jsonPerson.put(CEDULA_TAG, person.getCedula());
		jsonPerson.put(NAME_TAG, person.getName());
		JSONArray fineList = new JSONArray();
		for (Fine fine : person.getFines()) {
			JSONObject jsonFine = new JSONObject();
			jsonFine.put(DESCRIPTION_TAG, fine.getDescription());
			jsonFine.put(PLACE_TAG, fine.getPlace());
			fineList.put(jsonFine);
		}
		jsonPerson.put(FINE_LIST_TAG, fineList);
		json.put(PERSON_TAG, jsonPerson);
		File file = new File(PERSON_FILE_PATH);
		FileWriter fileWriter = new FileWriter(file,false);
		fileWriter.write(json.toString(4));
		fileWriter.flush();
		return file;
	}

}
