package country;
import location.*;
import population.Person;
import java.util.Random;

public abstract class Settlement {
	
	/**
	 * the constructor
	 * @param name - the settlement name
	 * @param location the settlement location
	 * @param ramzorColor - the settlement ramzor color
	 */
	public Settlement(String name, Location location, RamzorColor ramzorColor, int numPeople)   // add persons array
	{
		m_name = name;
		m_location = new Location(location);   // to build a copy constructor in location or to get point and size
		m_ramzorColor = ramzorColor;
		for(int i=0; i < numPeople; i++)
		{
			// call function who randomally create a person and then call ass person  function
			addPerson(p);
		}
		// adding an array of people
	}
	
	
	/**
	 * 
	 * @return the settlement name
	 */
	public String getSettlementName()
	{
		return m_name;
	}
	
	public void setRamzorColor(RamzorColor rc) {
		m_ramzorColor = rc;
	}
	
	public RamzorColor getRamzorColor() { //??????
		return m_ramzorColor;
	}
	
	
	public boolean equals(Object o) // check if needed
	{
		
	}
	
	

	public String toString()   
	{
		
	}
	
	public abstract RamzorColor calculateRamzorGrade();  // calculate the new color of the settlement
	
	
	/**
	 * 
	 * @return the ratio of sick people in the settlement
	 */
	public double contagiousPercent(){
		int peopleCount= m_people.length;
		double countSicks=0;
		for(int i=0; i<peopleCount; ++i) {
			if(m_people[i].checkIfHealthy()==false)
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
		Random rand = new Random();
		int xMin = m_location.getPointX();
		int yMax = m_location.getPointY();
		int xMax = xMin + m_location.getSizeWidth();
		int yMin = yMax - m_location.getSizeHeight();
		Point randPoint = new Point(rand.nextInt(xMax- xMin +1) + xMin, rand.nextInt(yMax- yMin +1) + yMin);
		return randPoint;
	}
	
	/**
	 * the method adds a person to the settlement people array
	 * @param p - a person
	 * @return return if it succeed to add the person to the array
	 */
	public boolean addPerson(Person p){
		Person newP = new Person(p);
		Person[] newArray = new Person[m_people.length + 1];
		int i;
		for(i=0; i < m_people.length; ++i)
			newArray[i] = m_people[i];
		newArray[i] = newP;
		m_people = newArray;
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
	
	private boolean removePersonfromArray(Person p)
	{
		if(getPersonIndex(p) != -1)
		{
			Person[] newArray = new Person[m_people.length - 1];
			;
			
			for(i=0; i<m_people.length; ++i)
				if()
					
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
		if(getPersonIndex(p) != -1)
		{
			
		}
		return true;
	}
	
	
	// attributes
	private String m_name;
	private Location m_location;
	private Person[] m_people;
	private RamzorColor m_ramzorColor;
	
}
