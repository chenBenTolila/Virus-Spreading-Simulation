package location;


public class Point {
	/**
	 * Ctor of Point class
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
	public String toString() 
	{
		return "(" + m_x + ", " + m_y + ")";
	}
	
	/**
	 * return true if the objects have the same values
	 */
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
	
	
	private int m_x;
	private int m_y;

}
