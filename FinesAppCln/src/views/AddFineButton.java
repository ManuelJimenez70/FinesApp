package views;

import java.awt.event.ActionListener;

import models.Events;
import models.Fine;

public class AddFineButton extends MyServiceButton {

	private AddFineFrame addFineFrame;

	public AddFineButton(String text, ActionListener actionListener) {
		super(text, actionListener);
		setActionCommand(Events.ADD_FINE_EVENT);
		initComponents(actionListener);
	}

	private void initComponents(ActionListener actionListener) {
		this.addFineFrame = new AddFineFrame(actionListener);
	}

	public void openAddFrame() {
		addFineFrame.setVisible(true);
	}

	public void closeAddFrame() {
		addFineFrame.dispose();
	}

	public void selectEvidency() {
		addFineFrame.selectEvidency();
	}

	public Fine getNewFine() {
		return addFineFrame.getNewFine();
	}

}
