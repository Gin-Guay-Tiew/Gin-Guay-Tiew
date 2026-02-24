package pages.levelSelection;

import components.BackBtn;
import components.MoneyDisplay;
import pages.MainFrame;

import javax.swing.*;
import java.awt.*;

public class LevelSelect extends JPanel {
    private MainFrame mainFrame;

    public LevelSelect(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        add(topPanel(), BorderLayout.NORTH);
    }

    private JPanel topPanel() {
        // TOP_PANEL Configuration //
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        topPanel.setBackground(Color.orange);

        // Components //
        JButton backBtn = new BackBtn();
        JPanel moneyDisplay = new MoneyDisplay();

        // Adding //
        topPanel.add(backBtn, BorderLayout.WEST);
        topPanel.add(moneyDisplay, BorderLayout.EAST);

        return topPanel;
    }
}