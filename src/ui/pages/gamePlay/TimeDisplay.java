package ui.pages.gamePlay;

import utilities.FontLoader;

import javax.swing.*;
import java.awt.*;

public class TimeDisplay extends JPanel {
    private final Font jerseyFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");
    private JLabel timeLabel;

    public TimeDisplay(){

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));
        setBackground(new Color(225, 225, 225));

        timeLabel = new JLabel("Time : 1:30");
        timeLabel.setFont(jerseyFont.deriveFont(25f));
        add(timeLabel, BorderLayout.CENTER);
    }

    public void updateTime(int totalSeconds){
        int min = totalSeconds / 60;
        int sec = totalSeconds % 60;
        timeLabel.setText(String.format("Time : %02d:%02d", min, sec));
    }
}
