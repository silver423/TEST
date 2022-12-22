package service;

import entity.EAccount;
import valueObject.VLogin;

public class SLogin {

	private EAccount eAccount;

	public SLogin() {
		this.eAccount = new EAccount();
	} // constructor

	public VLogin login(String id, String pw) {

		VLogin vLogin = this.eAccount.getLogin(id, pw);
		return vLogin;

	} // login

}