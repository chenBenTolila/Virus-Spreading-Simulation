package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import country.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapPanel extends JPanel{
	
	public MapPanel(Map m) {
		m_map = m;
	}

	public void drowConnections(Map m) {
		
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		JButton jb;
		for(int i=0; i < m.getNumOfSettlement() ;++i) {
			jb=m.settlementButton(i);
			jb.setLocation(m.getLocation(i).getX(), m.getLocation(i).getY());
			this.add(jb);
			//g.drawRect(jb);
		}
		
	}
	
	@Override
	public Dimension getPreferredSize() {
	return new Dimension(400, 400);
	}
	
	private Map m_map;
}
