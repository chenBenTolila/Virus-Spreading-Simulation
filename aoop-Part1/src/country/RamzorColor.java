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
	
	
	private RamzorColor(double cSick, Color color, double pTransfer) {
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
	
	
	private double cSick;
	private Color color;
	private double pTransfer;
	
}

