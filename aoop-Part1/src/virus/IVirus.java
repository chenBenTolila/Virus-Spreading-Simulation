package virus;
import population.Person;
import population.Sick;


public interface IVirus {
	 public double contagionProbability(Person p); // return the probability to get infected in Chinese variant 
	 public boolean tryToContagion(Person p1, Person p2); //if the second person is healthy check if he got infected by the first person
	 public boolean tryToKill(Sick s); // return true if the person died from the virus
}
