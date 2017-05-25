import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Collision extends JPanel
{
	private static final long serialVersionUID = 1L;
	private int player;

	public Collision(int p)
	{
		player = p;
		setSize(900, 700);
		setOpaque(false);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		URL rightURL = null;
		switch (player){
			case 0:
				rightURL = this.getClass().getResource("imgg/50P.png");
				break;
			case 1:
				rightURL = this.getClass().getResource("imgg/+50C.png");
		}


		BufferedImage rightI = new BufferedImage(900, 700, BufferedImage.TYPE_INT_ARGB);
		try
		{
			rightI = ImageIO.read(rightURL);

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		g.drawImage(rightI, 0, -50, null);
	}
}
