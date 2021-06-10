package ui;
import java.util.ArrayList;
import java.util.Stack;


public class LogFileCaretaker { 
	
	/**
	 * add m to the history states
	 * @param m -  a memento
	 */
	public void addMemento(String memento) { 
		history.add(memento);
	}
	
	/**
	 * 
	 * @return the memento that we added last
	 */
	public String getMemento() { 
		if(history.size() > 0)
			return history.pop();
		else 
			return null;
	} 
	
	/**
	 * 
	 * @return the number of states in that are saved in the history
	 */
	public int getSize()
	{
		return history.size();
	}
	
	private Stack<String> history = new Stack<>();
	//private ArrayList<LogFileMemento> statesList = new ArrayList<LogFileMemento>(1);
}
