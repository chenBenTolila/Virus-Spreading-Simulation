package population;
import location.Point;
import country.Settlement;
import virus.IVirus;

public class Person {
	/**
	 * 
	 * @param age - person age
	 * @param location - location of person
	 * @param settlement - settlement of person
	 */ 
	public Person(int age, Point location, Settlement settlement) {
		this.age= age;
		this.location= location;
		this.settlement=settlement;
	}
	
	public double contagionProbability() {
		//
	}
	
	public Person contagion(IVirus IV) {
		//
	}
	
	
	private int age;
	private Point location;
	private Settlement settlement;
}
