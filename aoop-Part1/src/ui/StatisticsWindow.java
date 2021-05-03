package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
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
	
	private String m_data[][]; // do function to data
	private String m_col[]={"Settlement Name","Settlemen Type","Ramzor Color", "Sick Percentage (in portion of 1)", "Doses amount", "Dead Amount", "People Amount"};     
	private JTable m_jt=null;
	private JTextField m_filterW; // keep the data from user
	private TableRowSorter<> sorter;
}
