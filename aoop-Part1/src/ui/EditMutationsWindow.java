package ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.table.AbstractTableModel;


import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import virus.*;

public class EditMutationsWindow extends JDialog{
	

	/**
     * default constructor
    */
	public EditMutationsWindow()
    {
		super((JFrame)null, "Mutations Window");
		setModalityType(ModalityType.APPLICATION_MODAL);
		MutationModel model = new MutationModel();
		JTable table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		this.add(new RowedTableScroll(table, model.getArrMutation()));
		this.pack();
		this.setModal(true);
		this.setVisible(true);

    }
	private class MutationModel extends AbstractTableModel {
		private boolean[][] m_mut;
		private final String[] m_mutationNames = {"British variant", "Chinese variant", "South African variant"};
		public String[] getArrMutation() {
			return m_mutationNames;
		}
		public MutationModel() {
			boolean temp[] = new boolean[m_mutationNames.length];
			m_mut= new boolean[getRowCount()][getColumnCount()];
			
			for(int i=0; i<getRowCount(); ++i)
			{
				if (i == 0)
					temp = BritishVariant.getMutBool();
				else if(i == 1)
					temp = ChineseVariant.getMutBool();
				else
					temp = SouthAfricanVariant.getMutBool();
				
				for(int j=0; j<getColumnCount(); ++j)
						m_mut[i][j]= temp[j];
			}
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
			setMutations(row, col);
		}
		@Override
		public Class getColumnClass(int column) { return getValueAt(0, column).getClass(); }
		
		public void setMutations(int row, int col)
		{
			switch(row)
			{
			case 0:
				if(col == 0)
					BritishVariant.editMutations("British variant", m_mut[row][col]);
				else if(col == 1)
					BritishVariant.editMutations("Chinese variant", m_mut[row][col]);
				else
					BritishVariant.editMutations("South African variant", m_mut[row][col]);
				break;
				
			case 1:
				if(col == 0)
					ChineseVariant.editMutations("British variant", m_mut[row][col]);
				else if(col == 1)
					ChineseVariant.editMutations("Chinese variant", m_mut[row][col]);
				else
					ChineseVariant.editMutations("South African variant", m_mut[row][col]);
				break;
				
			case 2:
				if(col == 0)
					SouthAfricanVariant.editMutations("British variant", m_mut[row][col]);
				else if(col == 1)
					SouthAfricanVariant.editMutations("Chinese variant", m_mut[row][col]);
				else
					SouthAfricanVariant.editMutations("South African variant", m_mut[row][col]);
				break;
			default:
			}
		}
	}
	
	
}
