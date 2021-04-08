package location;

public class Location {

	/**
	 * the constructor
	 * @param p - a position input
	 * @param s - a size input
	 */
	public Location(Point p, Size s)
	{
		m_position = new Point(p);
		m_size = new Size(s);
	}
	
	/**
	 * return true if the object has the same values
	 */
	public boolean equals(Object o)
	{
		boolean ans = false;
		if ((o instanceof Location))
			ans = (m_position.equals(((Location)o).m_position)) && (m_size.equals(((Location)o).m_size));
		return ans;
	}
	

	/**
	 * 
	 * @return the position x value
	 */
	public int getPointX()
	{
		return m_position.getX();
	}
	
	/**
	 * 
	 * @return the position y value
	 */
	public int getPointY()
	{
		return m_position.getY();
	}
	
	/**
	 * 
	 * @return the size width
	 */
	public int getSizeWidth()
	{
		return m_size.getWidth();
	}
	
	/**
	 * 
	 * @return the size height
	 */
	public int getSizeHeight()
	{
		return m_size.getHeight();
	}
	
	
	private Point m_position;
	private Size m_size;
}
