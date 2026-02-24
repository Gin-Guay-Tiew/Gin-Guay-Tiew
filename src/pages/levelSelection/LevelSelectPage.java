package pages.levelSelection;

import pages.MainFrame;

import javax.swing.*;
import java.awt.*;

public class LevelSelectPage extends JPanel {

    private MainFrame mainFrame;
    private Image bgImage;

    public LevelSelectPage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.bgImage = new ImageIcon("resources/images/levelSelection/Background.png").getImage();

        setLayout(new BorderLayout());
        setOpaque(true);

        LevelDisplay display = new LevelDisplay();

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