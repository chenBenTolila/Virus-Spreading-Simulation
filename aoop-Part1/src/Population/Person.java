package Population;

public class Person {
	private int age;
	private Point location;
	private Settlement settlement;
	
	//ctor 
	public Person(int age, Point location, Settlement settlement) {
		this.age= age;
		this.location= location;
		this.settlement=settlement;
	}
	
	public double contagionProbability() {
		
	}
	
	public Person contagion(IVirus IV) {
		
	}
}
