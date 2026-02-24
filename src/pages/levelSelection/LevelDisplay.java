package pages.levelSelection;

import utilities.CustomJLabel;
import utilities.CustomFontLoader;
import utilities.IconImage;
import utilities.IconFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LevelDisplay extends JPanel {
    private final Font jerseyFont = CustomFontLoader.loadCustomFont("resources/Jersey10.ttf");
    String[] levelsName = {"Wat Plook", "Vidya Garden<br>Market", "KMITL", "Suvarnabhumi<br>Airport", "Jurassic Park"};

    public LevelDisplay() {
        // DISPLAY Configuration //
        setLayout(new GridLayout(2, 3));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

        // Mapping array to get 1 2 3 / 6 5 4 order
        int[] displayOrder = {1, 2, 3, 6, 5, 4};

        for (int levelNum : displayOrder) {
            JPanel levelContainer = new JPanel();
            levelContainer.setLayout(new OverlayLayout(levelContainer));
            levelContainer.setOpaque(false);

            if (levelNum <= levelsName.length) {

                // Shadow (Bottom Layer)
                ImageIcon rawShadow = IconImage.create("resources/images/levels/Shadow.png", 150, 40);
                ImageIcon shadow_Selected = IconFilter.setOpacity(rawShadow, 0.35f);
                ImageIcon shadow_Unselect = IconFilter.setOpacity(rawShadow, 0.1f);
                JLabel iconShadow = new JLabel(shadow_Unselect);

                iconShadow.setAlignmentX(0.5f);
                iconShadow.setAlignmentY(0.5f);
                iconShadow.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));

                // Level Image (Middle Layer)
                String imagePath = "resources/images/levels/Level" + levelNum + ".png";
                ImageIcon icon_Selected = IconImage.create(imagePath, 130, 130);
                ImageIcon icon_Unselected = IconFilter.cloneDark(icon_Selected, 100);
                ImageIcon icon_Locked = IconFilter.cloneDark(icon_Selected, 200);
                JButton iconLevel = new JButton(icon_Locked);

                iconLevel.setBorderPainted(false);
                iconLevel.setContentAreaFilled(false);
                iconLevel.setFocusPainted(false);
                iconLevel.setAlignmentX(0.5f);
                iconLevel.setAlignmentY(0.5f);
                iconLevel.setCursor(new Cursor(Cursor.HAND_CURSOR));

                iconLevel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        iconShadow.setIcon(shadow_Selected);
                        iconLevel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        iconShadow.setIcon(shadow_Unselect);
                        iconLevel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                    }
                });

                // Level name (Top Layer)
                CustomJLabel textLabel = new CustomJLabel(levelsName[levelNum - 1], 5f);
                textLabel.setFont(jerseyFont.deriveFont(30f));
                textLabel.setAlignmentX(0.5f);
                textLabel.setAlignmentY(1f);
                textLabel.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 10));

                // Add To Panel
                levelContainer.add(textLabel);
                levelContainer.add(iconLevel);
                levelContainer.add(iconShadow);

            } else {
                levelContainer.add(new JLabel("")); // Empty slot
            }

            add(levelContainer);
        }
    }
}