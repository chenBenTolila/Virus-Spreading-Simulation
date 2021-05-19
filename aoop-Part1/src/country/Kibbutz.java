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
	public Kibbutz(String name, Location location, RamzorColor ramzorColor, int mp, Map map) {
		super(name, location, ramzorColor, mp, map);
	}
	/**
	 * return the type of settlement
	 */
	public String getSettlementType() {
		return "Kibbutz";
	}
	
	
	/**
	 * calculate the new color of the settlement
	 */
	@Override
	public RamzorColor calculateRamzorGrade()  
	{
		double newColor;    // the value of the new color
		newColor = 0.45 + Math.pow((Math.pow(1.5, getRamzorColor().getColorValue())*(contagiousPercent()-0.4)),3);    // calculate the new color
		setRamzorColor(RamzorColor.colorByValue(newColor));
		return getRamzorColor(); 
	}	
	
	/**
	 * return kibbutz in string form
	 */
	@Override
	public String toString(){
		return "settlement type: Kibbutz\n" + super.toString();
	}
}
