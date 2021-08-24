package models;

import java.util.ArrayList;

public class Person {
	
	private String cedula;
	private String name;
	private ArrayList<Fine> fines;
	
	public Person(String cedula, String name) {
		super();
		this.cedula = cedula;
		this.name = name;
		this.fines = new ArrayList<Fine>();
	}

	public String getCedula() {
		return cedula;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Fine> getFines() {
		return fines;
	}

	public void addFine(Fine fine) {
		fines.add(fine);
	}
	
	@Override
	public String toString() {
		String person = "cc: " + cedula + "\t\tNombre: " + name + "\n" + "Multas: \n";
		for (int i = 0; i < fines.size(); i++) {
			person += fines.get(i).toString() + "\n";
		}
		return person;
	}
	
}
