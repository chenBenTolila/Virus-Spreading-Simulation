package population;
import virus.IVirus;
import location.Point;
import country.Settlement; 

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

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
	 * copy constructor
	 * @param c - the convalescent we want to clone
	 */
	public Convalescent(Convalescent c) {
		super(c.getAge(),c.getLocation(), c.getSettlement());
		m_virus =c.m_virus;
	}
	
	
	/**
	 * return the probability of getting sick again
	 */
	@Override
	public double contagionProbability() {
		return 0.2;
	}
	
	
	/**
	 * return true if the person in not infected in the virus
	 */
	@Override
	public boolean checkIfSick(){
		return false;
	}
	 
	/**
	 * return true if the person in never infected in the virus
	 */
	@Override
	public boolean checkIfHealthy() {
		return false;
	}
	 /**
	  * return the convalescent person in string form
	  */
	@Override
	public String toString(){
		return super.toString() + "\tStatus: convalescent\trecovered from: "+ m_virus;
	}
	
	// data members
	private IVirus m_virus; // the type of virus the person recovered from
}
