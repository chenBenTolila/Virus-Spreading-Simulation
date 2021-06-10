package ui;
import java.util.ArrayList;
import ui.LogFileOriginator.LogFileMemento;


public class LogFileCaretaker { 
	
	/**
	 * add m to the history states
	 * @param m -  a memento
	 */
	public void addMemento(LogFileMemento m) { 
		if(statesList.size() == max_size)
			statesList.remove(0);
		statesList.add(m); 
	}
	
	/**
	 * 
	 * @return the memento that we added last
	 */
	public LogFileMemento getMemento() { 
		return (LogFileMemento) statesList.get(max_size - 1); 
	} 
	
	/**
	 * 
	 * @return the number of states in that are saved in the history
	 */
	public int getSize()
	{
		return statesList.size();
	}
	private int max_size = 1;
	private ArrayList<LogFileMemento> statesList = new ArrayList<LogFileMemento>(1);
}
