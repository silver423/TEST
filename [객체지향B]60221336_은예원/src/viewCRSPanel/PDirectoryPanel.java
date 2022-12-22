package viewCRSPanel;

import java.awt.LayoutManager;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import global.Locale;
import service.SDirectory;
import valueObject.VDirectory;
import valueObject.VLecture;

public class PDirectoryPanel extends JPanel {

	LayoutManager layoutManager;
	private PDirectory campusTable;
	private PDirectory collegeTable;
	private PDirectory departmentTable;
	private PLecture lectureTable;
	private ListSelectionHandler listSelectionHandler;

	public void showPDirectory() {

		layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layoutManager);

		listSelectionHandler = new ListSelectionHandler();

		JPanel subPanel1 = new JPanel();
		layoutManager = new BoxLayout(subPanel1, BoxLayout.X_AXIS);
		subPanel1.setLayout(layoutManager);

		JScrollPane scrollPane = new JScrollPane();
		campusTable = new PDirectory(Locale.DirectoryPanel.CAMPUS);
		campusTable.getSelectionModel().addListSelectionListener(listSelectionHandler);
		campusTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 테이블의 항목 중복 선택 방지
		scrollPane.setViewportView(campusTable);
		subPanel1.add(scrollPane);

		scrollPane = new JScrollPane();
		collegeTable = new PDirectory(Locale.DirectoryPanel.COLLEGE);
		collegeTable.getSelectionModel().addListSelectionListener(listSelectionHandler);
		collegeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(collegeTable);
		subPanel1.add(scrollPane);

		scrollPane = new JScrollPane();
		departmentTable = new PDirectory(Locale.DirectoryPanel.MAJOR);
		departmentTable.getSelectionModel().addListSelectionListener(listSelectionHandler);
		departmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(departmentTable);
		subPanel1.add(scrollPane);

		this.add(subPanel1); // Panel에 Panel 추가 가능

		JPanel subPanel2 = new JPanel(); // Lecture Panel 들어감
		layoutManager = new BoxLayout(subPanel2, BoxLayout.Y_AXIS);
		subPanel2.setLayout(layoutManager);

		scrollPane = new JScrollPane();
		lectureTable = new PLecture();
		lectureTable.getSelectionModel().addListSelectionListener(listSelectionHandler);
		lectureTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(lectureTable);
		subPanel2.add(scrollPane);

		this.add(subPanel2);

		updateTable(null);

	} // showPDirectory

	private class PDirectory extends JTable {

		private static final long serialVersionUID = 1L;

		private DefaultTableModel tableModel;
		Vector<VDirectory> vDirectories;

		public PDirectory(String headerName) {

			Vector<String> header = new Vector<>();
			header.add(headerName);

			tableModel = new DefaultTableModel(header, 0) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			}; // 테이블 셀 수정 금지

			this.setModel(tableModel);

		} // constructor

		public String setData(String fileName) {

			SDirectory sDirectory = new SDirectory();
			vDirectories = sDirectory.getDirectories(fileName);

			tableModel.setRowCount(0); // 테이블 초기화

			for (VDirectory vDirectory : vDirectories) {
				Vector<String> row = new Vector<>();
				row.add(vDirectory.getName());
				tableModel.addRow(row);
			}

			this.setRowSelectionInterval(0, 0); // 첫 번째 열이 자동으로 선택됨
			return fileName = vDirectories.get(0).getFileName();

		} // setData

		public Vector<VDirectory> getVDirectories() {
			return vDirectories;
		} // getVDirectories

	} // class PDirectory

	private class PLecture extends JTable {

		private static final long serialVersionUID = 1L;

		private DefaultTableModel tableModel;
		Vector<VLecture> vLectures;

		public PLecture() {

			Vector<String> header = new Vector<>();
			header.add(Locale.Table.CODE);
			header.add(Locale.Table.LECTURE);
			header.add(Locale.Table.PROFESSOR);
			header.add(Locale.Table.CREDIT);
			header.add(Locale.Table.TIME);

			tableModel = new DefaultTableModel(header, 0) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			}; // 테이블 셀 수정 금지

			this.setModel(tableModel);

		} // constructor

		public void setData(String fileName) {

			SDirectory sDirectory = new SDirectory();
			vLectures = sDirectory.getLectures(fileName);

			tableModel.setRowCount(0); // 테이블 초기화

			for (VLecture vLecture : vLectures) {
				Vector<String> row = new Vector<>();
				row.add(vLecture.getCode());
				row.add(vLecture.getLecture());
				row.add(vLecture.getProfessor());
				row.add(vLecture.getCredit());
				row.add(vLecture.getTime());
				tableModel.addRow(row);
			}

			this.setRowSelectionInterval(0, 0); // 첫 번째 열 자동 선택

		} // setData

		public Vector<VLecture> getVLectures() {
			return vLectures;
		} // getVLectures

		public DefaultTableModel getTableModel() {
			return tableModel;
		} // getTableModel

	} // class PLecture

	private void updateTable(Object object) {

		int n;
		String fileName = null;

		if (object == null) { // 처음에는 null로

			fileName = "root";
			fileName = campusTable.setData(fileName);
			// campusTable.setRowSelectionInterval(0, 0); // 첫 번째 열 자동 선택
			fileName = collegeTable.setData(fileName);
			fileName = departmentTable.setData(fileName);
			lectureTable.setData(fileName);

		} else if (object == campusTable.getSelectionModel()) {

			n = campusTable.getSelectedRow();

			if (n == -1) {
				return;
			} // 간접 선택 호출 막기 위해서

			fileName = campusTable.getVDirectories().get(n).getFileName();
			fileName = collegeTable.setData(fileName);
			fileName = departmentTable.setData(fileName);
			lectureTable.setData(fileName);
			// campusTable.getSelectionModel().clearSelection();

		} else if (object == collegeTable.getSelectionModel()) {

			n = collegeTable.getSelectedRow();

			if (n == -1) {
				return;
			}

			fileName = collegeTable.getVDirectories().get(n).getFileName();
			fileName = departmentTable.setData(fileName);
			lectureTable.setData(fileName);

		} else if (object == departmentTable.getSelectionModel()) {

			n = departmentTable.getSelectedRow();

			if (n == -1) {
				return;
			}

			fileName = departmentTable.getVDirectories().get(n).getFileName();
			lectureTable.setData(fileName);

		} else if (object == lectureTable.getSelectionModel()) {

		}

	} // updateTable

	public VLecture getSelectedLecture() {

		int n = lectureTable.getSelectedRow();

		if (n == -1) {
			return null;
		}

		VLecture selectedLecture = lectureTable.getVLectures().get(n);

		return selectedLecture;

	} // getSelectedLectures

	public void addLecture(VLecture lecture) {

	} // addLectures

	private class ListSelectionHandler implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent event) {

			if (!(event.getValueIsAdjusting())) {

				updateTable(event.getSource());

			} else {

			}

		} // valueChanged

	} // class ListSelectionHandler

} // class PDirectoryPanel