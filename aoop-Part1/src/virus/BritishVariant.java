package virus;
import population.*;
import java.util.Random;

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
	 public boolean tryToContagion(Person p1, Person p2){
		 Random rand = new Random();
		 double probToSick;
			if(p2.checkIfHealthy()) {
				probToSick = contagionProbability(p2)*Math.min(1,0.14*Math.pow(Math.E, 2-0.25*p1.distance(p2)));
				return probToSick >= rand.nextDouble();  // exclude 1 - [0,1)  ///	
			}
			else
				throw new RuntimeException();
	 }

	 /**
	  * return true if the person died from the virus
	  */
	 public boolean tryToKill(Sick s){
		 Random rand = new Random();
		 double probToDie;
		 if(s.getAge() <= 18)
			 probToDie = Math.max(0, dieProb18-0.01*dieProb18*Math.pow((s.getSicknessDuration()-15),2));
		 else
			 probToDie = Math.max(0, dieProbUp18-0.01*dieProbUp18*Math.pow((s.getSicknessDuration()-15),2));
		 return probToDie >= rand.nextDouble();  // exclude 1 - [0,1)  ///
	 }
	
	 
	 /**
	  * return the British variant in string form
	  */
	 public String toString() {
		 return "British variant";
	 }

	// data members
	public static final double dieProb18 = 0.01; // The probability of dying by the age of 18
	public static final double dieProbUp18 = 0.1; // The probability of dying over the age of 18
	public static final double infectProbAll = 0.7; // The probability of infection for all
	
	
}
