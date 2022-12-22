package view;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import global.Locale;

public class PAccountPanel extends JPanel {

	BoxLayout layoutManager;

	private static final long serialVersionUID = 1L;

	public PAccountPanel() {

	} // constructor

	public void showAccount(String name_) {

		JLabel name = new JLabel(Locale.LEFT_BRACKET + name_ + Locale.RIGHT_BRACKET);
		this.add(name);

		JLabel space = new JLabel(Locale.AccountPanel.HYPHEN);
		this.add(space);

		JLabel lt = new JLabel(Locale.AccountPanel.LOGIN_TIME);
		this.add(lt);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Locale.TIME_FORMAT);
		JLabel time = new JLabel(simpleDateFormat.format(new Date()));
		this.add(time);

	} // showAccount

} // class PAccountPanel