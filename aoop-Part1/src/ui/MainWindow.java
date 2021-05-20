package ui;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import simulation.*;
import country.Map;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

/**
 * class MainWindow
 */
public class MainWindow extends JFrame {

	BorderLayout myBorderLayout = new BorderLayout();
    
    /**
     * default constructor
     */
    public MainWindow()
    {
    	super("Main Window");
    	m =new Map(); // create an empty map
    	myBorderLayout.setHgap(10);
    	myBorderLayout.setVgap(0);
    	this.setLayout(myBorderLayout);
    	// creating the slider
    	createJSlider();
    	// creating the map panel;
    	mapP = new MapPanel(m);
    	this.add(mapP, BorderLayout.CENTER);  // need to change to a panel!!!!
    	
    	// creating the menu
    	menu = new Menu(this, m, mapP);   // creating the menu object
    	this.add(menu, BorderLayout.NORTH);
    	
 
    	this.pack();
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.addWindowListener(new java.awt.event.WindowAdapter() {
    	    @Override
    	    public void windowClosing(java.awt.event.WindowEvent e) {
    	        e.getWindow().dispose();
    	        System.out.println("Program Finished");
    	        System.exit(0);
    	    }
    	});
    	this.setVisible(true);
    }
    
    /**
     * creating the JSilder for the simulation speed
     */
    public void createJSlider()
    {
    	JPanel jp = new JPanel();
    	jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
    	jp.add(new JLabel("Simulation speed"));
        JSlider simuSpeed = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, deafultSimuSpeed);
        jp.add(simuSpeed, BorderLayout.SOUTH);
        simuSpeed.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				 
				Main.setSleepTime(simuSpeed.getValue());  // change the sleep Time of the simulation
			}
		});	
        
        //Turn on labels at major tick marks.
        simuSpeed.setMajorTickSpacing(2);
        simuSpeed.setMinorTickSpacing(1);
        simuSpeed.setPaintTicks(true);
        simuSpeed.setPaintLabels(true);
        this.add(jp, BorderLayout.SOUTH);
    }
    
    /**
     * method to repaint map after simulation
     */
    public void repaintMap()
    {
    	mapP.repaint();
    }
    
    /**
     *  update the statistic window
     */
    public void updateStatistic()
    {
    	if(menu != null)
    		menu.updateStatTable();
    }
    
    private MapPanel mapP = null; // map panel
    private Menu menu = null;  // the menu
    private Map m;
    
    // members for the slider
    private final int FPS_MIN = 1; 
    private final int FPS_MAX = 20; 
    private int deafultSimuSpeed = 10;
    		
}