package io;
import java.io.*;
import country.*;
import population.*;
import location.*;
import java.util.Random;

public class SimulationFile {
	
	/**
	 * the method create the map for the simulation from the information in the file
	 * @param map - will keep the map of the simulation
	 */
	public static void createMap(Map map) {
		FileReader fr; 
		try {
			String line;
			fr = new FileReader("SimulationFile.text");
			BufferedReader bufferedReader = new BufferedReader(fr);
			while((line = bufferedReader.readLine()) != null) {
				String[] data = line.split(";");
				Settlement temp;
				String name = data[1];
				Point cord  = new Point(Integer.parseInt(data[2]), Integer.parseInt(data[3]));
				Size sz = new Size(Integer.parseInt(data[4]), Integer.parseInt(data[5]));
				Location loc = new Location(cord, sz);
				int numPeople = Integer.parseInt(data[6]);
				switch(data[0])
				{
				case "City":
					temp = new City(name, loc, RamzorColor.GREEN);
					createPeopleArrey(temp, numPeople);  
					map.addSettlement(temp);  // adding the settlement to the map
					break;
					
				case "Kibbutz":
					temp = new Kibbutz(name, loc, RamzorColor.GREEN);
					createPeopleArrey(temp, numPeople);
					map.addSettlement(temp);  // adding the settlement to the map
					break;
					
				case "Moshav":
					temp  = new Moshav(name, loc, RamzorColor.GREEN);
					createPeopleArrey(temp, numPeople);
					map.addSettlement(temp);  // adding the settlement to the map
					break;
					
				 default:
					 System.out.println("settelment is undefined");
					 break;
				}
			}
			fr.close();
		}
		catch(IOException e1){
		
		}
	}
	
	/**
	 * the method calculates the age for a new person
	 * @return the age
	 */
	public static int intizializePersonAge()
	{
		int y;
		double x;
		Random rand = new Random();
		y = rand.nextInt(5);  
		Random r = new Random();
		x = r.nextGaussian()*deviation+mean;
		return (int)((5 * x) + y);
	}

	/**
	 * the method initialize a settlement with people
	 * @param s - the settlement
	 * @param numOfPersons - the number of people to add to the settlement
	 */
	public static void createPeopleArrey(Settlement s, int numOfPersons) {
		Healthy p;
		for(int i=0; i< numOfPersons; ++i) {
			p=new Healthy(intizializePersonAge(), s.randomLocation(), s );
			s.addPerson(p);
		}
	}
	
	// data members
	public static double deviation = 6;
	public static double mean = 9;
}
