package country;

public enum RamzorColor {
	GREEN(0.4),
	YELLOW(0.6),
	ORANGE(0.8),
	RED(0.5);
	
	private RamzorColor(double value)
	{
		this.value = value;
	}
	
	public double getValue()
	{
		return this.value;
	}
	
	public RamzorColor getColor(double PromotesDisease) {
		if(PromotesDisease < GREEN.getValue())
			return GREEN;
		else if(PromotesDisease < RED.getValue())
			return RED;
		else if(PromotesDisease < YELLOW.getValue())
			return YELLOW;
		else
			return ORANGE;
		
	}
	
	private final double value;
}
