package views;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

import models.Events;
import models.Person;


public class InfoPanel extends JPanel{
	
	private FindsMap map;
	private FindSearch search;
	private WelcomePanel welcomePanel;
	private CardLayout cardLayout;

	public InfoPanel() {
		cardLayout = new CardLayout();
		this.setPreferredSize(new Dimension(950,650));
		this.setLayout(cardLayout);
		initComponents();
	}

	private void initComponents() {
		this.welcomePanel = new WelcomePanel();
		add(welcomePanel, Events.WELCOME);
		
		this.map = new FindsMap();
		add(map, Events.EVENT_MAP);
		
		this.search = new FindSearch();
		add(search, Events.EVENT_SEARCH_FIND);
	}
	
	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setPoints(String points) {
		map.setPoints(points);
	}

	public void setInfo(Person person) {
		search.setInfo(person);
	}
	

}
