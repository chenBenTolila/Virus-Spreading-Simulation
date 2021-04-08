package population;
import virus.IVirus;
import location.Point;
import country.Settlement; 

public class Convalescent extends Person{
	
	public Convalescent(int age, Point location, Settlement settlement, IVirus virus) {
		super(age, location, settlement);
		this.virus =virus;
	}
	
	private  IVirus virus;
}
