package population;

import country.Settlement;
import location.Point;

public class Vaccinated extends Person{
	/***
	 * 
	 * @param age
	 * @param location
	 * @param settlement
	 * @param vaccinationTime
	 */
	public Vaccinated(int age, Point location, Settlement settlement, long vaccinationTime) {
		super(age, location, settlement);
		this.vaccinationTime= vaccinationTime;
	}
	
	public String toString(){
		return super.toString() + "vaccinationTime: "+ this.vaccinationTime;
	}
	
	public abstract double contagionProbability() {
		
	}
	
	private long vaccinationTime; // maybe add final
}
