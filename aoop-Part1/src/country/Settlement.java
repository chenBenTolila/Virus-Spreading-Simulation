package country;
import location.Location;


public class Settlement {
	
	/**
	 * the constructor
	 * @param name - the settlement name
	 * @param location the settlement location
	 * @param ramzorColor - the settlement ramzor color
	 */
	public Settlement(String name, Location location, RamzorColor ramzorColor)   // add persons array
	{
		m_name = name;
		m_location = new Location(location);   // to build a copy constructor in location or to get point and size
		m_ramzorColor = ramzorColor;
		// adding an array of people
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
	private RamzorColor m_ramzorColor;
	
}
