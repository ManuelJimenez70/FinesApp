package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyServiceButton extends JButton{
	
	private static final int GAP = 50;
	private static final Font FONT = new Font("Agency FB", Font.BOLD, 18);
	private static final URL ARROW_RIGHT = View.class.getResource("/resources/images/arrowRight.png");
	private static final Color BORDER_COLOR = Color.decode("#017993");
	
	private static final int STROKE = 5;


	public MyServiceButton(String text, ActionListener actionListener) {
		this.setBackground(Color.WHITE);
		this.addActionListener(actionListener);
		this.setBorder(null);
		this.setFocusable(false);
		this.setPreferredSize(new Dimension(230,50));
		this.setHorizontalTextPosition(JButton.RIGHT);
		this.setIconTextGap(GAP);
		this.setFont(FONT);
		this.setText(text);
		this.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ARROW_RIGHT)));
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		getGraphics2D(g2);
		paintBorders(g2);
	}

	private void getGraphics2D(Graphics2D g2) {
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		g2.setRenderingHints(rh);
	}

	private void paintBorders(Graphics2D g2) {
		g2.setColor(BORDER_COLOR);
		g2.fillRect(STROKE, 0, STROKE, getHeight());

		g2.setColor(Color.WHITE);
		g2.fillRect(0, getHeight()-STROKE, getWidth(), STROKE);

	}
	
}
