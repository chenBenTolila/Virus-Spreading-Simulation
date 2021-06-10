package ui;

public class LogFileOriginator {
	
	
	/**
	 *  updates the memento current state to the new state
	 * @param state - the new current state
	 */
	public void setState(String state) { this.state = state;} 
	
	
	/**
	 * 
	 * @return the state of the current memento
	 */
	public String getState() { return state; } 
	
	
	/*
	public LogFileMemento createMemento() { 
		return new LogFileMemento(state); 
	} 
	/**
	 * set a new memento
	 * @param memento - a memento
	 */
	/*
	public void setMemento(String memento) { 
		state = memento; 
	}
	*/
	
	private String state = null; // the current state
	
	/*
	// inner class
	public class LogFileMemento {
		
		public LogFileMemento(String state){ 
			this.state = state; 
		} 
		
		public String getState() { return state; }
		
		private String state;  // the log file absolute path
	}
	*/
}
