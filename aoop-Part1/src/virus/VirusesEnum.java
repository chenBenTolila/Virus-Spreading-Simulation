package virus;


public enum VirusesEnum {
	BRITISH("BritishVariant"),
	CHINESE("ChineseVariant"),
	SOUTHAFRICAN("SouthAfrican");
	
	private VirusesEnum(String virus)
	{
		m_virus = virus;
	}
	
	
	
	String m_virus;
}
