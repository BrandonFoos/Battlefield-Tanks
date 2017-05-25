/*
 * Copyright (c) 2017. Brandon A. Foos, All Rights Reserved. brandonfoos.com
 */

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GamePanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	public Background background = new Background();
	ArtilleryUnitA tankA = new ArtilleryUnitA();
	ArtilleryUnitB tankB = new ArtilleryUnitB();
	static int angle = 45;
	static boolean projectileInSky = false;
	JButton fire;
	static int powerA = 60;
	boolean demoMode = false;
	boolean clouds = false;
	
	public static JPanel controlsPanel = new JPanel();
	private Score score = new Score();
	JButton angleBtn;
	JButton powerBtn;
	public int aiDifficulty = 2;
	private int volley = 2;
	private JLabel volleyT;
	private Main creator;
	private static ArrayList pList = new ArrayList(100);
	private boolean redrawBattleGround = true;

	public GamePanel() {
		setSize(900, 900);
		setLayout(new BorderLayout());
		add(background);
		addMouseListener(this);

		// Panel For Volly
		JPanel volleyPanel = new JPanel();
		volleyPanel.setSize(200, 30);
		volleyPanel.setOpaque(false);
		volleyT = new JLabel();
		volleyT.setFont(new Font("Futura", 1, 15));
		volleyT.setText("Volley " + (volley / 2));
		volleyT.setForeground(Color.WHITE);
		volleyPanel.add(volleyT);
		volleyPanel.setLocation(345, 0);
		background.add(volleyPanel);

		background.add(controlBar());
		background.add(score);
		startGame();

	}

	public void startGame() {
		if (clouds)
			startClouds();
		do {
			System.out.println("Recalculating Tanks Position");
			int[][][] groundArray = background.getGroundArray();
			int tankALocation = (int) (Math.random() * 780 + 80);
			for (int i = 0; groundArray[tankALocation][i][3] <= 0; i++) {
				tankA.setX(tankALocation - 10);
				tankA.setY(i - 17);
				background.add(tankA);
			}

			int tankBLocation = (int) (Math.random() * 790 + 80);
			for (int i = 0; groundArray[tankBLocation][i][3] <= 0; i++) {

				tankB.setX(tankBLocation - 10);
				tankB.setY(i - 17);
				background.add(tankB);
			}
		} while (Math.abs(tankA.getX() - tankB.getX()) < 200);

	}

	private void startClouds() {
		Timer cloudTimer = new Timer();

		TimerTask cloudTask = new TimerTask() {

			@Override
			public void run() {

				Clouds cloud = new Clouds();
				background.add(cloud);
				Timer icloudTimer = new Timer();

				TimerTask icloudTask = new TimerTask() {
					int y = (20 + (int) (Math.random() * ((135 - 20) + 1)));
					int x = -130;

					@Override
					public void run() {
						cloud.setLocation(x, y);

						x += 1;
						if (x > 900)
							icloudTimer.cancel();

					}

				};
				icloudTimer.schedule(icloudTask, 0, 50);

			}

		};
		cloudTimer.schedule(cloudTask, 0, 9000);

	}

	public void fireProjectile(ArtilleryBase artilleryBase, int angle, int power) {
		//URL sound = this.getClass().getResource("Sounds/drop.wav");

		//AudioClip drop = Applet.newAudioClip(sound);

		//drop.play();

		
		
		Projectile p = new Projectile();
		background.add(p);
		Timer t = new Timer();

		TimerTask tTask = new TimerTask() {
			
			double x;
			double y;
			double elapsTime;

			public void run() {
				fire.setEnabled(false);
				if (artilleryBase == tankA)
					tankB.setTurn(false);
				if (artilleryBase == tankB)
					tankA.setTurn(false);
				elapsTime += 0.09;
				x = artilleryBase.getX() + (power * elapsTime * Math.cos(angle * Math.PI / 180));
				y = artilleryBase.getY() - (power * elapsTime * Math.sin(angle * Math.PI / 180)
						- ((9.806 * Math.pow(elapsTime, 2)) / 2));
				// System.out.println(x + ","+y);
				p.setLocation((int) x, (int) y);
				//pList.add(p);
				
				if (checkHit(x, y, tankA,artilleryBase,p) || checkHit(x, y, tankB,artilleryBase,p)) {
					playerTurn(artilleryBase);
					background.remove(p);
					t.cancel();
				}
				

			}
		};
		t.schedule(tTask, 0, 30);

	}

	public boolean checkHit(double x, double y, ArtilleryBase artilleryBase, ArtilleryBase offender, Projectile p2) {


		// adjust for out of bounds projectile
		if (x <= 0 || x >= 899) {
			x = 0;
			y = 0;
			miss(-30, -30,offender);
			return true;
		}
		if (y >= 699) {
			y = 0;
			x = 0;
			miss(-30, -30, offender);
			return true;
		}
		if (y <= 0) {
			y = 0;
			x = 0;
			return false;
		}
		int[][][] groundArray = background.getGroundArray();
		if (redrawBattleGround)
		{
		try {
			
			if (x > artilleryBase.getX() && x < artilleryBase.getX() + artilleryBase.getWidth()) {

				int highMark = artilleryBase.getY() + artilleryBase.getHighBound((int) x - artilleryBase.getX());
				int lowMark = artilleryBase.getY() + artilleryBase.getLowBound((int) x - artilleryBase.getX());

				if ((int) y > highMark && (int) y < lowMark) {
					projectileInSky = false;
					
					System.out.println("Collisoion has occured");
					collision(artilleryBase, (int) x, (int) y);
					
					return true;
				}
			}
			if (groundArray[(int) x][(int) y][3] < 1) {
				return false;
			}
			if (groundArray[(int) x][(int) y][3] > 0) {
				
				if (redrawBattleGround == true)
					background.hit((int) x + 3, (int) y);

				miss((int) x - 20, (int) y - 20, offender);
				adjustTanks();
				return true;
			}

			
			
			
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		}
		return false;
	}

	public JPanel controlBar() {

		URL imgURL = this.getClass().getResource("imgg/fire.png");
		BufferedImage img = new BufferedImage(136, 47, BufferedImage.TYPE_INT_RGB);
		try {
			img = ImageIO.read(imgURL);

		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon fireImg = new ImageIcon(img);
		fire = new JButton(fireImg);
		fire.setSize(136, 47);
		fire.setBorder(BorderFactory.createEmptyBorder());
		fire.setBorder(null);
		fire.setBorderPainted(false);
		fire.setContentAreaFilled(false);
		fire.setEnabled(true);
		fire.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!projectileInSky || demoMode) {
					fireProjectile(tankA, angle, powerA);
				}
				if (demoMode) {
					
					
					    	fireProjectile(tankA, 45 + (int) (Math.random() * ((135 - 45) + 1)), 60);
					    	fireProjectile(tankA, 45 + (int) (Math.random() * ((135 - 45) + 1)), 60);
					    	fireProjectile(tankA, 45 + (int) (Math.random() * ((135 - 45) + 1)), 60);
					    	fireProjectile(tankA, 45 + (int) (Math.random() * ((135 - 45) + 1)), 60);
					    	fireProjectile(tankA, 45 + (int) (Math.random() * ((135 - 45) + 1)), 60);
					    	fireProjectile(tankA, 45 + (int) (Math.random() * ((135 - 45) + 1)), 60);
					    	fireProjectile(tankA, 45 + (int) (Math.random() * ((135 - 45) + 1)), 60);
					    	fireProjectile(tankA, 45 + (int) (Math.random() * ((135 - 45) + 1)), 60);
					    	fireProjectile(tankA, 45 + (int) (Math.random() * ((135 - 45) + 1)), 60);
					    	fireProjectile(tankA, 45 + (int) (Math.random() * ((135 - 45) + 1)), 60);
					    	fireProjectile(tankA, 45 + (int) (Math.random() * ((135 - 45) + 1)), 60);
					    	fireProjectile(tankA, 45 + (int) (Math.random() * ((135 - 45) + 1)), 60);
					    	
					;

				}

			}

		});
		URL angleURL = this.getClass().getResource("imgg/angle.png");
		BufferedImage angleI = new BufferedImage(136, 47, BufferedImage.TYPE_INT_RGB);
		try {
			angleI = ImageIO.read(angleURL);

		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon angleImg = new ImageIcon(angleI);
		angleBtn = new JButton(String.valueOf(angle));
		angleBtn.setSize(45, 47);
		angleBtn.setFont(new Font("Futura", Font.PLAIN, 30));
		angleBtn.setIcon(angleImg);
		angleBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		angleBtn.setBorder(null);
		angleBtn.setBorderPainted(false);
		angleBtn.setContentAreaFilled(false);
		angleBtn.setVerticalTextPosition(SwingConstants.CENTER);
		angleBtn.setForeground(new Color(255, 255, 255));
		angleBtn.setBorder(BorderFactory.createEmptyBorder());

		URL leftURL = this.getClass().getResource("imgg/left.png");
		BufferedImage leftI = new BufferedImage(136, 47, BufferedImage.TYPE_INT_RGB);
		try {
			leftI = ImageIO.read(leftURL);

		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon leftImg = new ImageIcon(leftI);
		JButton leftBtn = new JButton(leftImg);
		leftBtn.setSize(45, 47);
		leftBtn.setBorder(null);
		leftBtn.setBorderPainted(false);
		leftBtn.setContentAreaFilled(false);
		leftBtn.setBorder(BorderFactory.createEmptyBorder());
		leftBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				angle += 1;
				angleBtn.setText(String.valueOf(angle));

			}

		});

		URL rightURL = this.getClass().getResource("imgg/right.png");
		BufferedImage rightI = new BufferedImage(136, 47, BufferedImage.TYPE_INT_RGB);
		try {
			rightI = ImageIO.read(rightURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon rightImg = new ImageIcon(rightI);
		JButton rightBtn = new JButton(rightImg);
		rightBtn.setBorder(null);
		rightBtn.setBorderPainted(false);
		rightBtn.setContentAreaFilled(false);
		rightBtn.setSize(45, 47);
		rightBtn.setBorder(BorderFactory.createEmptyBorder());
		rightBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				angle -= 1;
				angleBtn.setText(String.valueOf(angle));
			}

		});
		JPanel margin = new JPanel();
		margin.setOpaque(false);
		margin.setSize(200, 75);
		margin.setBorder(BorderFactory.createEmptyBorder());

		ImageIcon powerImg = new ImageIcon(angleI);
		powerBtn = new JButton(String.valueOf(powerA));
		powerBtn.setSize(45, 47);
		powerBtn.setFont(new Font("Futura", Font.PLAIN, 30));
		powerBtn.setIcon(powerImg);
		powerBtn.setBorder(null);
		powerBtn.setBorderPainted(false);
		powerBtn.setContentAreaFilled(false);
		powerBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		powerBtn.setVerticalTextPosition(SwingConstants.CENTER);
		powerBtn.setForeground(new Color(255, 255, 255));
		powerBtn.setBorder(BorderFactory.createEmptyBorder());

		JButton leftPowBtn = new JButton(leftImg);
		leftPowBtn.setSize(45, 47);
		leftPowBtn.setBorder(null);
		leftPowBtn.setBorderPainted(false);
		leftPowBtn.setContentAreaFilled(false);
		leftPowBtn.setBorder(BorderFactory.createEmptyBorder());

		leftPowBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				powerA -= 1;
				powerBtn.setText(String.valueOf(powerA));

			}

		});

		JButton rightPowBtn = new JButton(rightImg);
		rightPowBtn.setSize(45, 47);
		rightPowBtn.setBorder(BorderFactory.createEmptyBorder());
		rightPowBtn.setBorder(null);
		rightPowBtn.setBorderPainted(false);
		rightPowBtn.setContentAreaFilled(false);
		rightPowBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				powerA += 1;
				powerBtn.setText(String.valueOf(powerA));
			}

		});
		controlsPanel.add(leftPowBtn);
		controlsPanel.add(powerBtn);
		controlsPanel.add(rightPowBtn);
		controlsPanel.add(margin);
		controlsPanel.add(leftBtn);
		controlsPanel.add(angleBtn);
		controlsPanel.add(rightBtn);
		controlsPanel.add(margin);
		controlsPanel.add(margin);
		controlsPanel.add(fire);
		controlsPanel.setSize(900, 100);
		controlsPanel.setOpaque(false);
		controlsPanel.setLocation(0, 600);

		return controlsPanel;
	}

	public void miss(int x, int y, ArtilleryBase offender) {
		int player = 0;
		if (offender == tankA)
			player = 0;
		if (offender == tankB)
			player = 1;
		Miss missImg = new Miss(player);

		background.add(missImg);

		ExplodeIcon e = new ExplodeIcon();
		e.setLocation((int) x, (int) y);
		background.add(e);
		
		repaint();
		//System.out.println(Arrays.toString(pList.toArray()));
		Timer miss = new Timer();
		TimerTask missTask = new TimerTask() {

			@Override
			public void run() {
				background.remove(missImg);

				repaint();
			}

		};
		miss.schedule(missTask, 2000);

		Timer icon = new Timer();
		TimerTask iconTask = new TimerTask() {

			@Override
			public void run() {
				background.remove(e);
				repaint();
			}

		};
		icon.schedule(iconTask, 800);

	}

	public void collision(ArtilleryBase artilleryBase, int x, int y) {
		URL sound = this.getClass().getResource("Sounds/explosion.wav");

		AudioClip drop = Applet.newAudioClip(sound);

		drop.play();
		creator.vibrate();
		int player = 0;
		if (artilleryBase == tankA)
			player = 1;
		if (artilleryBase == tankB)
			player = 0;
		Collision c = new Collision(player);
		background.add(c);

		ExplodeIcon e = new ExplodeIcon();
		e.setLocation((int) x - 19, (int) y - 15);
		background.add(e);
		Timer cTimer = new Timer();
		TimerTask cTask = new TimerTask() {

			@Override
			public void run() {
				background.remove(c);
				repaint();
			}

		};
		cTimer.schedule(cTask, 2000);

		Timer icon = new Timer();
		TimerTask iconTask = new TimerTask() {

			@Override
			public void run() {
				background.remove(e);
				repaint();
			}

		};
		icon.schedule(iconTask, 800);
		if (artilleryBase == tankA)
			score.UpdateScore(1, 50);
		if (artilleryBase == tankB)
			score.UpdateScore(0, 50);
		if (volley > 40)
			score.checkWin();

		int[][][] groundArray = background.getGroundArray();

		do {
			int tankBLocation = (int) (Math.random() * 790 + 80);
			int tankY = 0;
			for (int i = 0; groundArray[tankBLocation][i][3] <= 0; i++) {

				tankY = i;
				background.add(tankB);
			}
			artilleryBase.setY(tankY-17);
			artilleryBase.setX(tankBLocation - 10);
		} while ((tankA.getX() - tankB.getX() < 200));
	}

	public void impact(int x, int y) {

	}

	public void explosion(ArtilleryBase artilleryBase) {

	}

	public static Boolean getProjectileInSky() {
		return projectileInSky;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (!projectileInSky) {
			if (e.getY() < tankA.getY()) {
				if (e.getX() > tankA.getX()) {
					double o = tankA.getY() - e.getY();
					double a = e.getX() - tankA.getX();
					int power = (int) (Math.sqrt((a * a) + (o * o))) / 4;

					// System.out.println("here");
					int theda = (int) Math.toDegrees(Math.atan(o / a));
					angle = theda;
					powerA = power;
					angleBtn.setText(String.valueOf(angle));
					powerBtn.setText(String.valueOf(powerA));
					fireProjectile(tankA, theda, power);
				}

				if (e.getX() <= tankA.getX()) {
					double o = tankA.getX() - e.getX();
					double a = tankA.getY() - e.getY();
					int power = (int) (Math.sqrt((a * a) + (o * o))) / 4;
					powerA = power;
					angle = (int) Math.toDegrees(Math.atan(o / a) + Math.PI / 2);
					angleBtn.setText(String.valueOf(angle));
					powerBtn.setText(String.valueOf(powerA));
					if (fire.isEnabled())
						fireProjectile(tankA, angle, powerA);
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// AI for Computer Players
	public void AIFire(ArtilleryBase offender) {
		int aiAngle = 0;
		int aiPower = 0;
		switch (aiDifficulty) {
		case 0:
			aiAngle = (int) (Math.random() * 180);
			aiPower = (int) (Math.random() * 80 + 30);
			fireProjectile(offender, aiAngle, aiPower);
			break;
		case 1:
			fireAIMedium(tankA, offender);
			break;
		case 2:
			fireAIHard(tankA,offender);
			break;
		
		}
	}
	
	private void fireAIMedium(ArtilleryBase target, ArtilleryBase offender)
	{
		double theta = 0.0;
	    double v = 0.0;
	    double g = 9.806;
	    
	    int y0 = Math.abs(target.getX() - tankB.getX());
	    int d = Math.abs(target.getY() - tankB.getY());
	    
	    theta = 45 + (Math.random() * 45);
	    theta = Math.toRadians(theta);
	    double range;
	    do
	    {
	    	range = v * Math.cos(theta) / 9.806 * (v * Math.sin(theta) + Math.sqrt(v * v * (Math.sin(theta) * Math.sin(theta)) + 19.612 * y0));
	    	v += 0.1;
	    	if (v > 100.0D)
	        {
	          v = 0.0;
	          theta = Math.random() * 0.7853981633974483 + 0.7853981633974483;
	        }
	    } while ((Math.abs(range - y0) > 1.0) || (Double.isNaN(range)));
	    theta = Math.toDegrees(theta);
	    if (offender.getX() > target.getX()) {
	        theta = 90.0D + (90.0D - theta);
	     }
	    fireProjectile(offender, (int)theta, (int)v + 15);
	    
	}
	private void fireAIHard(ArtilleryBase target, ArtilleryBase offender)
	{
		double theta = 0.0;
	    double v = 0.0;
	    double g = 9.806;
	    
	    int x = Math.abs(offender.getX()  - target.getX());
	    int y0 = target.getY() - offender.getY();
	    
	    theta = 45 + (Math.random() * 45);
	    theta = Math.toRadians(theta);
	    double range;
	    do
	    {
	    	range = ((v * Math.cos(theta)) /g) *((v * Math.sin(theta)) + Math.sqrt((v * v) * (Math.sin(theta) * Math.sin(theta)) + (2 * g * y0)));
	    	v += 0.1;
	    	if (v > 100.0D)
	        {
	          v = 0.0;
	          theta = Math.random() * 0.7853981633974483 + 0.7853981633974483;
	        }
	    } while ((Math.abs(range - x) > 1.0) || (Double.isNaN(range)));
	    theta = Math.toDegrees(theta);
	    if (offender.getX() > target.getX()) {
	        theta = 90.0D + (90.0D - theta);
	      }
	    fireProjectile(offender, (int)theta, (int)v);
	    
	}


	private void playerTurn(ArtilleryBase artilleryBase) {

		volley += 1;
		volleyT.setText("Volley " + (volley / 2));
		if (artilleryBase == tankB) {
			fire.setEnabled(true);
			tankA.setTurn(true);
		}
		if (artilleryBase == tankA) {
			tankB.setTurn(true);
			Timer aiFireT = new Timer();

			TimerTask aiFireTask = new TimerTask() {

				@Override
				public void run() {
					AIFire(tankB);

				}

			};

			aiFireT.schedule(aiFireTask, 1000);

		}

	}

	public void adjustTanks() {

		System.out.println("Recalculating Tanks Position");
		int[][][] groundArray = background.getGroundArray();
		int aY = 0;
		int tankALocation = tankA.getX();
		for (int i = tankA.getY(); groundArray[tankALocation + 10][i][3] <= 0; i++) {

			aY = i;
		}
		tankA.setY(aY - 17);
		// background.add(tankA);
		int bY = 0;
		int tankBLocation = (int) tankB.getX();
		for (int i = tankB.getY(); groundArray[tankBLocation + 10][i][3] <= 0; i++) {

			bY = i;

		}
		tankB.setY(bY - 17);
		// background.add(tankB);

	}

	public void setCreator(Main artilleryGame) {
		creator = artilleryGame;

	}


}
