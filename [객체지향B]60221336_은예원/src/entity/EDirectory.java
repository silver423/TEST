package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import valueObject.VDirectory;
import valueObject.VLecture;

public class EDirectory {

	public Vector<VDirectory> getDirectories(String fileName) {

		Vector<VDirectory> vDirectories = new Vector<>();

		try {

			Scanner scanner = new Scanner(new File("directory/" + fileName));

			while (scanner.hasNext()) {

				VDirectory vDirectory = new VDirectory();
				vDirectory.read(scanner);
				vDirectories.add(vDirectory);

			} // while

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return vDirectories;

	} // getDirectories

	public Vector<VLecture> getLectures(String fileName) {

		Vector<VLecture> vLectures = new Vector<>();

		try {

			Scanner scanner = new Scanner(new File("directory/" + fileName));

			while (scanner.hasNext()) {

				VLecture vLecture = new VLecture();
				vLecture.read(scanner);
				vLectures.add(vLecture);

			} // while

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return vLectures;

	} // getLectures

}