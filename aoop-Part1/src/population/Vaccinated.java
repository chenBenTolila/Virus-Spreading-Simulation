package population;
import simulation.Clock;

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
	
	
	/**
	 * the method returns the probabilty of the person to get infected in a virus
	 */
	public double contagionProbability() {
		long t = Clock.now() - m_vaccinationTime;
		if(t<21)
		{
			
		}
		else
		{
			
		}
	}
	
	/**
	 * return true if the person in not infected in the virus
	 */
	public boolean checkIfHealthy()
	{
		return true;
	}
	
	private long m_vaccinationTime; // maybe add final
}
