package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.MyModel;
import views.View;

public class Controller implements ActionListener{
	
	private View view;
	private MyModel model;
	
	public Controller() {
		this.model = new MyModel();
		this.view = new View(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case " ":
			
			break;
		default:
			break;
		}
	}
}
