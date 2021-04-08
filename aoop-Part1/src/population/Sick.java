package population;
import country.Settlement;
import location.Point;
import virus.IVirus;

public class Sick extends Person{
	
	public double contagionProbability() {
		
	}
	
	public boolean tryToDie() {
		
	}
	
	public Person recover() {
		
	}
	
	public Sick(int age, Point location, Settlement settlement, long contagiousTime, IVirus virus) {
		super(age, location, settlement);
		this.contagiousTime=contagiousTime;
		this.virus=virus;
	}
	
	public String toString(){
		return super.toString() + "contagiousTime: "+ this.contagiousTime + "virus: "+ this.virus
	}
	
	private  long contagiousTime;
	private  IVirus virus;
}
