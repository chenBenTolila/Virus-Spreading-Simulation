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
	
	public boolean checkIfHealthy()
	{
		return true;
	}
}
