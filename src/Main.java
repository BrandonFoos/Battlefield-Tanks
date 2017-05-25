

/*
 * Copyright (c) 2017. Brandon A. Foos, All Rights Reserved. brandonfoos.com
 */

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// Background Sound
	int randomSound = 1 + (int) (Math.random() * 2);											//Calculates Random int for random sound
	URL sound = this.getClass().getResource("Sounds/Intro Music" + randomSound + ".wav");		//URL for Background Audio
	AudioClip bgMusic = Applet.newAudioClip(sound);												//Audio Clip for Background Music

	//JPanels
	StartPanel startPanel = new StartPanel(); 													// Panel for Welcome Screen
	GamePanel gamePanel;																		// Panel for GamePlay

	// For JFrame Shake
	private final static int VIBRATION_LENGTH = 20;
	private final static int VIBRATION_VELOCITY = 5;
	
	
	public Main() {

		//Init the JFrame
		setTitle("Battlefield Tanks");
		setSize(896, 685);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//Prompt in the event of JFrame Close
		WindowListener exitListener = new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you would like to exit?", "Tank Game",
						JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				//System.out.println(response);
				if (response == 0) {
					setVisible(false);
					displayCredits();
					System.exit(0);
				}
				if (response == 1) {
					add(gamePanel);
					setVisible(true);

				}

			}
		};
		addWindowListener(exitListener);
		
		//StartPanel is added on launch of application add start game buttons to JFrame
		add(startPanel);
		buttons();
		
		//Starts Music bgMusic
		bgMusic.loop();
		
		//Init GamePanel for game play
		gamePanel = new GamePanel();
		gamePanel.setCreator(this);   //used for vibrate();
		

		setVisible(true);
	}

	//Creates buttons for Game Start
	private void buttons() {
		//Reads StartBtn.png and converts to button
		URL startURL = this.getClass().getResource("imgg/StartBtn.png");
		BufferedImage startI = new BufferedImage(170, 62, BufferedImage.TYPE_INT_RGB);
		try {
			startI = ImageIO.read(startURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon startImg = new ImageIcon(startI);
		
		//Init startBtn;
		JButton startBtn = new JButton(startImg);
		startBtn.setOpaque(false);
		startBtn.setBorder(null);
		startBtn.setBorderPainted(false);
		startBtn.setContentAreaFilled(false);
		startBtn.setSize(170, 62);
		startBtn.setBorder(BorderFactory.createEmptyBorder());
		
		startBtn.addActionListener(e -> {

			startGame();
			
		});
		
		

		startBtn.setLocation(365, 310);
		startPanel.getBK().add(startBtn);

	}
	
	//Removes startPanel and replaces with GamePanel.
	public void startGame() {

		remove(startPanel);
		add(gamePanel);

		
		GamePanel.controlsPanel.revalidate();
	}

	public static void displayCredits() {
		JOptionPane.showMessageDialog(null, new CreditsPanel(), "Credits", JOptionPane.PLAIN_MESSAGE);
	}

	//Shakes JFrame if a tank is struck.
	public void vibrate() {
		try {
			final int originalX = this.getLocationOnScreen().x;
			final int originalY = this.getLocationOnScreen().y;
			for (int i = 0; i < 5; i++) {
				Thread.sleep(10);
				this.setLocation(originalX, originalY + VIBRATION_VELOCITY);
				Thread.sleep(10);
				this.setLocation(originalX, originalY - VIBRATION_VELOCITY);
				Thread.sleep(10);
				this.setLocation(originalX + VIBRATION_VELOCITY, originalY);
				Thread.sleep(10);
				this.setLocation(originalX, originalY);
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
	

	public static void main(String[] args) throws InterruptedException {
		new Main();
	}

}
