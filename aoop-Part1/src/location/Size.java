package location;


/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */

public class Size {
	
	/**
	 * constructor of Size class
	 * @param width - width input
	 * @param height - height input
	 */
	public Size(int width , int height )
	{
		m_width = width;
		m_height = height;
	}
	
	/**
	 * copy constructor
	 * @param o - the object we want to copy
	 */
	public Size(Size o)
	{
		m_width = o.m_width;
		m_height = o.m_height;
	}
	
	
	/**
	 * return the size in string form
	 */
	@Override
	public String toString() 
	{
		return "width: " + m_width + "  heigth: " + m_height;
	}
	
	/**
	 * return true if the objects have the same values
	 */
	@Override
	public boolean equals(Object o)
	{
		boolean ans = false;
		if ((o instanceof Size))
			ans = (m_width == ((Size)o).m_width) && (m_height == ((Size)o).m_height);
		return ans;
	}
	
	/**
	 * @return m_width
	 */
	public int getWidth()
	{
		return m_width;
	}
	
	
	/**
	 * @return m_height
	 */
	public int getHeight()
	{
		return m_height;
	}
	
	
	// data members
	private int m_width; // Width of location
	private int m_height; // height of location
}
