package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FindsMap extends JPanel{
	
	private static final URL MAP = View.class.getResource("/resources/images/map.png");
	private static final int SIZE = 10;

	private ArrayList<Point> points;
	
	public FindsMap() {
		this.points = new ArrayList<Point>();
		this.setPreferredSize(new Dimension(950,650));
		this.setLayout(new BorderLayout());
	}
	
	
	public void setPoints(String points) {
		String[] coords = points.split(";");
		for (int i = 0; i < coords.length; i++) {
			String[] regrex = coords[i].split(",");
			this.points.add(new Point(Integer.parseInt(regrex[0]),Integer.parseInt(regrex[1])));
		}
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(MAP));
		g.setColor(Color.RED);
		g.drawImage(icon.getImage(), 0, 0, getWidth(),getHeight(),this);
		for (int i = 0; i < points.size(); i++) {
			g.fillOval(calculateX(points.get(i).x)-SIZE, calculateY(points.get(i).y)-SIZE, SIZE, SIZE);
		}
	}


	private int calculateX(int i) {
		return i*getWidth()/500;
	}
	

	private int calculateY(int i) {
		return i*getHeight()/500;
	}
}
