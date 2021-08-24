package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Events;
import models.Fine;

public class AddFinePanel extends JPanel {

	private static final String DESCRIPTION_TAG = "Descripcion Multa: ";
	private static final String EVIDENCY_TAG = "Seleccionar evidencia (imagen): ";
	private static final String EVIDENCY_BTN_TAG = "Examinar";
	private static final Color MY_COLOR = Color.decode("#017993");
	private static final String CANCEL_TAG = "Cancelar";
	private static final String ACEPT_TAG = "Aceptar";
	private static final String Y_TAG = "Y: ";
	private static final String X_TAG = "X: ";
	private static final String COORDS_TAG = "Coordenadas:     max (500,500)";
	private static final String NAME_TAG = "Nombre: ";
	private static final String CEDULA_TAG = "Cedula: ";
	private JLabel lbCedula, lbName, lbCoords, lbDescription,lbCoordX, lbCoordY, lbEvidency;
	private JTextField txCedula, txName, txDescription,txX, txY;
	private JButton btAcept, btCancel, btEvidency;
	private JFileChooser chooser;
	private String evidencyPath;

	public AddFinePanel(ActionListener actionListener) {
		evidencyPath = "";
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(600, 400));
		setBackground(Color.WHITE);
		initComponents(actionListener);
	}

	private void initComponents(ActionListener actionListener) {
		lbCedula = new JLabel(CEDULA_TAG);
		txCedula = new JTextField(11);
		lbName = new JLabel(NAME_TAG);
		txName = new JTextField(20);
		lbDescription = new JLabel(DESCRIPTION_TAG);
		txDescription = new JTextField(20);
		lbCoords = new JLabel(COORDS_TAG);
		lbCoordX = new JLabel(X_TAG);
		txX = new JTextField(4);
		lbCoordY = new JLabel(Y_TAG);
		txY = new JTextField(4);
		chooser = new JFileChooser();
		lbEvidency = new JLabel(EVIDENCY_TAG);
		createButtons(actionListener);
		addComponents();
	}

	private void createButtons(ActionListener actionListener) {
		btAcept = new JButton(ACEPT_TAG);
		btAcept.setPreferredSize(new Dimension(80, 30));
		btAcept.setBorder(null);
		btAcept.setFocusable(false);
		btAcept.setBackground(MY_COLOR);
		btAcept.setForeground(Color.WHITE);
		btAcept.addActionListener(actionListener);
		btAcept.setActionCommand(Events.ACEPT_EVENT);

		btCancel = new JButton(CANCEL_TAG);
		btCancel.setPreferredSize(new Dimension(80, 30));
		btCancel.setBorder(null);
		btCancel.setFocusable(false);
		btCancel.setBackground(MY_COLOR);
		btCancel.setForeground(Color.WHITE);
		btCancel.addActionListener(actionListener);
		btCancel.setActionCommand(Events.CANCEL_EVENT);

		btEvidency = new JButton(EVIDENCY_BTN_TAG);
		btEvidency.setPreferredSize(new Dimension(90, 30));
		btEvidency.setFocusable(false);
		btEvidency.setBackground(Color.WHITE);
		btEvidency.addActionListener(actionListener);
		btEvidency.setActionCommand(Events.EVIDENCY_EVENT);
	}

	private void addComponents() {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets.set(10, 10, 10, 10);
		add(lbCedula, c);
		c.gridx = 2;
		add(txCedula, c);
		c.gridx = 0;
		c.gridy = 1;
		add(lbName, c);
		c.gridx = 2;
		add(txName, c);
		c.gridx = 0;
		c.gridy = 2;
		add(lbDescription,c);
		c.gridx = 2;
		add(txDescription, c);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 4;
		add(lbCoords, c);
		c.gridy = 4;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		add(lbCoordX, c);
		c.gridx = 1;
		add(txX, c);
		c.gridx = 2;
		add(lbCoordY, c);
		c.gridx = 3;
		c.anchor = GridBagConstraints.WEST;
		add(txY, c);
		c.gridy = 5;
		c.gridx = 0;
		c.gridwidth = 2;
		add(lbEvidency, c);
		c.gridx = 2;
		add(btEvidency, c);
		c.gridy = 6;
		c.gridx = 0;
		c.anchor = GridBagConstraints.CENTER;
		add(btAcept, c);
		c.gridx = 2;
		c.anchor = GridBagConstraints.EAST;
		add(btCancel, c);
	}

	public void selectEvidency() {
		int option = chooser.showOpenDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) {
			this.evidencyPath = chooser.getSelectedFile().getPath();
		}
	}

	public Fine getNewFine() {
		Fine fine = new Fine(txDescription.getText(), txX.getText() + "," + txY.getText());
		fine.setNamePerson(txName.getText());
		fine.setCedulaPerson(txCedula.getText().trim());
		fine.setEvidencyPath(this.evidencyPath);
		clear();
		return fine;
	}

	private void clear() {
		this.txCedula.setText("");
		this.txDescription.setText("");
		this.txName.setText("");
		this.txX.setText("");
		this.txY.setText("");
	}

}
