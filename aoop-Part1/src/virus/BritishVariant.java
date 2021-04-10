package virus;
import population.Person;
import population.Sick;

public class BritishVariant implements IVirus {
	
	 public double contagionProbability(Person p){
		return p.contagionProbability()*infectProbAll;
	 }
	 
	 public boolean tryToContagion(Person p1, Person p2){
		 double probToSick;
			if(p2.checkIfHealthy()) {
				probToSick = contagionProbability(p2)*Math.min(1,0.14*Math.pow(Math.E, 2-0.25*p1.getDistance(p2)));
				if(probToSick < 1)
					return false;
				else 
					return true;
			}
			return false; // check what to return///
	 }

	 public boolean tryToKill(Sick s){
			double probToDie;
			if(s.getAge() <= 18)
				probToDie = Math.max(0, dieProb18-0.01*dieProb18*Math.pow((s.getSicknessDuration()-15),2));
			else
				probToDie = Math.max(0, dieProbUp18-0.01*dieProbUp18*Math.pow((s.getSicknessDuration()-15),2));
			if(probToDie < 1)
				return false;
			else 
				return true; // check what to return
		}
	
	
	public static final double dieProb18 = 0.01;
	public static final double dieProbUp18 = 0.1;
	public static final double infectProbAll = 0.7;
	
	
}
