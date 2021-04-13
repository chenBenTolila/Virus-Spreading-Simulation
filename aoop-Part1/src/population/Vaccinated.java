package population;
import simulation.Clock;
import country.Settlement;
import location.Point;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public class Vaccinated extends Person{
	
	
	/***
	 *  ctor
	 * @param age - vaccinated person age
	 * @param location - location of vaccinated
	 * @param settlement - settlement of vaccinated
	 * @param vaccinationTime - the moment get vaccinated
	 */
	public Vaccinated(int age, Point location, Settlement settlement, long vaccinationTime) {
		super(age, location, settlement);
		m_vaccinationTime= vaccinationTime;
	}
	 
	
	public Vaccinated(Vaccinated v) {
		super(v.getAge(),v.getLocation(), v.getSettlement());
		m_vaccinationTime=v.m_vaccinationTime;
	}
	
	
	 /**
	  * return the vaccinated person in string form
	  */

	public String toString(){
		return "vaccinated: " + super.toString() + "vaccinationTime: "+ m_vaccinationTime;
	}
	
	/**
	 * the method returns the probabilty of the person to get infected in a virus
	 */
	public double contagionProbability() {
		long t = Clock.now() - m_vaccinationTime;
		if(t<21)
		{
			return Math.min(1, 0.56+0.15*Math.sqrt(21-t));
		}
		else
		{
			return Math.max(0.05, 1.05/(t-14));
		}
	}
	
	/**
	 * return true if the person in not infected in the virus
	 */
	public boolean checkIfHealthy(){
		return true;
	}
	
	 // data members 
	private long m_vaccinationTime; // the moment get vaccinated
}
