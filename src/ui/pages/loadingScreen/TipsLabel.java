package ui.pages.loadingScreen;

import utilities.FontLoader;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.*;
import javax.swing.Timer;

public class TipsLabel extends JPanel {
    private final JTextPane tipsPane;
    private final String[] tips = {
            "Did you know? You can adjust you volume in the settings.",
            "Use your inner noodle spirit to serve customer faster!",
            "Keep an eye on heat meter next to the pot.",
            "Remember to save your progress frequently!",
            "Check out 'Tutorial' if you don't know how to play.",
            "Nanny :O"
    };
    private final Timer tipTimer;
    private final Random random = new Random();

    public TipsLabel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 50, 100, 35));
        setOpaque(false);

        tipsPane = new JTextPane();
        tipsPane.setEditable(false);
        tipsPane.setOpaque(false);
        tipsPane.setFocusable(false);
        Font loadedFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");
        tipsPane.setFont(loadedFont.deriveFont(24f));

        // Initial tip
        setTipText(tips[random.nextInt(tips.length)]);

        add(tipsPane, BorderLayout.CENTER);

        tipTimer = new Timer(5000, e -> showNextTip());
        tipTimer.start();
    }

    private void showNextTip() {
        String newTip = tips[random.nextInt(tips.length)];
        setTipText(newTip);
    }

    private void centerText() {
        StyledDocument doc = tipsPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    public void setTipText(String text) {
        tipsPane.setText("TIP: "+text);
        centerText();
    }

    public void stopTimer() {
        if (tipTimer != null) tipTimer.stop();
    }
}