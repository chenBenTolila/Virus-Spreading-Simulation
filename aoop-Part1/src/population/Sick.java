package population;
import virus.IVirus;

public class Sick extends Person{
	
	public boolean tryToDie() {
		
	}
	
	public Person recover() {
		
	}
	
	public Sick(long contagiousTime, IVirus virus) {
		this.contagiousTime=contagiousTime;
		this.virus=virus;
	}
	
	private  long contagiousTime;
	private  IVirus virus;
}
