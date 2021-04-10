package virus;
import population.Person;
import population.Sick;

public class ChineseVariant implements IVirus {
	
	public double contagionProbability(Person p){
		if(p.getAge() <= 18)
			return p.contagionProbability()*infectProb18;
		else if(p.getAge() <= 55)
			return p.contagionProbability()*infectProb55;
		else 
			return p.contagionProbability()*infectProbUp55;
	}
	
	public boolean tryToContagion(Person p1, Person p2){
		double probToSick;
		if(p2.checkIfHealthy()) {
			probToSick = contagionProbability(p2)*Math.min(1,0.14*Math.pow(Math.E, 2-0.25*p1.getDistance(p2)));
			if(probToSick<1)
				return false;
			else 
				return true;
		}
		return false; // check what to return
	}
	
	public boolean tryToKill(Sick s){
		double probToDie;
		if(s.getAge() <= 18)
			probToDie = Math.max(0, dieProb18-0.01*dieProb18*Math.pow((s.getSicknessDuration()-15),2));
		else if(s.getAge() <= 55)
			probToDie = Math.max(0, dieProb55-0.01*dieProb55*Math.pow((s.getSicknessDuration()-15),2));
		else
			probToDie = Math.max(0, dieProbUp55-0.01*dieProbUp55*Math.pow((s.getSicknessDuration()-15),2));
		if(probToDie < 1)
			return false;
		else 
			return true; // check what to return
	}
	
	
	
	public static final double dieProb18 = 0.001;
	public static final double dieProb55 = 0.05;
	public static final double dieProbUp55 = 0.1;
	public static final double infectProb18 = 0.2;
	public static final double infectProb55 = 0.5;
	public static final double infectProbUp55 = 0.7;
	
}
