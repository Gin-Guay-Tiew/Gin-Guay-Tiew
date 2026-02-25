package utilities;

import javax.swing.*;
import java.awt.*;

/**
 * Class for Managing navigation between different UI panels with Transitions.
 */
public class PageNavigator {

    private final JPanel mainPanel;
    private final CardLayout cardLayout;
    private final Transition animator;

    /**
     * Constructs a PageNavigator.
     *
     * @param mainPanel  The main container JPanel.
     * @param cardLayout The CardLayout.
     * @param animator   The Transition instance.
     */
    public PageNavigator(JPanel mainPanel, CardLayout cardLayout, Transition animator) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.animator = animator;
    }

    /**
     * Instantly switches the view to the specified page.
     *
     * @param pageName The string identifier of the page to show (e.g., MainFrame.MAIN_MENU).
     */
    public void toPage(String pageName) {
        toPage(pageName, false, 0);
    }

    /**
     * Switches the view to the specified page, Applying a default 500ms transition.
     *
     * @param pageName        The string identifier of the page to show.
     * @param applyTransition If true, plays the expansion and fade-out transition.
     */
    public void toPage(String pageName, boolean applyTransition) {
        toPage(pageName, applyTransition, 500);
    }

    /**
     * Switches the view to the specified page with full control over the transition and timing.
     *
     * @param pageName        The string identifier of the page to show.
     * @param applyTransition If true, plays the expansion and fade-out transition.
     * @param waitTime        The delay in milliseconds before switching page. (Ignored if applyTransition is false)
     */
    public void toPage(String pageName, boolean applyTransition, int waitTime) {
        if (!applyTransition) {
            cardLayout.show(mainPanel, pageName);
            return;
        }

        animator.expand(75);
        Timer timer = new Timer(waitTime, e -> {
            cardLayout.show(mainPanel, pageName);
            animator.fadeOut(0.1f);
        });
        timer.setRepeats(false);
        timer.start();
    }
}