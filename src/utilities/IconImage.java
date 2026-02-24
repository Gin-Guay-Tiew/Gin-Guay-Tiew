/**
 * Create ImageIcon object with proper scaling.
 *
 * @param width The width of the icon.
 * @param height The height of the icon.
 * @return ImageIcon object.
 */

package utilities;

import javax.swing.*;
import java.awt.*;

public class IconImage {

    public static ImageIcon create(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
}