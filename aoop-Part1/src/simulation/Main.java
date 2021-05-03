package simulation;

import country.*;
import location.Location;
import location.Point;
import location.Size;
import population.Healthy;
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
			test(m);
			
			MainWindow mw = new MainWindow(m);
			while(true)
			{
				//System.out.print("");
				if(stop == false && fileLoaded == true) {
					createSimu(m);
				}
			}  
		}
		catch (Exception ex1) {    // catch errors related to files
            System.out.println("Error!!!");
		}
		 
		 
	}
	
	public static void test(Map m) {
		String name="ash";
		Location loc=new Location(new Point(50,90),new Size(60,30));
		RamzorColor rc=RamzorColor.GREEN;
		int mp=600; 
		City a= new City(name, loc, rc, mp);
		Healthy h1= new Healthy(16 ,new Point(55,80),a);
		Healthy h2= new Healthy(26 ,new Point(45,70),a);
		a.intializeSickPeople(0.6);
		System.out.println(a.getSetColor().toString());
		a.calculateRamzorGrade();
		System.out.println(a.getSetColor().toString());
		
	}
	
	
	public static void createSimu(Map m)
	{
		int i = 1;
		while(true)  // do the simulation 
		{
			System.out.println("");
			if(stop == true)
				return;
			if(statusPlay == true) {
				// first phase
				System.out.println("************ Simulation " + (i) + " ************");
				m.contagionSimu();
				
				// second phase
				m.sickToConvalecent();
				
				// third phase
				m.transferSettlement();
				
				// fourth phase
				m.vaccinatedSettlement();
				System.out.println(m.toString());
				Clock.nextTick();
				try {
					Thread.sleep(1000 * sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("Failed to sleep betweem simulations");
				}
				++i;
			}	
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
	
	public static boolean getStatusPlay() {
		return statusPlay;
	}
	
	public static boolean getStop() {
		return stop;
	}
	
	public static boolean getFileLoaded() {
		return fileLoaded;
	}
	
	private static int sleepTime;   // will keep the speed of the simulation
	private static boolean fileLoaded = false;   // will keep true if a file is loaded
	private static boolean statusPlay = true;    // will keep if the simulation are played or paused
	private static boolean stop = false;   // will keep true is the simulation are paused
}


