package ui.pages.gamePlay;

import utilities.FontLoader;

import javax.swing.*;
import java.awt.*;

public class TimeDisplay extends JPanel {
    private final Font jerseyFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");
    private JLabel minLabel;
    private JLabel secLabel;

    public TimeDisplay(){

        setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 15));
        setBackground(new Color(225, 225, 225));

        Font font25 =  jerseyFont.deriveFont(30f);

        JLabel timeLabel = new JLabel("Time : ");
        timeLabel.setFont(font25);

        minLabel = new JLabel("00");
        minLabel.setFont(font25);
        minLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        minLabel.setPreferredSize(new Dimension(25,25));

        JLabel colonLabel = new JLabel(":");
        colonLabel.setFont(font25);

        secLabel = new JLabel("00");
        secLabel.setFont(font25);
        secLabel.setHorizontalAlignment(SwingConstants.LEFT);
        secLabel.setPreferredSize(new Dimension(25,25));

        add(timeLabel);
        add(minLabel);
        add(colonLabel);
        add(secLabel);
    }

    public void updateTime(int totalSeconds){
        int min = totalSeconds / 60;
        int sec = totalSeconds % 60;

        minLabel.setText(String.format("%02d",min));
        secLabel.setText(String.format("%02d",sec));
    }
}
