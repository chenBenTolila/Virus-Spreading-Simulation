package simulation;

import country.*;
import io.SimulationFile;
import java.io.*;


/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */


// for testing
import location.*;
import population.*;
import virus.*;


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
		for(int i =0; i < 5; ++i)  // do the simulation 5 times
		{
			System.out.println("************ Simulation " + (i+1) + "************");
			m.contagionSimu();
			System.out.println(m.toString());
		}  
		}
		catch (FileNotFoundException ex1) {    // catch errors related to files
            System.out.println("File not found");
        }catch (IOException ex2) {
        	System.out.println("Error in file");
        }
		
		// tryCode();   // checking the code
	}
	
	/**
	 * testing the code
	 */
	public static void tryCode()
	{
		Point point = new Point(0,0);
		Size size = new Size(10,10);
		Location loc = new Location(point, size);
		
		Kibbutz Ashdod = new Kibbutz("Ashdo", loc, RamzorColor.GREEN);
		Sick ploni = new Sick(100, point, Ashdod, Clock.now(), new SouthAfricanVariant());
		Ashdod.addPerson(ploni);
		for(int i = 0; i < 15; ++i)
			Clock.nextTick();
		
		System.out.println(ploni.getSicknessDuration());
		System.out.println(Ashdod.toString());
		System.out.println(ploni.tryToDie());
		System.out.println(Ashdod.toString());
		Ashdod.calculateRamzorGrade();
		
		Moshav Ash = new Moshav("hadar", loc, RamzorColor.GREEN);
		System.out.println(Ashdod.toString());
		System.out.println(Ash.toString());
		Ashdod.transferPerson(ploni, Ash);
		System.out.println(Ashdod.toString());
		System.out.println(Ash.toString());
		
		
		System.out.println(ploni.recover().toString());
		
	}
	
}
