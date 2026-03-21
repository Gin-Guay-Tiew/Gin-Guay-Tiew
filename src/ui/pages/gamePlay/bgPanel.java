package ui.pages.gamePlay;
import javax.swing.*;
import java.awt.*;

public class bgPanel extends JPanel {
    private Image bg;

    public bgPanel() {
        setLayout(null);
        setOpaque(false);
    }

    public void setBackgroundImage(String path) {
        bg = new ImageIcon(path).getImage();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bg != null) {
            g.drawImage(bg, 0, 0, getWidth(),getHeight(), this);
        }
    }
}