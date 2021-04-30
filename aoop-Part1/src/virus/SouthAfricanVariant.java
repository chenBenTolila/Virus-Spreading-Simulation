package virus;
import population.*;
import simulation.Clock;

import java.util.Random;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public class SouthAfricanVariant implements IVirus {
	
	
	/**
	 * return the probability to get infected in South African variant according to the person age
	 */
	public double contagionProbability(Person p){
		if(p.getAge() <= 18)   // get the contagion probability in accordance with the person age
			return p.contagionProbability()*infectProb18;
		else 
			return p.contagionProbability()*infectProbUp18;
	}
	
	
	/**
	 * if the second person is healthy check if he got infected by the first person
	 */
	public boolean tryToContagion(Sick p1, Person p2){
		 Random rand = new Random();   // the random probability of the person getting infected
		 double probToSick;   // the calculated probability
		 String vType;
		 double varSickProb;
		 if(!(p2.checkIfSick())) {   
			 if(Clock.DaysPassed(p1.getSicknessDuration()) < 5)
				 return false;
			 vType = mutations[rand.nextInt(mutations.length)];
			 varSickProb = calcProbToSick(vType, p2);
			 if(varSickProb == 0)
				 return false;
		
			 probToSick = varSickProb*Math.min(1,0.14*Math.pow(Math.E, 2-0.25*p1.distance(p2)));   // calculation of the probability
			 if (probToSick >= rand.nextDouble())  // exclude 1 - [0,1)  ///	
			 {
				 contage(vType, p2);
			 }
			 return true;
		 }
		 else
			 throw new RuntimeException();   // p2 is not healthy
	 }
	
	public boolean contage(String s, Person p)
	 {
		 ChineseVariant chinV = new ChineseVariant();
		 SouthAfricanVariant sAfriV = new SouthAfricanVariant();
		 if(s == "British variant") {
			 if (canContage == true)
				 {
				 	p.contagion(this);
				 	return true;
				 }
			 else return false;
		 }
		 else if(s == "Chinese variant")
		 {
			 if (canContage == true)
				 {
				 	p.contagion(chinV);
				 	return true;
				 }
			 else return false;
		 }
		 else
		 {
			 if (canContage == true)
				 {
				 	p.contagion(sAfriV);
				 	return true;
				 }
			 else return false;
		 }
	 }
	
	
	/**
	 * return true if the person died from the virus
	 */
	public boolean tryToKill(Sick s){
		Random rand = new Random();   // will keep the random probability of the person dying
		double probToDie;
		if(s.getAge() <= 18)
			probToDie = Math.max(0, dieProb18-0.01*dieProb18*Math.pow((s.getSicknessDuration()-15),2));   // probability to die for age 18 and under
		else
			probToDie = Math.max(0, dieProbUp18-0.01*dieProbUp18*Math.pow((s.getSicknessDuration()-15),2));  // probability to die for age above 18
		return probToDie >= rand.nextDouble();  // exclude 1 - [0,1)  ///
	}
	
	/**
	  * 
	  * @param s - the name of the variant
	  * @param p - a person
	  * @return the probability of the person to get infected in the variant
	  */
	 public double calcProbToSick(String s, Person p)
	 {
		 
		 BritishVariant britV = new BritishVariant();   
		 ChineseVariant chinV = new ChineseVariant();
		 if(s == "British variant")
			 return britV.contagionProbability(p);
		 else if(s == "Chinese variant")
			 return chinV.contagionProbability(p);
		 else
			 return contagionProbability(p);
	 }
	
	/**
	 * return the South African variant in string form
	 */
	@Override
	public String toString()
	{
		return "South African variant";
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
	private static final double dieProb18 = 0.05;   // The probability of dying by the age of 18
	private static final double dieProbUp18 = 0.08;    // The probability of dying over the age of 18
	private static final double infectProb18 = 0.6;    // The probability of infection by the age of 18
	private static final double infectProbUp18 = 0.5;    // The probability of infection over the age of 18
	private static String mutations[] = new String[] {"South African variant"};
	private static boolean canContage = true;
}
