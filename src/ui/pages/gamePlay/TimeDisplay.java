package ui.pages.gamePlay;

import ui.components.CustomJLabel;
import utilities.IconImage;
import utilities.FontLoader;

import javax.swing.*;
import java.awt.*;

public class TimeDisplay extends JPanel {
    private final Font jerseyFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");
    private CustomJLabel minLabel;
    private CustomJLabel secLabel;
    private CustomJLabel colonLabel;
    private CustomJLabel countLabel;
    private CustomJLabel livesValueLabel;
    private JLabel heartIcon;

    public TimeDisplay() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 15));
        setOpaque(false);

        Font timerFont = jerseyFont.deriveFont(35f);
        Font smallFont = jerseyFont.deriveFont(22f);

        JPanel timerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        timerPanel.setOpaque(false);
        timerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        minLabel = new CustomJLabel("00", 5f);
        minLabel.setFont(timerFont);
        minLabel.setForeground(Color.white);
        minLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        colonLabel = new CustomJLabel(":", 5f);
        colonLabel.setFont(timerFont);
        colonLabel.setForeground(Color.white);
        colonLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        secLabel = new CustomJLabel("00", 5f);
        secLabel.setFont(timerFont);
        secLabel.setForeground(Color.white);
        secLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        timerPanel.add(minLabel);
        timerPanel.add(colonLabel);
        timerPanel.add(secLabel);

        countLabel = new CustomJLabel("NA / NA", 4.5f);
        countLabel.setFont(smallFont);
        countLabel.setTextColor(new Color(200, 200, 200));
        countLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        countLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        JPanel livesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 0));
        livesPanel.setOpaque(false);
        livesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        livesValueLabel = new CustomJLabel("N/A", 4.5f);
        livesValueLabel.setFont(smallFont);
        livesValueLabel.setTextColor(new Color(255, 50, 50));
        livesValueLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 1));

        heartIcon = new JLabel(IconImage.create("resources/images/shared/Heart.png", 20, 20));
        heartIcon.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 1));

        livesPanel.add(livesValueLabel);
        livesPanel.add(heartIcon);

        add(timerPanel);
        add(Box.createVerticalStrut(-5));
        add(countLabel);
        add(Box.createVerticalStrut(2));
        add(livesPanel);
    }

    public void updateTime(int totalSeconds) {
        int min = totalSeconds / 60;
        int sec = totalSeconds % 60;
        Color c = (totalSeconds <= 10) ? new Color(255, 75, 75) : Color.white;

        minLabel.setForeground(c);
        secLabel.setForeground(c);
        colonLabel.setForeground(c);
        minLabel.setText(String.format("%02d", min));
        secLabel.setText(String.format("%02d", sec));
    }

    public void updateCount(int current, int max) {
        countLabel.setText(current + " N / " + max + " N");
    }

    public void updateLives(int health) {
        livesValueLabel.setText(String.valueOf(health));

        if (health <= 3) {
            livesValueLabel.setTextColor(new Color(255, 0, 0));
        } else {
            livesValueLabel.setTextColor(new Color(255, 92, 92));
        }
        revalidate();
        repaint();
    }

    public void setCountColor(Color color) {
        countLabel.setTextColor(color);
        repaint();
    }
}