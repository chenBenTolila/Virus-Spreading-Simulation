package country;
import location.*;
import population.*;
import virus.*;
import java.util.Random;
import simulation.Clock;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public abstract class Settlement {
	
	/**
	 * the constructor
	 * @param name - the settlement name
	 * @param location the settlement location
	 * @param ramzorColor - the settlement ramzor color
	 */
	public Settlement(String name, Location location, RamzorColor rc) {
		m_name = name;
		m_location = new Location(location);  
		m_ramzorColor = colorByValue(rc.getColorValue());
		m_people = new Person[0];     // create an empty array of citizens
	}
	
	
	/**
	 * 
	 * @return the settlement name 
	 */
	public String getSettlementName()
	{
		return m_name;
	}
	
	/**
	 * the method change the ramzor color 
	 * @param rc - the color we want to change to
	 */
	protected void setRamzorColor(RamzorColor rc)
	{
		m_ramzorColor = rc;
	}
	
	/**
	 * 
	 * @return the ramzor color of the settlement
	 */
	public RamzorColor getRamzorColor() { 
		return m_ramzorColor;
	}
	

	/**
	 * return the settlement in string form
	 */
	public String toString()   
	{
		String s= "Settlement name: " + m_name + "\nlocation: " + m_location.toString();
		s+="\nramzor color: " + m_ramzorColor.getColorInString();
		s += "\npeople in the settlement:\n";
		if(m_people.length == 0)     // to string of all the citizens
			s += "no people currently in the settlement\n";
		else
			for(int i=0;i<m_people.length;++i) {
				s+= m_people[i].toString()+"\n";
		}
		return s;
		
	}
	
	public abstract RamzorColor calculateRamzorGrade();  // Abstract - calculate the new color of the settlement
	
	
	/**
	 * 
	 * @return the ratio of sick people in the settlement
	 */
	public double contagiousPercent(){
		int peopleCount= m_people.length;   // the number of citizens in the settlement
		double countSicks=0;    // the amount of sick people
		for(int i=0; i<peopleCount; ++i) {    // go over all the people in the settlement
			if(m_people[i].checkIfHealthy()==false)   // check if they are sick
				countSicks ++; 
		}
		return countSicks/peopleCount;   // check if the result is double type ////
	}
	
	
	/**
	 * the function creates a random point in the settlement area
	 * @return the random point
	 */
	public Point randomLocation()
	{
		Random rand = new Random();    // will help choose the coordinates randomly 
		int xMin = m_location.getPointX();    // the smallest x value for a point in the settlement
		int yMin = m_location.getPointY();    // the smallest y value for a point in the settlement
		int xMax = xMin + m_location.getSizeWidth();    // the biggest x value for a point in the settlement
		int yMax = yMin + m_location.getSizeHeight();   // the biggest y value for a point in the settlement
		Point randPoint = new Point(rand.nextInt(xMax - xMin +1) + xMin, rand.nextInt(yMax- yMin +1) + yMin);
		return randPoint;
	}
	
	/**
	 * the method adds a person to the settlement people array
	 * @param p - a person
	 * @return return if it succeed to add the person to the array
	 */
	public boolean addPerson(Person p){
		Person[] newArray = new Person[m_people.length + 1];    // create a new array of people with size plus 1
		int i;
		for(i=0; i < m_people.length; ++i)   // go over the people
			newArray[i] = m_people[i];   // copy them to the new array
		newArray[i] = p;  // adding p itself///
		m_people = newArray;
		return true;
	}
	
	/**
	 * the function gets a person and search him in the person array of the settlement
	 * @param p - a person
	 * @return  the index of the person in the array of people, if he isn't there the function returns -1
	 */
	private int getPersonIndex(Person p)
	{
		for(int i = 0; i< m_people.length; i++)
			if(m_people[i] == p)    // if they reference to the same person
				return i;
		return -1;		
	}
	
	/**
	 * the method removes p from the settlement people array
	 * @param p - a person
	 * @return return if the method succeed to remove or not
	 */
	public boolean removePersonFromArr(Person p)
	{
		if(getPersonIndex(p) != -1)    // if p is in the settlement
		{
			Person[] newArray = new Person[m_people.length - 1];   // create a new array of people with size minus 1
			int j = 0; 
			for(int i=0; i<m_people.length; ++i) {   // go over the people 
				if(getPersonIndex(p) == i)    
					--j;
				else
					newArray[j] = m_people[i];   // copy everyone to the new array except for p
				++j;
			}
			m_people = newArray;
			return true;
		}
		else
			return false;
	}
	
	/**
	 * 
	 * @param p - the person we want to transfer
	 * @param s - the settlement we want to transfer the person to
	 * @return true if the transfer succeeded
	 */
	public boolean transferPerson(Person p, Settlement s){
		if(getPersonIndex(p) != -1)   // check if is in this settlement
		{
			if(removePersonFromArr(p))    // remove p from this settlement
				return s.addPerson(p);	  // add p to the new settlement
		}
		return false;    // return if the the transfer succeeded or failed
	}
	
	/**
	 * 
	 * @param val - a double that holds the settlement coefficient of the disease
	 * @return  return the color the value match
	 */
	public RamzorColor colorByValue(double val)
	{
		if(val <= 0.4)    // return the ramzor that match the value
			return RamzorColor.GREEN;
		else if(val <= 0.6)
			return RamzorColor.YELLOW;
		else if(val <= 0.8)
			return RamzorColor.ORANGE;
		else 
			return RamzorColor.RED;
		}
	
	/**
	 * 
	 * @return the amount of people in the settlement
	 */
	public int getPeopleAmount()  
	{
		return m_people.length;
	}
	
	/**
	 * the method turns 1% of the people in the settlement into sick people
	 */
	public void intializeSickPeople()
	{
		// create an object for each of the variants
		BritishVariant britV = new BritishVariant();   
		ChineseVariant chinV = new ChineseVariant();
		SouthAfricanVariant sAfriV = new SouthAfricanVariant();
		
		for(int i =0; i< m_people.length / 100; ++i)  // go over the first 1% of people in the array
		{
			if (i % 3 == 0)    // infect the selected person in one of the variants
				m_people[i].contagion(sAfriV);
			else if (i% 3 == 1)
				m_people[i].contagion(chinV);
			else
				m_people[i].contagion(britV);
		}
	}
	
	/**
	 * for in sick person in the settlement try to infect 6 healthy people
	 */
	public void tryToInfectSix() {
		int count=0;   // the number of attempted contagion for each sick person
		int j, i;   // keep the indexes for the arrays
		Random rand = new Random();   // will randomize the selection of the person to try contage
		Person[] sickArr=new Person[0];    // array of only the sick people in the settlement
		for(i =0; i< m_people.length; ++i)
		{
			if(m_people[i].checkIfHealthy()==false) {
				Person[] newArray = new Person[sickArr.length + 1];  // adding the sick people to the sick array
				for(j=0; j < sickArr.length; ++j)
					newArray[j] = sickArr[j];
				newArray[j] = m_people[i];  // adding p itself///
				sickArr = newArray;
			}
		}
		for(i =0; i< sickArr.length; ++i) {   // going over all the sick
			Clock.nextTick();   // adding a tick to the clock for each round of contagion tries
			while(count<6) {
				j=rand.nextInt(m_people.length);    // choose a person to contage randomly
				try {
					if(sickArr[i].getVirus().tryToContagion(sickArr[i], m_people[j]))   // try to contage the selected person
						m_people[j].contagion(sickArr[i].getVirus());
				}
				catch(RuntimeException ex)
				{
					System.out.println("Sick person cannot become sick again");   // the chosen person is already sick
				}
				count++;	
			}
			count=0;
		}
	}
		
	
	
	// attributes
	private String m_name;    // the  name of the settlement
	private Location m_location;    // the location of the settlement
	private Person[] m_people;    // the list of the people in the settlement
	private RamzorColor m_ramzorColor;   // the ramzor color of the settlement
	
}
