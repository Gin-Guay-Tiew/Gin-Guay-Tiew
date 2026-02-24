package pages.levelSelection;

import config.CustomFontLoader;
import pages.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LevelSelect extends JPanel {

    private final Font loadedFont = CustomFontLoader.loadCustomFont("Jersey10.ttf");
    private MainFrame mainFrame;

    public LevelSelect(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        add(topPanel(), BorderLayout.NORTH);
    }

    private JPanel topPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton backBtn = new BackBtn();
        JPanel moneyDisplay = new MoneyDisplay();

        topPanel.setBorder(new EmptyBorder(0, 0, 0, 75));

        topPanel.add(backBtn, BorderLayout.WEST);
        topPanel.add(moneyDisplay, BorderLayout.EAST);
        return topPanel;
    }
}