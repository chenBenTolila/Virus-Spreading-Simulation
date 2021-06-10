package ui;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

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
	
	
	
	private String state = null; // the current state
	
}
