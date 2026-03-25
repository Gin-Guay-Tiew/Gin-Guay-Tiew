package ui.components;

import utilities.FontLoader;
import utilities.IconImage;

import javax.swing.*;
import java.awt.*;

public class MoneyDisplay extends JPanel {
    private final Font jerseyFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");
    private JLabel moneyAmount;

    public MoneyDisplay(int amount) {
        // Display
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 37, 10, 37));
        setOpaque(false);

        // PaddingLabel
        JLabel paddingLabel = new JLabel(" ");
        paddingLabel.setFont(jerseyFont.deriveFont(17f));

        // MoneyLabel
        JLabel moneyLabel = new JLabel("Money");
        ImageIcon icon = IconImage.create("resources/images/shared/Money.png", 20, 20); // Icon for JLabel
        moneyLabel.setFont(jerseyFont.deriveFont(17f));
        moneyLabel.setIcon(icon);
        moneyLabel.setVerticalAlignment(JLabel.CENTER);

        // MoneyAmount
        moneyAmount = new JLabel(String.format("%,d N", amount));
        moneyAmount.setFont(jerseyFont.deriveFont(25f));
        moneyAmount.setVerticalAlignment(JLabel.CENTER);
        moneyAmount.setHorizontalAlignment(JLabel.CENTER);

        add(paddingLabel, BorderLayout.NORTH);
        add(moneyLabel, BorderLayout.CENTER);
        add(moneyAmount, BorderLayout.SOUTH);
    }

    public void updateMoney(int amount) {
        moneyAmount.setText(String.format("%,d N", amount));
        revalidate();
        repaint();
    }

    // Draw BG Image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("resources/images/shared/SignFrame.png").getImage(), 0, 0, getWidth(), getHeight(), this);
    }

}