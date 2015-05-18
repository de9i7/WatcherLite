package com.sandbox.ivtwatcher.gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import com.sandbox.ivtwatcher.model.IvtProcess;

/**
 *
 */
public class ProcessesModel extends AbstractTableModel {
    private String[] columnNames = null;
    private Object[][] data = null;

    public ProcessesModel(List<String> columnNames, List<IvtProcess> data) {

        this.columnNames = columnNames.toArray(new String[columnNames.size()]);

        this.data = new Object[data.size()][columnNames.size()];
        for (int i = 0; i < data.size(); i++) {
            this.data[i][0] = data.get(i).getTaskId();
            this.data[i][1] = data.get(i).getName();
            this.data[i][2] = data.get(i).getPath();
        }
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
