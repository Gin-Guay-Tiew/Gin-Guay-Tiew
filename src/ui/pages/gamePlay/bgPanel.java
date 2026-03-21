package ui.pages.gamePlay;
import javax.swing.*;
import java.awt.*;

public class bgPanel extends JPanel {
    private Image bg;
    private Image bgImage;
    private int x = 0;
    private int y = 0;
    private int width = 800;
    private int height = 400;

    public bgPanel() {
        setLayout(null);
        setOpaque(false);
    }


    public void setBackgroundImage(String path, int x, int y, int w, int h) {
        this.bgImage = new ImageIcon(path).getImage();
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImage != null) {
            g.drawImage(bgImage, x, y, width, height, this);
        }
    }
}