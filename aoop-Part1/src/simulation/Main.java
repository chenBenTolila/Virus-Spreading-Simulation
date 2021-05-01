package simulation;

import country.*;
import io.SimulationFile;
import ui.StatisticsWindow;

import java.awt.Graphics;
import java.io.*;
import javax.swing.*;   // for GUI
import ui.MapPanel;

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
		
		//StatisticsWindow s = new StatisticsWindow(m);   // need to remove!!!!!!!!!!
		new MapPanel();
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
	
	public static void SetfileLoaded(boolean val)
	{
		fileLoaded = val;
	}
	
	public static void setStatusPlay(boolean val)
	{
		statusPlay = val;
	}	
	
	public static void setPause(boolean val)
	{
		pause = val;
	}
	
	private static int sleepTime;   // will keep the speed of the simulation
	private static boolean fileLoaded = false;   // will keep true if a file is loaded
	private static boolean statusPlay = true;    // will keep if the simulation are played or paused
	private static boolean pause = false;   // will keep true is the simulation are paused
}


