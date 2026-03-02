package ui.pages.pauseMenu;

import main.MainFrame;
import ui.components.BackBtn;
import ui.components.ImageJButton;
import ui.pages.mainMenu.MainMenuPage;
import utilities.IconImage;

import javax.swing.*;
import java.awt.*;

public class pauseMenu extends JPanel {
    public pauseMenu(MainFrame frame){
        this.setLayout(new GridBagLayout());
        this.setOpaque(false);

        JPanel centerContain = new JPanel();
        centerContain.setLayout(new BoxLayout(centerContain, BoxLayout.Y_AXIS));
        centerContain.setOpaque(false);

        ImageIcon icon = IconImage.create(
                "resources/images/mainMenu/LogoGame.png",
                280, 280
        );// ty for the copy

        JLabel logo = new JLabel(icon);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerContain.add(logo);
        centerContain.add(Box.createVerticalStrut(20));


        // placeholder asf didn't even center
        JButton setting = new ImageJButton("resources/images/mainMenu/buttons/Settings", ".png", 30, 250, 40);
        JButton startGame = new ImageJButton("resources/images/mainMenu/buttons/StartGame", ".png", 30, 250, 40);
        JButton backBtn = new BackBtn(frame, "mainMenu");

        centerContain.add(startGame);
        centerContain.add(setting);
        centerContain.add(backBtn);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        add(centerContain, gbc);
    }
}
