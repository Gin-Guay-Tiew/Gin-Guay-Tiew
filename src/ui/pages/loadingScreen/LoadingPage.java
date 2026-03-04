package ui.pages.loadingScreen;

import javax.swing.*;
import java.awt.*;

public class LoadingPage extends JPanel {

    private final Image bgImage;

    public LoadingPage() {
        this.bgImage = new ImageIcon("resources/images/loadingScreen/Level1.png").getImage();

        setLayout(new BorderLayout());
        setOpaque(true);

        add(new JLabel(), BorderLayout.CENTER);
        add(new TipsLabel(), BorderLayout.SOUTH);
    }

    // Draw BG Image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }
}