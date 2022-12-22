package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import valueObject.VLogin;

public class EAccount {

	private String id;
	private String pw;
	private String name;
	// ...

	public EAccount() {

	} // constructor

	public VLogin getLogin(String id, String pw) {

		VLogin vLogin = new VLogin();

		try {

			File file = new File("accounts/accounts");
			Scanner scanner = new Scanner(file);

			int isFound = 0;
			while (scanner.hasNext()) {

				this.id = scanner.next();
				this.pw = scanner.next();
				this.name = scanner.next();

				if (this.id.equals(id)) {

					++isFound;

					if (this.pw.equals(pw)) {
						++isFound;
						break;
					} // inner if

				} // outer if

			} // while

			scanner.close();

			if (isFound == 2) {
				// vLogin = new VLogin();
				vLogin.setId(this.id);
				vLogin.setPw(this.pw);
				vLogin.setName(this.name);
			} else {
				vLogin = null;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return vLogin;

	} // getLogin

}