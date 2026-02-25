package pages.levelSelection;

import pages.MainFrame;
import utilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class LevelsDisplay extends JPanel {
    private final Font jerseyFont = CustomFontLoader.loadCustomFont("resources/Jersey10.ttf");
    private List<Level> levelsInfo;

    private void initLevels() {
        levelsInfo = new ArrayList<>();
        levelsInfo.add(new Level("Wat Plook", 0, true));
        levelsInfo.add(new Level("Vidya Garden<br>Market", 1000, false));
        levelsInfo.add(new Level("KMITL", 2000, false));
        levelsInfo.add(new Level("Suvarnabhumi<br>Airport", 3000, false));
        levelsInfo.add(new Level("Jurassic Park", 4000, false));
    }

    public LevelsDisplay(MainFrame mainFrame) {
        initLevels();

        // Display
        setLayout(new GridLayout(2, 3));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        setOpaque(false);

        // Mapping array to get 1 2 3 / 6 5 4 order
        int[] displayOrder = {1, 2, 3, 6, 5, 4};

        for (int levelNum : displayOrder) {
            JPanel levelContainer = new JPanel();
            levelContainer.setLayout(new OverlayLayout(levelContainer));
            levelContainer.setOpaque(false);

            if (levelNum <= levelsInfo.toArray().length) {
                // Level name (Top Layer)
                Level current_lv = levelsInfo.get(levelNum - 1);
                CustomJLabel textLabel = new CustomJLabel(current_lv.name, 5f);
                textLabel.setFont(jerseyFont.deriveFont(25f));
                textLabel.setTextColor(Color.WHITE);
                textLabel.setAlignmentX(0.5f);
                textLabel.setAlignmentY(1f);
                textLabel.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 10));

                // Level Image (Middle Layer)
                String imagePath = "resources/images/levelSelection/Level" + levelNum + ".png";
                ImageIcon icon_Selected = IconImage.create(imagePath, 130, 130);
                ImageIcon icon_Unselected = IconFilter.cloneDark(icon_Selected, 50);
                ImageIcon icon_Locked = IconFilter.cloneDark(icon_Selected, 175);
                JButton iconLevel = new JButton(icon_Unselected);
                iconLevel.setBorderPainted(false);
                iconLevel.setContentAreaFilled(false);
                iconLevel.setFocusPainted(false);
                iconLevel.setAlignmentX(0.5f);
                iconLevel.setAlignmentY(0.5f);
                iconLevel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                /// TEST
                if (levelNum > 1) {
                    iconLevel.setIcon(icon_Locked);
                }
                ///

                // Lock Icon (Above Image)
                String lockPath = "resources/images/shared/Locked.png";
                ImageIcon lockImg = IconImage.create(lockPath, 25, 25);
                JLabel lockIcon = new JLabel(lockImg);
                lockIcon.setAlignmentX(0.5f);
                lockIcon.setAlignmentY(0.5f);
                lockIcon.setVisible(iconLevel.getIcon() == icon_Locked);

                // Shadow (Bottom Layer)
                ImageIcon rawShadow = IconImage.create("resources/images/levelSelection/Shadow.png", 150, 40);
                ImageIcon shadow_Selected = IconFilter.setOpacity(rawShadow, 0.35f);
                ImageIcon shadow_Unselect = IconFilter.setOpacity(rawShadow, 0.1f);
                JLabel iconShadow = new JLabel(shadow_Unselect);
                iconShadow.setAlignmentX(0.5f);
                iconShadow.setAlignmentY(0.5f);
                iconShadow.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));

                // Hover Fx
                iconLevel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (iconLevel.getIcon() == icon_Unselected) { iconLevel.setIcon(icon_Selected); }
                        iconLevel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
                        lockIcon.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
                        iconShadow.setIcon(shadow_Selected);
                        textLabel.setTextColor(Color.CYAN);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (iconLevel.getIcon() == icon_Selected) { iconLevel.setIcon(icon_Unselected); }
                        iconLevel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                        lockIcon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                        iconShadow.setIcon(shadow_Unselect);
                        textLabel.setTextColor(Color.white);
                    }
                });
                iconLevel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("You need "+current_lv.unlockCost+" Noodels to unlock this!");
                    }
                });

                levelContainer.add(textLabel);
                levelContainer.add(lockIcon);
                levelContainer.add(iconLevel);
                levelContainer.add(iconShadow);
            } else {
                levelContainer.add(new JLabel("")); // Empty slot
            }

            add(levelContainer);
        }
    }

    private static class Level {
        String name;
        int unlockCost;
        boolean isUnlocked;

        public Level(String name, int unlockCost, boolean isUnlocked) {
            this.name = name;
            this.unlockCost = unlockCost;
            this.isUnlocked = isUnlocked;
        }
    }
}