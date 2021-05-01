package country;
import location.Location;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public class Moshav extends Settlement{

	/**
	 * constructor
	 * @param name- name of settlement
	 * @param location - location of settlement
	 * @param ramzorColor - ramzor color of settlement
	 * @param numPeople- number of people in settlement
	 */
	public Moshav(String name, Location location, RamzorColor ramzorColor, int mp) {
		super(name, location, ramzorColor, mp);
	}
	
	public String getSettlementType() {
		return "Moshav";
	}
	
	
	/**
	 * calculate the new ramzor color of settlement
	 */
	@Override
	public RamzorColor calculateRamzorGrade()  
	{
		double newColor;
		newColor= 0.3+ 3*Math.pow((Math.pow(1.2, getRamzorColor().getColorValue())*(contagiousPercent()-0.35)),5);
		setRamzorColor(colorByValue(newColor));
		return getRamzorColor(); 
	}
	
	/**
	 * return moshav in string form
	 */
	@Override
	public String toString(){
		return "Settlement type: Moshav\n" + super.toString();
	}
}
