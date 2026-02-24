package components;

import utilities.CustomFontLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

public class BackBtn extends JButton {
    private final Font jerseyFont = CustomFontLoader.loadCustomFont("resources/Jersey10.ttf");
    ;

    public BackBtn() {
        // BUTTON Configuration //
        super("< Back");
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFont(jerseyFont.deriveFont(32f));
    }

    // Graphics2D Outline & Hover Effects
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        String text = getText();
        FontMetrics fm = g2.getFontMetrics(getFont());
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;

        TextLayout tl = new TextLayout(text, getFont(), g2.getFontRenderContext());
        Shape shape = tl.getOutline(AffineTransform.getTranslateInstance(x, y));

        // Draw Outline
        if (getModel().isRollover()) {
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(5));
            g2.draw(shape);
        }

        // Fill Text
        g2.setColor(getModel().isRollover() ? Color.WHITE : Color.BLACK);
        g2.fill(shape);

        g2.dispose();
    }
}