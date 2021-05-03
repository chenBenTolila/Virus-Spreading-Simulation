package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import country.Map;


public class StatisticsWindow extends JFrame {
	/**
     * default constructor
    */
	public StatisticsWindow(Map m)
    {
		super("Statistics Window"); 
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		createFilterW();
		createTableWindow(m);
		createButtonOptions();
		this.setSize(1000,1000);  
	    this.setLocationRelativeTo(null);
		this.pack();
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
		JComboBox<String> cb = new JComboBox<String>(colSelect);
		
		JLabel l= new JLabel("Enter filter words:");
		m_filterW = new JTextField();
		m_filterW.setToolTipText("Filter Name Column");
        m_filterW.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { newFilter(); }
            public void removeUpdate(DocumentEvent e) { newFilter(); }
            public void changedUpdate(DocumentEvent e) { newFilter(); }});
	    p.add(cb);  
		p.add(l);
	    p.add(m_filterW); 
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
                case 3: return data.getIndexPercSick(rowIndex);
                case 4: return data.getIndexNumVDoses(rowIndex);
                case 5: return data.getIndexNumDead(rowIndex);
                case 6: return data.getIndexPeopleAmount(rowIndex);
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
            return columnIndex > 1;
        }

        @Override
        public void setValueAt(Object aValue, int row, int col) {
        	/*
            Student student = data.at(row);
            switch (col) {
                case 1: student.setName((String) aValue); break;
                case 2: student.setAge((Integer) aValue); break;
                case 3: student.setDrivingLicense((Boolean) aValue); break;
            }
            */
            fireTableCellUpdated(row, col);
        }
    }
	
	
	
	public void createTableWindow(Map m) 
	{   
        StatisticModel model = new StatisticModel(m);
        m_jt = new JTable(model);
        m_jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m_jt.setPreferredScrollableViewportSize(new Dimension(500, 70));
        m_jt.setFillsViewportHeight(true);
        m_jt.setRowSorter(sorter = new TableRowSorter<StatisticModel>(model));
        this.add(new JScrollPane(m_jt));
        //this.add(m_jt);
    }

    private void newFilter() {
        try {
            sorter.setRowFilter(RowFilter.regexFilter(m_filterW.getText(), 1));
        } catch (java.util.regex.PatternSyntaxException e) {
            // If current expression doesn't parse, don't update.
        }
    }
	
	
	
	
	private JTable m_jt = null;
	private JTextField m_filterW; // keep the data from user
	private TableRowSorter<StatisticModel> sorter;
}
