package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import country.Map;
import location.*;
import java.awt.Color;

import javax.swing.JPanel;

/**
 * @author Hadar Amsalem
 * ID: 316129212 
 * @author Chen Ben Tolila
 * ID: 207278029
 */ 

/*
 * map panel class
 */
public class MapPanel extends JPanel{
	
	public MapPanel(Map m, MainWindow mw) {
		super();
		mainWindow = mw;
		m_map = m;
		// MapPanel temp = this;
		setVisible(true);
		this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	if(m_map == null)
            		return;
            	Menu m_menu = mainWindow.getMenu();
            	if(m_menu == null)
            		return;
            	int max_x = m_map.getMaxXInMap();
        		int max_y = m_map.getMaxYInMap();
        		
        		double Xratio = getWidth() / (max_x + 1.0);
        		double Yratio = getHeight() / (max_y + 1.0);
                int x = (int)(evt.getX() / Xratio);
                int y = (int)(evt.getY() / Yratio);
                
                for(int i = 0; i < m_map.getNumOfSettlement(); ++i)
                	if(m_map.isPointInSetIndex(i, x, y))
                	{
                		m_menu.openStatWindow(m_map, i);
                		//System.out.println("need to open statistics");
                		break;
                	}
                	
            }
        });
	}
	
	/**
	 * method to paint map
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(m_map == null)
			return;
		
		int max_x = m_map.getMaxXInMap();
		int max_y = m_map.getMaxYInMap();
		
		double Xratio = getWidth() / (max_x + 1.0);
		double Yratio = getHeight() / (max_y + 1.0);
		
		//System.out.println("Xmax: " + max_x);
		//System.out.println("Ymax: " + max_y);
		
		for(int i=0;i< m_map.getNumOfSettlement(); ++i) {
			Point[] pm=m_map.connectedSettlements(i);
			for(int j=1; j < pm.length; ++j) {
				g.drawLine((int)(pm[0].getX() * Xratio), (int)(pm[0].getY() * Yratio), (int)(pm[j].getX() * Xratio), (int)(pm[j].getY() * Yratio));
				//System.out.println(m_map.getIndexSettName(i));
				//System.out.println("x middle: "+ pm[0].getX() * Xratio);
				//System.out.println("y middle: "+ pm[0].getY() * Yratio);
			}
		}
		Color col;
		Location loc;
		for(int i=0; i < m_map.getNumOfSettlement();++i) {
			col = m_map.getIndexColor(i);
			loc = m_map.getIndexLocation(i);
			if(col != null & loc != null)
			{
				g.setColor(col);
				g.fillRect((int)(loc.getPointX() * Xratio) , (int)(loc.getPointY() * Yratio), (int)(loc.getSizeWidth() * Xratio), (int)(loc.getSizeHeight() * Yratio));
				g.setColor(Color.BLACK);
				g.drawString(m_map.getIndexSettName(i), (int)(loc.getPointX() * Xratio), (int)((loc.getPointY()+(loc.getSizeHeight()/2)) * Yratio));
			}
		}
	}
	
	
	
	/*
	 * map size
	 */
	@Override
	public Dimension getPreferredSize() {
	return new Dimension(400, 400);
	}
	 
	private Map m_map; // keep map object
	private MainWindow mainWindow = null;
}
