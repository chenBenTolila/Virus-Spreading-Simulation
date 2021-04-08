package country;
import location.*;


public class Settlement {
	
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
	
	
	// not finished
	
	// Maybe add copy Ctor
	
	public boolean equals(Object o) // check if needed
	{
		
	}
	
	

	public String toString()   // check if needed 
	{
		
	}
	
	public RamzorColor calculateRamzorGrade()  // calculate the new color of the settlement
	{
	}
	
	public double contagiousPercent()   // 
	{}
	
	public Point randomLocation()
	{}
 
	public boolean addPerson(Person p)   
	{}
	
	public boolean transferPerson(Person p, Settlement s)
	{
		
	}
	
	
	// attributes
	private String m_name;
	private Location m_location;
	// need to add an array of people 
	private Person[] m_people;
	private RamzorColor m_ramzorColor;
	
}
