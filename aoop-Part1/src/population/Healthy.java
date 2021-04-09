package population;
import location.Point;
import country.Settlement;


public class Healthy extends Person{
	
	public Healthy(int age, Point location, Settlement settlement) {
		super(age, location, settlement);
	}
	
	/**
	 * return the basic reproduction number 
	 */
	public double contagionProbability() {
		return 1.0;
	}
	
	public Person vaccinate() {
		//
	}
	

	/**
	 * return true if the person in not infected in the virus
	 */
	public boolean checkIfHealthy()
	{
		return true;
	}
}
