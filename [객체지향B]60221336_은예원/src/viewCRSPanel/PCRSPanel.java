package viewCRSPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import global.Locale;
import valueObject.VLecture;
import view.PMainFrame;

public class PCRSPanel extends JPanel {

	PDirectoryPanel pDirectoryPanel;
	PControlPanel controlPanel1;
	PControlPanel controlPanel2;
	PBasketPanel basketPanel;
	PRegisterPanel registerPanel;
	PMainFrame mainFrame;

	BoxLayout layoutManager;

	public void showPCRSPanel(PMainFrame parent) {

		mainFrame = parent;

		ActionHandler actionHandler = new ActionHandler();

		layoutManager = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(layoutManager);

		pDirectoryPanel = new PDirectoryPanel();
		this.add(pDirectoryPanel);
		pDirectoryPanel.showPDirectory();

		controlPanel1 = new PControlPanel(Locale.ControlPanel.PANEL_ID_1, actionHandler);
		this.add(controlPanel1);

		JPanel subPanel1 = new JPanel(); // label1과 basket panel이 들어감
		layoutManager = new BoxLayout(subPanel1, BoxLayout.Y_AXIS);
		subPanel1.setLayout(layoutManager);

		JLabel label1 = new JLabel(Locale.PCRSPanel.BASKET);
		subPanel1.add(label1);

		JScrollPane scrollPane = new JScrollPane();
		basketPanel = new PBasketPanel();
		scrollPane.setViewportView(basketPanel);
		subPanel1.add(scrollPane);

		this.add(subPanel1);

		controlPanel2 = new PControlPanel(Locale.ControlPanel.PANEL_ID_2, actionHandler);
		this.add(controlPanel2);

		JPanel subPanel2 = new JPanel(); // label2, label3과 register panel이 들어감
		layoutManager = new BoxLayout(subPanel2, BoxLayout.Y_AXIS);
		subPanel2.setLayout(layoutManager);

		JLabel label2 = new JLabel(Locale.PCRSPanel.REGISTER);
		subPanel2.add(label2);

		scrollPane = new JScrollPane();
		registerPanel = new PRegisterPanel(mainFrame);
		scrollPane.setViewportView(registerPanel);
		subPanel2.add(scrollPane);

		this.add(subPanel2);

	} // showPCRSPanel

	private void moveFromLectureToBasket() {
		VLecture lecture = this.pDirectoryPanel.getSelectedLecture();
		this.basketPanel.addLecture(lecture);
	}

	private void moveFromBasketToLecture() {
		VLecture lecture = this.basketPanel.getSelectedLecture();
		this.pDirectoryPanel.addLecture(lecture);
	}

	private void moveFromBasketToRegister() {
		VLecture lecture = this.basketPanel.getSelectedLecture();
		this.registerPanel.addLecture(lecture);
	}

	private void moveFromRegisterToBasket() {
		VLecture lecture = this.registerPanel.getSelectedLecture();
		this.basketPanel.addLecture(lecture);
	}

	public class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			if (event.getActionCommand()
					.compareTo(Locale.ControlPanel.PANEL_ID_1 + Locale.ControlPanel.BUTTON_RIGHT) == 0) {

				moveFromLectureToBasket();

			} else if (event.getActionCommand()
					.compareTo(Locale.ControlPanel.PANEL_ID_1 + Locale.ControlPanel.BUTTON_LEFT) == 0) {

				moveFromBasketToLecture();

			} else if (event.getActionCommand()
					.compareTo(Locale.ControlPanel.PANEL_ID_2 + Locale.ControlPanel.BUTTON_RIGHT) == 0) {

				moveFromBasketToRegister();

			} else if (event.getActionCommand()
					.compareTo(Locale.ControlPanel.PANEL_ID_2 + Locale.ControlPanel.BUTTON_LEFT) == 0) {

				moveFromRegisterToBasket();

			}

		} // actionPerformed

	} // class ActionHandler

} // class PCRSPanel