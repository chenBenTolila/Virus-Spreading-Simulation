package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
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
		String colSelect[]={"by Ramzor Color","by num of Sick People", "by Num of Doses", "by Num of Dead people", "by Num of People"};        
		JComboBox cb= new JComboBox(colSelect);    
		JLabel l= new JLabel("Enter filter words:");
		JTextField filterW = new JTextField(); 
	    p.add(cb);  
		p.add(l);
	    p.add(filterW); 
		this.add(p); 
	}
	
	/**
	 * function for table show
	 */
	public void createTableWindow(Map m)
    {
		JPanel p = new JPanel();
		this.setLayout(new GridLayout(0, 1));
		String data[][]=m.makeData(); // do function to data
		String col[]={"Settlement Name","Settlemen kind","Ramzor Color", "Sick People", "Num Doses", "Num of Dead", "Num of People"};     
		JTable jt=new JTable(data,col);
		jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jt.setPreferredScrollableViewportSize(new Dimension(750, 200));
		jt.setFillsViewportHeight(true);

		jt.setSize(1000, 500);
		JScrollPane sp=new JScrollPane(jt);    
	    p.add(sp);
		this.add(p);

    }
	
	/**
	 * function for options to table
	 */
	public void createButtonOptions() {
		JPanel p = new JPanel();
		p.add(new JButton("Seve"));
		p.add(new JButton("Add Sick"));
		p.add(new JButton("Vaccinate"));
		this.add(p);
	}
	
	
	private JTextField filterW; // keep the data from user
}
