package country;

import location.Location;

public class Kibbutz extends Settlement{

	//ctor
	public Kibbutz(String name, Location location, RamzorColor ramzorColor, int numPeople) {
		super(name, location, ramzorColor, numPeople);
	}
		
	public RamzorColor calculateRamzorGrade()  // calculate the new color of the settlement
	{
		double newColor;
		newColor = 0.45 + Math.pow((Math.pow(1.5, getRamzorColor().getColorValue())*contagiousPercent()-0.4),3);
		setRamzorColor(colorByValue(newColor));
		return getRamzorColor(); 
	}	
}
