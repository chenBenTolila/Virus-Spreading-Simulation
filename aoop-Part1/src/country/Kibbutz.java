package country;

import location.Location;

public class Kibbutz extends Settlement{

	/**
	 * constractor
	 * @param name - name of settlement
	 * @param location - location of settlement
	 * @param ramzorColor - ramzor color of settlement
	 * @param numPeople - number of people in the settlement
	 */
	public Kibbutz(String name, Location location, RamzorColor ramzorColor, int numPeople) {
		super(name, location, ramzorColor, numPeople);
	}
	
	/**
	 * calculate the new color of the settlement
	 */
	public RamzorColor calculateRamzorGrade()  
	{
		double newColor;
		newColor = 0.45 + Math.pow((Math.pow(1.5, getRamzorColor().getColorValue())*contagiousPercent()-0.4),3);
		setRamzorColor(colorByValue(newColor));
		return getRamzorColor(); 
	}	
	
	/**
	 * return kibbutz in string form
	 */
	public String toString(){
		return "kibbutz " + super.toString();
	}
}
