package ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

/**
 * A custom JLabel that renders text with a decorative outline (stroke).
 * <p>
 * Supports basic multi-line text using the {@code <br>} tag and optional top-left alignment.
 */
public class CustomJLabel extends JLabel {

    private float strokePixels;
    private Color outlineColor = Color.BLACK;
    private boolean alignTopLeft = false;

    /**
     * Constructs a new CustomJLabel with a specified outline thickness.
     * Default behavior centers the text.
     *
     * @param text         The string to display (supports {@code <br>} for line breaks).
     * @param strokePixels The thickness of the text outline in pixels.
     */
    public CustomJLabel(String text, float strokePixels) {
        super(text != null ? text.trim() : "");
        this.strokePixels = strokePixels;
    }

    /**
     * Constructs a new CustomJLabel with specified outline thickness and alignment behavior.
     *
     * @param text         The string to display (supports {@code <br>} for line breaks).
     * @param strokePixels The thickness of the text outline in pixels.
     * @param alignTopLeft If true, text is pinned to the top-left; if false, text is centered.
     */
    public CustomJLabel(String text, float strokePixels, boolean alignTopLeft) {
        super(text != null ? text.trim() : "");
        this.strokePixels = strokePixels;
        this.alignTopLeft = alignTopLeft;
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
        repaint();
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

        String text = getText();
        if (text == null || text.isEmpty()) {
            g2.dispose();
            return;
        }

        String[] lines = text.split("<br>");
        FontMetrics fm = g2.getFontMetrics(getFont());
        int lineHeight = fm.getHeight();
        int totalHeight = lineHeight * lines.length;

        int y;
        if (alignTopLeft) {
            y = fm.getAscent();
        } else {
            y = (getHeight() - totalHeight) / 2 + fm.getAscent();
        }

        for (String line : lines) {
            String cleanLine = line.trim();
            int x;
            if (alignTopLeft) {
                x = (int) strokePixels;
            } else {
                x = (getWidth() - fm.stringWidth(cleanLine)) / 2;
            }

            if (!cleanLine.isEmpty()) {
                TextLayout tl = new TextLayout(cleanLine, getFont(), g2.getFontRenderContext());
                Shape shape = tl.getOutline(AffineTransform.getTranslateInstance(x, y));

                g2.setColor(outlineColor);
                g2.setStroke(new BasicStroke(strokePixels, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.draw(shape);

                g2.setColor(getForeground());
                g2.fill(shape);
            }

            y += lineHeight;
        }
        g2.dispose();
    }
}