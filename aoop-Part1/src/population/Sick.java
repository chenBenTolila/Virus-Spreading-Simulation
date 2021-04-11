package population;
import country.Settlement;
import location.Point;
import virus.IVirus;
import simulation.Clock;

public class Sick extends Person{
	
	/**
	 * ctor
	 * @param age - age of sick person
	 * @param location - location of sick person
	 * @param settlement - settlement of sick person
	 * @param contagiousTime - contagious time of sick person
	 * @param virus - type of virus of sick person
	 */
	public Sick(int age, Point location, Settlement settlement, long contagiousTime, IVirus virus) {
		super(age, location, settlement);
		m_contagiousTime=contagiousTime;   // second option get from current time in clock
		m_virus=virus;
	}
	
	/**
	 * return the contagion probability of sick person
	 */
	public double contagionProbability() {
		return 1;
	}
	
	/**
	 * return true if the person in not infected in the virus
	 */
	public boolean checkIfHealthy(){
		return false;
	}
	
	/**
	 * 
	 * @return sickness duration of sick person
	 */
	public long getSicknessDuration(){
		return Clock.now() - m_contagiousTime;
	}
	
	/**
	 * 
	 * @return calculate and returns whether the person will die from the disease
	 */
	public boolean tryToDie() {
		return m_virus.tryToKill(this);
	}
	
	/**
	 * make sick to healthy
	 * @return return healthy person
	 */
	public Person recover() {
		return new Convalescent(this.getAge(), this.getLocation(), this.getSettlement(),m_virus);
	}
	
	 /**
	  * return the sick person in string form
	  */
	public String toString(){
		return super.toString() + "contagiousTime: "+ m_contagiousTime + "virus: "+ m_virus;
	}
	
	 // data members
	private long m_contagiousTime; // The moment of infection
	private IVirus m_virus; // kind of virus
}