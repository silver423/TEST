package viewCRSPanel;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import global.Locale;
import valueObject.VLecture;
import view.PMainFrame;

public class PRegisterPanel extends JTable {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel tableModel;
	static Vector<VLecture> existLectures = new Vector<>();
	static int credit = 0;

	PMainFrame mainFrame;

	public PRegisterPanel(PMainFrame parent) {

		mainFrame = parent;

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

		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 테이블 항목 중복 선택 방지

	} // constructor

	public void addLecture(VLecture lecture) {

		try {

			int num = Integer.parseInt(lecture.getCredit());
			credit += num;

			if (credit > 20) {
				JOptionPane.showMessageDialog(null, "수강 신청 가능한 최대 학점은 20입니다.");
				credit -= num;
				return;
			} // 신청 가능한 학점을 넘기면

			Vector<String> row = new Vector<>();
			row.add(lecture.getCode());
			row.add(lecture.getLecture());
			row.add(lecture.getProfessor());
			row.add(lecture.getCredit());
			row.add(lecture.getTime());
			tableModel.addRow(row);
			existLectures.add(lecture);

			mainFrame.removeCredit();
			mainFrame.showCredit(credit); // 수강 신청한 학점에 반영

		} catch (NullPointerException e) {

			// 미선택 오류 방지

		}

	} // addLectures

	public VLecture getSelectedLecture() {

		int n = this.getSelectedRow();

		if (n == -1) {
			return null;
		} // 미선택 오류 방지

		VLecture selectedLecture = existLectures.get(n);
		tableModel.removeRow(n);
		existLectures.remove(n);

		int num = Integer.parseInt(selectedLecture.getCredit());
		credit -= num;
		mainFrame.removeCredit();
		mainFrame.showCredit(credit); // 수강 신청한 학점에 반영

		return selectedLecture;

	} // getSelectedLectures

} // class PRegisterPanel