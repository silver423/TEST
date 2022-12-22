package valueObject;

import java.util.Scanner;

public class VLecture {

	private String code;
	private String lecture;
	private String professor;
	private String credit;
	private String time;

	public String getCode() {
		return code;
	}

	public String getLecture() {
		return lecture;
	}

	public String getProfessor() {
		return professor;
	}

	public String getCredit() {
		return credit;
	}

	public String getTime() {
		return time;
	}

	public void read(Scanner scanner) {

		this.code = scanner.next();
		this.lecture = scanner.next();
		this.professor = scanner.next();
		this.credit = scanner.next();
		this.time = scanner.next();

	} // read

}