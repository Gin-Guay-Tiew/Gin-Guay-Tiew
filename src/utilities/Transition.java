package utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Manages visual transitions for UI components, specifically handling expansion and fade effects.
 * <p>
 * This class uses a {@link Timer} to animate a transition.
 */
public class Transition {
    private final JButton target;
    private final Image originalImage;
    private Timer timer;
    private int currentSize;
    private float alpha = 1.0f;

    /**
     * Constructs a new Transition handler for a specific button.
     *
     * @param target The JButton to be animated.
     * @param icon   The source icon used for the animation frames.
     */
    public Transition(JButton target, ImageIcon icon) {
        this.target = target;
        this.originalImage = icon.getImage();
        this.currentSize = 0; // Reset 0
    }

    /**
     * Resets the component size to zero and expands it to a fixed maximum size.
     * <p>
     * Resets size to 0, sets alpha to 1, and expand to 1500px.
     *
     * @param step The amount of pixels to grow per animation tick.
     */
    public void expand(int step) {
        if (timer != null && timer.isRunning()) timer.stop();
        this.currentSize = 0;
        this.alpha = 1.0f;
        target.setVisible(true);

        startAnimation(step);
    }

    /**
     * Reduces the opacity of the component until it is fully transparent.
     * <p>
     * Reduces alpha to 0.
     *
     * @param speed The rate of transparency reduction (e.g., 0.05f).
     */
    public void fadeOut(float speed) {
        if (timer != null && timer.isRunning()) timer.stop();

        timer = new Timer(16, e -> {
            alpha -= speed;
            if (alpha <= 0) {
                alpha = 0;
                target.setVisible(false);
                timer.stop();
            }
            updateBoundsAndIcon();
        });
        timer.start();
    }

    /**
     * Internal helper to drive the expansion animation loop.
     * * @param step The growth increment.
     */
    private void startAnimation(int step) {
        timer = new Timer(10, e -> {
            currentSize += step;
            if (currentSize >= 1500) {
                currentSize = 1500;
                timer.stop();
            }
            updateBoundsAndIcon();
        });
        timer.start();
    }

    /**
     * Calculates new bounds to keep the component centered and updates the icon.
     */
    private void updateBoundsAndIcon() {
        int centerX = 400;
        int x = centerX - (currentSize / 2);
        int centerY = 300;
        int y = centerY - (currentSize / 2);
        target.setBounds(x, y, currentSize, currentSize);

        if (currentSize > 0) {
            target.setIcon(createFadedIcon(originalImage, currentSize, alpha));
        } else {
            target.setIcon(null);
        }
    }

    /**
     * Scales the source image and applies a specific opacity level using AlphaComposite.
     * <p>
     * Helper to scale AND apply opacity.
     *
     * @param src     The source image to transform.
     * @param size    The target width and height for the square icon.
     * @param opacity The transparency level (0.0f to 1.0f).
     * @return A new {@link ImageIcon} with the applied transformations.
     */
    private ImageIcon createFadedIcon(Image src, int size, float opacity) {
        BufferedImage buf = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = buf.createGraphics();

        // Set transparency
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2.drawImage(src, 0, 0, size, size, null);
        g2.dispose();
        return new ImageIcon(buf);
    }
}