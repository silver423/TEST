package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import valueObject.VLogin;

public class Main {

	private PLoginDialog loginDialog;
	// private ActionHandler actionHandler;

	public Main() {

	} // constructor

	private void initialize() {

		ActionHandler actionHandler = new ActionHandler();

		this.loginDialog = new PLoginDialog(actionHandler);
		loginDialog.setVisible(true);

	} // initialize

	private void run() {

		// login Dialog�� ����Ǿ�� run() �����

		try {

			String s = loginDialog.getVLogin().getName();

			PMainFrame mainFrame = new PMainFrame();
			mainFrame.initialize(s);

		} catch (Exception e) {
			System.exit(0); // �α��� �� �ϰ� �α��� ���̾�α� X ��ư ������ ��
		}

	} // run

	private void finish() {

	} // finish

	class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			VLogin vLogin = loginDialog.login();

			if (vLogin == null) {
				loginDialog.showFail();
			} else {
				loginDialog.dispose();
			}

		}

	} // class ActionHandler

	public static void main(String[] args) {

		Main main = new Main();
		main.initialize();
		main.run();
		main.finish();
		// main.delete(); �ڹٴ� �̰��� �ڵ����� ��

	} // main

} // class Main