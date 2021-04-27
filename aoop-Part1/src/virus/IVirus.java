package virus;
import population.Person;
import population.Sick;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public interface IVirus {
	
	 public double contagionProbability(Person p); // return the probability to get infected in a variant 
	 public boolean tryToContagion(Sick p1, Person p2); //  if the second person is healthy check if he got infected by the first person
	 public boolean tryToKill(Sick s); // return true if the person died from the virus
}
