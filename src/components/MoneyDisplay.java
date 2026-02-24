package components;

import utilities.CustomFontLoader;
import utilities.IconImage;

import javax.swing.*;
import java.awt.*;

public class MoneyDisplay extends JPanel {
    private final Font jerseyFont = CustomFontLoader.loadCustomFont("resources/Jersey10.ttf");
    ;

    public MoneyDisplay() {
        // Display
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));
        setBackground(new Color(225, 225, 225));

        // MoneyLabel
        JLabel moneyLabel = new JLabel("Money");
        ImageIcon icon = IconImage.create("resources/images/shared/Money.png", 20, 20); // Icon for JLabel
        moneyLabel.setFont(jerseyFont.deriveFont(17f));
        moneyLabel.setIcon(icon);
        moneyLabel.setForeground(new Color(100, 100, 100));
        moneyLabel.setVerticalAlignment(JLabel.CENTER);

        // MoneyAmount
        JLabel moneyAmount = new JLabel("100,000 N");
        moneyAmount.setFont(jerseyFont.deriveFont(25f));

        add(moneyLabel, BorderLayout.NORTH);
        add(moneyAmount, BorderLayout.SOUTH);
    }
}