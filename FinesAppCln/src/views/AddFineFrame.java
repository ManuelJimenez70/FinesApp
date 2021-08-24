package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import models.Fine;

public class AddFineFrame extends JFrame{
	
	private static final String AGENCY_FONT_TYPE = "Agency FB";
	private static final String ADD_FINE = "Añadir Multa";
	private static final String TITTLE = "FinesApp";
	private AddFinePanel addFinePanel;
	private JLabel lbAddFine;
	
	
	public AddFineFrame(ActionListener actionListener) {
		super();
        this.setUndecorated(true);
		this.setTitle(TITTLE);
		this.setSize(new Dimension(600, 400));
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		initComponents(actionListener);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	private void initComponents(ActionListener actionListener) {
		this.lbAddFine = new JLabel(ADD_FINE);
		this.lbAddFine.setFont(new Font(AGENCY_FONT_TYPE, Font.PLAIN, 40));
		add(lbAddFine, BorderLayout.NORTH);
		
		this.addFinePanel = new AddFinePanel(actionListener);
		add(addFinePanel,BorderLayout.CENTER);
	}

	public void selectEvidency() {
		addFinePanel.selectEvidency();
	}

	public Fine getNewFine() {
		return addFinePanel.getNewFine();
	}


}
