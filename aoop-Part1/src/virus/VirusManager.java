package virus;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JTable;


public class VirusManager {
	private static VirusManager instance = null; 
	
	/***
	 * private ctor
	 */
	private VirusManager() { } 
	
	/***
	 * 
	 * @return the the virus manager instance
	 */
	public static VirusManager getVirusManager() { 
		if(instance == null) { 
			instance = new VirusManager(); 
		} 
		return instance; 
	} 
	
	
	public IVirus getRandomVirus(VirusesEnum virus)
	{ 
		VirusesEnum v[] = VirusesEnum.values();
		int index = -1;
		for(int i = 0; i < v.length; ++i)
		{
			if(virus.equals(v[i]))
				index = i;
		}
		if(index == -1)
			return null;
		else
		{
			String chosenVirus = randVirus(index);
			VirusFactory vf = new VirusFactory();
			if(chosenVirus == null)
				return null;
			else
			{
				return vf.getVirus(chosenVirus);
			}	
		}
	}
	
	/***
	 *  returning an index of a random virus that the virus in row
	 * @param row - an index of a row
	 * @return an index of a random virus with true value in the row 
	 */
	public String randVirus(int index)
	{
		//boolean row[] = getRow(index);
		if (index >= VirusesEnum.values().length)
			return null;
		
		Random rand = new Random();
		ArrayList<String> trueArray = new ArrayList<String>(0);
		for(int i = 0; i < mutationsTable.getColumnCount(); ++i)
			if(mutationsTable.getValueAt(index, i).equals(true))
				trueArray.add(VirusesEnum.getVirusNameByIndex(i));
		
		if(trueArray.size() == 0)
			return null;
		else
			return (trueArray.get(rand.nextInt(trueArray.size())));
	}
	
	
	/**
	 * 
	 * @param mTable - the edit mutation window JTable
	 */
	public void setmutationTable(JTable mTable)
	{
		mutationsTable = mTable;
	}
	
	private JTable mutationsTable = null;

}
