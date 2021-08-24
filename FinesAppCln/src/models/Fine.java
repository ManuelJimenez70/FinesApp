package models;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

public class Fine {

	private String description;
	private File evidency;
	private String evidencyPath;
	private Point place;
	private String namePerson;
	private String cedulaPerson;


	public Fine(String description, Point place) {
		super();
		this.evidencyPath = "";
		this.description = description;
		this.place = place;
	}

	public Fine(String description, String place) {
		this.evidencyPath = "";
		this.description = description;
		String[] points = place.split(",");
		this.place = new Point(Integer.parseInt(points[0]),Integer.parseInt(points[1]));
	}

	public String getDescription() {
		return description;
	}

	public String getEvidencyData(){
		return evidency.getPath();
	}
	
	public String getEvidencyPath() {
		return evidencyPath;
	}

	public String getPlace() {
		return place.x + "," + place.y;
	}
	
	public Point getPlacePoint() {
		return this.place;
	}
	
	@Override
	public String toString() {
		return "Descripcion: " + description + "\tCoordenadas: " + place.x + ", " + place.y;
	}

	public void setEvidencyPath(String evidencyPath) {
		this.evidencyPath = evidencyPath;
	}

	public void setCedulaPerson(String cedulaPerson) {
		this.cedulaPerson = cedulaPerson;
	}

	public void setNamePerson(String namePerson) {
		this.namePerson = namePerson;
	}
	
	public File getEvidency() {
		return evidency;
	}

	public String getNamePerson() {
		return namePerson;
	}

	public String getCedulaPerson() {
		return cedulaPerson;
	}


}
