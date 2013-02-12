package org.teipir.softeng;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public final class WareHouseFactory {
	/**
	 * @wbp.factory
	 */
	public static JTable createJTable() {
		JTable table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		return table;
	}
}