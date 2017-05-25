
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;


import javax.swing.JPanel;
import javax.swing.JSlider;

public class StartPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	Background background = new Background(false);
	int width = 900;
	int height = 700;
	static boolean cloudsRunning = true;
	Welcome w = new Welcome();
	public StartPanel()
	{
		setSize(900, 900);
		setLayout(new BorderLayout());

		startClouds();
		add(background);
		
		background.add(w);
		
		

	}

	

	private void startClouds()
	{

		Timer cloudTimer = new Timer();

		TimerTask cloudTask = new TimerTask()
		{

			@Override
			public void run()
			{

				Clouds cloud = new Clouds();
				background.add(cloud);
				Timer icloudTimer = new Timer();

				TimerTask icloudTask = new TimerTask()
				{
					int y = (20 + (int) (Math.random() * ((135 - 20) + 1)));
					int x = -130;

					@Override
					public void run()
					{
						cloud.setLocation(x, y);

						x += 1;
						if (x > 900 || !cloudsRunning)
						{
							icloudTimer.cancel();
							background.remove(cloud);
						}

					}

				};
				icloudTimer.schedule(icloudTask, 0, 40);

			}

		};
		cloudTimer.schedule(cloudTask, 0, 16000);

	}
//	public void showTankSetup()
//	{
//		TankSetup tS = new TankSetup();
//		tS.setLocation(0, -20);
//		cloudsRunning = false;
//		background.remove(w);
//		
//		JSlider tankAType = new JSlider(JSlider.HORIZONTAL,0,255,1);
//		tankAType.setLocation(300,400);
//
//		
//		background.add(tS);
//		background.add(tankAType);
//		
//	}
	
	public JPanel getBK() {
		return background;
	}

}
