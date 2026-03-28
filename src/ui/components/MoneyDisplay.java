package ui.components;

import logic.GamePlay.PlayerData;
import main.MainFrame;
import utilities.FontLoader;
import utilities.IconImage;

import javax.swing.*;
import java.awt.*;

public class MoneyDisplay extends JPanel {
    private final Font jerseyFont = FontLoader.loadCustomFont("/font/Jersey10.ttf");
    private CustomJLabel moneyAmount;
    private PlayerData data;

    public MoneyDisplay(MainFrame mainFrame) {
        // Display
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 37, 10, 37));
        setOpaque(false);

        // PaddingLabel
        JLabel paddingLabel = new JLabel(" ");
        paddingLabel.setFont(jerseyFont.deriveFont(17f));

        // MoneyLabel
        JLabel moneyLabel = new JLabel("Money");
        ImageIcon icon = IconImage.create("/images/shared/Money.png", 20, 20); // Icon for JLabel
        moneyLabel.setFont(jerseyFont.deriveFont(17f));
        moneyLabel.setIcon(icon);
        moneyLabel.setVerticalAlignment(JLabel.CENTER);

        // MoneyAmount
        moneyAmount = new CustomJLabel(String.format("%,d N", mainFrame.getPlayerData().getMoney()), 4f);
        moneyAmount.setFont(jerseyFont.deriveFont(25f));
        moneyAmount.setVerticalAlignment(JLabel.CENTER);
        moneyAmount.setHorizontalAlignment(JLabel.CENTER);
        moneyAmount.setForeground(new Color(230, 181, 42));
        moneyAmount.setOutlineColor(new Color(115, 51, 12));
        moneyAmount.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        mainFrame.getPlayerData().addPropertyChangeListener(evt -> {
            if ("money".equals(evt.getPropertyName())) {
                updateMoney((int) evt.getNewValue());
            }
        });

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
        g.drawImage(new ImageIcon(getClass().getResource("/images/shared/SignFrame.png")).getImage(), 0, 0, getWidth(), getHeight(), this);
    }

}