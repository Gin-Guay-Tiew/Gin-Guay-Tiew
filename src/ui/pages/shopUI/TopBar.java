package ui.pages.shopUI;

import main.MainFrame;
import ui.components.BackBtn;
import ui.components.CustomJLabel;
import ui.components.MoneyDisplay;
import utilities.FontLoader;

import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {

    private final Font loadedFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");

    public TopBar(MainFrame mainFrame) {

        // TopPanel Setup
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); // Balanced padding
        setOpaque(false);

        // West: Back Button
        JButton backBtn = new BackBtn(mainFrame, MainFrame.MAIN_MENU);

        // Center: Shop Title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setOpaque(false);

        CustomJLabel shopTitle = new CustomJLabel("  ~ Shop ~", 6f);
        shopTitle.setFont(loadedFont.deriveFont(42.5f));
        shopTitle.setForeground(Color.white);
        shopTitle.setBorder(BorderFactory.createEmptyBorder(35, 5, 0, 5));

        titlePanel.add(shopTitle);

        // East: Money Display
        MoneyDisplay moneyDisplay = new MoneyDisplay(mainFrame);

        // Add components to the TopBar
        add(backBtn, BorderLayout.WEST);
        add(titlePanel, BorderLayout.CENTER);
        add(moneyDisplay, BorderLayout.EAST);
    }
}