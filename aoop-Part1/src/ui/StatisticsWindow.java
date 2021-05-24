package ui;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import country.Map;
import io.StatisticsFile;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */


public class StatisticsWindow extends JFrame {
	/**
     * default constructor
    */
	public StatisticsWindow(Map m, int rowIndex)
    {
		super("Statistics Window"); 
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		createFilterW();
		createTableWindow(m , rowIndex);
		createButtonOptions(m);
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
		String colSelect[]={"Settlement Name", "Settlement Type", "Ramzor Color", "Doses amount"};        
		JComboBox<String> cb = new JComboBox<String>(colSelect); 
		JLabel l= new JLabel("Enter filter words:");
		m_filterW = new JTextField();
		m_filterW.setToolTipText("Filter Name Column");
        m_filterW.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
            	switch(cb.getSelectedIndex())
            	{
            	case 0:
            		newFilter(0);
            		break;
            	case 1:
            		newFilter(1);
            		break;
            	case 2:
            		newFilter(2);
            		break;
            	case 3:
            		newFilter(4);
            		break;
            	} 
            }
            public void removeUpdate(DocumentEvent e) { 
            	switch(cb.getSelectedIndex())
            	{
            	case 0:
            		newFilter(0);
            		break;
            	case 1:
            		newFilter(1);
            		break;
            	case 2:
            		newFilter(2);
            		break;
            	case 3:
            		newFilter(4);
            		break;
            	} 
            }
            public void changedUpdate(DocumentEvent e) { 
            	switch(cb.getSelectedIndex())
            	{
            	case 0:
            		newFilter(0);
            		break;
            	case 1:
            		newFilter(1);
            		break;
            	case 2:
            		newFilter(2);
            		break;
            	case 3:
            		newFilter(4);
            		break;
            	} 
            }});
	    p.add(cb);  
		p.add(l);
	    p.add(m_filterW); 
		this.add(p); 
	}
	
	
	/**
	 * function for options to table
	 */
	public void createButtonOptions(Map m) {
		JPanel p = new JPanel();
		JButton save=new JButton("Save");
		JButton addSick= new JButton("Add Sick");
		JButton vaccinate=new JButton("Vaccinate");
		p.add(save);
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtom();
				// mp.repaint();   // repainting the map
			}
		});
		p.add(addSick);
		addSick.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addSickButtom(m);
				m_jt.updateUI();
				//mp.repaint();   // repainting the map
			}
		});
		p.add(vaccinate);
		vaccinate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				vaccinateButtom(m);
				//mp.repaint();   // repainting the map
			}
		});
		this.add(p);
	}
	
	/**
	 * button to add sick to settlement 
	 * @param m get map
	 */
	public void addSickButtom(Map m) {
		try {
			int row= m_jt.getSelectedRow();
			if(row != -1) {
				m.addSickByName((String)m_jt.getValueAt(row, 0),0.001);
			}
		}
		catch(Exception e1)
		{
			System.out.println("Error - a non integer value was entered");
		}
		m_jt.updateUI();
	}
	
	
	/**
	 * Bottom to save the details from table to csv file
	 */
	public void saveButtom(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(this);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    StatisticsFile.exportToCSV(m_jt, fileToSave.getAbsolutePath());
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		}
		
	}
	
	/**
	 * vaccinate Buttom to give doses to settlement
	 * @param m get map
	 */
	public void vaccinateButtom(Map m) {
		JPanel jp = new JPanel();
		JDialog jd = new JDialog();
		JLabel input = new JLabel("Enter number of vaccinate doses:");
		jp.add(input);
		JTextField numDoses = new JTextField();
		numDoses.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(numDoses.getText());
					int row= m_jt.getSelectedRow();
					if(row != -1) {
						m.setIndexNumDoses((String)m_jt.getValueAt(row, 0), value);
					}
				}
				catch(Exception e1)
				{
					System.out.println("Error - a non integer value was entered");
				}
				m_jt.updateUI();
				jd.dispose();
			}
		});
		numDoses.setToolTipText("Fill Number of doses");
		numDoses.setMinimumSize(new Dimension(120,80));
		jp.add(numDoses);  
		jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
		jd.setLocationRelativeTo(null);
		jd.setPreferredSize(new Dimension(280,70));
		jd.setModal(true);
		jd.add(jp);
		jd.pack();
		jd.setVisible(true);
	}
	
	/**
	 * class StatisticModel to statistic table
	 */
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
                case 2: return data.getIndexColorString(rowIndex);
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
            return false;
        }

        @Override
        public void setValueAt(Object aValue, int row, int col) {
            fireTableCellUpdated(row, col);
           
        }
    }
	
	
	/**
	 * create the statistic table
	 * @param m get map
	 * @param rowIndex get index of row of settlement
	 */
	public void createTableWindow(Map m, int rowIndex) 
	{   
        StatisticModel model = new StatisticModel(m);
        m_jt = new JTable(model);
        m_jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m_jt.setPreferredScrollableViewportSize(new Dimension(750, 200));
        m_jt.setFillsViewportHeight(true);
        m_jt.setRowSorter(sorter = new TableRowSorter<StatisticModel>(model));
        this.add(new JScrollPane(m_jt));
        if(rowIndex != -1)
        	m_jt.addRowSelectionInterval(rowIndex, rowIndex);
        
    }
   /**
    * make the filter to table
    * @param column get number of column
    */
    private void newFilter(int column) {
        try {
            sorter.setRowFilter(RowFilter.regexFilter(m_filterW.getText(), column));
        } catch (java.util.regex.PatternSyntaxException e) {
            // If current expression doesn't parse, don't update.
        }
    }
	
    
    /**
     * update the information in the table
     */
	public void updateTable()
	{
		m_jt.updateUI();
	}
	
	
	/**
	 * 
	 * @param index - an index of a row we want to select in the table
	 */
	public void deselectAndSelectARow(int index)
	{
		m_jt.clearSelection();
		if(index != -1)
			m_jt.addRowSelectionInterval(index, index);
	}
	
	private JTable m_jt = null; // keep the table object
	private JTextField m_filterW; // keep the data from user
	private TableRowSorter<StatisticModel> sorter; // sort table 
}
