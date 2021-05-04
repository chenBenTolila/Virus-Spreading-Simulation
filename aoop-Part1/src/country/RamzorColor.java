package country;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

import java.awt.Color;

public enum RamzorColor 
{
	GREEN(0.4, Color.GREEN, 1), 
	YELLOW(0.6, Color.YELLOW, 0.8), 
	ORANGE(0.8, Color.ORANGE, 0.6), 
	RED(0.5, Color.RED, 0.4);
	
	/**
	 * constructor
	 * @param cSick Probability of sicks 
	 * @param color get color
	 * @param pTransfer Probability of transfer a settlement
	 */
	private RamzorColor(double pTransfer, Color color, double cSick) {
		this.cSick= cSick;
		this.color= color;
		this.pTransfer=pTransfer;
	}
	
	/***
	 * 
	 * @return the upper limit for each color
	 */
	public double getColorValue()   
	{
		return cSick;
	}
	
	/**
	 * 
	 * @return the color object of the ramzor color
	 */
	public Color getColor()   
	{
		return color;
	}
	
	/**
	 * 
	 * @return the transfer probability of the ramzor color
	 */
	public double getPTransfer() {
		return pTransfer;
	}
	
	/**
	 * @return get the color in a string format
	 */
	public String getColorInString()  
	{
		switch(this)
		{
		case GREEN:
			return "Green";
			
		case YELLOW:
			return "Yellow";
			
		case ORANGE:
			return "Orange";
			
		case RED:
			return "Red";
			
		default:
			return "no color";
		}
	}
	
	
	/**
	 * 
	 * @param val - a double that holds the settlement coefficient of the disease
	 * @return  return the color the value match
	 */
	public static RamzorColor colorByValue(double val)
	{
		if(val <= 0.4)    // return the ramzor that match the value
			return RamzorColor.GREEN;
		else if(val <= 0.6)
			return RamzorColor.YELLOW;
		else if(val <= 0.8)
			return RamzorColor.ORANGE;
		else 
			return RamzorColor.RED;
		}
	
	
	private double cSick; 
	private Color color; // save color
	private double pTransfer; // save probability of color transition
	
}

