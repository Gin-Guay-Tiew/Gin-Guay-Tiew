package components;

import config.CustomFontLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;

public class BackBtn extends JButton {
    public BackBtn() {
        super("< Back");
        Font JerseyFont = CustomFontLoader.loadCustomFont("resources/Jersey10.ttf");
        setFont(JerseyFont.deriveFont(24f));
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

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
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(getModel().isRollover() ? 5 : 0));
        g2.draw(shape);

        // Fill Text
        g2.setColor(getModel().isRollover() ? Color.WHITE : Color.BLACK);
        g2.fill(shape);

        g2.dispose();
    }
}
