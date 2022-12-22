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

		// login Dialog가 종료되어야 run() 실행됨

		try {

			String s = loginDialog.getVLogin().getName();

			PMainFrame mainFrame = new PMainFrame();
			mainFrame.initialize(s);

		} catch (Exception e) {
			System.exit(0); // 로그인 안 하고 로그인 다이얼로그 X 버튼 눌렀을 시
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
		// main.delete(); 자바는 이것을 자동으로 함

	} // main

} // class Main