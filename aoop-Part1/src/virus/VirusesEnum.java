package virus;


/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public enum VirusesEnum {
	BRITISH("BritishVariant"),
	CHINESE("ChineseVariant"),
	SOUTHAFRICAN("SouthAfrican");
	
	private VirusesEnum(String virus)
	{
		m_virus = virus;
	}
	
	public static String getVirusNameByIndex(int index)
	{
		switch(index)
		{
		case 0:
			return "BritishVariant";
		case 1:
			return "ChineseVariant";
		case 2: 
			return "SouthAfrican";
		default: 
			return null;
		}
	}
	
	String m_virus;
}
