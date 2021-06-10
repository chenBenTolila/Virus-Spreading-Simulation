package virus;


public class VirusFactory {
	
	/**
	 *	a virus factory method
	 * @param index - an index of a virus
	 * @return the relevant virus
	 */
	public IVirus getVirus(String virus)
	{
		switch(virus)
		{
		case 1:
			return new BritishVariant();
		case 2:
			return new ChineseVariant();
		case 3: 
			return new SouthAfricanVariant();
		default: 
			return null;
		}
	}
}
