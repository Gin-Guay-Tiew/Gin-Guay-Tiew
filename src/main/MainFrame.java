package main;

import ui.pages.gamePlay.gamePlayScreen;
import ui.pages.levelSelection.LevelSelectPage;
import ui.pages.loadingScreen.LoadingPage;
import ui.pages.mainMenu.MainMenuPage;
import ui.pages.pressESC.PauseScreen;
import ui.pages.tutorialGame.GameTutorialPage;
import ui.pages.endGame.WinLosePage;
import ui.pages.settingMenu.MainSettingPage;
import ui.pages.shopUI.ShopScreen;
import logic.Shop.ShopManager;
import logic.GamePlay.PlayerData;
import utilities.DataManager;
import utilities.IconImage;
import utilities.PageNavigator;
import ui.components.PopupWindow;
import utilities.Transition;
import utilities.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements WindowListener {

    public static final String MAIN_MENU = "mainMenu";
    public static final String LEVEL_SELECT = "levelSelect";
    public static final String TUTORIAL = "tutorial";
    public static final String ENDGAME = "winlosepage";
    public static final String SETTING = "setting";
    public static final String GAME = "gamePlay";
    public static final String SHOP_UI = "shop";
    public static final String PAUSE = "pause";

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private Transition animator;
    private PageNavigator navigator;
    PopupWindow pop = new PopupWindow();
    private boolean isWarningActive = false;

    private JPanel currentGameScreen;
    private int currentLevel;

    private String previousPage;
    private PauseScreen pauseScreen;

    private PlayerData playerData = new PlayerData();
    private String forceBackPage;

    // สำหรับสร้างหน้าเกมใหม่ตอนกด play again
    public void startNewGame(int levelID) {

        currentLevel = levelID;
        SoundManager.playLevelMusic(levelID);

        if (currentGameScreen != null) {
            mainPanel.remove(currentGameScreen);
        }

        currentGameScreen = new gamePlayScreen(this, levelID);
        mainPanel.add(currentGameScreen, "gamePlay");

        mainPanel.revalidate();
        mainPanel.repaint();

        navigator.toPage("gamePlay", true, 500);
    }

    // ขอสร้างส่วนเสริมเพิ่มสำหรับกดเล่นใหม่
    public void replayGame() {
        startNewGame(currentLevel);
    }

    public void closeApp() {
        DataManager.savePlayerData(playerData);
        System.exit(0);
    }

    public PageNavigator getNavigator() {
        return navigator;
    }

    public String getPreviousPage() {
        return previousPage;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public String getForceBackPage() {
        return forceBackPage;
    }

    public MainFrame() {
        setTitle("Gin-Guay-Tiew");
        setSize(800, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(this);
        setLocationRelativeTo(null);
        setResizable(false);
        ImageIcon img = new ImageIcon("resources/images/shared/AppIcon.png");
        setIconImage(img.getImage());
        this.playerData = utilities.DataManager.loadPlayerData();
        SoundManager.setVolume(playerData.getVolumeLv() / 100f);

        // Layered Position Setup (TransitionFrame Positioning :D)
        JPanel glass = (JPanel) getGlassPane();
        glass.setLayout(null);
        glass.setVisible(true);

        ImageIcon transIcon = IconImage.create("resources/images/shared/Transition.png", 50, 50);
        JButton transFrame = new JButton();
        transFrame.setIcon(transIcon);
        transFrame.setBorderPainted(false);
        transFrame.setContentAreaFilled(false);
        transFrame.setFocusPainted(false);
        transFrame.setBounds(400, 300, 0, 0);

        ShopManager gameController = new ShopManager(this, playerData);

        animator = new Transition(transFrame, transIcon);
        glass.add(transFrame);
        navigator = new PageNavigator(mainPanel, cardLayout, animator);
        pauseScreen = new PauseScreen(this);

        mainPanel.add(new MainMenuPage(this), MAIN_MENU); // + MainMenu
        mainPanel.add(new LevelSelectPage(this), LEVEL_SELECT); // + LevelSelection
        mainPanel.add(new GameTutorialPage(this), TUTORIAL); // + Tutorial
        mainPanel.add(new LoadingPage("Level1"), "loading_1"); // + Loading Screen
        mainPanel.add(new LoadingPage("Level2"), "loading_2"); // + Loading Screen
        mainPanel.add(new LoadingPage("Level3"), "loading_3"); // + Loading Screen
        mainPanel.add(new LoadingPage("Level4"), "loading_4"); // + Loading Screen
        mainPanel.add(new LoadingPage("Level5"), "loading_5"); // + Loading Screen
        mainPanel.add(new WinLosePage(this), ENDGAME);
        mainPanel.add(new MainSettingPage(this, this.playerData), SETTING);
        mainPanel.add(new ShopScreen(this, gameController), SHOP_UI);
        mainPanel.add(pauseScreen, PAUSE);

        navigator.toPage(MAIN_MENU, false);
        SoundManager.playMenuBackground();

        add(mainPanel);

        setupGlobalESC();

        setVisible(true);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        String[] btnPaths = {
                "resources/images/shared/buttons/Yes",
                "resources/images/shared/buttons/No"
        };
        String[] btnLabels = {"Yes", "No"};
        ActionListener[] btnActions = {
                ex -> closeApp(),
                null
        };
        pop.createPopup(
                this,
                "Are you sure you want to leave the kitchen?", // Message
                "resources/images/shared/popups/Demo.png", // Background Path
                btnPaths,
                btnLabels,
                btnActions
        );
    }

    private void setupGlobalESC() {
        JRootPane root = this.getRootPane();

        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("ESCAPE"), "pauseGame");

        root.getActionMap().put("pauseGame", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentPage = navigator.getCurrentPage();

                if (GAME.equals(currentPage) || "gamePlay".equals(currentPage)) {
                    previousPage = currentPage;

                    if (currentGameScreen instanceof ui.pages.gamePlay.gamePlayScreen game) {
                        game.pauseGame();
                    }

                    navigator.toPage(PAUSE, false);
                    pauseScreen.fadeIn();
                } else if (PAUSE.equals(currentPage)) {
                    pauseScreen.fadeOut(() -> {
                        navigator.toPage(previousPage, false);
                        if (currentGameScreen instanceof ui.pages.gamePlay.gamePlayScreen game) {
                            game.resumeGame();
                        }
                    });
                }
            }
        });
    }

    private void setupGlassPane() {
        JPanel glass = (JPanel) getGlassPane();
        glass.setLayout(null);
        glass.setVisible(true);

        ImageIcon transIcon = IconImage.create("resources/images/shared/Transition.png", 50, 50);
        JButton transFrame = new JButton();
        transFrame.setIcon(transIcon);
        transFrame.setBorderPainted(false);
        transFrame.setContentAreaFilled(false);
        transFrame.setFocusPainted(false);
        transFrame.setBounds(400, 300, 0, 0);

        animator = new Transition(transFrame, transIcon);
        glass.add(transFrame);
        navigator = new PageNavigator(mainPanel, cardLayout, animator);
    }

    // Handles adding all pages to the CardLayout
    private void initPages() {
        mainPanel.removeAll(); // Clear everything
        ShopManager gameController = new ShopManager(this, playerData);

        // Add every page
        mainPanel.add(new MainMenuPage(this), MAIN_MENU);
        mainPanel.add(new LevelSelectPage(this), LEVEL_SELECT);
        mainPanel.add(new GameTutorialPage(this), TUTORIAL);
        mainPanel.add(new LoadingPage("Level1"), "loading_1");
        mainPanel.add(new LoadingPage("Level2"), "loading_2");
        mainPanel.add(new LoadingPage("Level3"), "loading_3");
        mainPanel.add(new LoadingPage("Level4"), "loading_4");
        mainPanel.add(new LoadingPage("Level5"), "loading_5");
        mainPanel.add(new WinLosePage(this), ENDGAME);
        mainPanel.add(new MainSettingPage(this, this.playerData), SETTING);
        mainPanel.add(new ShopScreen(this, gameController), SHOP_UI);
        mainPanel.add(new PauseScreen(this), PAUSE);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void resetAndRefresh() {
        int oldVolume = this.playerData.getVolumeLv();
        boolean oldSfx = this.playerData.isStateSFX();
        this.playerData = new PlayerData();
        playerData.setStateSFX(oldSfx);
        playerData.setVolumeLv(oldVolume);
        DataManager.savePlayerData(playerData);
        initPages();
        navigator.toPage(MAIN_MENU, false);
    }

    public void setBackBtnPage(String previousPage) {
        this.forceBackPage = previousPage;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    public gamePlayScreen getGamePanel() {
        return (gamePlayScreen) currentGameScreen;
    }
}