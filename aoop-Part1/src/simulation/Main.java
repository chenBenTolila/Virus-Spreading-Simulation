package simulation;

import country.Map;
import country.RamzorColor;
import io.SimulationFile;
import java.io.*;


/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */


// try
import location.*;
import population.*;
import country.City;
import country.Moshav;
import virus.*;


public class Main {

	/**
	 * class main
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		Map m = new Map();
		SimulationFile.createMap(m);
		m.addSickToMap();
		for(int i =0; i < 5; ++i)  // 5 times
			m.contagionSimu();
		
		// System.out.println(m.toString());
		}
		catch (FileNotFoundException ex1) {
            System.out.println("File not found");
        }catch (IOException ex2) {
        	System.out.println("Error in file");
        }
		
		tryCode();
	}
	
	public static void tryCode()
	{
		Point point = new Point(0,0);
		Size size = new Size(10,10);
		Location loc = new Location(point, size);
		
		City Ashdod = new City("Ashdo", loc, RamzorColor.GREEN);
		Sick ploni = new Sick(100, point, Ashdod, Clock.now(), new SouthAfricanVariant());
		Ashdod.addPerson(ploni);
		for(int i = 0; i < 15; ++i)
			Clock.nextTick();
		/*
		System.out.println(Ashdod.getRamzorColor().getColorInString());
		System.out.println(ploni.getSicknessDuration());
		System.out.println(Ashdod.toString());
		System.out.println(ploni.tryToDie());
		System.out.println(Ashdod.toString());
		Ashdod.calculateRamzorGrade();
		System.out.println(Ashdod.getRamzorColor().getColorInString());
		
		Moshav Ash = new Moshav("hadar", loc, RamzorColor.GREEN);
		System.out.println(Ashdod.toString());
		System.out.println(Ash.toString());
		Ashdod.transferPerson(ploni, Ash);
		System.out.println(Ashdod.toString());
		System.out.println(Ash.toString());
		*/
		
		System.out.println(ploni.recover().toString());
		
		
		
		
	}
	
}
