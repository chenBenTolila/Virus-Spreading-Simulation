package simulation;

import country.Map;
import io.SimulationFile;

public class Main {

	/**
	 * class main
	 * @param args
	 */
	public static void main(String[] args) {
		Map m = new Map();
		SimulationFile.createMap(m);
		m.addSickToMap();
		m.contagionSimu();
	}
	
}
