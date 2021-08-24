package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import models.Person;

public class FindSearch extends JPanel {

	private static final String NO_RESULTS = "Sin Resultados";
	private static final String CEDULA_LBL = "Cedula: ";
	private static final String NAME_LBL = "Nombre: ";
	private static final String[] COLUMNS_NAMES = { "Descripción", "X", "Y", "Evidencia" };
	private static final String AGENCY_FONT_TYPE = "Agency FB";
	private JTable table;
	private JPanel personInfo;
	private JLabel lbName, lbNamePerson, lbCedula, lbCedulaPerson, lbNoFound;

	public FindSearch() {
		this.setPreferredSize(new Dimension(950, 650));
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		lbNoFound = new JLabel(NO_RESULTS);
		lbNoFound.setFont(new Font(AGENCY_FONT_TYPE, Font.PLAIN, 40));
	}

	public void setInfo(Person person) {
		removeAll();
		repaint();
		revalidate();
		if (person != null) {
			createHeader(person);
			createTable(person);
			repaint();
			revalidate();
		} else {
			add(lbNoFound, BorderLayout.CENTER);
		}
	}

	private void createHeader(Person person) {
		personInfo = new JPanel();
		personInfo.setLayout(new GridLayout(2, 2));
		personInfo.setBackground(Color.WHITE);
		lbName = new JLabel(NAME_LBL);
		personInfo.add(lbName);

		lbNamePerson = new JLabel(person.getName());
		personInfo.add(lbNamePerson);

		lbCedula = new JLabel(CEDULA_LBL);
		personInfo.add(lbCedula);

		lbCedulaPerson = new JLabel(person.getCedula());
		personInfo.add(lbCedulaPerson);

		add(personInfo, BorderLayout.NORTH);

	}

	private void createTable(Person person) {
		String[][] rowsData = getData(person);
		table = new JTable(rowsData, COLUMNS_NAMES);
		table.setPreferredSize(new Dimension(950, 650));
		add(new JScrollPane(table), BorderLayout.CENTER);
	}

	private String[][] getData(Person person) {
		String[][] data = new String[person.getFines().size()][4];
		for (int i = 0; i < person.getFines().size(); i++) {
			data[i][0] = person.getFines().get(i).getDescription();
			data[i][1] = String.valueOf(person.getFines().get(i).getPlacePoint().x);
			data[i][2] = String.valueOf(person.getFines().get(i).getPlacePoint().y);
			data[i][3] = " ";

		}
		return data;
	}

}
