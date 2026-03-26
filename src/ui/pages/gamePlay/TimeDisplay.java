package ui.pages.gamePlay;

import ui.components.CustomJLabel;
import utilities.FontLoader;

import javax.swing.*;
import java.awt.*;

public class TimeDisplay extends JPanel {
    private final Font jerseyFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");
    private CustomJLabel minLabel;
    private CustomJLabel secLabel;
    private CustomJLabel colonLabel;
    private CustomJLabel countLabel;

    public TimeDisplay() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 15, 0, 15));
        setOpaque(false);

        Font timerFont = jerseyFont.deriveFont(35f);
        Font smallFont = jerseyFont.deriveFont(20f);

        JPanel timerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        timerPanel.setOpaque(false);

        minLabel = new CustomJLabel("00", 5f);
        minLabel.setFont(timerFont);
        minLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        minLabel.setPreferredSize(new Dimension(35, 35));
        minLabel.setForeground(Color.white);

        colonLabel = new CustomJLabel(":", 5f);
        colonLabel.setFont(timerFont);
        colonLabel.setForeground(Color.white);
        colonLabel.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));

        secLabel = new CustomJLabel("00", 5f);
        secLabel.setFont(timerFont);
        secLabel.setHorizontalAlignment(SwingConstants.LEFT);
        secLabel.setPreferredSize(new Dimension(35, 35));
        secLabel.setForeground(Color.white);

        timerPanel.add(minLabel);
        timerPanel.add(colonLabel);
        timerPanel.add(secLabel);

        countLabel = new CustomJLabel("0/20", 3.5f);
        countLabel.setFont(smallFont);
        countLabel.setForeground(new Color(200, 200, 200));
        countLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        countLabel.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));

        add(timerPanel);
        add(Box.createVerticalStrut(-5));
        add(countLabel);
    }

    public void updateTime(int totalSeconds) {
        int min = totalSeconds / 60;
        int sec = totalSeconds % 60;

        if (totalSeconds <= 10) {
            Color urgencyColor = new Color(255, 75, 75);
            minLabel.setForeground(urgencyColor);
            secLabel.setForeground(urgencyColor);
            colonLabel.setForeground(urgencyColor);
        } else {
            minLabel.setForeground(Color.white);
            secLabel.setForeground(Color.white);
            colonLabel.setForeground(Color.white);
        }

        minLabel.setText(String.format("%02d", min));
        secLabel.setText(String.format("%02d", sec));
    }

    public void updateCount(int current, int max) {
        countLabel.setText(current + "/" + max);
    }
}