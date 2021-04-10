package country;

import location.Location;

public class City extends Settlement{
	
	//ctor
	public City(String name, Location location, RamzorColor ramzorColor, int numPeople) {
		super(name, location, ramzorColor, numPeople);
	}
		
	public RamzorColor calculateRamzorGrade()  // calculate the new color of the settlement
	{
		m_c= 0.2*Math.pow(4,1.25*contagiousPercent());
		return getRamzorColor().getColor(m_c); // ??????
	}
	
}
