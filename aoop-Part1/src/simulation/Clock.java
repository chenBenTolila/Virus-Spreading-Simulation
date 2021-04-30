package simulation;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public class Clock {
	
	/**
	 * 
	 * @return the current time in the simulation
	 */
	public static long now() {
		return currentTime;
	}
	
	/**
	 * advance the time in one
	 */
	public static void nextTick() {
		currentTime++;
	}
	
	/**
	 * 
	 * @param c get start time
	 * @return the number of the days that passed
	 */
	public static int DaysPassed(long c) {
		return (int)(Math.ceil((c-now())/ticks_per_day));
		
	}
	
	/**
	 * change the number of ticks per day in the simulation
	 * @param numTicks - the new number of ticks per day
	 */
	public static void setTicksPerDay(int numTicks)
	{
		if (numTicks > 0)
			ticks_per_day = numTicks;
	}
	
	private static long currentTime = 0;  // the current time in the simulation
	private static int  ticks_per_day=1; // number of ticks in one day
}
