package io;
import java.io.*;
import country.*;
import simulation.*;

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
	public static boolean createMap(Map map) throws IOException {
		FileReader fr; 
		String line;
		Settlement temp;
		if(fileName!=null) {
			fr = new FileReader(fileName);
			map.resetMap();
			Clock.resetTime();
			BufferedReader bufferedReader = new BufferedReader(fr);
			SettlementFactory SettlementF = new SettlementFactory();
			while((line = bufferedReader.readLine()) != null) {
				line = line.replaceAll(" ", "");
				String[] data = line.split(";");
				if(!(data[0].equals("#"))) {
					temp = SettlementF.getFactory(data, map);
					map.addSettlement(temp);
				}
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
			map.addSickToMap(0.01);   // initialize the population with 1% of sick people
			return true;
		}
		else {
			System.out.println("Failed to open the file");
			return false;
		}
	}
	
	/**
	 * 
	 * @param data get data of settlement
	 * @param map get map of simulation
	 */
	/*
	public static Settlement settFactory(String[] data, Map map) {
		Settlement temp;
		String name = data[1];
		Point cord  = new Point(Integer.parseInt(data[2]), Integer.parseInt(data[3]));
		Size sz = new Size(Integer.parseInt(data[4]), Integer.parseInt(data[5]));
		Location loc = new Location(cord, sz);
		int numPeople = Integer.parseInt(data[6]);
		switch(data[0])
		{
		case "City":
			temp = new City(name, loc, RamzorColor.GREEN, (int)(numPeople*maxP), map);
			createPeopleArray(temp, numPeople);  
			break;
				
		case "Kibbutz":
			temp = new Kibbutz(name, loc, RamzorColor.GREEN, (int)(numPeople*maxP), map);
			createPeopleArray(temp, numPeople);
			break;
				
		case "Moshav":
			temp  = new Moshav(name, loc, RamzorColor.GREEN, (int)(numPeople*maxP), map);
			createPeopleArray(temp, numPeople);
			break;
				
		default:
			 System.out.println("type of settelment is undefined");
			 temp=null;
			 break;
		}
		return temp;
	}
	*/

	
	/**
	 * the method calculates the age for a new person
	 * @return the age
	 */
	/*
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
	*/

	/**
	 * the method initialize a settlement with people
	 * @param s - the settlement
	 * @param numOfPersons - the number of people to add to the settlement
	 */
	/*
	public static void createPeopleArray(Settlement s, int numOfPersons) {
		Healthy p;
		for(int i=0; i< numOfPersons; ++i) {   
			p=new Healthy(intizializePersonAge(), s.randomLocation(), s );   // create an healthy person
			s.addPerson(p);   // add the person to the settlement
		}
	}
	
	*/
	
	/**
	 * 
	 * @param fName - new name to file
	 */
	public static void setFileName(String fName) {
		fileName=fName;
	}
	
	 
	private static String fileName = null; // save the name of file
}
