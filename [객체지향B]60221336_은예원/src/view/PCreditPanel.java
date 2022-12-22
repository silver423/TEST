package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PCreditPanel extends JPanel {

	JPanel subPanel;
	JLabel credit;

	public PCreditPanel() {

	} // constructor

	public void showCredit(int n) {

		String s = "���� ��û�� ����: " + Integer.toString(n);
		credit = new JLabel(s);

		subPanel = new JPanel();
		subPanel.add(credit);

		this.add(subPanel);

	} // showCredit

	public void removeCredit() {

		this.removeAll();
		this.updateUI();

	} // removeCredit

} // class PCreditPanel