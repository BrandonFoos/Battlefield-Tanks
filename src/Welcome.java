
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Welcome extends JPanel
{
	private static final long serialVersionUID = 1L;
	int width = 900;
	int height = 700;
	public Welcome()
	{
		setSize(width,height);
		setOpaque(false);
		
		
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		URL navURL = this.getClass().getResource("imgg/Welcome.png");
		BufferedImage nav = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		try
		{
			nav = ImageIO.read(navURL);

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		g.drawImage(nav, 0, -50, width, height, null);
	}
}
