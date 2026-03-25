package utilities;

import java.awt.*;
import javax.swing.*;

public class GifResizer implements Icon {
    private final ImageIcon icon;
    private final int width;
    private final int height;

    public GifResizer(String path, int width, int height) {
        this.icon = new ImageIcon(path);
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        // Paints the animated GIF scaled to fit the button
        Timer timer = new Timer(4000, e1 -> {
            // This code runs AFTER 4 seconds
            btn.setName("pot");
            ImageIcon icon = IconImage.create("resources/images/gamePlay/ingredients/noodles/boilingPot/not_boiling.png", 380, 380);
            btn.setIcon(icon);
            System.out.println("Item removed after 4 seconds");
            repaint();
        });
    }

    @Override
    public int getIconWidth() { return width; }

    @Override
    public int getIconHeight() { return height; }
}