import java.awt.*;
import javax.swing.*;

public class ResizableIcon implements Icon {
    private final ImageIcon icon;
    private final int width;
    private final int height;

    public ResizableIcon(String path, int width, int height) {
        this.icon = new ImageIcon(path);
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        // Paints the animated GIF scaled to fit the button
        g.drawImage(icon.getImage(), x, y, width, height, c);
    }

    @Override
    public int getIconWidth() { return width; }

    @Override
    public int getIconHeight() { return height; }
}