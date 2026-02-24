package utilities;

import javax.swing.*;
import java.awt.*;

/**
 * Utility for creating and scaling ImageIcons.
 */
public class IconImage {

    /**
     * Loads an image from the given path and scales it to the specified dimensions.
     * @param path   The file path to the image.
     * @param width  The target width in pixels.
     * @param height The target height in pixels.
     * @return A scaled {@link ImageIcon} object.
     */
    public static ImageIcon create(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);

        if (icon.getIconWidth() == -1) {
            throw new RuntimeException("FILE NOT FOUND at: " + new java.io.File(path).getAbsolutePath());
        }

        Image image = icon.getImage();
        Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
}