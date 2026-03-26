package ui.pages.settingMenu;

import ui.components.BackBtn;
import ui.components.CustomJLabel;
import utilities.FontLoader;
import main.MainFrame;

import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {

    private final Font loadedFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");

    public TopBar(MainFrame mainFrame) {

        // TopPanel
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 20, 0, 0)); // Padding :3
        setOpaque(false);

        // Components
        JPanel nevText = new JPanel(new FlowLayout());
        nevText.setOpaque(false);
        JButton backBtn = new BackBtn(mainFrame, "mainMenu");
        JLabel text = new CustomJLabel("~ Settings ~", 7f);
        text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        text.setFont(loadedFont.deriveFont(45f));
        text.setForeground(Color.white);
        nevText.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 55));
        nevText.add(text);

        add(backBtn, BorderLayout.WEST);
        add(nevText, BorderLayout.CENTER);
    }
}