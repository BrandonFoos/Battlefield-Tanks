
/*
 * Copyright (c) 2017. Brandon A. Foos, All Rights Reserved. brandonfoos.com
 */

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.IOException;

import java.net.URL;


import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.sun.glass.ui.Timer;

public class Background extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width = 899;
	private int height = 700;
	private int groundCoordinates[] = new int[width];
	private static int[][][] groundArray;
	private boolean staticClouds = true;
	private BufferedImage battleground;

	public Background() 
	{
		setLayout(null);
		setSize(width, height);
		initBattleground();
		redrawBattleground();
	}
	public Background(boolean staticClouds) 
	{
		setLayout(null);
		setSize(width, height);
		initBattleground();
		redrawBattleground();
		this.staticClouds = staticClouds;
	}
	public static int getY(int x)
	{
		return 0;

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		printGround(g2);
		
		g.drawImage(battleground, 0, 0, width, height, null);
		g.setColor(Color.white);
		if (staticClouds)
		{
		g.fillRoundRect(730, 85, 80, 20, 20, 20);
		g.fillRoundRect(700, 110, 110, 30, 30, 30);
		g.fillRoundRect(350, 90, 100, 30, 30, 30);
		g.fillRoundRect(320, 125, 130, 40, 40, 40);
		}

	}

	public void hit(int x, int y)
	{
		URL sound = this.getClass().getResource("Sounds/explosion.wav");

		AudioClip drop = Applet.newAudioClip(sound);
		
		drop.play();
		
		
		
		groundArray[x][y][3] = 0;
		groundArray[x][y-1][3] = 0;
		groundArray[x][y-2][3] = 0;
		groundArray[x][y-3][3] = 0;
		groundArray[x][y-4][3] = 0;
		groundArray[x][y-5][3] = 0;
		groundArray[x][y-6][3] = 0;
		groundArray[x][y-7][3] = 0;
		groundArray[x][y-8][3] = 0;
		
		groundArray[x+1][y][3] = 0;
		groundArray[x+1][y-1][3] = 0;
		groundArray[x+1][y-2][3] = 0;
		groundArray[x+1][y-3][3] = 0;
		groundArray[x+1][y-4][3] = 0;
		groundArray[x+1][y-5][3] = 0;
		groundArray[x+1][y-6][3] = 0;
		groundArray[x+1][y-7][3] = 0;
		groundArray[x+1][y-8][3] = 0;
		
		groundArray[x+2][y][3] = 0;
		groundArray[x+2][y-1][3] = 0;
		groundArray[x+2][y-2][3] = 0;
		groundArray[x+2][y-3][3] = 0;
		groundArray[x+2][y-4][3] = 0;
		groundArray[x+2][y-5][3] = 0;
		groundArray[x+2][y-6][3] = 0;
		groundArray[x+2][y-7][3] = 0;
	
		groundArray[x+3][y][3] = 0;
		groundArray[x+3][y-1][3] = 0;
		groundArray[x+3][y-2][3] = 0;
		groundArray[x+3][y-3][3] = 0;
		groundArray[x+3][y-4][3] = 0;
		groundArray[x+3][y-5][3] = 0;
		groundArray[x+3][y-6][3] = 0;
		groundArray[x+3][y-7][3] = 0;
		
		groundArray[x+4][y][3] = 0;
		groundArray[x+4][y-1][3] = 0;
		groundArray[x+4][y-2][3] = 0;
		groundArray[x+4][y-3][3] = 0;
		groundArray[x+4][y-4][3] = 0;
		groundArray[x+4][y-5][3] = 0;
		groundArray[x+4][y-6][3] = 0;
		
		groundArray[x+5][y][3] = 0;
		groundArray[x+5][y-1][3] = 0;
		groundArray[x+5][y-2][3] = 0;
		groundArray[x+5][y-3][3] = 0;
		groundArray[x+5][y-4][3] = 0;
		groundArray[x+5][y-5][3] = 0;
		groundArray[x+5][y-6][3] = 0;
		
		groundArray[x+6][y][3] = 0;
		groundArray[x+6][y-1][3] = 0;
		groundArray[x+6][y-2][3] = 0;
		groundArray[x+6][y-3][3] = 0;
		groundArray[x+6][y-4][3] = 0;
		groundArray[x+6][y-5][3] = 0;
		
		groundArray[x+7][y][3] = 0;
		groundArray[x+7][y-1][3] = 0;
		groundArray[x+7][y-2][3] = 0;
		groundArray[x+7][y-3][3] = 0;
		groundArray[x+7][y-4][3] = 0;
		
		groundArray[x+8][y][3] = 0;
		groundArray[x+8][y-1][3] = 0;
		groundArray[x+8][y-2][3] = 0;
		
		groundArray[x+9][y][3] = 0;
		
		
		groundArray[x][y+1][3] = 0;
		groundArray[x][y+2][3] = 0;
		groundArray[x][y+3][3] = 0;
		groundArray[x][y+4][3] = 0;
		groundArray[x][y+5][3] = 0;
		groundArray[x][y+6][3] = 0;
		groundArray[x][y+7][3] = 0;
		groundArray[x][y+8][3] = 0;
		groundArray[x][y+9][3] = 0;
		
		groundArray[x+1][y+1][3] = 0;
		groundArray[x+1][y+2][3] = 0;
		groundArray[x+1][y+3][3] = 0;
		groundArray[x+1][y+4][3] = 0;
		groundArray[x+1][y+5][3] = 0;
		groundArray[x+1][y+6][3] = 0;
		groundArray[x+1][y+7][3] = 0;
		groundArray[x+1][y+8][3] = 0;
		groundArray[x+1][y+9][3] = 0;
		
		groundArray[x+2][y+1][3] = 0;
		groundArray[x+2][y+2][3] = 0;
		groundArray[x+2][y+3][3] = 0;
		groundArray[x+2][y+4][3] = 0;
		groundArray[x+2][y+5][3] = 0;
		groundArray[x+2][y+6][3] = 0;
		groundArray[x+2][y+7][3] = 0;
		groundArray[x+2][y+8][3] = 0;
		
		groundArray[x+3][y+1][3] = 0;
		groundArray[x+3][y+2][3] = 0;
		groundArray[x+3][y+3][3] = 0;
		groundArray[x+3][y+4][3] = 0;
		groundArray[x+3][y+5][3] = 0;
		groundArray[x+3][y+6][3] = 0;
		groundArray[x+3][y+7][3] = 0;
		groundArray[x+3][y+8][3] = 0;
		
		groundArray[x+4][y+1][3] = 0;
		groundArray[x+4][y+2][3] = 0;
		groundArray[x+4][y+3][3] = 0;
		groundArray[x+4][y+4][3] = 0;
		groundArray[x+4][y+5][3] = 0;
		groundArray[x+4][y+6][3] = 0;
		groundArray[x+4][y+7][3] = 0;
		
		groundArray[x+5][y+1][3] = 0;
		groundArray[x+5][y+2][3] = 0;
		groundArray[x+5][y+3][3] = 0;
		groundArray[x+5][y+4][3] = 0;
		groundArray[x+5][y+5][3] = 0;
		groundArray[x+5][y+6][3] = 0;
		groundArray[x+5][y+7][3] = 0;
		
		groundArray[x+6][y+1][3] = 0;
		groundArray[x+6][y+2][3] = 0;
		groundArray[x+6][y+3][3] = 0;
		groundArray[x+6][y+4][3] = 0;
		groundArray[x+6][y+5][3] = 0;
		groundArray[x+6][y+6][3] = 0;
		
		groundArray[x+7][y+1][3] = 0;
		groundArray[x+7][y+2][3] = 0;
		groundArray[x+7][y+3][3] = 0;
		groundArray[x+7][y+4][3] = 0;
		groundArray[x+7][y+5][3] = 0;
		
		groundArray[x+8][y+1][3] = 0;
		groundArray[x+8][y+2][3] = 0;
		groundArray[x+8][y+3][3] = 0;
		
		groundArray[x+9][y+1][3] = 0;
		
		groundArray[x-1][y][3] = 0;
		groundArray[x-1][y-1][3] = 0;
		groundArray[x-1][y-2][3] = 0;
		groundArray[x-1][y-3][3] = 0;
		groundArray[x-1][y-4][3] = 0;
		groundArray[x-1][y-5][3] = 0;
		groundArray[x-1][y-6][3] = 0;
		groundArray[x-1][y-7][3] = 0;
		
		groundArray[x-2][y][3] = 0;
		groundArray[x-2][y-1][3] = 0;
		groundArray[x-2][y-2][3] = 0;
		groundArray[x-2][y-3][3] = 0;
		groundArray[x-2][y-4][3] = 0;
		groundArray[x-2][y-5][3] = 0;
		groundArray[x-2][y-6][3] = 0;
		groundArray[x-2][y-7][3] = 0;
		
		groundArray[x-3][y][3] = 0;
		groundArray[x-3][y-1][3] = 0;
		groundArray[x-3][y-2][3] = 0;
		groundArray[x-3][y-3][3] = 0;
		groundArray[x-3][y-4][3] = 0;
		groundArray[x-3][y-5][3] = 0;
		groundArray[x-3][y-6][3] = 0;
		
		groundArray[x-4][y][3] = 0;
		groundArray[x-4][y-1][3] = 0;
		groundArray[x-4][y-2][3] = 0;
		groundArray[x-4][y-3][3] = 0;
		groundArray[x-4][y-4][3] = 0;
		groundArray[x-4][y-5][3] = 0;
		groundArray[x-4][y-6][3] = 0;
		
		groundArray[x-5][y][3] = 0;
		groundArray[x-5][y-1][3] = 0;
		groundArray[x-5][y-2][3] = 0;
		groundArray[x-5][y-3][3] = 0;
		groundArray[x-5][y-4][3] = 0;
		groundArray[x-5][y-5][3] = 0;
		
		groundArray[x-6][y][3] = 0;
		groundArray[x-6][y-1][3] = 0;
		groundArray[x-6][y-2][3] = 0;
		groundArray[x-6][y-3][3] = 0;
		groundArray[x-6][y-4][3] = 0;
		
		groundArray[x-7][y][3] = 0;
		groundArray[x-7][y-1][3] = 0;
		groundArray[x-7][y-2][3] = 0;
		groundArray[x-7][y-3][3] = 0;
		
		groundArray[x-8][y][3] = 0;
		groundArray[x-8][y-1][3] = 0;
		
		
		groundArray[x-1][y][3] = 0;
		groundArray[x-1][y+1][3] = 0;
		groundArray[x-1][y+2][3] = 0;
		groundArray[x-1][y+3][3] = 0;
		groundArray[x-1][y+4][3] = 0;
		groundArray[x-1][y+5][3] = 0;
		groundArray[x-1][y+6][3] = 0;
		groundArray[x-1][y+7][3] = 0;
		
		groundArray[x-2][y][3] = 0;
		groundArray[x-2][y+1][3] = 0;
		groundArray[x-2][y+2][3] = 0;
		groundArray[x-2][y+3][3] = 0;
		groundArray[x-2][y+4][3] = 0;
		groundArray[x-2][y+5][3] = 0;
		groundArray[x-2][y+6][3] = 0;
		groundArray[x-2][y+7][3] = 0;
		
		groundArray[x-3][y][3] = 0;
		groundArray[x-3][y+1][3] = 0;
		groundArray[x-3][y+2][3] = 0;
		groundArray[x-3][y+3][3] = 0;
		groundArray[x-3][y+4][3] = 0;
		groundArray[x-3][y+5][3] = 0;
		groundArray[x-3][y+6][3] = 0;
		
		groundArray[x-4][y][3] = 0;
		groundArray[x-4][y+1][3] = 0;
		groundArray[x-4][y+2][3] = 0;
		groundArray[x-4][y+3][3] = 0;
		groundArray[x-4][y+4][3] = 0;
		groundArray[x-4][y+5][3] = 0;
		groundArray[x-4][y+6][3] = 0;
		
		groundArray[x-5][y][3] = 0;
		groundArray[x-5][y+1][3] = 0;
		groundArray[x-5][y+2][3] = 0;
		groundArray[x-5][y+3][3] = 0;
		groundArray[x-5][y+4][3] = 0;
		groundArray[x-5][y+5][3] = 0;
		
		groundArray[x-6][y][3] = 0;
		groundArray[x-6][y+1][3] = 0;
		groundArray[x-6][y+2][3] = 0;
		groundArray[x-6][y+3][3] = 0;
		groundArray[x-6][y+4][3] = 0;
		
		groundArray[x-7][y][3] = 0;
		groundArray[x-7][y+1][3] = 0;
		groundArray[x-7][y+2][3] = 0;
		groundArray[x-7][y+3][3] = 0;
		
		groundArray[x-8][y][3] = 0;
		groundArray[x-8][y+1][3] = 0;
		
		//if (GamePanel.getPList().isEmpty())
			redrawBattleground();
		
		
	}


	private void printGround(Graphics2D g)
	{
		
	
	}

	private void initBattleground()
	{
		System.out.println("Reinit Background");
		//int Result = 1 + (int) (Math.random() * 2);

		// URL imgURL = this.getClass().getResource("imgg/Battleground" + Result
		// + ".jpg");
		URL imgURL = this.getClass().getResource("imgg/Battleground1.png");
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		try
		{
			img = ImageIO.read(imgURL);

		} catch (IOException e)
		{
			e.printStackTrace();
		}

		// g.drawImage(img,0,0,width,height,null);
		groundArray = new int[img.getHeight()][img.getWidth()][4];
		for (int i = 0; i < img.getHeight(); i++)
		{
			//System.out.println("running");
			for (int j = 0; j < img.getWidth(); j++)
			{
				Color rgb = new Color(img.getRGB(j, i), true);
				groundArray[i][j][0] = rgb.getRed();
				groundArray[i][j][1] = rgb.getGreen();
				groundArray[i][j][2] = rgb.getBlue();
				groundArray[i][j][3] = rgb.getAlpha();

			}
		}

		// System.out.println(Arrays.toString(groundCoordinates));

	}

	public int[][][] getGroundArray()
	{
		return groundArray;
	}

	private void redrawBattleground()
	{
		battleground = new BufferedImage(groundArray.length,groundArray[1].length,BufferedImage.TYPE_INT_ARGB);
		Graphics g = battleground.createGraphics();
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		URL imgURL = this.getClass().getResource("imgg/BK1.png");

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		try
		{
			img = ImageIO.read(imgURL);

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		g.drawImage(img, 0, 0, width, height, null);
		g2.setColor(Color.WHITE);

		for (int i = 0; i < groundArray.length; i++)
		{
			for (int j = 0; j < groundArray[i].length; j++)
			{

				int rC = groundArray[i][j][0];
				int gC = groundArray[i][j][1];
				int bC = groundArray[i][j][2];
				int aC = groundArray[i][j][3];
				g.setColor(new Color(rC, gC, bC, aC));
				g.drawRect(i, j, 1, 1);
			}
		}
		URL navURL = this.getClass().getResource("imgg/navbar.png");
		BufferedImage nav = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		try
		{
			nav = ImageIO.read(navURL);

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		g.drawImage(nav, 0, -37, width, height, null);
		
		repaint();
		
	}
	
}
