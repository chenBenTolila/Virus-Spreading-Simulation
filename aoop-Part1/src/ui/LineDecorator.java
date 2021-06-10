package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import country.Settlement;

public class LineDecorator {
	
	/***
	 * Ctor
	 * @param a settlement one
	 * @param b settlement two
	 */
	public LineDecorator(Settlement a, Settlement b)
	{
		m_a = a;
		m_b = b;
	}
	
	/***
	 * draw the colorful lines between the settlement
	 * @param g for draw
	 * @param Xratio size of line
	 * @param Yratio size of line
	 */
	public void drawColoredLine(Graphics g, double Xratio, double Yratio)
	{
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color a = m_a.getSetColor();
		Color b = m_b.getSetColor();
		Color cl = new Color((a.getRed() + b.getRed()) / 2, (a.getGreen() + b.getGreen()) / 2, (a.getBlue() + b.getBlue()) / 2);
		int YMidA = m_a.middleOfSettlement().getY();
		int XMidA = m_a.middleOfSettlement().getX();
		int YMidB = m_b.middleOfSettlement().getY();
		int XMidB = m_b.middleOfSettlement().getX();
		g.setColor(cl);
		g.drawLine((int)(XMidA * Xratio), (int)(YMidA * Yratio), (int)(XMidB * Xratio), (int)(YMidB * Yratio));	
		
		g.setColor(Color.black);
	}
	
	private Settlement m_a; // first settlement 
	private Settlement m_b; // second settlement
}
