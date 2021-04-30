package country;
import location.*;
import population.*;
import virus.*;
import java.util.Random;


/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public abstract class Settlement {
	
	/**
	 * the constructor
	 * @param name - the settlement name
	 * @param location the settlement location
	 * @param ramzorColor - the settlement ramzor color
	 */
	public Settlement(String name, Location location, RamzorColor rc, int mp) {
		m_name = name;
		m_location = new Location(location);  
		m_ramzorColor = colorByValue(rc.getColorValue());
		m_people = new Person[0];     // create an empty array of healthy citizens
		m_sickPeople = new Sick[0];   // create an empty array of sick citizens
		m_maxPeople = mp;
		m_numVDoses = 0;
		m_connectS = new Settlement[0];  // create an empty array of connected settlements
		m_numDead =0;
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
	protected RamzorColor getRamzorColor() { 
		return m_ramzorColor;
	}
	

	/**
	 * return the settlement in string form
	 */
	@Override
	public String toString()   
	{
		String s= "Settlement name: " + m_name + "\nlocation: " + m_location.toString();
		s+="\nramzor color: " + m_ramzorColor.getColorInString();
		s += "\npeople in the settlement:\n";
		if(m_people.length == 0)     // to string of all the citizens
			s += "no people currently in the settlement\n";
		else
			for(int i=0;i<m_people.length;++i) {
				s+= m_people[i].toString()+"\n";
		}
		return s;
		
	}
	
	public abstract RamzorColor calculateRamzorGrade();  // Abstract - calculate the new color of the settlement
	
	
	/**
	 * 
	 * @return the ratio of sick people in the settlement
	 */
	public double contagiousPercent(){
		double countSicks=m_sickPeople.length;    // the amount of sick people
		int peopleCount= m_people.length + (int)countSicks;   // the number of citizens in the settlement
		return countSicks/peopleCount;   // check if the result is double type ////
	}
	
	
	/**
	 * the function creates a random point in the settlement area
	 * @return the random point
	 */
	public Point randomLocation()
	{
		Random rand = new Random();    // will help choose the coordinates randomly 
		int xMin = m_location.getPointX();    // the smallest x value for a point in the settlement
		int yMin = m_location.getPointY();    // the smallest y value for a point in the settlement
		int xMax = xMin + m_location.getSizeWidth();    // the biggest x value for a point in the settlement
		int yMax = yMin + m_location.getSizeHeight();   // the biggest y value for a point in the settlement
		Point randPoint = new Point(rand.nextInt(xMax - xMin +1) + xMin, rand.nextInt(yMax- yMin +1) + yMin);
		return randPoint;
	}
	
	/**
	 * the method adds a person to the settlement people array
	 * @param p - a person
	 * @return return if it succeed to add the person to the array
	 */
	public boolean addPerson(Person p){
		int i;
		if(getPersonIndex(p) != -1)   // check if p already exist in the people array
			return false;
		if (getMaxPeople() >= getPeopleAmount())
			return false;
		Person[] newArray = new Person[m_people.length + 1];// create a new array of people with size plus 1
		for(i=0; i < m_people.length; ++i)   // go over the people
			newArray[i] = m_people[i];   // copy them to the new array
		newArray[i] = p;  // adding p itself///
		m_people = newArray;
		return true;
	}
	
	/**
	 * 
	 * @param s get new sick person
	 * @return return if it succeed to add the sick person to the array
	 */
	public boolean addSickPerson(Sick s){
		int i;
		if(getSickPersonIndex(s) != -1)   // check if s already exist in the sick people array
			return false;
		if (getMaxPeople() >= getPeopleAmount())
			return false;
		Sick[] newArray = new Sick[m_sickPeople.length + 1];
		for(i=0; i < m_sickPeople.length; ++i)   // go over the people
			newArray[i] = m_sickPeople[i];  
		newArray[i] = s;  // adding p itself///
		m_sickPeople = newArray;
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
	 * the function gets a sick person and search him in the sick people array of the settlement
	 * @param p - a sick person
	 * @return  the index of the person sick in the array of sick people, if he isn't there the function returns -1
	 */
	private int getSickPersonIndex(Sick p)
	{
		for(int i = 0; i< m_sickPeople.length; i++)
			if(m_sickPeople[i] == p)    // if they reference to the same person
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
		if(getPersonIndex(p) != -1)    // if p is in the settlement
		{
			Person[] newArray = new Person[m_people.length - 1];   // create a new array of people with size minus 1
			int j = 0; 
			for(int i=0; i<m_people.length; ++i) {   // go over the people 
				if(getPersonIndex(p) == i)    
					--j;
				else
					newArray[j] = m_people[i];   // copy everyone to the new array except for p
				++j;
			}
			m_people = newArray;
			return true;
		}
		else
			return false;
	}
	
	/**
	 * the method removes p from the settlement sick people array
	 * @param p - a sick person
	 * @return return if the method succeed in removing or not
	 */
	public boolean removeSickPersonFromArr(Sick p)
	{
		if(getSickPersonIndex(p) != -1)    // if p is in the settlement
		{
			Sick[] newArray = new Sick[m_sickPeople.length - 1];   // create a new array of sick people with size minus 1
			int j = 0; 
			for(int i=0; i<m_sickPeople.length; ++i) {   // go over the people 
				if(getSickPersonIndex(p) == i)    
					--j;
				else
					newArray[j] = m_sickPeople[i];   // copy everyone to the new array except for p
				++j;
			}
			m_sickPeople = newArray;
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
		if (s.getMaxPeople() >= s.getPeopleAmount())   // check if there is place in the settlement s
			return false;
		Random rand= new Random();
		double prob = m_ramzorColor.getPTransfer()*s.m_ramzorColor.getPTransfer();
		if(rand.nextDouble() >= prob ) {
			if(getPersonIndex(p) != -1)   // check if is in this settlement
			{
				if(removePersonFromArr(p))    // remove p from this settlement
					return s.addPerson(p);	  // add p to the new settlement
			}
		}
		return false;    // return if the the transfer succeeded or failed
	}
	
	/**
	 * 
	 * @param p - the sick person we want to transfer
	 * @param s - the settlement we want to transfer the sick person to
	 * @return true if the transfer succeeded
	 */
	public boolean transferSickPerson(Sick p, Settlement s){
		if (s.getMaxPeople() >= s.getPeopleAmount())   // check if there is place in the settlement s
			return false;
		Random rand= new Random();
		double prob = m_ramzorColor.getPTransfer()*s.m_ramzorColor.getPTransfer();
		if(rand.nextDouble() >= prob ) {
			if(getSickPersonIndex(p) != -1)   // check if is in this settlement
			{
				if(removeSickPersonFromArr(p))    // remove p from this settlement
					return s.addSickPerson(p);	  // add p to the new settlement
			}
		}
		return false;    // return if the the transfer succeeded or failed
	}
	
	/**
	 * 
	 * @param val - a double that holds the settlement coefficient of the disease
	 * @return  return the color the value match
	 */
	public RamzorColor colorByValue(double val)
	{
		if(val <= 0.4)    // return the ramzor that match the value
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
		return m_people.length + m_sickPeople.length;   // the amount of healthy people + the amount of sick people
	}
	
	/**
	 * the method turns 1% of the people in the settlement into sick people
	 */
	public void intializeSickPeople()
	{
		// create an object for each of the variants
		BritishVariant britV = new BritishVariant();   
		ChineseVariant chinV = new ChineseVariant();
		SouthAfricanVariant sAfriV = new SouthAfricanVariant();
		for(int i =0; i< m_people.length / 100; ++i)  // go over the first 1% of people in the array
		{
			if (i % 3 == 0)    // infect the selected person in one of the variants
				m_people[i].contagion(sAfriV);
			else if (i% 3 == 1)
				m_people[i].contagion(chinV);
			else
				m_people[i].contagion(britV);
		}
	}
	
	/**
	 * for in sick person in the settlement try to infect 6 healthy people
	 */
	public void tryToInfectSix() {
		int count=0;   // the number of attempted contagion for each sick person
		int j, i;   // keep the indexes for the arrays
		Random rand = new Random();   // will randomize the selection of the person to try contage
		for(i =0; i< m_sickPeople.length; ++i) {   // going over all the sick
			while(count<6) {
				j=rand.nextInt(m_people.length);    // choose a person to contage randomly
				try {
					if(m_sickPeople[i].getVirus().tryToContagion(m_sickPeople[i], m_people[j]))   // try to contage the selected person
						m_people[j].contagion(m_sickPeople[i].getVirus());
				}
				catch(RuntimeException ex)
				{
					System.out.println("A sick person cannot become sick again");   // the chosen person is already sick
				}
				count++;	
			}
			count=0;
		}
	}
	
	/**
	 * 
	 * @param vd get more vaccines doses
	 */
	public void addVDoses(int vd) {
		if(vd>0)
			m_numVDoses+=vd;
	}
	
	/**
	 * 
	 * @return the number of vaccines doses
	 */
	public int getNumVDoses() {
		return m_numVDoses;
	}
	
	/**
	 * 
	 * @return the max number of people in settlement
	 */
	public int getMaxPeople() {
		return m_maxPeople;
	}
	
	/**
	 * the function gets a settlement and search it in the connected settlements array 
	 * @param p - a settlement
	 * @return  the index of the settlement in the array of connected settlements, if it isn't there the function returns -1
	 */
	private int getConnectSetIndex(Settlement s)
	{
		for(int i = 0; i< m_connectS.length; i++)
			if(m_connectS[i] == s)    // if they reference to the same person
				return i;
		return -1;		
	}
	
	/**
	 *  the method add a settlement to the list of the connected settlements
	 * @param a - a settlement
	 * @return  if the method succeeded to add the settlement to the list
	 */
	public boolean addConnectedSettlement(Settlement a)
	{
		if (getConnectSetIndex(a) == -1)  // check if a doesn't already exist in the list
		{
			Settlement temp[] = new Settlement[m_connectS.length+1];   // create new allocation
			int i;
			for(i =0; i < m_connectS.length; ++i)
				temp[i] = m_connectS[i];
			temp[i] = a;
			m_connectS = temp;
		}
		return true;
	}
	
	/**
	 * add one new dead people
	 */
	public void addNewDead() {
		m_numDead++;
	}
	
	/**
	 * 
	 * @return number of dead people
	 */
	public int getNumDead() {
		return m_numDead;
	}
	
	// attributes
	private String m_name;    // the  name of the settlement
	private Location m_location;    // the location of the settlement
	private Person[] m_people;    // the list of the people in the settlement
	private RamzorColor m_ramzorColor;   // the ramzor color of the settlement
	private int m_maxPeople; // the max number of people in settlement
	private int m_numVDoses; // the number of vaccines doses
	private Sick[] m_sickPeople; // the list of the sick people in settlement
	private Settlement[] m_connectS; // the array of close settlements
	private int m_numDead; // the number of dead people
}
