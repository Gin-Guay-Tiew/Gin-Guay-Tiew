package ui.pages.settingMenu;

import ui.components.BackBtn;
import utilities.FontLoader;
import main.MainFrame;
import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {

    private final Font loadedFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");


    public TopBar(MainFrame mainFrame,String str) {

        // TopPanel
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 55)); // Padding :3
        setOpaque(false);

        // Components
        JPanel nevText = new JPanel(new FlowLayout());
        JButton backBtn = new BackBtn(mainFrame, "mainMenu");
        backBtn.setActionCommand("back");
        backBtn.addActionListener(new ButtonSetting(backBtn));
        JLabel text = new JLabel(str);
        text.setFont(loadedFont.deriveFont(50f));
        nevText.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 55));
        nevText.add(text);

        add(backBtn, BorderLayout.WEST);
        add(nevText, BorderLayout.CENTER);
    }
}