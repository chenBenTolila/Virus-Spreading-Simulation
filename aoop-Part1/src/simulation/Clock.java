package simulation;

public class Clock {
	
	public static long now() {
		return currentTime;
	}
	
	public static void nextTick() {
		currentTime++;
	}
	
	
	private static long currentTime;  // Difference
}
