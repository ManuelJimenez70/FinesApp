package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class View extends JFrame{

	private static final String TITTLE = "FinesApp";
	private Header header;
	private Body body;
	private Footer footer;
	
	
	public View(ActionListener actionListener) {
		super();
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource("/resource/images/titleIcon.png")));
		this.setTitle(TITTLE);
		this.setSize(new Dimension(500, 600));
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		initComponents(actionListener);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void initComponents(ActionListener actionListener) {
		this.header = new Header(actionListener);
		this.body = new Body(actionListener);
		this.footer = new Footer(actionListener);
		this.add(this.header,BorderLayout.NORTH);
		this.add(this.body,BorderLayout.CENTER);
		this.add(this.footer, BorderLayout.SOUTH);
	}
	
}
