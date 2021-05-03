package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import TableMVCExample.Course;
import TableMVCExample.Student;
import country.Map;


public class StatisticsWindow extends JFrame {

	/**
     * default constructor
    */
	public StatisticsWindow(Map m)
    {
		super("Statistics Window"); 
	    this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
	    m_data=m.makeData(); // do function to data  
		m_jt=new JTable(m_data,m_col);
		createFilterW();
		createTableWindow(m);
		createButtonOptions();
		this.setSize(800,500);  
	    this.setLocationRelativeTo(null);
		this.setVisible(true);
    }
	/**
	 * function for filter part
	 */
	public void createFilterW() {
		JPanel p = new JPanel();
		BoxLayout bl=new BoxLayout(p, BoxLayout.LINE_AXIS);
		p.setLayout(bl);
		String colSelect[]={"Ramzor Color","Settlemen Type", "Doses amount", "Sick Percentage (in portion of 1)"};        
		JComboBox<String> cb = new JComboBox(colSelect);   
		JLabel l= new JLabel("Enter filter words:");
		JTextField filterW = new JTextField(); 	
	    p.add(cb);  
		p.add(l);
	    p.add(filterW); 
		this.add(p); 
	}
	
	private void newFilter() {
        try {
            sorter.setRowFilter(RowFilter.regexFilter(m_filterW.getText(), 1));
        } catch (java.util.regex.PatternSyntaxException e) {
            // If current expression doesn't parse, don't update.
        }
    }
	/**
	 * function for table show
	 */
	public void createTableWindow(Map m)
    {
		JPanel p = new JPanel();
		this.setLayout(new GridLayout(0, 1));
		String data[][]=m.makeData(); // do function to data
		String col[]={"Settlement Name","Settlemen Type","Ramzor Color", "Sick Percentage (in portion of 1)", "Doses amount", "Dead Amount", "People Amount"};     
		JTable jt=new JTable(data,col);
		jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jt.setPreferredScrollableViewportSize(new Dimension(780, 300));
		jt.setFillsViewportHeight(true);

		jt.setSize(1200, 800);
		JScrollPane sp=new JScrollPane(jt);    

		jt.setSize(1000, 500); 
		this.add(new JScrollPane(jt));

	    p.add(sp);
		this.add(p);

    }
	
	/**
	 * function for options to table
	 */
	public void createButtonOptions() {
		JPanel p = new JPanel();
		p.add(new JButton("Save"));
		p.add(new JButton("Add Sick"));
		p.add(new JButton("Vaccinate"));
		this.add(p);
	}
	
	
	
	
	
	private static class StatisticModel extends AbstractTableModel {
        private Map data;
        private final String[] columnNames = {"Settlement Name","Settlemen Type","Ramzor Color", "Sick Percentage (in portion of 1)", "Doses amount", "Dead Amount", "People Amount"};

        public StatisticModel(Map data) {
            this.data = data;
        }

        @Override
        public int getRowCount() {
            return data.getNumOfSettlement();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0: return data.getIndexSettName(rowIndex);
                case 1: return data.getIndexSettType(rowIndex);
                case 2: return data.getIndexColor(rowIndex);
                case 3: return data.getIndexNumSick(rowIndex);
                case 4: return data.getIndexNumVDoses(columnIndex);
                case 5: return data.getIndexNumDead(columnIndex);
                case 6:
            }
            return null;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex > 0;
        }

        @Override
        public void setValueAt(Object aValue, int row, int col) {
            Student student = data.at(row);
            switch (col) {
                case 1: student.setName((String) aValue); break;
                case 2: student.setAge((Integer) aValue); break;
                case 3: student.setDrivingLicense((Boolean) aValue); break;
            }
            fireTableCellUpdated(row, col);
        }
    }
	
	
	
	
	
	// private String m_data[][]; // do function to data
	// private String m_col[]={"Settlement Name","Settlemen Type","Ramzor Color", "Sick Percentage (in portion of 1)", "Doses amount", "Dead Amount", "People Amount"};     
	private JTable m_jt=null;
	private JTextField m_filterW; // keep the data from user
	private TableRowSorter<> sorter;
}
