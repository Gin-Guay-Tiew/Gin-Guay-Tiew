package utilities;

import javax.swing.*;
import java.awt.*;

public class IconBtn extends JButton {
    public IconBtn(ImageIcon normal, ImageIcon hover, ImageIcon pressed) {

        setIcon(normal);
        setRolloverIcon(hover);
        setPressedIcon(pressed);

        setBorder(null);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setMargin(new Insets(0,0,0,0));
        setIconTextGap(0);

        Dimension size = new Dimension(
                normal.getIconWidth(),
                normal.getIconHeight()
        );

        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
    }
}