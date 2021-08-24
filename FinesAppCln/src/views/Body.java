package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Fine;
import models.Person;

public class Body extends JPanel {

	private ServicesPanel services;
	private InfoPanel info;

	public Body(ActionListener actionListener) {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1200, 650));
		setBackground(Color.WHITE);
		initComponents(actionListener);
	}

	private void initComponents(ActionListener actionListener) {
		this.services = new ServicesPanel(actionListener);
		add(services, BorderLayout.WEST);

		this.info = new InfoPanel();
		add(info, BorderLayout.CENTER);
	}

	public void openAddFrame() {
		services.openAddFrame();
	}

	public void closeAddFrame() {
		services.closeAddFrame();
	}

	public void selectEvidency() {
		services.selectEvidency();
	}

	public Fine getNewFine() {
		return services.getNewFine();
	}

	public CardLayout getCardLayout() {
		return info.getCardLayout();
	}

	public InfoPanel getInfoPanel() {
		return info;
	}

	public void setPoints(String points) {
		info.setPoints(points);
	}

	public void setInfo(Person person) {
		info.setInfo(person);
	}

}
