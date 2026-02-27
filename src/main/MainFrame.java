package main;

import ui.pages.levelSelection.LevelSelectPage;
import ui.pages.loadingScreen.LoadingPage;
import ui.pages.mainMenu.MainMenuPage;
import ui.pages.tutorialGame.GameTutorialPage;
import ui.pages.endGame.WinLosePage;
import ui.pages.settingMenu.MainSettingPage;
import utilities.IconImage;
import utilities.PageNavigator;
import ui.components.PopupWindow;
import utilities.Transition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements WindowListener {

    public static final String MAIN_MENU = "mainMenu";
    public static final String LEVEL_SELECT = "levelSelect";
    public static final String TUTORIAL = "tutorial";
    public static final String LOADING_SCREEN = "loadingScreen";
    public static final String ENDGAME = "winlosepage";
    public static final String SETTING = "setting";

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private Transition animator;
    private PageNavigator navigator;
    PopupWindow pop = new PopupWindow();
    private boolean isWarningActive = false;

    public void closeApp() {
        System.exit(0);
    }

    public PageNavigator getNavigator() {
        return navigator;
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

        // Keep window on screen
        Timer snapTimer = new Timer(100, e -> {
            if (isWarningActive) return;
            Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

            int currentX = getX();
            int currentY = getY();
            int width = getWidth();
            int height = getHeight();

            int newX = currentX;
            int newY = currentY;

            // Clamp X (Left and Right edges)
            if (currentX < screenBounds.x) {
                newX = screenBounds.x;
            } else if (currentX + width > screenBounds.width) {
                newX = screenBounds.width - width;
            }

            // Clamp Y (Top and Bottom edges)
            if (currentY < screenBounds.y) {
                newY = screenBounds.y;
            } else if (currentY + height > screenBounds.height) {
                newY = screenBounds.height - height;
            }

            // Snap back if out of bounds
            if (newX != currentX || newY != currentY) {
                setLocation(newX, newY);
                isWarningActive = true;

                // Create warning popUp
                Timer popupDelayTimer = new Timer(25, delayEvent -> {

                    String[] btnPaths = {"resources/images/shared/buttons/Ok"};
                    String[] btnLabels = {"No"}; // "No" triggers dialog.dispose() will close popup naja!
                    ActionListener[] btnActions = {null};

                    JDialog dialog = pop.createPopup(
                            this,
                            "Out of bounds!\nThe kitchen needs to stay on your screen.",
                            "resources/images/shared/popups/Demo.png",
                            btnPaths,
                            btnLabels,
                            btnActions
                    );

                    // Unlock ONLY after the user closes the popup
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            isWarningActive = false;
                        }
                    });
                });

                popupDelayTimer.setRepeats(false);
                popupDelayTimer.start();
            }
        });
        snapTimer.setRepeats(false);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                snapTimer.restart();
            }
        });

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

        animator = new Transition(transFrame, transIcon);
        glass.add(transFrame);

        // Initialize the navigator before adding ui.pages
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        navigator = new PageNavigator(mainPanel, cardLayout, animator);

        mainPanel.add(new MainMenuPage(this), MAIN_MENU); // + MainMenu
        mainPanel.add(new LevelSelectPage(this), LEVEL_SELECT); // + LevelSelection
        mainPanel.add(new GameTutorialPage(), TUTORIAL); // + Tutorial
        mainPanel.add(new LoadingPage(), LOADING_SCREEN); // + Loading Screen
        mainPanel.add(new WinLosePage(this), ENDGAME);
        mainPanel.add(new MainSettingPage(), SETTING);

        navigator.toPage(ENDGAME, false);

        add(mainPanel);
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
}