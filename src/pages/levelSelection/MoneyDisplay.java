package pages.levelSelection;

import javax.swing.*;
import java.awt.*;

public class MoneyDisplay extends JPanel {
    public MoneyDisplay() {
        setBackground(Color.orange);

        JLabel money = new JLabel("Money");

        ImageIcon icon = new ImageIcon("Money.png");
        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // scale it smoothly
        icon = new ImageIcon(newimg);  // transform it back to ImageIcon

        money.setIcon(icon);

        add(money);
    }
}
