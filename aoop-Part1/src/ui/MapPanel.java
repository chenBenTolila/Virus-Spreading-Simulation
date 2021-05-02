package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import country.Map;
import location.*;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapPanel extends JPanel{
	
	public MapPanel(Map m) {
		super();
		m_map = m;
		setVisible(true);
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for(int i=0;i< m_map.getNumOfSettlement(); ++i) {
			Point[] pm=m_map.connectedSettlements(i);
			//JLabel lab=new JLabel(m_map.ge);
			for(int j=1; j < pm.length; ++j) {
				g.drawLine(pm[0].getX(), pm[0].getY(), pm[j].getX(), pm[j].getY());
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
				g.fillRect(loc.getPointX(), loc.getPointY(), loc.getSizeWidth(), loc.getSizeHeight());
			}
		}
		
		
	}
	
	@Override
	public Dimension getPreferredSize() {
	return new Dimension(400, 400);
	}
	
	private Map m_map;
}
