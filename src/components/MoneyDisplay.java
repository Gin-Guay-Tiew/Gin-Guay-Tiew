package components;

import javax.swing.*;
import java.awt.*;

public class MoneyDisplay extends JPanel {
    public MoneyDisplay() {
        setBackground(Color.orange);

        JLabel money = new JLabel("Money");

        ImageIcon icon = new ImageIcon("resources/images/Money.png");
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);

        money.setIcon(icon);

        add(money);
    }
}
