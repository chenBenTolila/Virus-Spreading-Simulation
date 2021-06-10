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
	
	
	
	
	// start test
	
	/**
	  * if the second person is healthy check if he got infected by the first person
	  */
	 public boolean tryToContagion(Sick p1, Person p2){
		 
		 Random rand = new Random();   // the random probability of the person getting infected
		 double probToSick;   // the calculated probability
		 IVirus vType;   // the variant type
		 double varSickProb;   // the probability of p2 to get sick infected in the variant according to his age
		 if(!(p2.checkIfSick())) {   // check if the p2 is healthy
			 if(Clock.DaysPassed(p1.getContagiousTime()) < 5)   // check that p1 is sick for 5 or more days
			 {
				 return false;
			 }
			 
			 vType = (VirusManager.getVirusManager()).getRandomVirus(VirusesEnum.SOUTHAFRICAN);
			 if(vType == null)  // this variant can't develop to any variant
				 return false;
			 
			 varSickProb = vType.contagionProbability(p2);
			 if(varSickProb == 0)   // if the variant can contage at the moment (by status)
				 return false;
		
			 probToSick = varSickProb*Math.min(1,0.14*Math.pow(Math.E, 2-0.25*p1.distance(p2)));   // calculation of the probability
			 if (probToSick >= rand.nextDouble())  // exclude 1 - [0,1)  ///	
			 {
				 p2.contagion(vType);
			 }
			 return true;
		 }
		 else
			 throw new RuntimeException();   // p2 is not healthy
	 }
	
	
	// end test
	
	
	
	
	
	
	
	
	/**
	  * the method infect the person in the variant
	  * @param s - a variant name
	  * @param p - a person 
	  * @return if the infection succeeded or not
	  */
	 /*
	 public boolean contage(String s, Person p)
	 {
		 // creating all the variants kinds
		 BritishVariant britV = new BritishVariant();   
		 ChineseVariant chinV = new ChineseVariant();
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
				 	p.contagion(chinV);   // infecting the person in this variant
				 	return true;
				 }
			 else return false;
		 }
		 else
		 {
			 if (SouthAfricanVariant.getCanContage() == true)  // checking this the variant can contage (according to it's status)
				 {
				 	p.contagion(this);  // infecting the person in this variant
				 	return true;
				 }
			 else return false;
		 }
	 }
	*/
	
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
	 * return the South African variant in string form
	 */
	@Override
	public String toString()
	{
		return "South African variant";
	}
	
	

	
	// data members
	private final double dieProb18 = 0.05;   // The probability of dying by the age of 18
	private final double dieProbUp18 = 0.08;    // The probability of dying over the age of 18
	private final double infectProb18 = 0.6;    // The probability of infection by the age of 18
	private final double infectProbUp18 = 0.5;    // The probability of infection over the age of 18
	
}
