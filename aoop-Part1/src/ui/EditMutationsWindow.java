package ui;


import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import virus.*;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

/*
 * class Edit Mutations Window
 */
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
		
		/**
		 * create the mutatiom model
		 */
		public MutationModel() {
			m_mut = new boolean[getRowCount()][getColumnCount()];
			
			// boolean temp[] = new boolean[m_mutationNames.length];
			for(int i=0; i<getRowCount(); ++i)
			{
				/*
				if (i == 0)
					temp = new boolean[] {true, false, false};
				else if(i == 1)
					temp = ChineseVariant.getMutBool();
				else
					temp = SouthAfricanVariant.getMutBool();
				*/
				for(int j=0; j<getColumnCount(); ++j)
					if(i == j)
						m_mut[i][j] = true;
					else
						m_mut[i][j] = false;
			}
		}
		
		/**
		 * 
		 * @param index - the index of the row we want to return 
		 * @return the row we chose
		 */
		private boolean[] getRow(int index)
		{
			if (index < getRowCount() && index >= 0)
				return m_mut[index];
			else
				return null;
		}
		
		/***
		 *  returning an index of a random virus that the virus in row
		 * @param row - an index of a row
		 * @return an index of a random virus with true value in the row 
		 */
		public int randVirus(int index)
		{
			boolean row[] = getRow(index);
			if(row == null)
				return -1;
			Random rand = new Random();
			ArrayList<Integer> trueArray = new ArrayList<Integer>(row.length);
			for(int i = 0; i < row.length; ++i)
				if(row[i] == true)
					trueArray.add(i);
			return (trueArray.get(rand.nextInt(trueArray.size())));
			
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
		
		/*
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
		*/
	}
	
	
}
