import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Settings extends JPanel {
	
	public Settings() {
		setOpaque(false);
		setSize(800,600);
		
	}
	public void paintComponent(Graphics g)
	{
		
	}
	
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, new Settings());
	}
}

