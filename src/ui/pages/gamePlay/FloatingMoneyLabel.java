package ui.pages.gamePlay;

import ui.components.CustomJLabel;
import utilities.FontLoader;

import javax.swing.*;
import java.awt.*;

public class FloatingMoneyLabel extends CustomJLabel {
    private int opacity = 255;
    private int yOffset = 0;

    // ตั้งชื่อตัวแปรให้สื่อความหมายชัดเจน
    private final Color GREEN_MONEY = new Color(65, 214, 65); // สีเขียวสำหรับเงิน
    private final Color RED_X = new Color(214, 65, 65);      // สีแดงสำหรับ X
    private final Color outlineColor = new Color(0, 0, 0);

    private Color currentColor; // เก็บสีที่จะใช้แสดงผลจริง
    private final Font jerseyFont = FontLoader.loadCustomFont("/font/Jersey10.ttf");

    public FloatingMoneyLabel(String amount, int x, int y) {
        super(amount, 4.0f);

        setFont(jerseyFont.deriveFont(40f));

        if (amount.equals("X")) {
            currentColor = RED_X;
        } else {
            currentColor = GREEN_MONEY;
        }

        setForeground(currentColor);
        setOutlineColor(outlineColor);
        setBounds(x, y, 150, 50);

        Timer timer = new Timer(67, e -> {
            yOffset -= 2;
            opacity -= 15; // ปรับให้จางไวขึ้นนิดหน่อยเพื่อให้ดู smooth

            if (opacity <= 0) {
                opacity = 0;
                ((Timer) e.getSource()).stop();
                if (getParent() != null) {
                    Container parent = getParent();
                    parent.remove(this);
                    parent.repaint();
                }
            } else {
                setLocation(x, y + yOffset);

                // อัปเดตสีโดยใช้ค่า currentColor ที่เลือกไว้ตอนต้น
                setForeground(new Color(
                        currentColor.getRed(),
                        currentColor.getGreen(),
                        currentColor.getBlue(),
                        opacity)
                );

                // อัปเดตสีขอบให้จางลงตาม
                setOutlineColor(new Color(
                        outlineColor.getRed(),
                        outlineColor.getGreen(),
                        outlineColor.getBlue(),
                        opacity)
                );
            }
        });
        timer.start();
    }
}