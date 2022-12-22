package viewCRSPanel;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import global.Locale;
import valueObject.VLecture;

public class PBasketPanel extends JTable {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel tableModel;
	Vector<VLecture> existLectures = new Vector<>();

	public PBasketPanel() {

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

		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	} // constructor

	public void addLecture(VLecture lecture) {

		try {

			for (int i = 0; i < existLectures.size(); ++i) {
				if (lecture.getCode().equals(existLectures.get(i).getCode())) {
					JOptionPane.showMessageDialog(null, "동일한 강의가 미리 담기에 있습니다.");
					return;
				}
			} // 미리 담은 강의를 또 미리 담을 수 없음

			for (int i = 0; i < PRegisterPanel.existLectures.size(); ++i) {
				if (lecture.getCode().equals(PRegisterPanel.existLectures.get(i).getCode())) {
					JOptionPane.showMessageDialog(null, "이미 수강 신청한 강의입니다.");
					return;
				}
			} // 신청한 강의를 미리 담을 수 없음

			Vector<String> row = new Vector<>();
			row.add(lecture.getCode());
			row.add(lecture.getLecture());
			row.add(lecture.getProfessor());
			row.add(lecture.getCredit());
			row.add(lecture.getTime());
			tableModel.addRow(row);
			existLectures.add(lecture);

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

		return selectedLecture;

	} // getSelectedLectures

}