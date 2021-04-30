package ui;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import simulation.*;


public class Menu extends JMenuBar {

	public Menu()
	{
		
	}
	
	public void createFileMenu()
    {
        JMenu m1 = new JMenu("FILE");
        this.add(m1);
        
        // create File menu
        JMenuItem m11 = new JMenuItem("Load");
        JMenuItem m12 = new JMenuItem("Statistics");
        JMenuItem m13 = new JMenuItem("Mutations Edit");
        JMenuItem m14 = new JMenuItem("Exit");
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        m1.add(m14);
    }
	public void createSimulationMenu()
	{
		JMenu m2 = new JMenu("Simulation");
		this.add(m2);
		
     // create Simulation menu
        JMenuItem m21 = new JMenuItem("Play");
        m21.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
       
        JMenuItem m22 = new JMenuItem("Pause");
        m22.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        JMenuItem m23 = new JMenuItem("Stop");
        m23.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        JMenuItem m24 = new JMenuItem("Set Ticks Per Day");
        m24.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createSetTicksDialog();
			}
		});
        
        m2.add(m21);
        m2.add(m22);
        m2.add(m23);
        m2.add(m24);
	}  
	
	public void createHelpMeu()
	{
		JMenu m3 = new JMenu("Help");
		this.add(m3);
		
     // create Simulation menu
        JMenuItem m31 = new JMenuItem("Help");
        JMenuItem m32 = new JMenuItem("About");
        m32.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createAboutDialog();
			}
		});
        
        m3.add(m31);
        m3.add(m32);
    }
	
	/**
	 * create a dialog that takes as input a number of ticks per day
	 */
	public void createSetTicksDialog()
	{
		String input = JOptionPane.showInputDialog("Enter numer of ticks per day:");
		Clock.setTicksPerDay(Integer.parseInt(input));
	}
	
	public void createAboutDialog()
	{
		JDialog dialog = new JDialog((JFrame)null, "About");
		Container dialogContainer = dialog.getContentPane();
		dialogContainer.setLayout(new BorderLayout());
	      dialogContainer.add(new JLabel("Authors: Chen Ben Tolila,\\nHadar Amsalem\\nCreation Date: 15/04/21")
	      , BorderLayout.CENTER);
		dialog.pack();
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null); // center on screen
	}
}
