
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class CreditsPanel
extends JPanel {
    private static final long serialVersionUID = 1;

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, new CreditsPanel(), "Test Credits", JOptionPane.PLAIN_MESSAGE);
    }

    public CreditsPanel() {
        JLabel titleLabel = new JLabel("Artillery Game: 1.0");
        titleLabel.setHorizontalAlignment(0);
        titleLabel.setPreferredSize(new Dimension(200, 75));
        titleLabel.setForeground(new Color(80, 80, 140));
        Font font = new Font("Courier", 1, 16);
        titleLabel.setFont(font);
        JLabel programLabel = new JLabel("Game Programming");
        programLabel.setHorizontalAlignment(0);
        programLabel.setPreferredSize(new Dimension(200, 30));
        Font font2 = new Font("Courier", 1, 15);
        programLabel.setFont(font2);
        JLabel copyrightLabel = new JLabel("<html>\u00a9 Copyright 2015 Brandon A. Foos</html>");
        copyrightLabel.setHorizontalAlignment(0);
        JLabel copyrightLabel2 = new JLabel("All Rights Reserved.");
        copyrightLabel2.setHorizontalAlignment(0);
        JLabel music = new JLabel("Music By Mattia Cupelli ");
        music.setFont(new Font("Futura", 1, 15));
        music.setHorizontalAlignment(0);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 210));
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        panel.setBackground(Color.WHITE);
        panel.add(titleLabel);
        panel.add(music);
        panel.add(copyrightLabel);
        panel.add(copyrightLabel2);
        this.add(panel);
    }
}

