package io;
import java.io.*;
import country.Settlement;
import population.*;
import java.util.Random;
import java.util.Scanner;


public class SimulationFile {
	
	
	public void createMap() {
		Scanner sc =new Scanner(System.in);
		FileReader fr; 
		String st;
		try {
			fr = new FileReader("SimulationFile.text");
			//st = fr.readLine();

			
			fr.close();
		}
		catch(IOException e1){
		
		}
	}
	
	
	public int intizializePersonAge()
	{
		int y;
		double x;
		Random rand = new Random();
		y = rand.nextInt(5);  
		Random r = new Random();
		x = r.nextGaussian()*deviation+mean;
		return (int)((5 * x) + y);
	}

	
	public void createPeopleArrey(Settlement s, int numOfPersons) {
		Healthy p;
		for(int i=0; i< numOfPersons; ++i) {
			p=new Healthy(intizializePersonAge(), s.randomLocation(), s );
			s.addPerson(p);
		}
	}
	
	// data members
	public static double deviation = 6;
	public static double mean = 9;
}
