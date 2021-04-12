package country;
import location.*;
import population.*;
import virus.*;
import java.util.Random;

public abstract class Settlement {
	
	/**
	 * the constructor
	 * @param name - the settlement name
	 * @param location the settlement location
	 * @param ramzorColor - the settlement ramzor color
	 */
	public Settlement(String name, Location location, RamzorColor rc) {
		m_name = name;
		m_location = new Location(location);   // to build a copy constructor in location or to get point and size
		m_ramzorColor = colorByValue(rc.getColorValue());
		m_people = new Person[0];
	}
	
	
	/**
	 * 
	 * @return the settlement name
	 */
	public String getSettlementName()
	{
		return m_name;
	}
	
	/**
	 * the method change the ramzor color 
	 * @param rc - the color we want to change to
	 */
	protected void setRamzorColor(RamzorColor rc)
	{
		m_ramzorColor = rc;
	}
	
	/**
	 * 
	 * @return the ramzor color of the settlement
	 */
	public RamzorColor getRamzorColor() { 
		return m_ramzorColor;
	}
	

	/**
	 * return the settlement in string form
	 */
	public String toString()   
	{
		String s= "name: " + m_name + "\nlocation: " + m_location.toString()+"people in the settlement:\n";
		for(int i=0;i<m_people.length;++i) {
			s+= m_people[i].toString()+"\n";
		}
		s+="ramzor color: " + m_ramzorColor.getColorInString();
		return s;
		
	}
	
	public abstract RamzorColor calculateRamzorGrade();  // calculate the new color of the settlement
	
	
	/**
	 * 
	 * @return the ratio of sick people in the settlement
	 */
	public double contagiousPercent(){
		int peopleCount= m_people.length;
		double countSicks=0;
		for(int i=0; i<peopleCount; ++i) {
			if(m_people[i].checkIfHealthy()==false)
				countSicks ++; 
		}
		return countSicks/peopleCount;   // check if the result is double type ////
	}
	
	
	/**
	 * the function creates a random point in the settlement area
	 * @return the random point
	 */
	public Point randomLocation()
	{
		Random rand = new Random();
		int xMin = m_location.getPointX();
		int yMax = m_location.getPointY();
		int xMax = xMin + m_location.getSizeWidth();
		int yMin = yMax - m_location.getSizeHeight();
		Point randPoint = new Point(rand.nextInt(xMax- xMin +1) + xMin, rand.nextInt(yMax- yMin +1) + yMin);
		return randPoint;
	}
	
	/**
	 * the method adds a person to the settlement people array
	 * @param p - a person
	 * @return return if it succeed to add the person to the array
	 */
	public boolean addPerson(Person p){
		Person[] newArray = new Person[m_people.length + 1];  // check if the allocation succeed
		int i;
		for(i=0; i < m_people.length; ++i)
			newArray[i] = m_people[i];
		newArray[i] = p;  // adding p itself///
		m_people = newArray;
		return true;
	}
	
	/**
	 * the function gets a person and search him in the person array of the settlement
	 * @param p - a person
	 * @return  the index of the person in the array of people, if he isn't there the function returns -1
	 */
	private int getPersonIndex(Person p)
	{
		for(int i = 0; i< m_people.length; i++)
			if(m_people[i] == p)    // if they reference to the same person
				return i;
		return -1;		
	}
	
	/**
	 * the method removes p from the settlement people array
	 * @param p - a person
	 * @return return if the method succeed to remove or not
	 */
	public boolean removePersonFromArr(Person p)
	{
		if(getPersonIndex(p) != -1)
		{
			Person[] newArray = new Person[m_people.length - 1];
			int j = 0; 
			for(int i=0; i<m_people.length; ++i)
				if(getPersonIndex(p) == i)
					--j;
				else
					newArray[j] = m_people[i];
				++j;
			return true;
		}
		else
			return false;
	}
	
	/**
	 * 
	 * @param p - the person we want to transfer
	 * @param s - the settlement we want to transfer the person to
	 * @return true if the transfer succeeded
	 */
	public boolean transferPerson(Person p, Settlement s){
		if(getPersonIndex(p) != -1)
		{
			if(removePersonFromArr(p))
				return s.addPerson(p);	
		}
		return false;
	}
	
	/**
	 * 
	 * @param val - a double that holds the settlement coefficient of the disease
	 * @return  return the color the value match
	 */
	public RamzorColor colorByValue(double val)
	{
		if(val <= 0.4)
			return RamzorColor.GREEN;
		else if(val <= 0.6)
			return RamzorColor.YELLOW;
		else if(val <= 0.8)
			return RamzorColor.ORANGE;
		else 
			return RamzorColor.RED;
		}
	
	/**
	 * 
	 * @return the amount of people in the settlement
	 */
	public int getPeopleAmount()  
	{
		return m_people.length;
	}
	
	/**
	 * the method turns 1% of the people in the settlement into sick people
	 */
	public void intializeSickPeople()
	{
		BritishVariant britV = new BritishVariant();
		ChineseVariant chinV = new ChineseVariant();
		SouthAfricanVariant sAfriV = new SouthAfricanVariant();
		for(int i =0; i< m_people.length / 100; ++i)
		{
			if (i % 3 == 0)
				m_people[i].contagion(sAfriV);
			else if (i% 3 == 1)
				m_people[i].contagion(chinV);
			else
				m_people[i].contagion(britV);
		}
	}
	
	/**
	 * try to infect 6 healthy persons in virus
	 */
	public void tryToInfectSix() {
		int count=0;
		int j, i;
		Random rand = new Random();
		Person[] sickArr=new Person[0];
		for(i =0; i< m_people.length; ++i)
		{
			if(m_people[i].checkIfHealthy()==false) {
				Person[] newArray = new Person[m_people.length + 1];  // check if the allocation succeed
				for(j=0; j < sickArr.length; ++j)
					newArray[j] = sickArr[j];
				newArray[j] = m_people[i];  // adding p itself///
				sickArr = newArray;
			}
		}
		for(i =0; i< sickArr.length; ++i) {
			while(count<6) {
				j=rand.nextInt(m_people.length);
				if(sickArr[i].getVirus().tryToContagion(sickArr[i], m_people[j])) 
					m_people[j].contagion(sickArr[i].getVirus());
				count++;	
			}
			count=0;
		}
	}
		
	
	
	// attributes
	private String m_name;
	private Location m_location;
	private Person[] m_people;
	private RamzorColor m_ramzorColor;
	
}
