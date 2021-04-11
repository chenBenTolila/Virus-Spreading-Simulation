package io;
import java.io.*;
import population.Person;
import java.util.Random;


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
	
	public Person createPerson(Settelment s)
	{
		int y;
		int x;
		Random rand = new Random();
		y = rand.nextInt(5);
		
	}

	
	public static double deviation = 6;
	public static double mean = 9;
}
