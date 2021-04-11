package population;
import virus.IVirus;
import location.Point;
import country.Settlement; 

public class Convalescent extends Person{
	
	/**
	 * ctor
	 * @param age - keep the age of convalescent
	 * @param location - keep the location of convalescent
	 * @param settlement - keep the settlement of convalescent
	 * @param virus- keep the type of virus the convalescent over
	 */
	public Convalescent(int age, Point location, Settlement settlement, IVirus virus) {
		super(age, location, settlement);
		m_virus =virus;
	}
	
	/**
	 * return the probability of getting sick again
	 */
	public double contagionProbability() {
		return 0.2;
	}
	
	
	/**
	 * return true if the person in not infected in the virus
	 */
	
	public boolean checkIfHealthy(){
		return true;
	}
	 
	 /**
	  * return the convalescent person in string form
	  */

	public String toString(){
		return super.toString() + "virus: "+ m_virus;
	}
	
	// data members
	private IVirus m_virus; // the type of virus that over
}
