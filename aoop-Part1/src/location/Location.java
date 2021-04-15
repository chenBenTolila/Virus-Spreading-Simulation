package location;


/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

// location class
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
	 * copy constructor
	 * @param l the location to be copied
	 */
	public Location(Location l)
	{
		m_position = new Point(l.m_position);
		m_size = new Size(l.m_size);
	}
	
	/**
	 * return the location in string form
	 */
	@Override
	public String toString() 
	{
		return "position: " + m_position.toString() + "  size: " + m_size.toString();
	}
	
	/**
	 * return true if the object has the same values
	 */
	@Override
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
	
	// data members
	private Point m_position; // represents upper left corner of the location
	private Size m_size; // keep the size of location
}
