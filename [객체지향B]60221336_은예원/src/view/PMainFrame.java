package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import global.Constant;
import viewCRSPanel.PCRSPanel;

public class PMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	static PCreditPanel creditPanel;
	PAccountPanel accountPanel;
	PCRSPanel pCRSPanel;
	BorderLayout layoutManager;

	public PMainFrame() {

		// attributes(속성)
		// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(Constant.CMainFrame.WIDTH, Constant.CMainFrame.HEIGHT); // 상수를 직접 쓰지 마라.
		this.setLocation(Constant.CMainFrame.X, Constant.CMainFrame.Y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// components
		layoutManager = new BorderLayout();
		this.setLayout(layoutManager); // 바깥에서 세팅하지 말 것

		this.accountPanel = new PAccountPanel();
		this.add(this.accountPanel, BorderLayout.NORTH);

		this.pCRSPanel = new PCRSPanel();
		this.add(this.pCRSPanel, BorderLayout.CENTER); // 자식 등록

		creditPanel = new PCreditPanel();
		this.add(creditPanel, BorderLayout.SOUTH);

	} // constructor

	public void initialize(String s) {

		accountPanel.showAccount(s);
		creditPanel.showCredit(0);
		pCRSPanel.showPCRSPanel(this);
		this.setVisible(true);

	} // initialize

	public void removeCredit() {

		creditPanel.removeCredit();

	} // removeCredit

	public void showCredit(int n) {

		creditPanel.showCredit(n);

	} // changeCredit

} // class PMainFrame