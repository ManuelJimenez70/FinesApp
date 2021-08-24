package models;

import java.util.ArrayList;

public class PeopleFinesManager {
	
	private ArrayList<Person> people;
	
	public PeopleFinesManager(ArrayList<Person> people) {
		this.people = people;
	}

	public ArrayList<Person> getPeople() {
		return people;
	}

	public void setPeople(ArrayList<Person> people) {
		this.people = people;
	}

	public void addPerson(Person newPerson) {
		this.people.add(newPerson);
	}

	public String getAllPositions() {
		String pos = "";
		for (Person person : people) {
			pos += person.getPositions()+";";
		}
		return pos.substring(0, pos.lastIndexOf(";"));
	}

	public Person searchPerson(String cedula) {
		for (Person person : people) {
			if (cedula.equals(person.getCedula())) {
				return person;
			}
		}
		return null;
	}
	
}
