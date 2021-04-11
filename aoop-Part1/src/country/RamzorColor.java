package country;

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
}

