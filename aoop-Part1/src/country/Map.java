package country;


public class Map {
	
	/**
	 * constructor
	 */
	public Map()
	{
		m_settlements = new Settlement[0];
	}
	

	
	/**
	 * the method adds a settlement to the map
	 * @param s - the settlement we want to add
	 * @return boolean according to if the add succeeded or failed
	 */
	public Settlement[] addSettlement(Settlement s){
		Settlement[] newArray = new Settlement[m_settlements.length + 1];  // check if the allocation succeed
		int i;
		for(i=0; i < m_settlements.length; ++i)
			newArray[i] = m_settlements[i];
		newArray[i] = s;  
		m_settlements = newArray;
		return m_settlements;
	}
	
	/**
	 * return the map in string form
	 */
	public String toString()   
	{
		String s= "Map:\n" ;
		for(int i=0;i<m_settlements.length;++i) {
			s+= m_settlements[i].toString()+"\n";
		}
		return s;
	}
	
	
	public boolean setSettlements(Settlement[] sett)
	{
		m_settlements = sett;
		return true;
	}
	
	/**
	 * the 1% of the population in the to sick
	 */
	public void addSickToMap()
	{
		for(int i = 0; i < m_settlements.length; ++i)
		{
			m_settlements[i].intializeSickPeople();
		}
	}
	 /**
	  * try to contagion 6 people
	  */
	public void contagionSimu() {
		for(int i = 0; i < m_settlements.length; ++i)
		{
			m_settlements[i].tryToInfectSix();
		}
	}
	
	// data member
	private Settlement[] m_settlements;
}
