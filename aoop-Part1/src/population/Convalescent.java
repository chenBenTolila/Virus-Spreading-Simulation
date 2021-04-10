package population;
import virus.IVirus;
import location.Point;
import country.Settlement; 

public class Convalescent extends Person{
	
	public Convalescent(int age, Point location, Settlement settlement, IVirus virus) {
		super(age, location, settlement);
		m_virus =virus;
	}
	
	public double contagionProbability() {
		return 0.2;
	}
	
	
	/**
	 * return true if the person in not infected in the virus
	 */
	public boolean checkIfHealthy()
	{
		return true;
	}
	
	public String toString(){
		return super.toString() + "virus: "+ m_virus;
	}
	
	private IVirus m_virus;
}
