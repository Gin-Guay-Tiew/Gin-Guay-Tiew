package pages.levelSelection;

import pages.MainFrame;

import javax.swing.*;
import java.awt.*;

public class LevelSelectPage extends JPanel {

    private MainFrame mainFrame;
    private Image bgImage;

    public LevelSelectPage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.bgImage = new ImageIcon("resources/images/levels/Background.png").getImage();

        setLayout(new BorderLayout());
        setOpaque(true);

        // LEVEL_DISPLAY Configuration //
        LevelDisplay display = new LevelDisplay();
        display.setOpaque(false);

        // Add To Panel
        add(new TopBar(mainFrame), BorderLayout.NORTH);
        add(display, BorderLayout.CENTER);
    }

    // Draw BG Image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }
}