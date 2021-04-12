package country;

import population.Person;

public class Map {
	
	private Settlement[] m_settlement;
	
	
	public boolean addSettlement(Settlement s){
		Settlement[] newArray = new Settlement[m_settlement.length + 1];  // check if the allocation succeed
		int i;
		for(i=0; i < m_settlement.length; ++i)
			newArray[i] = m_settlement[i];
		newArray[i] = s;  // adding p itself///
		m_settlement = newArray;
		return true;
	}
}
