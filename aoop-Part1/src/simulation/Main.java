package simulation;

import country.Map;
import io.SimulationFile;
import java.io.*;


public class Main {

	/**
	 * class main
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		Map m = new Map();
		SimulationFile.createMap(m);
		m.addSickToMap();
		System.out.println(m.toString());
		m.contagionSimu();
		//System.out.println(m.toString());
		}
		catch (FileNotFoundException ex1) {
            System.out.println("File not found");
        }catch (IOException ex2) {
        	System.out.println("Error in file");
        }
	}
	
}
