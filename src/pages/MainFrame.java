package pages;

import pages.mainMenu.MainMenuPage;
import pages.levelSelection.LevelSelectPage;
import pages.settingMenu.MainSettingPage;
import utilities.IconImage;
import utilities.PageNavigator;
import utilities.Transition;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static final String MAIN_MENU = "mainMenu";
    public static final String LEVEL_SELECT = "levelSelect";

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private Transition animator;
    private PageNavigator navigator;

    public MainFrame() {
        setTitle("Gin-Guay-Tiew");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Layered Position Setup (TransitionFrame Positioning :D)
        JPanel glass = (JPanel) getGlassPane();
        glass.setLayout(null);
        glass.setVisible(true);

        ImageIcon transIcon = IconImage.create("resources/images/shared/TransitionIcon.png", 50, 50);
        JButton transFrame = new JButton();
        transFrame.setIcon(transIcon);
        transFrame.setBorderPainted(false);
        transFrame.setContentAreaFilled(false);
        transFrame.setFocusPainted(false);
        transFrame.setBounds(400, 300, 0, 0);

        animator = new Transition(transFrame, transIcon);
        glass.add(transFrame);

        // Initialize the navigator before adding pages
        navigator = new PageNavigator(mainPanel, cardLayout, animator);

        mainPanel.add(new MainMenuPage(this), MAIN_MENU); // + MainMenu
        mainPanel.add(new LevelSelectPage(this), LEVEL_SELECT); // + LevelSelection

        navigator.toPage(MAIN_MENU, false);

        add(mainPanel);
        setVisible(true);
    }

    public PageNavigator getNavigator() {
        return navigator;
    }
}