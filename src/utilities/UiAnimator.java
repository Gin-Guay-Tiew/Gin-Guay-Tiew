package utilities;

import javax.swing.*;
import java.awt.event.*;

public class UiAnimator {
    private final JComponent component;
    private Timer currentTimer;
    private int currentPadding = 0;

    public UiAnimator(JComponent component) {
        this.component = component;
    }

    /**
     * Animates the top padding of the component.
     * @param targetPadding The final padding amount you want to reach
     * @param speed How many pixels to move per frame
     * @param delay Milliseconds between frames (e.g., 15 for ~60fps)
     */
    public void animatePos(int targetPadding, int speed, int delay) {
        // Kill any ongoing animation
        if (currentTimer != null && currentTimer.isRunning()) {
            currentTimer.stop();
        }

        currentTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPadding == targetPadding) {
                    currentTimer.stop();
                    return;
                }

                if (currentPadding < targetPadding) {
                    currentPadding = Math.min(currentPadding + speed, targetPadding);
                } else {
                    currentPadding = Math.max(currentPadding - speed, targetPadding);
                }

                component.setBorder(BorderFactory.createEmptyBorder(currentPadding, 0, 0, 0));
            }
        });

        currentTimer.start();
    }
}