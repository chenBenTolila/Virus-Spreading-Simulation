package ui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import country.Map;

public class EditMutationsWindow extends JFrame{
	

	/**
     * default constructor
    */
	public EditMutationsWindow()
    {
		super("Edit Mutations");
		MutationModel model = new MutationModel();
		JTable table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		this.add(new RowedTableScroll(table, model.getArrMutation()));
		this.pack();
		this.setVisible(true);

    }
	private class MutationModel extends AbstractTableModel {
		private boolean[][] m_mut;
		private final String[] m_mutationNames = {"British variant", "Chinese variant", "South African variant"};
		public String[] getArrMutation() {
			return m_mutationNames;
		}
		public MutationModel() {
			m_mut= new boolean[getRowCount()][getColumnCount()];
			for(int i=0;i<getRowCount();++i)
				for(int j=0;j<getColumnCount();++j)
					if(i==j)
						m_mut[i][j]=true;
					else
						m_mut[i][j]=false;
		}
		@Override
		public int getRowCount() { return m_mutationNames.length; }
		@Override
		public int getColumnCount() { return m_mutationNames.length; }
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return m_mut[rowIndex][columnIndex];
		}
		
		@Override
		public String getColumnName(int column)
		{ 
			return m_mutationNames[column]; 
			}
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex >= 0;
		}
		@Override
		public void setValueAt(Object aValue, int row, int col) {
		m_mut[row][col]=((Boolean) aValue); 
		fireTableCellUpdated(row, col);
		}
		@Override
		public Class getColumnClass(int column) { return getValueAt(0, column).getClass(); }

	}
	
	
}
