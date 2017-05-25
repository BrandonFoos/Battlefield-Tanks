
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;


import javax.imageio.ImageIO;
import javax.swing.*;


public class ExplodeIcon extends JPanel

{

	private static final long serialVersionUID = 1L;

	public ExplodeIcon()
	{
		setSize(42,44);
		setOpaque(false);
		
//		Timer frameTimer = new Timer();
//
//		TimerTask frameTask = new TimerTask()
//		{
//			int frame = 1;
//			@Override
//			public void run()
//			{
//				if (frame > 7)
//				{
//					//remove(framePanel);
//					frameTimer.cancel();
//				}
//				URL imgURL = this.getClass().getResource("imgg/Explode/explode-"+ frame + ".png");
//				BufferedImage img = new BufferedImage(42, 44, BufferedImage.TYPE_INT_RGB);
//				try
//				{
//					img = ImageIO.read(imgURL);
//
//
//				} catch (IOException e)
//				{
//					e.printStackTrace();
//				}
//
//				JPanel framePanel = new JPanel();
//				framePanel.setSize(42,44);
//				framePanel.add(new JLabel(new ImageIcon(img)));
//				remove(framePanel);
//				add(framePanel);
//
//
//			}
//
//		};
//		frameTimer.schedule(frameTask, 20,16);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		URL imgURL = this.getClass().getResource("imgg/Explode/explode-5.png");
		BufferedImage img = new BufferedImage(42, 44, BufferedImage.TYPE_INT_RGB);
		try
		{
			img = ImageIO.read(imgURL);
			

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		g2.drawImage(img, 0, 0, 42, 44, null);
	}
}
