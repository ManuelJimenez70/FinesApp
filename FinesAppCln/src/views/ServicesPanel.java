package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import models.Events;
import models.Fine;

public class ServicesPanel extends JPanel{
	
	private static final String FINDS_MAP_TXT = "Ver Mapa de Multas";
	private static final int STROKE = 3;
	private static final String ADD_FINE_TEXT = "Añadir Nueva Multa";
	private AddFineButton adder;
	private MyServiceButton showMap;

	public ServicesPanel(ActionListener actionListener) {
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(250,650));
		setBackground(Color.WHITE);
		initComponents(actionListener);
	}

	private void initComponents(ActionListener actionListener) {
		this.adder = new AddFineButton(ADD_FINE_TEXT, actionListener);
		add(adder);
		
		this.showMap = new MyServiceButton(FINDS_MAP_TXT, actionListener);
		this.showMap.setActionCommand(Events.SHOW_MAP_EVENT);
		add(showMap);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.decode("#017993"));
		g.fillRect(getWidth()-STROKE, 0, STROKE, getHeight());
	}

	public void openAddFrame() {
		adder.openAddFrame();
	}

	public void closeAddFrame() {
		adder.closeAddFrame();
	}

	public void selectEvidency() {
		adder.selectEvidency();
	}

	public Fine getNewFine() {
		return adder.getNewFine();
	}

}
