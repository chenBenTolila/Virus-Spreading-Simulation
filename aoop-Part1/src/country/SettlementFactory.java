package country;

import java.util.Random;

import location.Location;
import location.Point;
import location.Size;
import population.Healthy;

public class SettlementFactory {
	
	/**
	 * 
	 * @param data get data of settlement
	 * @param map get map of simulation
	 */
	public Settlement getFactory(String[] data, Map map) {
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
	
	/**
	 * the method calculates the age for a new person
	 * @return the age
	 */
	public int intizializePersonAge()
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
	public void createPeopleArray(Settlement s, int numOfPersons) {
		Healthy p;
		for(int i=0; i< numOfPersons; ++i) {   
			p=new Healthy(intizializePersonAge(), s.randomLocation(), s );   // create an healthy person
			s.addPerson(p);   // add the person to the settlement
		}
	}
	
	private final double maxP = 1.3; // max peapole in the settlement = numpeople *maxP
	private final double deviation = 6;    // deviation 
	private final double mean = 9;    // mean 
}
