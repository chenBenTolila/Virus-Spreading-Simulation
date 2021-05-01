package ui;

import java.awt.Graphics;
import country.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapPanel extends JPanel{
	
	//public MapPanel() {
		//paintSettlement(g,m);
	//}

	
	
	
	public void paintSettlement(Graphics g, Map m) {
		JButton jb;
		for(int i=0; i < m.getNumOfSettlement() ;++i) {
			jb=m.settlementButton(i);
			jb.setLocation(m.getLocation(i).getX(), m.getLocation(i).getY());
			this.add(jb);
			//g.drawRect(jb);
		}
		
	}
	
}
