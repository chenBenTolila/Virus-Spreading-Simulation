package country;

import location.Location;

public class City extends Settlement{
	
	/**
	 * constructor
	 * @param name - name of settlement
	 * @param location - location of settlement
	 * @param ramzorColor - ramzor color of settlement
	 * @param numPeople - number of people in settlement
	 */
	public City(String name, Location location, RamzorColor ramzorColor) {
		super(name, location, ramzorColor);
	}
		
	/**
	 * calculate the new color of the settlement
	 */
	public RamzorColor calculateRamzorGrade()  
	{
		double newColor;
		newColor= 0.2*Math.pow(4,1.25*contagiousPercent());
		setRamzorColor(colorByValue(newColor));
		return getRamzorColor(); 
	}
	
	/**
	 * return city in string form 
	 */
	public String toString(){
		return "City " + super.toString();
	}
	
}