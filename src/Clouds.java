/*
 * Copyright (c) 2017. Brandon A. Foos, All Rights Reserved. brandonfoos.com
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;

import javax.swing.JPanel;

public class Clouds extends JPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Random random = new Random();
	boolean rand = random.nextBoolean();
	public Clouds() {
		
		setSize(130,90);
		setOpaque(false);
		
	}
	public Clouds(boolean rand)
	{
		this.rand = rand;
		setSize(130,90);
		setOpaque(false);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2.setColor(new Color(255,255,255));
		
		
		if (rand)
		{
			g2.fillRoundRect(30, 0, 80, 20, 20, 20);
			g2.fillRoundRect(0, 25, 110, 30, 30, 30);
		}
		if (!rand)
		{
			g2.fillRoundRect(30, 0, 100, 30, 30, 30);
			g2.fillRoundRect(0, 35, 130, 40, 40, 40);
		}
		
	}
}
