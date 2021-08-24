package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;

import org.json.JSONObject;

import models.Fine;
import models.Person;

public class Client {

	private static final String PERSON_FILE_PATH = "src/resources/persistance/person.txt";
	private static final String EVIDENCY_FILE_PATH = "src/resources/images/";
	private static final String CONSULT_PERSON_SERVICE = "/consultPerson";
	private static final String SHOW_FINES_MAP_SERVICE = "/showFinesMap";
	private static final String ADD_NEW_FINE_SERVICE = "/addNewFine";
	private Socket socket;
	public static final int PORT = 4000;
	public static final String IP = "127.0.0.1";
	private DataOutputStream outputChannel;
	private DataInputStream inputChannel;

	public Client() throws IOException {
		this.socket = new Socket(IP, PORT);
		outputChannel = new DataOutputStream(socket.getOutputStream());
		inputChannel = new DataInputStream(socket.getInputStream());
	}

	public void addFine(File fineJson, Fine fineEvidency) throws IOException {
		byte[] fileContent = Files.readAllBytes(fineJson.toPath());
		sendRequest(ADD_NEW_FINE_SERVICE, fileContent);
		if (fineEvidency.getEvidencyPath() != "") {
			File evidency = new File(fineEvidency.getEvidencyPath());
			byte[] evidencyContent = Files.readAllBytes(evidency.toPath());
			sendFile(evidencyContent);
		} else {
			sendFile(fineEvidency.getEvidencyPath().getBytes());
		}
		int response = inputChannel.readInt();
		System.out.println(response);
		resetConnection();
	}

	private void resetConnection() throws UnknownHostException, IOException {
		this.socket = new Socket(IP, PORT);
		outputChannel = new DataOutputStream(socket.getOutputStream());
		inputChannel = new DataInputStream(socket.getInputStream());
	}

	public void sendRequest(String service, String params) throws IOException {
		outputChannel.writeUTF(service);
		outputChannel.writeUTF(params);
	}

	public void sendRequest(String service) throws IOException {
		outputChannel.writeUTF(service);
	}

	public void sendRequest(String service, byte[] data) throws IOException {
		outputChannel.writeUTF(service);
		outputChannel.writeInt(data.length);
		outputChannel.write(data);
	}

	public void sendFile(byte[] data) throws IOException {
		outputChannel.writeInt(data.length);
		outputChannel.write(data);
	}


	public String getPoints() throws IOException {
		sendRequest(SHOW_FINES_MAP_SERVICE);
		String response = inputChannel.readUTF();
		outputChannel.writeInt(200);
		resetConnection();
		return response;
	}

	public File getPersonFile(String cedula) throws IOException {
		sendRequest(CONSULT_PERSON_SERVICE);
		outputChannel.writeUTF(cedula);
		int sizePersonFile = inputChannel.readInt();
		byte[] personInBytes = new byte[sizePersonFile]; 
		inputChannel.read(personInBytes);
		if (personInBytes.length>0) {
			String dataFineStr = new String(personInBytes);
			File fileFine = new File(PERSON_FILE_PATH);
			FileWriter fwf = new FileWriter(fileFine, false);
			fwf.write(dataFineStr);
			fwf.close();
			readEvidency();
			resetConnection();
			return fileFine;
		}
		resetConnection();
		return null;
	}

	private void readEvidency() throws IOException, UnknownHostException {
		int finesQuantity = inputChannel.readInt();
		for (int i = 0; i < finesQuantity; i++) {
			int sizeEvidencyFile = inputChannel.readInt();
			byte[] evidencyInBytes = new byte[sizeEvidencyFile]; 
			inputChannel.read(evidencyInBytes);
			if (evidencyInBytes.length>0) {
				String dataEvidencyStr = new String(evidencyInBytes);
				File evidencyFile = new File(EVIDENCY_FILE_PATH + i + ".png");
				FileWriter fwe = new FileWriter(evidencyFile, false);
				fwe.write(dataEvidencyStr);
				fwe.close();
				resetConnection();
			}else {
				resetConnection();
			}
		}
		resetConnection();
	}

}
