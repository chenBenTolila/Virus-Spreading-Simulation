package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import country.Map;
import location.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapPanel extends JPanel{
	
	public MapPanel(Map m) {
		m_map = m;
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for(int i=0;i< m_map.getNumOfSettlement(); ++i) {
			Point[] pm=m_map.connectedSettlements(i);
			for(int j=0;i<pm.length;++j) {
				g.drawLine(pm[0].getX(), pm[0].getY(), pm[j].getX(), pm[j].getY());
			}
		}
		
		for(int i=0; i < m_map.getNumOfSettlement();++i) {
			
		}
		
	}
	
	@Override
	public Dimension getPreferredSize() {
	return new Dimension(200, 200);
	}
	
	private Map m_map;
}
