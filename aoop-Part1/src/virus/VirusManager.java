package virus;

public class VirusManager {
	private static VirusManager instance = null; 
	
	/***
	 * private ctor
	 */
	private VirusManager() { } 
	
	/***
	 * 
	 * @return the the virus manager instance
	 */
	public static VirusManager getVirusManager() { 
		if(instance == null) { 
			instance = new VirusManager(); 
		} 
		return instance; 
	} 
	
	
	public VirusesEnum getRandomVirus(VirusesEnum virus)
	{
		VirusesEnum v[] = VirusesEnum.values();
		int index = -1;
		for(int i = 0; i < v.length; ++i)
		{
			if(virus.equals(v[i]))
				index = i;
		}
		
	}

}
