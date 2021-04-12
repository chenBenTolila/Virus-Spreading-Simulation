package population;
import location.Point;
import simulation.Clock;
import country.Settlement;
import virus.IVirus;

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
		p.getSettlement().addPerson(this);
	}
	
	/**
	 * abstract method
	 * @return returns the probability of the person to get infected in virus
	 */
	public abstract double contagionProbability();
	
	
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
		if(this.checkIfHealthy()==true) {
			Sick s = new Sick(m_age, m_location, m_settlement, Clock.now(), IV);
			getSettlement().addPerson(s);   // add the sick person to the settlement
			getSettlement().removePersonfromArr(this);  // remove the person healthy person from the settlement
			return s;
			}
		else
			throw new RuntimeException("The person is allready sick");
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
	public String toString(){
		return "age:"+ m_age + "location: " + m_location.toString() +"settlement: " + m_settlement.getSettlementName();
	}
	
	/**
	 * return true if the object has the same values
	 */
	public boolean equals(Object o)
	{
		boolean ans = false;
		if ((o instanceof Person))
			ans = (m_location.equals(((Person)o).m_location)) && (m_age == ((Person)o).m_age);
		return ans;
	}
	
	// data members
	private int m_age; // person age
	private Point m_location; // location of person
	private Settlement m_settlement; // Settlement of person
}


