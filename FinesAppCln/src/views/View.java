package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.UIManager;

import models.Fine;
import models.Person;

public class View extends JFrame{

	
	private static final String ICON_IMAGE = "/resources/images/icon.png";
	private static final Font MY_FONT = new Font("Agency FB", Font.PLAIN, 20);
	private static final String LABEL_FONT_PROP = "Label.font";
	private static final String TITTLE = "FinesApp";
	private Header header;
	private Body body;
	
	
	public View(ActionListener actionListener) {
		super();
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource(ICON_IMAGE)));
		UIManager.put(LABEL_FONT_PROP, MY_FONT);
		this.setTitle(TITTLE);
		this.setSize(new Dimension(1200, 700));
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		initComponents(actionListener);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void initComponents(ActionListener actionListener) {
		this.header = new Header(actionListener);
		this.body = new Body(actionListener);
		this.add(this.header,BorderLayout.NORTH);
		this.add(this.body,BorderLayout.CENTER);
	}
	
	public String getCedulaToearch() {
		return this.header.getCedulaToearch();
	}

	public void openAddFrame() {
		body.openAddFrame();
	}

	public void closeAddFrame() {
		body.closeAddFrame();
	}

	public void selectEvidency() {
		body.selectEvidency();
	}

	public Fine getNewFine() {
		return body.getNewFine();
	}

	public CardLayout getCardLayout() {
		return body.getCardLayout();
	}

	public InfoPanel getInfoPanel() {
		return body.getInfoPanel();
	}

	public void setPoints(String points) {
		body.setPoints(points);
	}

	public void setInfo(Person person) {
		body.setInfo(person);
	}
	
}
