package ui.pages.engGame;

import main.MainFrame;
import utilities.FontLoader;
import ui.components.ImageJButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageBtn extends JPanel implements ActionListener {
    private MainFrame mainFrame;
    private final Font jerseyFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");
    private JButton backBtn, playAgainBtn, shopBtn;

    public ManageBtn(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setOpaque(false);
        setBorder(new EmptyBorder(20,30,100,30));

        // ================= Button Images =================

        backBtn = new ImageJButton("resources/images/mainMenu/buttons/Shop", ".png", 30, 250, 40);
        playAgainBtn = new ImageJButton("resources/images/mainMenu/buttons/StartGame", ".png", 30, 250, 40);
        shopBtn = new ImageJButton("resources/images/mainMenu/buttons/StartGame", ".png", 30, 250, 40);

        add(backBtn);
        add(playAgainBtn);
        add(shopBtn);

        // ================= Action =================
        backBtn.setActionCommand("backToMainMenu");
        playAgainBtn.setActionCommand("playAgain");
        shopBtn.setActionCommand("shop");
        backBtn.addActionListener(this);
        playAgainBtn.addActionListener(this);
        shopBtn.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("backToMainMenu")) {
            mainFrame.getNavigator().toPage("mainMenu",true,500);
        }
    }
}
