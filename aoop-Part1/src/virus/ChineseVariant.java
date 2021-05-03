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

public class ChineseVariant implements IVirus {
	
	
	/**
	 * return the probability to get infected in Chinese variant according to the person age
	 */
	public double contagionProbability(Person p){
		if(p.getAge() < 18)    // get the contagion probability in accordance with the person age
			return p.contagionProbability()*infectProb18;
		else if(p.getAge() <= 55)
			return p.contagionProbability()*infectProb55;
		else 
			return p.contagionProbability()*infectProbUp55;
	}
	
	
	/**
	 * if the second person is healthy check if he got infected by the first person
	 */
	public boolean tryToContagion(Sick p1, Person p2){
		
		if(mutations.length == 0)
			return false;
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
	  * the method infect the person in the variant
	  * @param s - a variant name
	  * @param p - a person 
	  * @return if the infection succeeded or not
	  */
	 public boolean contage(String s, Person p)
	 {
		 // creating all the variants kinds
		 BritishVariant britV = new BritishVariant();   
		 SouthAfricanVariant sAfriV = new SouthAfricanVariant();
		 if(s.equals("British variant")) 
		 {
			 if (BritishVariant.getCanContage() == true)   // checking this the variant can contage (according to it's status)
				 {
				 	p.contagion(britV);  // infecting the person in this variant
				 	return true;
				 }
			 else return false;
		 }
		 else if(s.equals("Chinese variant"))
		 {
			 if (ChineseVariant.getCanContage() == true)  // checking that this variant can contage (according to it's status)
				 {
				 	p.contagion(this);   // infecting the person in this variant
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
		 BritishVariant britV = new BritishVariant();
		 SouthAfricanVariant sAfriV = new SouthAfricanVariant();
		 
		 if(s.equals("British variant")) {
			 if (BritishVariant.getCanContage() == true)  // checking this the variant can contage (according to it's status)
				 return britV.contagionProbability(p);
			 else return 0;
		 }
		 else if(s.equals("Chinese variant"))
			 if (ChineseVariant.getCanContage() == true)  // checking this the variant can contage (according to it's status)
				 return contagionProbability(p);
			 else return 0;
		 	
		 else
			 if(SouthAfricanVariant.getCanContage() == true)  // checking this the variant can contage (according to it's status)
				 return sAfriV.contagionProbability(p);
			 else return 0;
	 }
	
	
	/**
	 * return true if the person died from the virus
	 */
	public boolean tryToKill(Sick s){
		Random rand = new Random();   // will keep the random probability of the person dying
		double probToDie;
		if(s.getAge() < 18)
			probToDie = Math.max(0, dieProb18-0.01*dieProb18*Math.pow((s.getSicknessDuration()-15),2));   // calculate the probability to die if the person is 18 and under
		else if(s.getAge() <= 55)
			probToDie = Math.max(0, dieProb55-0.01*dieProb55*Math.pow((s.getSicknessDuration()-15),2));   // calculate the probability to die if the person is 19 - 55
		else
			probToDie = Math.max(0, dieProbUp55-0.01*dieProbUp55*Math.pow((s.getSicknessDuration()-15),2));   // calculate the probability to die if the person is above 55
		return probToDie >= rand.nextDouble();  // exclude 1 - [0,1)  ///
	}
	
	
	/**
	 * return the Chinese variant in string form
	 */
	@Override
	public String toString()
	{
		return "Chinese variant";
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
	
	
	/**
	 * 
	 * @param virus - a variant name
	 * @param flag - equals true if we want to add the variant to mutations, and false to remove it from mutation.
	 */
	public static void editMutations(String virus, boolean flag)
	{
		if(flag)   // need to add the virus to mutations
		{
			if(checkVar(virus) == false)  // if the virus doesn't already exist in mutations 
			{
				// adding the virus to mutations
				String temp[] = new String[mutations.length + 1];
				int i;
				for( i = 0; i < mutations.length; ++i)
					temp[i] = mutations[i];
				temp[i] = virus;
				mutations = temp;
			}
			
			if(mutations.length != 0)  // check if this virus can develop into a different virus 
				canContage = true;
		}
		
		else  // need to delete the virus from mutations
		{ 
			if(checkVar(virus) == true)  // if the virus exist in mutations
			{
				// remove the virus from mutations
				String temp[] = new String[mutations.length - 1];
				int j = 0;
				for(int i = 0; i < mutations.length; ++i)
				{
					if(mutations[i].equals(virus))
						--j;
					else
						temp[j] = mutations[i];
					++j;
				}
				mutations = temp;
			}
			
			if(mutations.length == 0)  // check if this virus can develop into a different virus 
				canContage = false;
		}
	}
	
	/**
	 * 
	 * @param virus -  a virus name
	 * @return true if the virus is in mutations
	 */
	private static boolean checkVar(String virus)
	{
		for(int i = 0; i < mutations.length; ++i)
			if(virus.equals(mutations[i]))
				return true;
		return false;
	}
	
	
	// data members
	private static final double dieProb18 = 0.001; // The probability of dying by the age of 18
	private static final double dieProb55 = 0.05; // The probability of dying over the age of 18 to 55
	private static final double dieProbUp55 = 0.1; // The probability of dying over the age of 55
	private static final double infectProb18 = 0.2; //  The probability of infection by the age of 18 
	private static final double infectProb55 = 0.5; //  The probability of infection over the age of 18 to 55
	private static final double infectProbUp55 = 0.7;  // The probability of infection over the age of 55
	private static String mutations[] = new String[] {"Chinese variant"};
	private static boolean canContage = true;
}
