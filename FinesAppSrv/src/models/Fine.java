package models;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Fine {

	private String description;
	private File evidency;
	private String evidencyPath;
	private Point place;

	public Fine(String description, Point place) {
		super();
		this.description = description;
		this.place = place;
	}

	public Fine(String description, String place) {
		this.description = description;
		String[] points = place.split(",");
		this.place = new Point(Integer.parseInt(points[0]), Integer.parseInt(points[1]));
	}

	public Fine(String description, String place, String filePath) {
		this.description = description;
		String[] points = place.split(",");
		this.place = new Point(Integer.parseInt(points[0]), Integer.parseInt(points[1]));
		try {
			if (filePath.split(".")[1].equals("png")) {
				this.evidency = new File(filePath);
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}
	}

	public File getEvidency() {
		return evidency;
	}
	
	public String getEvidencyName() {
		return evidencyPath;
	}

	public String getDescription() {
		return description;
	}

	public String getEvidencyData() throws IOException {
		return evidency.getPath();
	}

	public String getPlace() {
		return place.x + "," + place.y;
	}

	@Override
	public String toString() {
		return "Descripcion: " + description + "\tCoordenadas: " + place.x + ", " + place.y;
	}

	public void setEvidency(File fileEvidency) {
		this.evidency = fileEvidency;
		this.evidencyPath = fileEvidency.getName();
	}

}
