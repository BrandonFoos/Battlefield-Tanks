import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TankSetup extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TankSetup() {
		setSize(900,700);
		setOpaque(false);
		//setPreferredSize(new Dimension(900,700));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		URL imgURL = this.getClass().getResource("imgg/settings.png");
		BufferedImage img = new BufferedImage(900, 700, BufferedImage.TYPE_INT_RGB);
		try
		{
			img = ImageIO.read(imgURL);

		} catch (IOException e)
		{
			System.out.println("error with Image");
		}
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(img, 0, 0, 900, 700,null);
	}
	
}
