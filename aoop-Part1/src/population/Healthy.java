package population;
import location.Point;
import simulation.Clock;
import country.Settlement;


public class Healthy extends Person{
	
	public Healthy(int age, Point location, Settlement settlement) {
		super(age, location, settlement);
	}
	
	/**
	 * return the basic reproduction number 
	 */
	public double contagionProbability() {
		return 1;
	}
	
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
	
	public String toString(){
		return super.toString();
	}
}
