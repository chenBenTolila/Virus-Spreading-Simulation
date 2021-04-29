package simulation;

import country.*;
import io.SimulationFile;
import java.io.*;
import javax.swing.*;   // for GUI


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
		for(int i =0; i < 5; ++i)  // do the simulation 5 times
		{
			System.out.println("************ Simulation " + (i+1) + " ************");
			m.contagionSimu();
			System.out.println(m.toString());
		}  
		}
		catch (FileNotFoundException ex1) {    // catch errors related to files
            System.out.println("File not found");
        }catch (IOException ex2) {
        	System.out.println("Error in file");
        }
	}
}
