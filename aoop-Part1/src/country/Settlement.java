package country;
import location.*;
import population.*;
import simulation.Clock;
import virus.*;
import java.util.Random;
import java.awt.Color;


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
		m_ramzorColor = RamzorColor.colorByValue(rc.getColorValue());
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
	 * 
	 * @return the color of the settlement in color form
	 */
	public Color getSetColor()
	{
		return m_ramzorColor.getColor();
	}
	

	/**
	 * return the settlement in string form
	 */
	@Override
	public String toString()   
	{
		String s= "Settlement name: " + m_name + "\nlocation: " + m_location.toString();
		s+="\nramzor color: " + m_ramzorColor.getColorInString();
		s+= "number of vaccine doses: "+ m_numVDoses+"\n";
		s+= "max people in settlement: "+ m_maxPeople+"\n";
		s+= "number of dead: "+ m_numDead+"\n";
		s += "\nconnected to settlement: \n";
		if(m_connectS.length == 0)
			s += "no connected settlement\n";
		else
			for(int i=0; i < m_connectS.length;++i){
				s+= m_connectS[i].getSettlementName() + "\n";
			}
		s += "\npeople in the settlement:\n";
		if(m_people.length == 0 && m_sickPeople.length == 0 )     // to string of all the citizens
			s += "no people currently in the settlement\n";
		else
			for(int i=0;i<m_people.length;++i) {
				s+= m_people[i].toString()+"\n";
			}
		s += "\nsick people in the settlement:\n";
		if(m_sickPeople.length == 0  && m_people.length != 0)
			s += "no sick people currently in the settlement\n";
		else
			for(int i=0; i < m_sickPeople.length;++i){
				s+= m_sickPeople[i].toString()+"\n";
			}
		return s;
	}
	
	public abstract RamzorColor calculateRamzorGrade();  // Abstract - calculate the new color of the settlement
	
	public abstract String getSettlementType(); // return the type of all settlement 
	
	/**
	 * 
	 * @return the ratio of sick people in the settlement
	 */
	public double contagiousPercent(){
		double countSicks=m_sickPeople.length;    // the amount of sick people
		double peopleCount= m_people.length + countSicks;   // the number of citizens in the settlement
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
		if (getMaxPeople() <= getPeopleAmount())
			return false;
		Person[] newArray = new Person[m_people.length + 1];// create a new array of people with size plus 1
		for(i=0; i < m_people.length; ++i)   // go over the people
			newArray[i] = m_people[i];   // copy them to the new array
		newArray[i] = p;  // adding p itself///
		m_people = newArray;
		calculateRamzorGrade();
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
		if (getMaxPeople() <= getPeopleAmount())
			return false;
		Sick[] newArray = new Sick[m_sickPeople.length + 1];
		for(i=0; i < m_sickPeople.length; ++i)   // go over the people
			newArray[i] = m_sickPeople[i];  
		newArray[i] = s;  // adding p itself///
		m_sickPeople = newArray;
		calculateRamzorGrade();
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
			calculateRamzorGrade();
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
			calculateRamzorGrade();
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
	 * @return the amount of people in the settlement
	 */
	public int getPeopleAmount()  
	{
		return m_people.length + m_sickPeople.length;   // the amount of healthy people + the amount of sick people
	}
	
	/**
	 * the method turns 1% of the healthy people in the settlement into sick people
	 */
	public void intializeSickPeople(double p)
	{
		// create an object for each of the variants
		BritishVariant britV = new BritishVariant();   
		ChineseVariant chinV = new ChineseVariant();
		SouthAfricanVariant sAfriV = new SouthAfricanVariant();
		// int numPeople = m_people.length + m_sickPeople.length;  check what is the 20%!!!!
		Random rand = new Random();
		int randVirus;
		if(p > 1) {
			System.out.println("Error - trying to infect more than the anount of healty people in settlement");
			return;
		}
		
		double turnSickNum = m_people.length * p;
		for(int i =0; i < turnSickNum; ++i)  // go over the first p percent of the people in the array
		{
			randVirus = rand.nextInt(3);
			if (randVirus == 0)    // infect the selected person in one of the variants
				m_people[0].contagion(sAfriV);
			else if (randVirus == 1)
				m_people[0].contagion(chinV);
			else
				m_people[0].contagion(britV);
		}
	}
	
	/**
	 * for 20% of the sick people in the settlement try to infect 3 healthy people
	 */
	public void tryToInfectThree() {
		int count=0;   // the number of attempted contagion for each sick person
		int j, i;   // keep the indexes for the arrays
		Random rand = new Random();   // will randomize the selection of the person to try contage
		int sickNum = m_sickPeople.length;   // calculating 20% of the sick
		for(i =0; i < sickNum*0.2; ++i) {   // going over 20% of the sick
			while(count<3) {
				if(m_people.length == 0)
				{
					System.out.println("can't infect more, all the people are sick");
					return;
				}
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
	
	/**
	 * make sick people who passed 25 days to convalescent 
	 */
	public void makeConvalescent(){
		for(int i=0; i < m_sickPeople.length; ++i)   
			if(Clock.DaysPassed(m_sickPeople[i].getSicknessDuration())>=25) {
				addPerson(new Convalescent(m_sickPeople[i].getAge(), m_sickPeople[i].getLocation(), m_sickPeople[i].getSettlement(), m_sickPeople[i].getVirus()));
				removeSickPersonFromArr(m_sickPeople[i]);
			}
	}
	
	/**
	 * make healthy people to vaccinated 
	 */
	public void vaccinatedPeople(){
		for(int i=0; i < m_people.length; ++i) {
			if(m_people[i].checkIfHealthy() && m_numVDoses>0) {
				addPerson(new Vaccinated(m_people[i].getAge(), m_people[i].getLocation(), m_people[i].getSettlement(), Clock.now()));
				removePersonFromArr(m_people[i]);
				m_numVDoses--;
			}
		}
	}
	
	/**
	 * 
	 * @return the number of the sick people
	 */
	public int getNumOfSick() {
		return m_sickPeople.length;
	}
	
	/**
	 * try to transfer 3% people from settlement
	 */
	public void tryToTransfer()
	{
		if(m_connectS.length == 0)
			return;
		Random rand = new Random();
		int gp, gs;
		double amountOfTransfers = this.getPeopleAmount() *0.03;
		for(int i=0; i<amountOfTransfers; ++i) {
			gp=rand.nextInt(this.getPeopleAmount());
			gs=rand.nextInt(m_connectS.length);
			if(gp<m_people.length) {
				transferPerson(m_people[gp],m_connectS[gs]);
			}
			else {
				transferPerson(m_sickPeople[gp- m_people.length],m_connectS[gs]);
			}
		}
	}
	/**
	 * 
	 * @return location of settlement
	 */
	public Location getLocation() {
		return new Location(m_location);
	}
	/**
	 * 
	 * @return point in the middle of settlement
	 */
	public Point middleOfSettlement() {
		return new Point(m_location.getPointX()+m_location.getSizeWidth()/2,m_location.getPointY()+m_location.getSizeHeight()/2);
	}
	
	//maybe not
	public int getNumConeccted() {
		return m_connectS.length;
	}
	
	/**
	 * 
	 * @return array of middle points of all connected settlements to settlement
	 */
	public Point[] connectedMiddlePoints() {
		Point[] pm=new Point[m_connectS.length+1];
		pm[0]=this.middleOfSettlement();
		for(int i=1;i<=m_connectS.length;++i) {
			pm[i]=m_connectS[i-1].middleOfSettlement();
		}
		return pm;
	}
	
	/**
	 * 
	 * @param x - the x coordinate of a point 
	 * @param y - the y coordinate of a point
	 * @return if the point is in the area of the settlement
	 */
	public boolean isPointInSet(int x, int y)
	{
		if ((m_location.getPointX() <= x) && (m_location.getPointX() + m_location.getSizeWidth() >= x))
			if((m_location.getPointY() <= y) && (m_location.getPointY() + m_location.getSizeHeight() >= y))
				return true;
		return false;
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
