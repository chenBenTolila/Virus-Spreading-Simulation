package io;
import java.io.*;
import population.Person;
import java.util.Random;


public class SimulationFile {
	
	
	public void readFromFile() {
		FileReader fr; 
		try {
			fr = new FileReader("SimulationFile.text");
			
			
			fr.close();
		}
		catch(IOException e1){
		
		}
	}
	
	
	public Person intizializePersonAge(Settelment s)
	{
		int y;
		int x;
		Random rand = new Random();
		y = rand.nextInt(5);  // check if y is real or integer //
		Random r = new Random();
		double mySample = r.nextGaussian()*deviation+mean;
		
	}

	
	public static double deviation = 6;
	public static double mean = 9;
}
