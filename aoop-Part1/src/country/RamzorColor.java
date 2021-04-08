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
	
	private final double value;
}
