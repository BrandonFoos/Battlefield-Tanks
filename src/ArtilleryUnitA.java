
/*
 * Copyright (c) 2017. Brandon A. Foos, All Rights Reserved. brandonfoos.com
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ArtilleryUnitA extends ArtilleryBase
{
	private static final long serialVersionUID = 1L;

	public ArtilleryUnitA()
	{
		super();
		setSize(30, 20);
		// setPreferredSize(new Dimension(50,50));

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		URL imgURL = this.getClass().getResource("imgg/tankA.png");

		BufferedImage img = new BufferedImage(30, 18, BufferedImage.TYPE_INT_ARGB);
		try
		{
			img = ImageIO.read(imgURL);

		} catch (IOException e)
		{
			e.printStackTrace();
		}

		g2.drawImage(img, 0, 0, 30, 18, null);
	}

}
