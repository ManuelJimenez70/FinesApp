package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Header extends JPanel{
	
	
	private static final int STROKE = 5;
	private static final URL HEADER_ICON = View.class.getResource("/resources/images/header.png");
	private JLabel txtheader;
	private SearchPanel searchPanel;

	
	public Header(ActionListener actionListener) {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1200,50));
		setBackground(Color.WHITE);
		initComponents(actionListener);
	}

	private void initComponents(ActionListener actionListener) {
		this.txtheader = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(HEADER_ICON)));
		add(txtheader, BorderLayout.WEST);
		
		this.searchPanel = new SearchPanel(actionListener);
		add(searchPanel, BorderLayout.EAST);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.decode("#017993"));
		g.fillRect(0, getHeight()- STROKE, getWidth(), STROKE);
	}

	public String getCedulaToearch() {
		return this.searchPanel.getCedulaToearch();
	}

}
