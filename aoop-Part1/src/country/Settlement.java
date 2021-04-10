package country;
import location.*;
import population.Person;


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
	
	//cctor
	public Settlement(Settlement s) {
		m_name = s.name;
		m_location = new Location(location);   // to build a copy constructor in location or to get point and size
		m_ramzorColor = s.ramzorColor;
		for(int i=0; i < numPeople; i++)
		{
			// call function who randomally create a person and then call ass person  function
			addPerson(p);
		}
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
	
	// not finished
	
	// Maybe add copy Ctor
	
	public boolean equals(Object o) // check if needed
	{
		
	}
	
	

	public String toString()   // check if needed 
	{
		
	}
	
	public abstract RamzorColor calculateRamzorGrade();  // calculate the new color of the settlement
	
	
	public double contagiousPercent(){
		int len= m_people.length;
		int countSicks=0;
		for(int i=0; i<len; ++i) {
			if(m_people[i].checkIfHealthy()==false)
				countSicks ++; 
		}
		return countSicks/len;
	}
	
	public Point randomLocation()
	{}
 
	public boolean addPerson(Person p){
		
	}
	
	public boolean transferPerson(Person p, Settlement s){
		
		return true;
	}
	
	
	// attributes
	private String m_name;
	private Location m_location;
	// need to add an array of people 
	private Person[] m_people;
	private RamzorColor m_ramzorColor;
	protected double m_c; // ?????
	
}
