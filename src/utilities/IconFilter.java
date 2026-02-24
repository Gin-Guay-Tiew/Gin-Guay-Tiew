/**
 * Return Icon after applied filters.
 * <p>
 * [ .setOpacity ]
 * @param originalIcon Your Icon object.
 * @param opacity (float) Icon opacity.
 * <p>
 * [ .cloneDark ]
 * @param originalIcon Your Icon object.
 * @param alpha (int) How dark the Icon should be. (0-255).
 * <p>
 * [ .cloneBlack ]
 * @param originalIcon Your Icon object.
 * @return ImageIcon object.
 */

package utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class IconFilter {

    public static ImageIcon setOpacity(Icon originalIcon, float opacity) {
        int width = originalIcon.getIconWidth();
        int height = originalIcon.getIconHeight();

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();

        opacity = Math.max(0.0f, Math.min(1.0f, opacity));

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        originalIcon.paintIcon(null, g2, 0, 0);

        g2.dispose();
        return new ImageIcon(img);
    }

    public static ImageIcon cloneBlack(Icon originalIcon) {
        return applyColorFilter(originalIcon, Color.BLACK);
    }

    public static ImageIcon cloneDark(Icon originalIcon, int alpha) {
        return applyColorFilter(originalIcon, new Color(0, 0, 0, alpha));
    }

    private static ImageIcon applyColorFilter(Icon originalIcon, Color filterColor) {
        int width = originalIcon.getIconWidth();
        int height = originalIcon.getIconHeight();

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();

        originalIcon.paintIcon(null, g2, 0, 0);

        g2.setComposite(AlphaComposite.SrcAtop);
        g2.setColor(filterColor);
        g2.fillRect(0, 0, width, height);

        g2.dispose();
        return new ImageIcon(img);
    }
}