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
		 if(p2.checkIfHealthy()) {   
			 if(Clock.DaysPassed(p1.getSicknessDuration()) < 5)
				 return false;
			 probToSick = contagionProbability(p2)*Math.min(1,0.14*Math.pow(Math.E, 2-0.25*p1.distance(p2)));   // calculation of the probability
			 return probToSick >= rand.nextDouble();  // exclude 1 - [0,1)  ///	
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

	// data members
	public static final double dieProb18 = 0.01; // The probability of dying by the age of 18
	public static final double dieProbUp18 = 0.1; // The probability of dying over the age of 18
	public static final double infectProbAll = 0.7; // The probability of infection for all
	
	
}
