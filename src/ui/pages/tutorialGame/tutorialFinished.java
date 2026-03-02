package ui.pages.tutorialGame;

import main.MainFrame;
import ui.components.BackBtn;
import ui.components.ImageJButton;
import ui.pages.mainMenu.MainBtn;
import utilities.FontLoader;

import javax.swing.*;
import java.awt.*;

public class tutorialFinished extends JPanel {
    private final Font jerseyFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");
    public tutorialFinished(MainFrame frame){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(20, 20, 20, 20);


        JLabel text = new JLabel("Tutorial Finished!");
        text.setFont(jerseyFont.deriveFont(96f));
        gbc.gridy = 0;
        add(text, gbc);

        JButton backBtn = new BackBtn();// hellnah needed to be changed
        JButton startGame = new ImageJButton("resources/images/mainMenu/buttons/StartGame", ".png", 30, 250, 40);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        buttonPanel.setOpaque(false);

        buttonPanel.add(backBtn);
        buttonPanel.add(startGame);



        gbc.gridy = 1;
        add(buttonPanel, gbc);


        MainBtn actionBtn = new MainBtn(frame);

        startGame.setActionCommand("Start Game");
        backBtn.setActionCommand("Main Menu");


        startGame.addActionListener(actionBtn);

    }
}
