package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel{
	
	private static final URL HEADER_ICON = View.class.getResource("/resources/images/welcome.png");
	private JLabel welcome;
	
	public WelcomePanel() {
		this.setPreferredSize(new Dimension(950,650));
		this.setLayout(new BorderLayout());
		welcome = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(HEADER_ICON)));
		add(welcome, BorderLayout.CENTER);
	}

}
