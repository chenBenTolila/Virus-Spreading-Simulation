package virus;
import population.*;
import java.util.Random;
import simulation.Clock;


/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public class BritishVariant implements IVirus {
	
	
	/**
	 * return the probability to get infected in British variant according to the person age
	 */
	 public double contagionProbability(Person p){
		return p.contagionProbability()*infectProbAll;
	 }
	 
	 
	 /**
	  * if the second person is healthy check if he got infected by the first person
	  */
	 public boolean tryToContagion(Sick p1, Person p2){
		 Random rand = new Random();   // the random probability of the person getting infected
		 double probToSick;   // the calculated probability
		 String vType;   // the variant type
		 double varSickProb;   // the probability of p2 to get sick infected in the variant according to his age
		 if(!(p2.checkIfSick())) {   // check if the p2 is healthy
			 if(Clock.DaysPassed(p1.getSicknessDuration()) < 5)   // check that p1 is sick for 5 or more days
				 return false;
			 vType = mutations[rand.nextInt(mutations.length)];
			 varSickProb = calcProbToSick(vType, p2);
			 if(varSickProb == 0)   // if the variant can contage at the moment (by status)
				 return false;
		
			 probToSick = varSickProb*Math.min(1,0.14*Math.pow(Math.E, 2-0.25*p1.distance(p2)));   // calculation of the probability
			 if (probToSick >= rand.nextDouble())  // exclude 1 - [0,1)  ///	
			 {
				 contage(vType, p2);  // infect p2 in the chosen variant
			 }
			 return true;
		 }
		 else
			 throw new RuntimeException();   // p2 is not healthy
	 }

	 /**
	  * return true if the person died from the virus
	  */
	 public boolean tryToKill(Sick s){
		 Random rand = new Random();   // will keep the random probability of the person dying
		 double probToDie;   // will keep the calculated probability
		 if(s.getAge() <= 18)
			 probToDie = Math.max(0, dieProb18-0.01*dieProb18*Math.pow((s.getSicknessDuration()-15),2));   // calculate the probability to die if the person is 18 and under
		 else
			 probToDie = Math.max(0, dieProbUp18-0.01*dieProbUp18*Math.pow((s.getSicknessDuration()-15),2));  // calculate the probability to die if the person in above 18
		 return probToDie >= rand.nextDouble();  // exclude 1 = [0,1)  ///
	 }
	
	 
	 /**
	  * return the British variant in string form
	  */
	 @Override
	 public String toString() {
		 return "British variant";
	 }
	 
	 /**
	  * the method infect the person in the variant
	  * @param s - a variant name
	  * @param p - a person 
	  * @return if the infection succeeded or not
	  */
	 public boolean contage(String s, Person p)
	 {
		 // creating all the variants kinds
		 ChineseVariant chinV = new ChineseVariant();   
		 SouthAfricanVariant sAfriV = new SouthAfricanVariant();
		 if(s.equals("British variant")) 
		 {
			 if (BritishVariant.getCanContage() == true)   // checking this the variant can contage (according to it's status)
				 {
				 	p.contagion(this);  // infecting the person in this variant
				 	return true;
				 }
			 else return false;
		 }
		 else if(s.equals("Chinese variant"))
		 {
			 if (ChineseVariant.getCanContage() == true)  // checking that this variant can contage (according to it's status)
				 {
				 	p.contagion(chinV);   // infecting the person in this variant
				 	return true;
				 }
			 else return false;
		 }
		 else
		 {
			 if (SouthAfricanVariant.getCanContage() == true)  // checking this the variant can contage (according to it's status)
				 {
				 	p.contagion(sAfriV);  // infecting the person in this variant
				 	return true;
				 }
			 else return false;
		 }
	 }
	 
	 /**
	  * 
	  * @param s - the name of the variant
	  * @param p - a person
	  * @return the probability of the person to get infected in the variant
	  */
	 public double calcProbToSick(String s, Person p)
	 {
		 // creating all the variant types
		 ChineseVariant chinV = new ChineseVariant();
		 SouthAfricanVariant sAfriV = new SouthAfricanVariant();
		 
		 if(s.equals("British variant")) {
			 if (BritishVariant.getCanContage() == true)  // checking this the variant can contage (according to it's status)
				 return contagionProbability(p);
			 else return 0;
		 }
		 else if(s.equals("Chinese variant"))
			 if (ChineseVariant.getCanContage() == true)  // checking this the variant can contage (according to it's status)
				 return chinV.contagionProbability(p);
			 else return 0;
		 	
		 else
			 if(SouthAfricanVariant.getCanContage() == true)  // checking this the variant can contage (according to it's status)
				 return sAfriV.contagionProbability(p);
			 else return 0;
	 }
	 
	/**
	 * updates the canContage value
	 * @param val - a boolean value
	 */
	public static void setCanContage(boolean val)
	{
		canContage = val;
	}
	
	/**
	 * 
	 * @return the value of canContage
	 */
	public static boolean getCanContage()
	{
		return canContage;
	}
	
	
	// data members
	private static final double dieProb18 = 0.01; // The probability of dying by the age of 18
	private static final double dieProbUp18 = 0.1; // The probability of dying over the age of 18
	private static final double infectProbAll = 0.7; // The probability of infection for all 
	private static String mutations[] = new String[] {"British variant"};
	private static boolean canContage = true;
}
