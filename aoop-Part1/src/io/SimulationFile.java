package io;
import java.io.*;
import country.*;
import population.*;
import location.*;
import java.util.Random;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public class SimulationFile {
	
	/**
	 * the method create the map for the simulation from the information in the file
	 * @param map - will keep the map of the simulation
	 */
	public static void createMap(Map map) throws IOException {
		FileReader fr; 
		String line;
		if(fileName!=null) {
		fr = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fr);
		while((line = bufferedReader.readLine()) != null) {
			line = line.replaceAll(" ", "");
			String[] data = line.split(";");
			if(!(data[0].equals("#"))) 
				createSettlement(data, map);
		}
		fr.close();	
		fr = new FileReader(fileName);
		bufferedReader = new BufferedReader(fr);
		while((line = bufferedReader.readLine()) != null) {
			line = line.replaceAll(" ", "");
			String[] data = line.split(";");
			if(data[0].equals("#"))
				map.connectSettlements(data[1], data[2]);
		}
		fr.close();
		}
		System.out.println("there is no file to open\n");
	}
	
	
	/**
	 * 
	 * @param data get data of settlement
	 * @param map get map of simulation
	 */
	public static void createSettlement(String[] data, Map map) {
		Settlement temp;
		String name = data[1];
		Point cord  = new Point(Integer.parseInt(data[2]), Integer.parseInt(data[3]));
		Size sz = new Size(Integer.parseInt(data[4]), Integer.parseInt(data[5]));
		Location loc = new Location(cord, sz);
		int numPeople = Integer.parseInt(data[6]);
		switch(data[0])
		{
		case "City":
			temp = new City(name, loc, RamzorColor.GREEN, (int)(numPeople*maxP));
			createPeopleArray(temp, numPeople);  
			map.addSettlement(temp);  // adding the settlement to the map
			break;
				
		case "Kibbutz":
			temp = new Kibbutz(name, loc, RamzorColor.GREEN, (int)(numPeople*maxP));
			createPeopleArray(temp, numPeople);
			map.addSettlement(temp);  // adding the settlement to the map
			break;
				
		case "Moshav":
			temp  = new Moshav(name, loc, RamzorColor.GREEN, (int)(numPeople*maxP));
			createPeopleArray(temp, numPeople);
			map.addSettlement(temp);  // adding the settlement to the map
			break;
				
		default:
			 System.out.println("type of settelment is undefined");
			 break;
		}
	}
	
	/**
	 * the method calculates the age for a new person
	 * @return the age
	 */
	public static int intizializePersonAge()
	{
		int y;   // a random integer between 1-4 (including)
		double x;
		double temp;
		Random rand = new Random();
		y = rand.nextInt(5);
		do {
			temp = rand.nextGaussian();
		}while (temp > 1 || temp < -1);
		x = temp*deviation + mean;   // initialize x
		return (int)((5 * x) + y);   // return the calculated age
	}

	/**
	 * the method initialize a settlement with people
	 * @param s - the settlement
	 * @param numOfPersons - the number of people to add to the settlement
	 */
	public static void createPeopleArray(Settlement s, int numOfPersons) {
		Healthy p;
		for(int i=0; i< numOfPersons; ++i) {   
			p=new Healthy(intizializePersonAge(), s.randomLocation(), s );   // create an healthy person
			s.addPerson(p);   // add the person to the settlement
		}
	}
	
	public static void setFileName(String fName) {
		fileName=fName;
	}
	
	// static data members
	public static double deviation = 6;    // deviation 
	public static double mean = 9;    // mean
	public static final double maxP = 1.3;
	private static String fileName = null;
}
