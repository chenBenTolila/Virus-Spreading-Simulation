package ui;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import io.SimulationFile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import country.Map;
import simulation.*;


public class Menu extends JMenuBar {

	public Menu(JFrame parent, Map m)
	{
		createFileMenu(parent, m);
		createSimulationMenu();
		createHelpMenu();
	}
	
	
	/**
	 * creates the mini menu file
	 */
	public void createFileMenu(JFrame parent, Map m)
    {
		
        JMenu m1 = new JMenu("FILE");
        this.add(m1);
        
        // create File menu
        JMenuItem m11 = new JMenuItem("Load");
        m11.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseFile(m);
			}
		});
        JMenuItem m12 = new JMenuItem("Statistics");
        m12.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new StatisticsWindow(m);
			}
		});
        
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
	 * choose new file
	 */
	public void chooseFile(Map m){
		if(Main.getStop() || !Main.getFileLoaded())
		{
		FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
	    dialog.setMode(FileDialog.LOAD);
	    dialog.setVisible(true);
	    String file = dialog.getFile();
	    System.out.println(file + " chosen.");
	    SimulationFile.setFileName(file);
	    Main.setStatusPlay(true);
	    try {
			SimulationFile.createMap(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error with file opening");
		}
		}
		else {
			JDialog dialog = new JDialog((JFrame)null, "file error");
			Container dialogContainer = dialog.getContentPane();
		    dialogContainer.add(new JLabel("Stop the current simulation in order to load a file"));
		    dialog.pack();
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		}	
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
				System.out.println("in play");
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
				Main.setStop(true);
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
		SpinnerModel model = new SpinnerNumberModel(10, //initial value
													5,  // minimum
													20, //max
													1); //step

		JSpinner spinner = new JSpinner(model);
		spinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					int value = (Integer) spinner.getValue();
					Clock.setTicksPerDay(value);
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
		jd.setSize(80, 40);
		jd.setLocationRelativeTo(null);
		jd.add(jp);
		jd.setModal(true);
		jd.pack();
		jd.setVisible(true);
	}
	
	/**
	 * create an about modeless dialog
	 */
	public void createAboutDialog()
	{
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
		jp.add(new JLabel("Authors: Chen Ben Tolila, "));
		jp.add(new JLabel("Hadar Amsalem"));
		jp.add(new JLabel("Creation Date: 15/04/21"));
		JDialog dialog = new JDialog((JFrame)null, "About");
		/*
		Container dialogContainer = dialog.getContentPane();
	    dialogContainer.add(new JLabel("Authors: Chen Ben Tolila,\r\n" + " Hadar Amsalem" + "\nCreation Date: 15/04/21"));
	    */
		dialog.add(jp);
	    dialog.pack();
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null); // center on screen
	}
	
	/**
	 * create an help modal dialog
	 */
	public void createHelpDialog()
	{
	    JOptionPane.showMessageDialog(null, "The program simulates the infection process of a virus that develops into different mutations.\r\n"
	    		+ "The simulation takes place on different types of settlement,\r\n" + "and combines all the necessary information about the people in the settlement.\r\n"
	    		+ "");
	}
}
