import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Projectile extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Projectile()
	{
		setSize(5,5);
		setOpaque(false);
		
	}
	
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK);
		g2.fillOval(0, 0, 5,5);
	
	}
	
	

}
