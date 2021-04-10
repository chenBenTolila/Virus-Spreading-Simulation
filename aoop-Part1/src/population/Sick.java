package population;
import country.Settlement;
import location.Point;
import virus.IVirus;
import simulation.Clock;

public class Sick extends Person{
	
	public Sick(int age, Point location, Settlement settlement, long contagiousTime, IVirus virus) {
		super(age, location, settlement);
		m_contagiousTime=contagiousTime;   // second option get from current time in clock
		m_virus=virus;
	}
	
	public double contagionProbability() {
		return 1;
	}
	
	
	/**
	 * return true if the person in not infected in the virus
	 */
	public boolean checkIfHealthy()
	{
		return false;
	}
	
	public long getSicknessDuration()
	{
		return Clock.now() - m_contagiousTime;
	}
	
	public boolean tryToDie() {
		return m_virus.tryToKill(this);
	}
	
	public Person recover() {
		return new Convalescent(this.getAge(), this.getLocation(), this.getSettlement(),m_virus);
	}
	
	
	public String toString(){
		return super.toString() + "contagiousTime: "+ m_contagiousTime + "virus: "+ m_virus;
	}
	
	private long m_contagiousTime;
	private IVirus m_virus;
}
