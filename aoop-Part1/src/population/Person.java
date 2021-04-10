//<<<<<<< HEAD
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
		m_settlement= settlement; // new Settlement(settlement); can not creat cctor
	}
	
	
	public abstract double contagionProbability();
	
	public Person contagion(IVirus IV) {
		return new Sick(m_age, m_location, m_settlement, Clock.now(), IV);
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
	
	public Point getLocation() {
		return new Point(m_location); // check in deep
	}
	
	public Settlement getSettlement() {
		return m_settlement; // do cctor to settlement class
	}
	
	// cctor problem
	//public void setSettlement(Settlement s) {
	//	m_settlement = new Settlement(s); // do cctor to settlement class
	//}
	
	public double distance(Person p) {
		return m_location.distanceBetweenTwoPoints(p.m_location);
	}
	
	public String toString(){
		return "age:"+ m_age + "location: " + m_location.toString() +"settlement: " + m_settlement.getSettlementName();
	}
	
	private int m_age;
	private Point m_location;
	private Settlement m_settlement;
}

//=======
//>>>>>>> branch 'main' of https://github.com/chenBenTolila/aoop1.git
