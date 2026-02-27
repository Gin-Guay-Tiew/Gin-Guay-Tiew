package ui.pages.settingMenu;

import ui.components.BackBtn;
import utilities.FontLoader;
import main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JLabel text = new JLabel(str);
        text.setFont(loadedFont.deriveFont(50f));
        nevText.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 55));
        nevText.add(text);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getNavigator().toPage("mainMenu", true);
            }
        });

        add(backBtn, BorderLayout.WEST);
        add(nevText, BorderLayout.CENTER);
    }
}