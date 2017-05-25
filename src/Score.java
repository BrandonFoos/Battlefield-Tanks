import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Score extends JPanel {
	private static final long serialVersionUID = 1L;
	private int score0 = 0;
	public boolean score0Enabled = false;
	public int score1 = 0;
	public boolean score1Enabled = false;
	public int score2 = 0;
	public boolean score2Enabled = false;
	public int score3 = 0;
	public boolean score3Enabled = false;

	JLabel player0;
	JLabel player1;
	JLabel player2;
	JLabel player3;

	public Score() {
		setLayout(new FlowLayout(FlowLayout.LEFT));

		setSize(200, 500);
		setOpaque(false);

		player0 = new JLabel();
		player0.setFont(new Font("Futura", 1, 15));
		player0.setText("Player: $" + score0);
		player0.setForeground(Color.WHITE);
		add(player0);
		
		player1 = new JLabel("Computer 1: $" + String.valueOf(score1));
		player1.setFont(new Font("Futura", 1, 15));
		player1.setForeground(Color.white);
		add(player1);
		
		
		if (score2Enabled) {
			player2 = new JLabel("Computer 2: $" + String.valueOf(score2));
			player2.setFont(new Font("Futura", 1, 15));
			player2.setForeground(Color.white);
			add(player2);
		}

		if (score3Enabled) {
			player3 = new JLabel("Computer 3: $" + String.valueOf(score3));
			player3.setFont(new Font("Futura", 1, 15));
			player3.setForeground(Color.WHITE);
			add(player3);
		}
		
		
	}

	public void UpdateScore(int player, int amount) {
		switch (player) {
		case 0:
			score0 += amount;
			System.out.println("This score: " + score0);
			player0.setText("Player: $" + score0);
			player0.revalidate();

			break;
		case 1:
			score1 += amount;
			player1.setText("Computer 1: $" + score1);
			player1.revalidate();
			break;
		case 2:
			if (score2Enabled)
			{
			player2.setText("Computer 2: $" + String.valueOf(score2 += amount));
			}
			break;
		case 3:
			if (score2Enabled)
				player3.setText("Computer 3: $" + String.valueOf(score3 += amount));
			break;
		}
		revalidate();
	}

	public void checkWin() {
		if (score0 > score1)
		{
			JOptionPane.showMessageDialog(null, "Player Wins","Winner",JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Computer Wins","Winner",JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
	}

}
