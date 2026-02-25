package utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImageJButton extends JButton {
    ImageIcon btnImage;
    ImageIcon btnImage_Hover;
    ImageIcon btnImage_Clicked;
    public ImageJButton(String btnImagePath, String btnImageType, float fontSize, int widht, int height) {

        btnImage = IconImage.create(btnImagePath+btnImageType, widht, height);
        btnImage_Hover = IconImage.create(btnImagePath+"_Hover"+btnImageType, widht, height);
        btnImage_Clicked = IconImage.create(btnImagePath+"_Clicked"+btnImageType, widht, height);
        setIcon(btnImage);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);

        setMargin(new Insets(0, 0, 0, 0));
        setBorder(BorderFactory.createEmptyBorder());
        setIconTextGap(0);


        Font jerseyFont = CustomFontLoader.loadCustomFont("resources/Jersey10.ttf");
        setFont(jerseyFont.deriveFont(16f));


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(btnImage_Hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(btnImage);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setIcon(btnImage_Clicked);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (getBounds().contains(e.getPoint())) {
                    setIcon(btnImage_Hover);
                } else {
                    setIcon(btnImage);
                }
            }
        });
    }
}