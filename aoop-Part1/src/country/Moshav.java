package country;
import location.Location;

public class Moshav extends Settlement{

	//ctor
	public Moshav(String name, Location location, RamzorColor ramzorColor, int numPeople) {
		super(name, location, ramzorColor, numPeople);
	}
	
	public RamzorColor calculateRamzorGrade()  // calculate the new color of the settlement
	{
		m_c= 0.3+ 3*Math.pow((Math.pow(1.2, m_c)*contagiousPercent()-0.35),5);
		return getRamzorColor().getColor(m_c); // ??????
	}
}
