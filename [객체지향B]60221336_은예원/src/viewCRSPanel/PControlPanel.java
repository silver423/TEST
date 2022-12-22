package viewCRSPanel;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import global.Locale;
import viewCRSPanel.PCRSPanel.ActionHandler;

public class PControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton buttonRight;
	private JButton buttonLeft;

	public PControlPanel(String panelID, ActionHandler actionHandler) {

		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layoutManager);

		buttonRight = new JButton(Locale.ControlPanel.BUTTON_RIGHT);
		this.buttonRight.addActionListener(actionHandler);
		this.buttonRight.setActionCommand(panelID + this.buttonRight.getText()); // 불일치를 피해라
		this.add(buttonRight);

		buttonLeft = new JButton(Locale.ControlPanel.BUTTON_LEFT);
		this.buttonLeft.addActionListener(actionHandler);
		this.buttonLeft.setActionCommand(panelID + this.buttonLeft.getText());
		this.add(buttonLeft);

	} // constructor

}