package utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

/**
 * Utility class for applying visual filters and transformations to {@link Icon} objects.
 * <p>
 * Provides methods for adjusting opacity and applying color overlays while preserving
 * transparency.
 */
public class IconFilter {

    /**
     * Creates a new ImageIcon with the specified opacity level.
     *
     * @param originalIcon The source Icon to modify.
     * @param opacity      The transparency level, where 0.0f is fully transparent and 1.0f is fully opaque.
     * @return A new {@link ImageIcon} with the applied opacity.
     */
    public static ImageIcon setOpacity(Icon originalIcon, float opacity) {
        int width = originalIcon.getIconWidth();
        int height = originalIcon.getIconHeight();

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();

        // Clamp opacity between 0.0 and 1.0
        opacity = Math.max(0.0f, Math.min(1.0f, opacity));

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        originalIcon.paintIcon(null, g2, 0, 0);

        g2.dispose();
        return new ImageIcon(img);
    }

    /**
     * Creates a solid black version of the provided Icon.
     *
     * @param originalIcon The source Icon.
     * @return A black {@link ImageIcon} preserving the original's shape and transparency.
     */
    public static ImageIcon cloneBlack(Icon originalIcon) {
        return applyColorFilter(originalIcon, Color.BLACK);
    }

    /**
     * Creates a darkened version of the Icon by applying a semi-transparent black overlay.
     *
     * @param originalIcon The source Icon.
     * @param alpha        The intensity of the darkness (0-255).
     * @return A darkened {@link ImageIcon}.
     */
    public static ImageIcon cloneDark(Icon originalIcon, int alpha) {
        return applyColorFilter(originalIcon, new Color(0, 0, 0, alpha));
    }

    /**
     * Internal helper to apply a color tint/overlay to an Icon using AlphaComposite.SrcAtop.
     *
     * @param originalIcon The source Icon.
     * @param filterColor  The color (including alpha) to apply over the icon.
     * @return A filtered {@link ImageIcon}.
     */
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