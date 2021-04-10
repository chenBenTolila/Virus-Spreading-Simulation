package country;

import location.Location;

public class Kibbutz extends Settlement{

	//ctor
	public Kibbutz(String name, Location location, RamzorColor ramzorColor, int numPeople) {
		super(name, location, ramzorColor, numPeople);
	}
		
	public RamzorColor calculateRamzorGrade()  // calculate the new color of the settlement
	{
		m_c= 0.45+ Math.pow((Math.pow(1.5, m_c)*contagiousPercent()-0.4),3);
		return getRamzorColor().getColor(m_c); // ??????
	}	
}
