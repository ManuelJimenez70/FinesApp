package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Header extends JPanel{
	
	private JLabel txtheader;

	public Header(ActionListener actionListener) {
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(500,100));
		setBackground(new Color(10, 72, 148));
		initComponents(actionListener);
	}

	private void initComponents(ActionListener actionListener) {
		this.txtheader = new JLabel("Header");
		this.txtheader.setFont(new Font("Agency FB", Font.BOLD, 20));
		this.add(txtheader);
	}

}
