package country;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public enum RamzorColor 
{
	GREEN, YELLOW, ORANGE, RED;
	
	
	public double getColorValue()
	{
		switch(this)
		{
		case GREEN:
			return 0.4;
			
		case YELLOW:
			return 0.6;
			
		case ORANGE:
			return 0.8;
			
		case RED:
			return 0.5;
			
		 default:
			 return 0;
		}
	}
	
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
}

