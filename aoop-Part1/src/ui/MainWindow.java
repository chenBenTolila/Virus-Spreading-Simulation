package ui;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import simulation.*;
import country.Map;


public class MainWindow extends JFrame {
    BorderLayout myBorderLayout = new BorderLayout();
    
    /**
     * default constructor
     */
    public MainWindow(Map m)
    {
    	super("Main Window");
    	myBorderLayout.setHgap(10);
    	myBorderLayout.setVgap(0);
    	this.setLayout(myBorderLayout);
    	// creating the slider
    	createJSlider();
    	// creating the map panel;
    	MapPanel mp = new MapPanel(m);
    	this.add(mp, BorderLayout.CENTER);  // need to change to a panel!!!!
    	
    	// creating the menu
    	Menu menu = new Menu(this, m, mp);   // creating the menu object
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
        JSlider simuSpeed = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, Main.getSleepTime());
        jp.add(simuSpeed, BorderLayout.SOUTH);
        simuSpeed.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				 
				Main.setSleepTime(simuSpeed.getValue());  // change the sleep Time of the simulation
			}
		});	
        
        //Turn on labels at major tick marks.
        simuSpeed.setMajorTickSpacing(10);
        simuSpeed.setMinorTickSpacing(1);
        simuSpeed.setPaintTicks(true);
        simuSpeed.setPaintLabels(true);
        this.add(jp, BorderLayout.NORTH);
    }
    
    
    // members for the slider
    private static final int FPS_MIN = 1;
    private static final int FPS_MAX = 20;
}