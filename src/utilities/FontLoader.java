package utilities;

import java.awt.*;
import java.io.InputStream;

/**
 * Utility class for loading and registering fonts from resources (JAR-safe).
 */
public class FontLoader {

    /**
     * Loads a custom font from resources folder.
     *
     * Example:
     * Font font = FontLoader.loadCustomFont("/font/Jersey10.ttf", 32f);
     *
     * @param path Path inside resources (must start with '/')
     * @param size Font size
     * @return Font object, fallback to Arial if failed
     */
    public static Font loadCustomFont(String path) {
        try {
            InputStream is = FontLoader.class.getResourceAsStream(path);

            if (is == null) {
                throw new RuntimeException("Font not found: " + path);
            }

            Font font = Font.createFont(Font.TRUETYPE_FONT, is);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            return font.deriveFont(16f);

        } catch (Exception e) {
            System.err.println("Error loading custom font: " + e.getMessage());
            return new Font("Arial", Font.PLAIN, 16);
        }
    }
}