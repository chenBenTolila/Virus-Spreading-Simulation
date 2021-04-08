package population;
import location.Point;
import country.Settlement;
import virus.IVirus;

public abstract class Person {
	/**
	 * 
	 * @param age - person age
	 * @param location - location of person
	 * @param settlement - settlement of person
	 */ 
	public Person(int age, Point location, Settlement settlement) {
		this.age= age;
		this.location= new Location(location);
		this.settlement= new Settlement(settlement);
	}
	
	public abstract double contagionProbability();
	
	
	public Person contagion(IVirus IV) {
		//
	}
	
	public String toString(){
		return "age:"+ this.age + "location: " + this.location.toString() +"settlement: " + this.settlement.getSettlementName()
	}
	
	private int age;
	private Point location;
	private Settlement settlement;
}
