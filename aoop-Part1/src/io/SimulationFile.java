package io;
import java.io.*;


public class SimulationFile {
	public static void main (String []args) {
		FileReader fr; 
		try {
			fr = new FileReader("SimulationFile.text");
			
			
			fr.close();
		}
		catch(IOException e1){
		
		}

	}

}
