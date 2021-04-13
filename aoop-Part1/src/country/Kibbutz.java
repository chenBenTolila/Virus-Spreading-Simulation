package country;
import location.Location;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public class Kibbutz extends Settlement{

	/**
	 * constructor
	 * @param name - name of settlement
	 * @param location - location of settlement
	 * @param ramzorColor - ramzor color of settlement
	 * @param numPeople - number of people in the settlement
	 */
	public Kibbutz(String name, Location location, RamzorColor ramzorColor) {
		super(name, location, ramzorColor);
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
