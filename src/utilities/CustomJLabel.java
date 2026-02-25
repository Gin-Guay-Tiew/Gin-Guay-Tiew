package utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

/**
 * A custom JLabel that renders text with a decorative outline (stroke).
 * <p>
 * Supports basic multi-line text using the {@code <br>} tag.
 */
public class CustomJLabel extends JLabel {

    private float strokePixels;
    private Color outlineColor = Color.BLACK;

    /**
     * Constructs a new CustomJLabel with a specified outline thickness.
     * <p>
     * Usage: {@code new CustomJLabel("HELLO<br>WORLD", 2.0f);}
     *
     * @param text         The string to display (supports {@code <br>} for line breaks).
     * @param strokePixels The thickness of the text outline in pixels.
     */
    public CustomJLabel(String text, float strokePixels) {
        super(text.trim());
        this.strokePixels = strokePixels;
    }

    /**
     * Sets the fill color of the text.
     */
    public void setTextColor(Color color) {
        setForeground(color);
        repaint();
    }

    /**
     * Sets the color of the text outline.
     */
    public void setOutlineColor(Color color) {
        this.outlineColor = color;
        repaint(); // Refresh the component to show the new color
    }

    /**
     * Sets the thickness of the outline.
     */
    public void setOutlineThickness(float pixels) {
        this.strokePixels = pixels;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        String[] lines = getText().split("<br>");
        FontMetrics fm = g2.getFontMetrics(getFont());
        int lineHeight = fm.getHeight();
        int totalHeight = lineHeight * lines.length;

        int y = (getHeight() - totalHeight) / 2 + fm.getAscent();

        for (String line : lines) {
            String paddedLine = " " + line.trim() + " ";
            int x = (getWidth() - fm.stringWidth(paddedLine)) / 2;

            TextLayout tl = new TextLayout(paddedLine, getFont(), g2.getFontRenderContext());
            Shape shape = tl.getOutline(AffineTransform.getTranslateInstance(x, y));

            // Draw Outline
            g2.setColor(outlineColor);
            g2.setStroke(new BasicStroke(strokePixels));
            g2.draw(shape);

            // Fill Text
            g2.setColor(getForeground());
            g2.fill(shape);

            y += lineHeight;
        }
        g2.dispose();
    }
}