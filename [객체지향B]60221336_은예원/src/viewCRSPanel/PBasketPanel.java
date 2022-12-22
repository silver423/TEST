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
		}; // ���̺� �� ���� ����

		this.setModel(tableModel);

		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	} // constructor

	public void addLecture(VLecture lecture) {

		try {

			for (int i = 0; i < existLectures.size(); ++i) {
				if (lecture.getCode().equals(existLectures.get(i).getCode())) {
					JOptionPane.showMessageDialog(null, "������ ���ǰ� �̸� ��⿡ �ֽ��ϴ�.");
					return;
				}
			} // �̸� ���� ���Ǹ� �� �̸� ���� �� ����

			for (int i = 0; i < PRegisterPanel.existLectures.size(); ++i) {
				if (lecture.getCode().equals(PRegisterPanel.existLectures.get(i).getCode())) {
					JOptionPane.showMessageDialog(null, "�̹� ���� ��û�� �����Դϴ�.");
					return;
				}
			} // ��û�� ���Ǹ� �̸� ���� �� ����

			Vector<String> row = new Vector<>();
			row.add(lecture.getCode());
			row.add(lecture.getLecture());
			row.add(lecture.getProfessor());
			row.add(lecture.getCredit());
			row.add(lecture.getTime());
			tableModel.addRow(row);
			existLectures.add(lecture);

		} catch (NullPointerException e) {

			// �̼��� ���� ����

		}

	} // addLectures

	public VLecture getSelectedLecture() {

		int n = this.getSelectedRow();

		if (n == -1) {
			return null;
		} // �̼��� ���� ����

		VLecture selectedLecture = existLectures.get(n);

		tableModel.removeRow(n);
		existLectures.remove(n);

		return selectedLecture;

	} // getSelectedLectures

}