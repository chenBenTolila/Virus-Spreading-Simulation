<<<<<<< HEAD
package population;
import location.Point;
import country.Settlement;
import virus.IVirus;

public abstract class Person {
	/**
	 * 
	 * @param age - person age
	 * @param location - location of person
	 * @param settlement - settlement of person
	 */ 
	public Person(int age, Point location, Settlement settlement) {
		this.age = age; 
		this.location= new Location(location);
		this.settlement= new Settlement(settlement);
	}
	
	
	public abstract double contagionProbability();
	
	public Person contagion(IVirus IV) {
		//
	}
	
	// we added
	public abstract boolean checkIfHealthy();   
	
	/**
	 * 
	 * @return the age of the person
	 */
	public int getAge()
	{
		return m_age;
	}
	
	public double distance()
	
	public String toString(){
		return "age:"+ m_age + "location: " + m_location.toString() +"settlement: " + this.settlement.getSettlementName()
	}
	
	private int m_age;
	private Point m_location;
	private Settlement m_settlement;
}
=======
>>>>>>> branch 'main' of https://github.com/chenBenTolila/aoop1.git
