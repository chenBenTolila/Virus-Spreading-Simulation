package population;
import location.Point;
import simulation.Clock;
import country.Settlement;


public class Healthy extends Person{
	
	/**
	 * ctor
	 * @param age - keep the age of healthy person
	 * @param location - keep the location of healthy person
	 * @param settlement - keep the settlement of healthy person
	 */
	public Healthy(int age, Point location, Settlement settlement) {
		super(age, location, settlement);
	}
	
	public Healthy(Healthy h) {
		super(h.getAge(),h.getLocation(), h.getSettlement());
	}
	
	
	/**
	 * return the basic reproduction number 
	 */
	public double contagionProbability() {
		return 1;
	}
	
	/**
	 * make the healthy person to vaccinate
	 * @return return vaccinate person
	 */
	public Person vaccinate() {
		return new Vaccinated(this.getAge(), this.getLocation(), this.getSettlement(), Clock.now());
	}
	

	/**
	 * return true if the person in not infected in the virus
	 */
	
	public boolean checkIfHealthy()
	{
		return true;
	}
	 
	 /**
	  * return the healthy person in string form
	  */

	public String toString(){
		return super.toString();
	}
}
