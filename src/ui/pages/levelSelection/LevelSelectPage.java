package ui.pages.levelSelection;

import main.MainFrame;

import javax.swing.*;
import java.awt.*;

public class LevelSelectPage extends JPanel {
    public LevelSelectPage(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        setOpaque(true);

        add(new TopBar(mainFrame), BorderLayout.NORTH);
        add(new LevelsDisplay(mainFrame), BorderLayout.CENTER);
    }

    // Draw BG Image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("resources/images/levelSelection/Background.png").getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}