package view;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.TextField;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import global.Constant;
import global.Locale;
import service.SLogin;
import valueObject.VLogin;
import view.Main.ActionHandler;

public class PLoginDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	// Frame mainFrame;
	// PLoginDialog loginDialog;
	// PAccountPanel accountPanel;
	// PCRSPanel pCRSPanel;
	SLogin sLogin;
	VLogin vLogin;
	PLoginDialog pLoginDialog;

	TextField tfId;
	JPasswordField tfPw;
	TextField tfFail;
	JButton btLogin;

	public PLoginDialog(ActionHandler actionHandler) {

		// super(parent);

		pLoginDialog = this;
		sLogin = new SLogin();

		this.setModal(true);
		this.setSize(new Dimension(Constant.LoginDialog.WIDTH, Constant.LoginDialog.HEIGHT));
		this.setLocation(Constant.LoginDialog.X, Constant.LoginDialog.Y);

		LayoutManager layoutManager = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		this.setLayout(layoutManager);

		JPanel line1 = new JPanel();
		this.add(line1);

		JPanel line2 = new JPanel();
		this.add(line2);

		JPanel line3 = new JPanel();
		this.add(line3);

		JPanel line4 = new JPanel();
		this.add(line4);

		tfFail = new TextField();
		tfFail.setColumns(22);
		tfFail.setEditable(false);
		line4.add(tfFail);

		JLabel lbId = new JLabel(Locale.LoginDialog.ID);
		line1.add(lbId);

		tfId = new TextField();
		tfId.setColumns(10); // id 입력창 10자로 제한?
		line1.add(tfId);

		JLabel lbPw = new JLabel(Locale.LoginDialog.PW);
		line2.add(lbPw);

		tfPw = new JPasswordField();
		tfPw.setColumns(10);
		line2.add(tfPw);

		btLogin = new JButton(Locale.LoginDialog.LOGIN);
		this.getRootPane().setDefaultButton(btLogin); // Enter 누르면 LOGIN 버튼 눌림
		line3.add(btLogin);

		btLogin.addActionListener(actionHandler); // 버튼에 마우스 이벤트 감지기 달기

	} // constructor

	public VLogin login() {

		String id = this.tfId.getText();
		String pw = this.tfPw.getText();
		vLogin = sLogin.login(id, pw);

		return vLogin;

	} // login

	public void showFail() {
		tfFail.setText(Locale.LoginDialog.LOGIN_FAILED);
	} // showFail

	public VLogin getVLogin() {
		return this.vLogin;
	}

}