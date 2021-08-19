package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Footer extends JPanel{
	
	private JLabel txtfooter;


	public Footer(ActionListener actionListener) {
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(500,100));
		setBackground(new Color(10, 72, 148));
		initComponents(actionListener);
	}

	private void initComponents(ActionListener actionListener) {
		this.txtfooter = new JLabel("Header");
		this.txtfooter.setFont(new Font("Agency FB", Font.BOLD, 20));
		this.add(txtfooter);		
	}

}
