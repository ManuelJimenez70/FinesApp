package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import models.Events;
import models.FileManager;
import models.Fine;
import models.Person;
import network.Client;
import views.View;

public class Controller implements ActionListener {

	private View view;
	private Client client;
	private FileManager fileManager;

	public Controller() throws IOException {
		this.fileManager = new FileManager();
		this.view = new View(this);
		this.client = new Client();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case Events.ACEPT_EVENT:
			Fine f = view.getNewFine();
			view.closeAddFrame();
			try {
				client.addFine(this.fileManager.fineToJson(f), f);
				view.setPoints(client.getPoints());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case Events.ADD_FINE_EVENT:
			view.openAddFrame();
			break;
		case Events.CANCEL_EVENT:
			view.closeAddFrame();
			break;
		case Events.EVIDENCY_EVENT:
			view.selectEvidency();
			break;
		case Events.SEARCH_PERSON_EVENT:
			String cedula = view.getCedulaToearch();
			try {
				File personFile = client.getPersonFile(cedula);
				Person person = fileManager.readPersonFile(personFile);
				view.setInfo(person);
				view.getCardLayout().show(view.getInfoPanel(), Events.EVENT_SEARCH_FIND);

			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case Events.SHOW_MAP_EVENT:
			try {
				view.setPoints(client.getPoints());
				view.getCardLayout().show(view.getInfoPanel(), Events.EVENT_MAP);

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
}
