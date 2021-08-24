package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

import org.json.JSONObject;

import models.FileManager;
import models.Fine;
import models.PeopleFinesManager;
import models.Person;

public class Server {

	private static final String FINE_FILE_PATH = "src/resources/persistance/fine.txt";

	private static final String NAME_TAG = "name";

	private static final String CEDULA_TAG = "cedula";

	private static final String PLACE_TAG = "place";

	private static final String DESCRIPTION_TAG = "description";

	private static final String FINE_TAG = "fine";

	public static final int PORT = 4000;

	private ServerSocket serverSocket;
	private PeopleFinesManager finesManager;
	private FileManager fileManager;

	public Server() throws IOException {
		this.fileManager = new FileManager();
		this.finesManager = new PeopleFinesManager(this.fileManager.getPeople());
		this.serverSocket = new ServerSocket(PORT);
		Logger.getGlobal().info("Se inicia server en el puerto:" + PORT);
		manageConnection();
	}

	private void manageConnection() {
		new Thread(() -> {
			while (!serverSocket.isClosed()) {
				try {
					Socket connection = serverSocket.accept();
					Logger.getGlobal().info("Nueva conexion");
					DataInputStream inputChannel = new DataInputStream(connection.getInputStream());
					DataOutputStream outputChannel = new DataOutputStream(connection.getOutputStream());
					String service = inputChannel.readUTF();
					switch (service) {
					case "/consultPerson":
						serviceConsultPerson(inputChannel, outputChannel);
						break;
					case "/addNewFine":
						serviceAddFineToPerson(inputChannel, outputChannel);
						break;
					case "/showFinesMap":
						serviceShowFinesMap(inputChannel, outputChannel);
						break;
					}
				} catch (IOException e) {
					Logger.getGlobal().warning(e.getMessage());
				}
			}
		}).start();
	}

	private void serviceShowFinesMap(DataInputStream inputChannel, DataOutputStream outputChannel) throws IOException {
		outputChannel.writeUTF(getAllPositions());
		System.out.println(inputChannel.readInt());
	}

	private void serviceAddFineToPerson(DataInputStream inputChannel, DataOutputStream outputChannel)
			throws IOException {
		System.out.println("Añadir multa");
		int sizeFineFile = inputChannel.readInt();
		byte[] dataFine = new byte[sizeFineFile];
		inputChannel.read(dataFine);
		String dataFineStr = new String(dataFine);
		File fileFine = new File(FINE_FILE_PATH);
		FileWriter fwf = new FileWriter(fileFine, false);
		fwf.write(dataFineStr);
		fwf.close();

		int sizeFineImage = inputChannel.readInt();
		byte[] evidencyFine = new byte[sizeFineImage];
		inputChannel.read(evidencyFine);



		addNewFine(fileFine, evidencyFine);
		outputChannel.writeInt(200);
	}

	private void addNewFine(File fileFine, byte[] evidencyFine) throws IOException {
		readNewFine(fileFine, evidencyFine);
	}

	private void readNewFine(File fileFine, byte[] evidencyFine) throws IOException {
		String cedula = "";
		String name = "";
		String text = String.join(" ", Files.readAllLines(Paths.get(fileFine.getPath())));
		JSONObject fineJson = new JSONObject(text);
		JSONObject fine = fineJson.getJSONObject(FINE_TAG);
		Fine newFine = new Fine(((JSONObject) (fine)).get(DESCRIPTION_TAG).toString(),
				((JSONObject) (fine)).get(PLACE_TAG).toString());
		cedula += ((JSONObject) (fine)).get(CEDULA_TAG).toString();
		name += ((JSONObject) (fine)).get(NAME_TAG).toString();
		
		File fileEvidency = new File("src/resources/images/" + cedula + newFine.getDescription() + newFine.getPlace() + ".png");
		Files.write(fileEvidency.toPath(), evidencyFine);
		
		newFine.setEvidency(fileEvidency);
		addFineToPerson(newFine, cedula, name);
	}

	private void addFineToPerson(Fine newFine, String cedula, String name) throws IOException {
		for (Person p : this.finesManager.getPeople()) {
			if (p.getCedula().equals(cedula)) {
				p.addFine(newFine);
				upDateJson();
				return;
			}
		}
		Person newPerson = new Person(cedula, name);
		newPerson.addFine(newFine);
		this.finesManager.addPerson(newPerson);
		upDateJson();
	}

	private void upDateJson() throws IOException {
		this.fileManager.setPeople(this.finesManager.getPeople());
		this.fileManager.writeJson();
	}

	private void serviceConsultPerson(DataInputStream inputChannel, DataOutputStream outputChannel) throws IOException {
		Person person = this.finesManager.searchPerson(inputChannel.readUTF());
		if (person != null) {
			File filePerson = this.fileManager.PersonToJson(person);
			byte[] personBytes = Files.readAllBytes(filePerson.toPath());
			sendFile(personBytes, outputChannel);
			int findQuantity = person.getFines().size();
			outputChannel.writeInt(findQuantity);
			System.out.println(findQuantity);
			for (int i = 0; i < findQuantity; i++) {
				if (person.getFines().get(i).getEvidency()!=null && person.getFines().get(i).getEvidency().exists()) {
					byte[] evidencyBytes = Files.readAllBytes(person.getFines().get(i).getEvidency().toPath());
					outputChannel.writeInt(evidencyBytes.length);
					sendFile(evidencyBytes, outputChannel);
				}else {
					outputChannel.writeInt(0);
					byte[] evidencyBytes = new byte[0];
					sendFile(evidencyBytes, outputChannel);
				}

			}
		} else {
			byte[] personBytes = new byte[0];
			sendFile(personBytes, outputChannel);
		}
	}

	public void sendFile(byte[] data, DataOutputStream outputChannel) throws IOException {
		outputChannel.writeInt(data.length);
		outputChannel.write(data);
	}

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getAllPositions() {
		return this.finesManager.getAllPositions();
	}

}
