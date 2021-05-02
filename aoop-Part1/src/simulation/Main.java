package simulation;

import country.*;
import io.SimulationFile;
import ui.StatisticsWindow;

import java.awt.Graphics;
import java.io.*;
import javax.swing.*;   // for GUI
import ui.*;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public class Main {

	/**
	 * class main
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		Map m = new Map();   // create an empty map
		SimulationFile.createMap(m);   // read the map from file
		m.addSickToMap();   // initialize the population with 1% of sick people
		
		createSimu(m);
		// MainWindow mw = new MainWindow();
		//StatisticsWindow s = new StatisticsWindow(m);   // need to remove!!!!!!!!!!
		new MapPanel(m);
		}
		catch (FileNotFoundException ex1) {    // catch errors related to files
            System.out.println("File not found");
        }catch (IOException ex2) {
        	System.out.println("Error in file");
        }
	}
	
	
	public static void createSimu(Map m)
	{
		int i = 1;
		while(statusPlay)  // do the simulation 5 times
		{
			if(stop == false)
				return;
			// first phase
			System.out.println("************ Simulation " + (i) + " ************");
			m.contagionSimu();
			System.out.println(m.toString());
			
			// second phase
			m.sickToConvalecent();
			System.out.println(m.toString());
			// third phase
		
			//fourth phase
			
			try {
				Thread.sleep(1000 * sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Failed to sleep betweem simulations");
			}
			++i;
			if(i == 5)
				statusPlay = false;
		}
	}
	
	
	
	/**
	 * the method sets a new sleep time
	 * @param st  - sleep time
	 */
	public static void setSleepTime(int st)
	{
		sleepTime = st;
	}
	
	/**
	 * the method sets the value of file loaded indicator
	 * @param val - a boolean value
	 */
	public static void SetfileLoaded(boolean val)
	{
		fileLoaded = val;
	}
	
	
	/**
	 * the method sets the value of play status indicator
	 * @param val - a boolean value
	 */
	public static void setStatusPlay(boolean val)
	{
		statusPlay = val;
	}	
	
	/**
	* the method sets the value of stop indicator
	 * @param val - a boolean value
	 */
	public static void setStop(boolean val)
	{
		stop = val;
	}
	
	private static int sleepTime;   // will keep the speed of the simulation
	private static boolean fileLoaded = false;   // will keep true if a file is loaded
	private static boolean statusPlay = true;    // will keep if the simulation are played or paused
	private static boolean stop = false;   // will keep true is the simulation are paused
}


