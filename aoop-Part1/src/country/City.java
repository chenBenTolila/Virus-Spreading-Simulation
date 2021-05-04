package country;
import location.Location;


/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public class City extends Settlement{
	
	/**
	 * constructor
	 * @param name - name of settlement
	 * @param location - location of settlement
	 * @param ramzorColor - ramzor color of settlement
	 * @param numPeople - number of people in settlement
	 */
	public City(String name, Location location, RamzorColor ramzorColor, int mp) {
		super(name, location, ramzorColor, mp);
	}
	
	/**
	 * return the type of settlement
	 */
	public String getSettlementType() {
		return "City";
	}
	
	
	/**
	 * calculate the new color of the settlement
	 */
	@Override
	public RamzorColor calculateRamzorGrade()  
	{
		double newColor;   // the value of the new color
		newColor= 0.2*Math.pow(4,1.25*contagiousPercent());   // calculate the new color
		setRamzorColor(RamzorColor.colorByValue(newColor));
		return getRamzorColor(); 
	}
	
	/**
	 * return city in string form 
	 */
	@Override
	public String toString(){
		return "Settlement type: City\n" + super.toString();
	}
	
}