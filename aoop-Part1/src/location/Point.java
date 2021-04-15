package location;


/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public class Point {
	
	/**
	 * constructor of Point class
	 * @param x - is the x coordinate of the point
	 * @param y - is the y coordinate of the point
	 */
	public Point(int x , int y )
	{
		m_x = x;
		m_y = y;
	}
	
	/**
	 * copy constructor
	 * @param o - the object we want to copy
	 */
	public Point(Point o)
	{
		m_x = o.m_x;
		m_y = o.m_y;
	}
	
	/**
	 * return the point in string form
	 */
	@Override
	public String toString() 
	{
		return "(" + m_x + ", " + m_y + ")";
	}
	
	/**
	 * return true if the objects have the same values
	 */
	@Override
	public boolean equals(Object o)
	{
		boolean ans = false;
		if ((o instanceof Point))
			ans = (m_x == ((Point)o).m_x) && (m_y == ((Point)o).m_y);
		return ans;
	}
	
	/**
	 * @return m_x
	 */
	public int getX()
	{
		return m_x;
	}
	
	
	/**
	 * @return m_y
	 */
	public int getY()
	{
		return m_y;
	}
	
	/**
	 * 
	 * @param p2
	 * @return the distance between two points
	 */
	public double distanceBetweenTwoPoints(Point p2)
	{
		return Math.sqrt(Math.pow((m_y - p2.m_y),2) + Math.pow((m_x - p2.m_x),2));  // calculation of the distance
	}
	
	// data members
	private int m_x; // keep the x of point
	private int m_y; // keep the y of point

}
