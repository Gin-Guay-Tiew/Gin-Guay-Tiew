/**
 * Create JLabel with stroke attached.
 * Usage : new CustomJLabel("STRING HERE<br>SUPPORT LINEBREAKS", (float) strokePixels);
 *
 * @param text The string you want to turn into CustomJLabel.
 * @param strokePixels Stroke thickness.
 * @return JLabel object with stroke.
 */

package utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

public class CustomJLabel extends JLabel {
    private final float strokePixels;

    public CustomJLabel(String text, float strokePixels) {
        super(text.trim());
        this.strokePixels = strokePixels;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Split by <br> tag
        String[] lines = getText().split("<br>");
        FontMetrics fm = g2.getFontMetrics(getFont());
        int lineHeight = fm.getHeight();
        int totalHeight = lineHeight * lines.length;
        int y = (getHeight() - totalHeight) / 2 + fm.getAscent();

        for (String line : lines) {
            // Force " " on every line
            String paddedLine = " " + line.trim() + " ";

            int x = (getWidth() - fm.stringWidth(paddedLine)) / 2;
            TextLayout tl = new TextLayout(paddedLine, getFont(), g2.getFontRenderContext());
            Shape shape = tl.getOutline(AffineTransform.getTranslateInstance(x, y));

            // Draw Outlin
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(strokePixels));
            g2.draw(shape);

            // Fill Text
            g2.setColor(Color.WHITE);
            g2.fill(shape);

            y += lineHeight;
        }
        g2.dispose();
    }
}