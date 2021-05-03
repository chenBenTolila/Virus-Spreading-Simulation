package ui;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import simulation.*;


public class Menu extends JMenuBar {

	public Menu(JFrame parent)
	{
		createFileMenu(parent);
		createSimulationMenu();
		createHelpMenu();
	}
	
	
	/**
	 * creates the mini menu file
	 */
	public void createFileMenu(JFrame parent)
    {
		
        JMenu m1 = new JMenu("FILE");
        this.add(m1);
        
        // create File menu
        JMenuItem m11 = new JMenuItem("Load");
        JMenuItem m12 = new JMenuItem("Statistics");
        
        JMenuItem m13 = new JMenuItem("Mutations Edit");  // opening Mutation Edit as a modal
        m13.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new EditMutationsWindow(parent);
			}
		});
        
        JMenuItem m14 = new JMenuItem("Exit");   // creating Exit option
        m14.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Program Finished");
				System.exit(0);   // close the system
			}
		});
        
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        m1.add(m14);
    }
	
	/**
	 * create the mini menu simulation
	 */
	public void createSimulationMenu()
	{
		JMenu m2 = new JMenu("Simulation");
		this.add(m2);
		
     // create Simulation menu
        JMenuItem m21 = new JMenuItem("Play");
        m21.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.setStatusPlay(true);  // update the play status to true
			}
		});
       
        JMenuItem m22 = new JMenuItem("Pause");
        m22.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.setStatusPlay(false);  // update the play status to false
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
	
	/**
	 * create mini menu help
	 */
	public void createHelpMenu()
	{
		JMenu m3 = new JMenu("Help");
		this.add(m3);
		
     // create Simulation menu
        JMenuItem m31 = new JMenuItem("Help");
        m31.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createHelpDialog();	
			}
		});
        
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
		String numTicks[] = new String[21];
		for(int i = 10; i < 30; ++i)
		{
			numTicks[i-10] = String.valueOf(i);
		}
		// String[] monthStrings = getMonthStrings(); //get month names
		SpinnerListModel ticksModel = new SpinnerListModel(numTicks);
		JSpinner spinner = new JSpinner(ticksModel);
		spinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					int temp = Integer.parseInt((String)spinner.getValue());
					System.out.println(temp + " hello");
				}
				catch(Exception e1)
				{
					System.out.println("Error - a non integer value was entered");
				}
			}
		});
		JPanel jp = new JPanel();
		JLabel input = new JLabel("Enter numer of ticks per day:");
		jp.add(input);
		jp.setLayout(new BoxLayout(jp, BoxLayout.LINE_AXIS));
		jp.add(spinner);
		JDialog jd = new JDialog();
		jd.add(jp);
		jd.setModal(true);
		jd.setVisible(true);
	}
	
	/**
	 * create an about modeless dialog
	 */
	public void createAboutDialog()
	{
		JDialog dialog = new JDialog((JFrame)null, "About");
		Container dialogContainer = dialog.getContentPane();
	    dialogContainer.add(new JLabel("Authors: Chen Ben Tolila, " + "\nHadar Amsalem" + "\nCreation Date: 15/04/21"));
	    dialog.pack();
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null); // center on screen
	}
	
	/**
	 * create an help modal dialog
	 */
	public void createHelpDialog()
	{
		/*
		JDialog dialog = new JDialog((JFrame)null, "Help");
		Container dialogContainer = dialog.getContentPane();
		JOptionPane.showMessageDialog(null, " ");
		dialogContainer.setLayout(new BorderLayout());
	      dialogContainer.add(new JLabel("print help message")
	      , BorderLayout.CENTER);
	    dialog.pack();
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null); // center on screen
	      */
	    JOptionPane.showMessageDialog(null, "The program simulates the infection process of a virus that develops into different mutations.\r\n"
	    		+ "The simulation takes place on different types of settlement, and combines all the necessary information about the people in the settlement.\r\n"
	    		+ "");
	}
}
