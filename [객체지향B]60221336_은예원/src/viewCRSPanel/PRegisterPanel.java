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
		}; // ���̺� �� ���� ����

		this.setModel(tableModel);

		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // ���̺� �׸� �ߺ� ���� ����

	} // constructor

	public void addLecture(VLecture lecture) {

		try {

			int num = Integer.parseInt(lecture.getCredit());
			credit += num;

			if (credit > 20) {
				JOptionPane.showMessageDialog(null, "���� ��û ������ �ִ� ������ 20�Դϴ�.");
				credit -= num;
				return;
			} // ��û ������ ������ �ѱ��

			Vector<String> row = new Vector<>();
			row.add(lecture.getCode());
			row.add(lecture.getLecture());
			row.add(lecture.getProfessor());
			row.add(lecture.getCredit());
			row.add(lecture.getTime());
			tableModel.addRow(row);
			existLectures.add(lecture);

			mainFrame.removeCredit();
			mainFrame.showCredit(credit); // ���� ��û�� ������ �ݿ�

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

		int num = Integer.parseInt(selectedLecture.getCredit());
		credit -= num;
		mainFrame.removeCredit();
		mainFrame.showCredit(credit); // ���� ��û�� ������ �ݿ�

		return selectedLecture;

	} // getSelectedLectures

} // class PRegisterPanel