package virus;
import population.Person;
import java.lang.*; 

public class ChineseVariant implements IVirus {
	
	public double contagionProbability(Person p){
		if(p.getAge() < 18)
			return p.contagionProbability()*dieProb18;
		else if(p.getAge() < 55)
			return p.contagionProbability()*dieProb55;
		else 
			return p.contagionProbability()*dieProbUp55;
	}
	
	public boolean tryToContagion(Person p1, Person p2){
		double probToSick;
		if(p2.checkIfHealthy()) {
			probToSick = p2.contagionProbability()*Math.min(1,0.14*Math.pow(Math.E, 2-0.25*p1.getDistance(p2)));
			if(p2.contagionProbability() > probToSick)
				return false;
			else 
				return true;
		}
		return false; // check what to return
	}
	
	public boolean tryToKill(Sick s){
		//probToDie=Math.max(0, dieProb18)
	}
	
	
	
	public static final double dieProb18 = 0.1;
	public static final double dieProb55 = 5;
	public static final double dieProbUp55 = 10;
	public static final double infectProb18 = 20;
	public static final double infectProb55 = 50;
	public static final double infectProbUp55 = 70;
	
}
