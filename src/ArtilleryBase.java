

/*
 * Copyright (c) 2017. Brandon A. Foos, All Rights Reserved. brandonfoos.com
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ArtilleryBase extends JPanel
{

	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private double velocity;
	private double theta;
	private boolean turn = false;
	private int[] highBound = { 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
			10, 11, 12 };
	private int[] lowBound = { 12, 12, 12, 15, 15, 16, 16, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
			17, 17, 17, 16, 16, 15, 15, 12, 12 };
	
	

	public ArtilleryBase()
	{
		setSize(20, 30);
		System.out.println(highBound.length);
		setOpaque(false);
	}


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

		URL imgURL = this.getClass().getResource("imgg/tank Base.png");

		BufferedImage img = new BufferedImage(30, 18, BufferedImage.TYPE_INT_ARGB);
		try
		{
			img = ImageIO.read(imgURL);

		} catch (IOException e)
		{
			e.printStackTrace();
		}

		g2.drawImage(img, 0, 0, 30, 18, null);
		g2.setColor(Color.red);
		
		// for (int i = 0; i < highBound.length; i++)
		// g2.drawLine(i, highBound[i], i, highBound[i]);
		// for (int i = 0; i < lowBound.length; i++)
		// g2.drawLine(i, lowBound[i], i, lowBound[i]);
		

	}

	public int getX()
	{
		return this.x;
	}

	protected void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return this.y;
	}

	protected void setY(int y)
	{
		this.y = y;
	}

	public double getVelocity()
	{
		return this.velocity;

	}

//	private void setVelocity(double velocity)
//	{
//		this.velocity = velocity;
//	}

	public double getTheta()
	{
		return this.theta;
	}

//	private void setTheta(double theta)
//	{
//		this.theta = theta;
//	}

	public int getHighBound(int x)
	{
		return highBound[x];

	}

	public int getLowBound(int x)
	{
		return lowBound[x];
	}


	public boolean isTurn() {
		return turn;
	}


	public void setTurn(boolean turn) {
		this.turn = turn;
	}

}
