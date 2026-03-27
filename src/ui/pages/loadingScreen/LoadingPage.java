package ui.pages.loadingScreen;

import javax.swing.*;
import java.awt.*;

import utilities.SoundManager;

public class LoadingPage extends JLayeredPane {

    private final Image bgImage;

    public LoadingPage(String SelectedLevel) {

        this.bgImage = new ImageIcon("resources/images/shared/levelBackgrounds/" + SelectedLevel + ".gif").getImage();

        setLayout(new OverlayLayout(this));

        JPanel frame = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image frameImg = new ImageIcon("resources/images/loadingScreen/Frame.png").getImage();
                g.drawImage(frameImg, 0, 0, getWidth(), getHeight(), this);
            }
        };

        frame.setOpaque(false);
        frame.setLayout(new BorderLayout());

        frame.add(new JLabel(), BorderLayout.CENTER);
        frame.add(new TipsLabel(), BorderLayout.SOUTH);

        TripleIcons icons = new TripleIcons();
        icons.setOpaque(false);

        add(icons, JLayeredPane.DRAG_LAYER);
        add(frame, JLayeredPane.PALETTE_LAYER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }
}