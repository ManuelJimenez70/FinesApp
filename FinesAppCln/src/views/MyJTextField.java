package views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class MyJTextField extends JTextField {

	private static final String TOOL_TIP_TEXT = "Digite una cedula";

	private static final String INITIAL_TEXT = " ";

	private static final Color BORDER_COLOR = Color.decode("#017993");

	private static final int STROKE = 2;
	private static final int ARC_SIZE = 10;


	public MyJTextField() {
		super();
		this.setToolTipText(TOOL_TIP_TEXT);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(200,30));
		this.setBorder(null);
		this.setText(INITIAL_TEXT);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		getGraphics2D(g2);
		paintBorder(g2);
	}

	private void getGraphics2D(Graphics2D g2) {
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		g2.setRenderingHints(rh);
	}

	private void paintBorder(Graphics2D g2) {
		g2.setColor(BORDER_COLOR);
		g2.setStroke(new BasicStroke(STROKE));
		g2.drawRoundRect(getWidth()/500, getHeight()/10, getWidth()-getWidth()/100, getHeight()-getHeight()/5, ARC_SIZE, ARC_SIZE);
	}
	
}
