package ui.pages.levelSelection;

import main.MainFrame;
import javax.swing.*;
import java.awt.*;

import static utilities.IconFilter.setOpacity;

public class LevelSelectPage extends JPanel {
    private Image backgroundImage;

    public LevelSelectPage(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        setOpaque(true);
        this.backgroundImage = new ImageIcon("resources/images/levelSelection/Background.png").getImage();
        add(new TopBar(mainFrame), BorderLayout.NORTH);
        add(new LevelsDisplay(mainFrame, this), BorderLayout.CENTER);
    }

    public void changeBg(String imagePath) {
        ImageIcon newIcon = new ImageIcon(imagePath);
        if (!imagePath.equals("resources/images/levelSelection/Background.png")) {
            newIcon = setOpacity(newIcon, 0.5f);
        }
        this.backgroundImage = newIcon.getImage();
        repaint();
        revalidate();
    }

    // Draw BG Image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}