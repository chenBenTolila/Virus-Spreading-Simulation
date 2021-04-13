package simulation;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public class Clock {
	
	public static long now() {
		return currentTime;
	}
	
	public static void nextTick() {
		currentTime++;
	}
	
	
	private static long currentTime = 0;  // Difference
}
