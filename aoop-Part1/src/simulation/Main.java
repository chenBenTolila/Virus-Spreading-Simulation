package simulation;

import country.Map;
import io.SimulationFile;

public class Main {

	/**
	 * class main
	 * @param args
	 */
	public static void main(String[] args) {
		SimulationFile sf=new SimulationFile();
		Map m= new Map();
		sf.createMap(m);
		initializeSick(m);
	}
	
	public static void initializeSick(Map m) {
		
	}
}
