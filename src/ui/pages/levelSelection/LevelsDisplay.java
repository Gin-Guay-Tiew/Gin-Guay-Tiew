package ui.pages.levelSelection;

import main.MainFrame;
import ui.components.CustomJLabel;
import ui.components.PopupWindow;
import utilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class LevelsDisplay extends JPanel {
    private final Font jerseyFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");
    private List<Level> levelsInfo;
    PopupWindow pop = new PopupWindow();

    private void initLevels() {
        levelsInfo = new ArrayList<>();
        levelsInfo.add(new Level("IT Building", 0, true));
        levelsInfo.add(new Level("Faculty Of<br>Architecture", 1000, false, 50));
        levelsInfo.add(new Level("Vidya Garden<br>Market", 2000, false));
        levelsInfo.add(new Level("Suvarnabhumi<br>Airport", 3000, false));
        levelsInfo.add(new Level("Mars", 4000, false));
    }

    public LevelsDisplay(MainFrame mainFrame) {
        initLevels();

        // Display
        setLayout(new GridLayout(2, 3));
        setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 35));
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
                textLabel.setFont(jerseyFont.deriveFont(26f));
                textLabel.setTextColor(Color.WHITE);
                textLabel.setAlignmentX(0.5f);
                textLabel.setAlignmentY(1f);
                textLabel.setBorder(BorderFactory.createEmptyBorder(100, 10, 0, 10));

                // Level Image (Middle Layer)
                String imagePath = "resources/images/levelSelection/Level" + levelNum + "/Image.png";
                ImageIcon icon_Selected = new ImageIcon(imagePath);
                ImageIcon icon_Unselected = IconFilter.cloneDark(icon_Selected, 50);
                ImageIcon icon_Locked = IconFilter.cloneDark(icon_Selected, 175);
                JButton iconLevel = new JButton(icon_Unselected);
                iconLevel.setBorderPainted(false);
                iconLevel.setContentAreaFilled(false);
                iconLevel.setFocusPainted(false);
                iconLevel.setAlignmentX(0.5f);
                iconLevel.setAlignmentY(0.5f);
                iconLevel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                iconLevel.setBorder(BorderFactory.createEmptyBorder(0, 0, current_lv.iconBtmMargin, 0));
                if (!current_lv.isUnlocked) {
                    iconLevel.setIcon(icon_Locked);
                }

                // Lock Icon (Above Image)
                String lockPath = "resources/images/levelSelection/Locked.png";
                JLabel lockIcon = new JLabel(new ImageIcon(lockPath));
                lockIcon.setAlignmentX(0.5f);
                lockIcon.setAlignmentY(0.5f);
                lockIcon.setVisible(!current_lv.isUnlocked);

                // Hover Fx
                iconLevel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (iconLevel.getIcon() == icon_Unselected) {
                            iconLevel.setIcon(icon_Selected);
                        }
                        iconLevel.setBorder(BorderFactory.createEmptyBorder(0, 0, current_lv.iconBtmMargin + 10, 0));
                        textLabel.setTextColor(Color.CYAN);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (iconLevel.getIcon() == icon_Selected) {
                            iconLevel.setIcon(icon_Unselected);
                        }
                        iconLevel.setBorder(BorderFactory.createEmptyBorder(0, 0, current_lv.iconBtmMargin, 0));
                        textLabel.setTextColor(Color.white);
                    }
                });
                iconLevel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("You need " + current_lv.unlockCost + " Noodles to unlock this!");
                        if (current_lv.isUnlocked) {
                            int realLevel = Math.min(levelNum, levelsInfo.size());
                            mainFrame.startNewGame(realLevel);
                            return;
                        }
                        String[] btnPaths = {
                                "resources/images/shared/buttons/Yes",
                                "resources/images/shared/buttons/No"
                        };
                        String[] btnLabels = {"Yes", "No"}; // "No" triggers dialog.dispose() will close popup naja!
                        ActionListener[] btnActions = {
                                ex -> unlockLevel(levelNum, iconLevel, lockIcon, icon_Unselected),
                                null
                        };
                        pop.createPopup(
                                mainFrame,
                                "Are you sure?\nUnlock this level at the cost of " + String.format("%,d", current_lv.unlockCost) + " N",
                                "resources/images/shared/popups/Demo.png",
                                btnPaths,
                                btnLabels,
                                btnActions
                        );
                    }
                });

                levelContainer.add(textLabel);
                levelContainer.add(lockIcon);
                levelContainer.add(iconLevel);
            } else {
                levelContainer.add(new JLabel("")); // Empty slot
            }

            add(levelContainer);
        }
    }

    public void unlockLevel(int levelNum, JButton iconLevel, JLabel lockIcon, ImageIcon defaultLevelIcon) {
        ImageIcon loadedGif_lv = new ImageIcon("resources/images/levelSelection/Level" + levelNum + "/Cracking.gif");
        loadedGif_lv.getImage().flush();
        iconLevel.setIcon(loadedGif_lv);
        ImageIcon loadedGif_lk = new ImageIcon("resources/images/levelSelection/Unlock.gif");
        loadedGif_lk.getImage().flush();
        lockIcon.setIcon(loadedGif_lk);

        Level current_lv = levelsInfo.get(levelNum - 1);
        current_lv.isUnlocked = true;

        Timer gifDelay = new Timer(2000, delayEvent -> {
            iconLevel.setIcon(defaultLevelIcon);
            lockIcon.setIcon(new ImageIcon("resources/images/levelSelection/Locked.gif"));
            lockIcon.setVisible(false);
        });
        gifDelay.setRepeats(false);
        gifDelay.start();
    }

    private static class Level {
        String name;
        int unlockCost, iconBtmMargin;
        boolean isUnlocked;

        public Level(String name, int unlockCost, boolean isUnlocked) {
            this.name = name;
            this.unlockCost = unlockCost;
            this.isUnlocked = isUnlocked;
            this.iconBtmMargin = 0;
        }

        public Level(String name, int unlockCost, boolean isUnlocked, int iconBtmMargin) {
            this.name = name;
            this.unlockCost = unlockCost;
            this.isUnlocked = isUnlocked;
            this.iconBtmMargin = iconBtmMargin;
        }
    }
}