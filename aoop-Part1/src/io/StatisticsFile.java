package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public class StatisticsFile {
	
	/**
	 * 
	 * @param tableToExport get data table to read from
	 * @param pathToExportTo get name of file to save
	 * @return true if Succeeded else false
	 */
	public static boolean exportToCSV(JTable tableToExport,
	        String pathToExportTo) {

	    try {

	        TableModel model = tableToExport.getModel();
	        FileWriter csv = new FileWriter(new File(pathToExportTo+".csv"));

	        for (int i = 0; i < model.getColumnCount(); i++) {
	            csv.write(model.getColumnName(i) + ",");
	        }

	        csv.write("\n");

	        for (int i = 0; i < tableToExport.getRowCount(); i++) {
	            for (int j = 0; j < tableToExport.getColumnCount(); j++) {
	                csv.write(model.getValueAt(tableToExport.convertRowIndexToModel(i), j).toString() + ",");
	            }
	            csv.write("\n");
	        }

	        csv.close();
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	

	
}
