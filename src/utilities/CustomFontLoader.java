/**
 * Create JLabel with stroke attached.
 * Declaration : private final Font jerseyFont = CustomFontLoader.loadCustomFont("resources/Jersey10.ttf");
 * Usage : Your_JLabel.deriveFont(32f);
 *
 * @param fontFileName Path of font file.
 * @return Loaded Font object.
 */

package utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CustomFontLoader {

    public static Font loadCustomFont(String fontFileName) {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontFileName));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            return customFont.deriveFont(16f);

        } catch (IOException | FontFormatException e) {
            System.err.println("Error loading custom font: " + e.getMessage());
            return new Font("Arial", Font.PLAIN, 16);
        }
    }
}