package population;
import location.Point;
import simulation.Clock;
import country.Settlement;
import virus.IVirus;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public abstract class Person {
	
	/**
	 * 
	 * @param m_age - person age
	 * @param m_location - location of person
	 * @param m_settlement - settlement of person
	 */ 
	public Person(int age, Point location, Settlement settlement) {
		m_age = age; 
		m_location = new Point(location);
		m_settlement = settlement; 
	}
	
	/**
	 * copy constructor
	 * @param p - the person we want to clone
	 */
	public Person(Person p)
	{
		this(p.getAge(),p.getLocation(), p.getSettlement());
		p.getSettlement().addPerson(this);   // add the this to the same settlement
		// the method don't delete p from the settlement
	}
	
	/**
	 * abstract method
	 * @return returns the probability of the person to get infected in virus
	 */
	public abstract double contagionProbability();
	
	
	/**
	 * abstract method 
	 * @return returns if the person is sick
	 */
	public abstract boolean checkIfSick(); 
	
	/**
	 * abstract method 
	 * @return returns if the person is healthy
	 */
	public abstract boolean checkIfHealthy(); 
	
	
	/**
	 * make the person sick
	 * @param IV - type of the virus of the sick person
	 * @return return the sick person
	 */
	public Person contagion(IVirus IV) {
		if(this.checkIfSick()==false) {
			Sick s = new Sick(m_age, m_location, m_settlement, Clock.now(), IV);
			getSettlement().addSickPerson(s);   // add the sick person to the settlement
			getSettlement().removePersonFromArr(this);  // remove the person healthy person from the settlement
			return s;
			}
		else
			throw new RuntimeException("The person is allready sick");   // this is already sick
	}
	
	/**
	 * 
	 * @return the kind of the virus 
	 */
	public IVirus getVirus() {
		return null;    // for Sick class
	}
	
	/**
	 * 
	 * @return the age of the person
	 */
	public int getAge()
	{
		return m_age;
	}
	
	/**
	 * 
	 * @return the location of person
	 */
	public Point getLocation() {
		return new Point(m_location);
	}
	
	
	/**
	 * 
	 * @return the settlement of person
	 */
	public Settlement getSettlement() {
		return m_settlement; 
	}
	
	
	/**
	 * 
	 * @param p get person
	 * @return the distance between two persons
	 */
	public double distance(Person p) {
		return m_location.distanceBetweenTwoPoints(p.m_location);
	}
	 
	 /**
	  * return the person in string form
	  */
	@Override
	public String toString(){
		return "age: "+ m_age + " \tlocation: " + m_location.toString() +"\tsettlement: " + m_settlement.getSettlementName();
	}
	
	/**
	 * return true if the object has the same values
	 */
	@Override
	public boolean equals(Object o)
	{
		boolean ans = false;
		if ((o instanceof Person))
			ans = (m_location.equals(((Person)o).m_location)) && (m_age == ((Person)o).m_age);
		return ans;
	}
	
	
	public void setSettlement(Settlement s)
	{
		m_settlement = s;
	}
	
	// data members
	private int m_age; // person age
	private Point m_location; // location of person
	private Settlement m_settlement; // Settlement of person
}


