package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Events;

public class SearchPanel extends JPanel implements MouseListener {

	private static final String SEARCH_FINDS_LBL = "Consultar Multas: ";

	private static final URL SEARCH_ICON = View.class.getResource("/resources/images/search.png");

	private static final URL SEARCH_ICON_IN = View.class.getResource("/resources/images/searchIn.png");

	private JButton btnSearch;
	private JLabel description;
	private MyJTextField searcher;

	public SearchPanel(ActionListener actionListener) {
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(500, 60));
		setBackground(Color.WHITE);
		initComponents(actionListener);
	}

	private void initComponents(ActionListener actionListener) {
		
		this.description = new JLabel(SEARCH_FINDS_LBL);
		add(description);

		this.searcher = new MyJTextField();
		add(searcher);

		createBtn(actionListener);
		add(btnSearch);
	}

	private void createBtn(ActionListener actionListener) {
		this.btnSearch = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(SEARCH_ICON)));
		this.btnSearch.setBorder(null);
		this.btnSearch.setFocusable(false);
		this.btnSearch.setBackground(Color.WHITE);
		this.btnSearch.addActionListener(actionListener);
		this.btnSearch.setActionCommand(Events.SEARCH_PERSON_EVENT);
		this.btnSearch.addMouseListener(this);
	}

	public String getCedulaToearch() {
		return this.searcher.getText().trim();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == btnSearch) {
			btnSearch.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(SEARCH_ICON_IN)));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == btnSearch) {
			btnSearch.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(SEARCH_ICON)));
		}
	}
}
