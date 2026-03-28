package ui.pages.loadingScreen;

import ui.components.CustomJLabel;
import utilities.FontLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;
import java.util.*;
import javax.swing.Timer;

public class TipsLabel extends JPanel {
    private final CustomJLabel tipsPanel;
    private final String[] tips = {
            "Did you know? You can adjust you volume in the settings.",
            "Use your inner noodle spirit to serve customer faster!",
            "Keep an eye on heat meter next to the pot.",
            "Remember to save your progress frequently!",
            "Check out 'Tutorial' if you don't know how to play.",
    };
    private final Timer tipTimer;
    private final Random random = new Random();
    Font jersyFont = FontLoader.loadCustomFont("/font/Jersey10.ttf");

    public TipsLabel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 50, 25, 35));
        setOpaque(false);

        tipsPanel = new CustomJLabel("", 5f, true);
        tipsPanel.setOpaque(false);
        tipsPanel.setFocusable(false);
        tipsPanel.setFont(jersyFont.deriveFont(30f));
        tipsPanel.setForeground(Color.white);
        tipsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 90, 0));
        setTipText(tips[random.nextInt(tips.length)]);

        // Initial tip
        setTipText(tips[random.nextInt(tips.length)]);

        // Loading
        JLabel loadLabel = new JLabel("Loading");
        loadLabel.setFont(jersyFont.deriveFont(32f));
        loadLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        loadLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        loadLabel.setIcon(new ImageIcon(getClass().getResource("/images/loadingScreen/Loading.gif")));

        add(tipsPanel, BorderLayout.NORTH);
        add(loadLabel, BorderLayout.SOUTH);

        tipTimer = new Timer(5000, e -> showNextTip());
        tipTimer.start();
    }

    private void showNextTip() {
        String newTip = tips[random.nextInt(tips.length)];
        setTipText(newTip);
    }

    public void setTipText(String text) {
        tipsPanel.setText("TIP: "+text);
    }

    public void stopTimer() {
        if (tipTimer != null) tipTimer.stop();
    }
}