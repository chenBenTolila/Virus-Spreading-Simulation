package country;
import location.Location;

public class Moshav extends Settlement{

	/**
	 * constructor
	 * @param name- name of settlement
	 * @param location - location of settlement
	 * @param ramzorColor - ramzor color of settlement
	 * @param numPeople- number of people in settlement
	 */
	public Moshav(String name, Location location, RamzorColor ramzorColor) {
		super(name, location, ramzorColor);
	}
	
	/**
	 * calculate the new ramzor color of settlement
	 */
	public RamzorColor calculateRamzorGrade()  
	{
		double newColor;
		newColor= 0.3+ 3*Math.pow((Math.pow(1.2, getRamzorColor().getColorValue())*contagiousPercent()-0.35),5);
		setRamzorColor(colorByValue(newColor));
		return getRamzorColor(); 
	}
	
	/**
	 * return moshav in string form
	 */
	public String toString(){
		return "Moshav " + super.toString();
	}
}
