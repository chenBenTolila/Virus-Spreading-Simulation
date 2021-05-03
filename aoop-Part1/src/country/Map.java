package country;

import javax.swing.JButton;
import location.*;
import java.awt.Color;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public class Map {
	
	/**
	 * constructor
	 */
	public Map()
	{
		m_settlements = new Settlement[0];   // create an empty map
	}
	
	public void resetMap()
	{
		m_settlements = new Settlement[0];
	}
	
	/**
	 * the method adds a settlement to the map
	 * @param s - the settlement we want to add
	 * @return boolean according to if the add succeeded or failed
	 */
	public Settlement[] addSettlement(Settlement s){
		Settlement[] newArray = new Settlement[m_settlements.length + 1];  // create a new settlement array
		int i;
		for(i=0; i < m_settlements.length; ++i)   // go over the existing settlements array
			newArray[i] = m_settlements[i];    // copy the settlements to the new array
		newArray[i] = s;  // copy the new settlement
		m_settlements = newArray;  
		return m_settlements;
	}
	
	/**
	 * return the map in string form
	 */
	public String toString()   
	{
		String s = "=======================================\n"; 
		s += "The Map:\n" ;
		for(int i=0;i<m_settlements.length;++i) {
			s+= m_settlements[i].toString()+"\n";
			if(i != (m_settlements.length -1))
				s += "-----------------------\n";
		}
		s += "=======================================\n";
		return s;
	}
	
	
	
	/**
	 * Initialize 1% of the population as sick
	 */
	public void addSickToMap(double p)
	{
		for(int i = 0; i < m_settlements.length; ++i)  // go over the settlements
		{
			m_settlements[i].intializeSickPeople(p);    // turn 1% of settlement's population into sick people
		}
	}
	 /**
	  * try to contagion 3 people with each sick person
	  */
	public void contagionSimu() {
		for(int i = 0; i < m_settlements.length; ++i)  // go over the settlements
		{
			m_settlements[i].tryToInfectThree();   
		}
	}
	
	/**
	 * 
	 * @param s1 get first name of settlement
	 * @param s2 get second name of settlement
	 */
	public void connectSettlements(String s1, String s2) {
		int index=-1;
		for(int i=0; i < m_settlements.length; ++i) {
			if((s1.equals(m_settlements[i].getSettlementName())) || (s2.equals(m_settlements[i].getSettlementName())))
				if(index==-1)
					index=i;
				else {
					m_settlements[i].addConnectedSettlement(m_settlements[index]);
					m_settlements[index].addConnectedSettlement(m_settlements[i]);
				}
		}
	}
	
	/**
	 * make the sick people in all settlement convalescent if passed 25 days
	 */
	public void sickToConvalecent() {
		for(int i=0; i < m_settlements.length; ++i) {
			m_settlements[i].makeConvalescent();
		}
	}
	
	/**
	 * vaccinated healthy people in settlement
	 */
	public void vaccinatedSettlement() {
		for(int i=0; i < m_settlements.length; ++i) {
			m_settlements[i].vaccinatedPeople();
		}
	}
	/**
	 * 
	 * @return create the data of all settlement
	 */
	public String[][] makeData(){
		String[][] data= new String [m_settlements.length][7];
		for(int i=0; i < m_settlements.length; ++i) {
			 data[i][0]=  m_settlements[i].getSettlementName();
			 data[i][1]=  m_settlements[i].getSettlementType();
			 data[i][2]=  "" + m_settlements[i].getRamzorColor();
			 data[i][3]=  String.valueOf(m_settlements[i].contagiousPercent());
			 data[i][4]=  String.valueOf(m_settlements[i].getNumVDoses());
			 data[i][5]=  String.valueOf(m_settlements[i].getNumDead());
			 data[i][6]=  String.valueOf(m_settlements[i].getPeopleAmount());					 
		}
		return data;
	}
	/**
	 * start transfer from settlement
	 */
	public void transferSettlement() {
		for(int i=0; i < m_settlements.length; ++i) {
			m_settlements[i].tryToTransfer();
		}
	}
	/**
	 * 
	 * @return  number of settlements
	 */
	public int getNumOfSettlement() {
		return m_settlements.length;
	}
	
	/**
	 * 
	 * @param index get index of settlement
	 * @return location of settlement
	 */
	public Point getLocation(int index)   // maybe not needed!!!!!!
	{
		return new Point(m_settlements[index].getLocation().getPointX(),m_settlements[index].getLocation().getPointY());
	}
	
	/**
	 * 
	 * @param index of a settlement in the settlements array
	 * @return the location of the chosen settlement
	 */
	public Location getIndexLocation(int index)
	{
		if(index < m_settlements.length)
			return m_settlements[index].getLocation();
		return null;
	}
	
	/**
	 * 
	 * @param index of a settlement in the settlements array
	 * @return 
	 */
	public Color getIndexColor(int index)
	{
		if(index < m_settlements.length)
			return m_settlements[index].getSetColor();
		return null;
	}
	
	public String getIndexColorString(int index)
	{
		if(index < m_settlements.length)
			return m_settlements[index].getRamzorColor().getColorInString();
		return null;
	}
	
	public Point[] connectedSettlements(int index) {
		return m_settlements[index].connectedMiddlePoints();
	}
	
	public String getIndexSettName(int index)
	{
		if(index < m_settlements.length)
			return m_settlements[index].getSettlementName();
		return null;
	}
	
	public String getIndexSettType(int index)
	{
		if(index < m_settlements.length)
			return m_settlements[index].getSettlementType();;
		return null;
	}
	
	public String getIndexPercSick(int index)
	{
		if(index < m_settlements.length)
			return String.valueOf(m_settlements[index].contagiousPercent());;
		return null;
	}
	
	public int getIndexNumVDoses(int index)
	{
		if(index < m_settlements.length)
			return m_settlements[index].getNumVDoses();
		return -1;
	}
	
	public int getIndexNumDead(int index)
	{
		if(index < m_settlements.length)
			return m_settlements[index].getNumDead();
		return -1;
	}
	
	public int getIndexPeopleAmount(int index)
	{
		if(index < m_settlements.length)
			return m_settlements[index].getPeopleAmount();
		return -1;
	}
	
	/**
	 * 
	 * @param index - an index of a settlement in the settlement array
	 * @param x - the x coordinate of a point
	 * @param y - the y coordinate of a point
	 * @return if the point is in the chosen settlement's area
	 */
	public boolean isPointInSetIndex(int index, int x, int y)
	{
		if(index < m_settlements.length)
			return m_settlements[index].isPointInSet(x, y);
		return false;
	}
	
	public void setIndexNumDoses(String name, int doses) {
		for(int i=0; i< m_settlements.length; ++i) {
			if(m_settlements[i].getSettlementName().equals(name)) {
				m_settlements[i].addVDoses(doses);
				break;
			}
		}
	}
			
	private Settlement[] m_settlements;    // the list of settlements in the simulation
}
