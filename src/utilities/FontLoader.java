package utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Utility class for loading and registering external font files.
 */
public class FontLoader {

    /**
     * Loads a custom font from the specified file path
     * Usage Example:
     * <pre>
     * Font jerseyFont = CustomFontLoader.loadCustomFont("resources/font/Jersey10.ttf");
     * myLabel.setFont(jerseyFont.deriveFont(32f));
     * </pre>
     *
     * @param fontFileName The relative or absolute path to the .ttf font file.
     * @return A {@link Font} object with a default size of 16pt.
     * Returns a plain "Arial" font if loading fails.
     */
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