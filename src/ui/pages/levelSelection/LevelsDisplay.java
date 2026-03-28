package ui.pages.levelSelection;

import logic.GamePlay.PlayerData;
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
    private final Font jerseyFont = FontLoader.loadCustomFont("/font/Jersey10.ttf");
    private List<Level> levelsInfo;
    private MainFrame mainFrame;
    PopupWindow pop = new PopupWindow();

    private void initLevels() {
        levelsInfo = new ArrayList<>();
        levelsInfo.add(new Level("IT Building", 0, true));
        levelsInfo.add(new Level("Faculty Of<br>Architecture", 1000, false, 50));
        levelsInfo.add(new Level("Vidva Garden<br>Market", 2000, false));
        levelsInfo.add(new Level("Suvarnabhumi<br>Airport", 3000, false));
        levelsInfo.add(new Level("Mars", 4000, false));
    }

    public LevelsDisplay(MainFrame mainFrame, LevelSelectPage LvPage) {
        initLevels();

        // Display
        setLayout(new GridLayout(2, 3));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 35));
        setOpaque(false);
        this.mainFrame = mainFrame;

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

                if (mainFrame.getPlayerData().getLevel() >= levelNum) {
                    current_lv.isUnlocked = true;
                }

                // Level Image (Middle Layer)
                String imagePath = "/images/levelSelection/Level" + levelNum + "/Image.png";
                ImageIcon icon_Selected = new ImageIcon(getClass().getResource((imagePath)));
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
                String lockPath = "/images/levelSelection/Locked.png";
                JLabel lockIcon = new JLabel(new ImageIcon(getClass().getResource((lockPath))));
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
                        LvPage.changeBg("/images/shared/levelBackgrounds/Level" + levelNum + ".png");
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (iconLevel.getIcon() == icon_Selected) {
                            iconLevel.setIcon(icon_Unselected);
                        }
                        iconLevel.setBorder(BorderFactory.createEmptyBorder(0, 0, current_lv.iconBtmMargin, 0));
                        textLabel.setTextColor(Color.white);
                        LvPage.changeBg("/images/levelSelection/Background.png");
                    }
                });
                iconLevel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SFXManager.play(SFX.CLICK);
                        if (current_lv.isUnlocked) {
                            int realLevel = Math.min(levelNum, levelsInfo.size());
                            mainFrame.startNewGame(realLevel);
                            return;
                        }
                        String[] btnPaths = {
                                "/images/shared/buttons/Yes",
                                "/images/shared/buttons/No"
                        };
                        String[] btnLabels = {"Yes", "No"}; // "No" triggers dialog.dispose() will close popup naja!
                        ActionListener[] btnActions = {
                                ex -> unlockLevel(mainFrame.getPlayerData(), levelNum, iconLevel, lockIcon, icon_Unselected),
                                null
                        };
                        pop.createPopup(
                                mainFrame,
                                "Are you sure?\nUnlock this level at the cost of " + String.format("%,d", current_lv.unlockCost) + " N",
                                "/images/shared/popups/Demo.png",
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

    public void unlockLevel(PlayerData plrData, int levelNum, JButton iconLevel, JLabel lockIcon, ImageIcon defaultLevelIcon) {
        Level current_lv = levelsInfo.get(levelNum - 1);

        if (plrData.getMoney() < current_lv.unlockCost) {
            System.out.println("Give player +100000 Noodle. Now have "+plrData.getMoney());
            String[] btnPaths = {
                    "/images/shared/buttons/Ok",
            };
            String[] btnLabels = {"No"}; // "No" triggers dialog.dispose() will close popup naja!
            ActionListener[] btnActions = {
                    null
            };
            pop.createPopup(
                    mainFrame,
                    "Not enough Noodles!",
                    "/images/shared/popups/Demo.png",
                    btnPaths,
                    btnLabels,
                    btnActions
            );
            return;
        } else if (plrData.getLevel() != levelNum-1) {
            String[] btnPaths = {
                    "/images/shared/buttons/Ok",
            };
            String[] btnLabels = {"No"}; // "No" triggers dialog.dispose() will close popup naja!
            ActionListener[] btnActions = {
                    null
            };
            pop.createPopup(
                    mainFrame,
                    "Unlock previous level first!",
                    "/images/shared/popups/Demo.png",
                    btnPaths,
                    btnLabels,
                    btnActions
            );
            return;
        }

        ImageIcon loadedGif_lv = new ImageIcon(getClass().getResource(("/images/levelSelection/Level" + levelNum + "/Cracking.gif")));
        loadedGif_lv.getImage().flush();
        iconLevel.setIcon(loadedGif_lv);
        ImageIcon loadedGif_lk = new ImageIcon(getClass().getResource(("/images/levelSelection/Unlock.gif")));
        loadedGif_lk.getImage().flush();
        lockIcon.setIcon(loadedGif_lk);
        SFXManager.play(SFX.UNLOCK);
        current_lv.isUnlocked = true;
        plrData.spendMoney(current_lv.unlockCost);
        plrData.setLevel(levelNum);

        Timer gifDelay = new Timer(2000, delayEvent -> {
            iconLevel.setIcon(defaultLevelIcon);
            lockIcon.setIcon(new ImageIcon(getClass().getResource(("/images/levelSelection/Locked.gif"))));
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