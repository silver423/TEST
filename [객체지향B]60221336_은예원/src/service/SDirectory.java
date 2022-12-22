package service;

import java.util.Vector;

import entity.EDirectory;
import valueObject.VDirectory;
import valueObject.VLecture;

public class SDirectory {

	private EDirectory eDirectory;

	public SDirectory() {

		eDirectory = new EDirectory();

	} // constructor

	public Vector<VDirectory> getDirectories(String fileName) {
		return eDirectory.getDirectories(fileName);

	} // getDirectories

	public Vector<VLecture> getLectures(String fileName) {
		return eDirectory.getLectures(fileName);
	} // getLectures

}