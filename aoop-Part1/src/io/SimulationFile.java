package io;
import java.io.*;
import country.*;
import simulation.*;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public class SimulationFile {
	
	/**
	 * the method create the map for the simulation from the information in the file
	 * @param map - will keep the map of the simulation
	 */
	public static boolean createMap(Map map) throws IOException {
		FileReader fr; 
		String line;
		Settlement temp;
		if(fileName!=null) {
			fr = new FileReader(fileName);
			map.resetMap();
			Clock.resetTime();
			BufferedReader bufferedReader = new BufferedReader(fr);
			SettlementFactory SettlementF = new SettlementFactory();
			while((line = bufferedReader.readLine()) != null) {
				line = line.replaceAll(" ", "");
				String[] data = line.split(";");
				if(!(data[0].equals("#"))) {
					temp = SettlementF.getFactory(data, map);
					map.addSettlement(temp);
				}
			}
			fr.close();	
			fr = new FileReader(fileName);
			bufferedReader = new BufferedReader(fr);
			while((line = bufferedReader.readLine()) != null) {
				line = line.replaceAll(" ", "");
				String[] data = line.split(";");
				if(data[0].equals("#"))
					map.connectSettlements(data[1], data[2]);
			}
			fr.close();
			map.addSickToMap(0.01);   // initialize the population with 1% of sick people
			return true;
		}
		else {
			System.out.println("Failed to open the file");
			return false;
		}
	}
	
	
	/**
	 * 
	 * @param fName - new name to file
	 */
	public static void setFileName(String fName) {
		fileName=fName;
	}
	
	 
	private static String fileName = null; // save the name of file
}
