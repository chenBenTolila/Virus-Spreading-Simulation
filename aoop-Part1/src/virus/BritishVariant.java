package virus;
import population.Person;
import java.lang.*;

public class BritishVariant implements IVirus {
	
	 public double contagionProbability(Person p){
		if(p.getAge() < 18)
			return p.contagionProbability()*dieProb18;
		else 
			return p.contagionProbability()*dieProbUp18;
			
	 }
	 
	 public boolean tryToContagion(Person p1, Person p2){
		 double probToSick;
			if(p2.checkIfHealthy()) {
				probToSick = p2.contagionProbability()*min(1,0.14*Math.pow(Math.E, 2-0.25*p1.getDistance(p2)));
				if(p2.contagionProbability() > probToSick)
					return false;
				else 
					return true;
			}
			return false; // check what to return///
	 }

	 //boolean tryToKill(Sick){}
	
	
	public static final double dieProb18 = 1;
	public static final double dieProbUp18 = 10;
	public static final double infectProbAll = 70;
	
	
}
