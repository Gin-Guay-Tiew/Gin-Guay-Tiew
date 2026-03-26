package ui.pages.pressESC;
import main.MainFrame;
import ui.components.ImageJButton;
import utilities.IconImage;

import javax.swing.*;

public class PauseScreen extends JPanel {
    public PauseScreen(MainFrame mainFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        add(Box.createVerticalStrut(120));

        JLabel logo = new JLabel(
                IconImage.create("resources/images/mainMenu/LogoGame.png", 300, 120)
        );
        logo.setAlignmentX(CENTER_ALIGNMENT);

        add(logo);
        add(Box.createVerticalStrut(40));

        JButton resume = new ImageJButton(
                "resources/images/endGame/BackToTheGame", ".png", 30, 280, 70
        );

        JButton setting = new ImageJButton(
                "resources/images/mainMenu/buttons/Settings", ".png", 30, 280, 70
        );

        JButton menu = new ImageJButton(
                "resources/images/endGame/backToMenu", ".png", 30, 280, 70
        );

        resume.setAlignmentX(CENTER_ALIGNMENT);
        setting.setAlignmentX(CENTER_ALIGNMENT);
        menu.setAlignmentX(CENTER_ALIGNMENT);

        add(resume);
        add(Box.createVerticalStrut(20));
        add(setting);
        add(Box.createVerticalStrut(20));
        add(menu);

    }
}
