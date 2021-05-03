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
import virus.*;

public class EditMutationsWindow extends JFrame{
	

	/**
     * default constructor
    */
	public EditMutationsWindow(JFrame parent)
    {
		super("Edit Mutations");
		parent.disable();
		MutationModel model = new MutationModel();
		JTable table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		this.add(new RowedTableScroll(table, model.getArrMutation()));
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.addWindowListener(new java.awt.event.WindowAdapter() {
    	    @Override
    	    public void windowClosing(java.awt.event.WindowEvent e) {
    	        e.getWindow().dispose();
    	        parent.enable();
    	    }
    	});
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
