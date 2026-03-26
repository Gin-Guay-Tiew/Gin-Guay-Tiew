package ui.pages.pressESC;

import main.MainFrame;
import ui.components.ImageJButton;
import utilities.IconImage;

import javax.swing.*;
import java.awt.*;

public class PauseScreen extends JPanel {

    private Image backgroundImage;

    public PauseScreen(MainFrame mainFrame) {

        setLayout(new GridBagLayout());

        // ================= Background =================
        ImageIcon original = new ImageIcon("resources/images/mainMenu/Background.gif");
        backgroundImage = original.getImage();

        // ================= Center Container =================
        JPanel centerContain = new JPanel();
        centerContain.setLayout(new BoxLayout(centerContain, BoxLayout.Y_AXIS));
        centerContain.setOpaque(false);

        // ================= Logo =================
        ImageIcon icon = IconImage.create(
                "resources/images/mainMenu/LogoGame.png",
                280, 280
        );

        JLabel logo = new JLabel(icon);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerContain.add(logo);
        centerContain.add(Box.createVerticalStrut(20));

        // ================= Buttons =================
        JButton resume = new ImageJButton(
                "resources/images/endGame/BackToTheGame", ".png", 30, 250, 40
        );

        JButton setting = new ImageJButton(
                "resources/images/mainMenu/buttons/Settings", ".png", 30, 250, 40
        );

        JButton menu = new ImageJButton(
                "resources/images/endGame/backToMenu", ".png", 30, 250, 40
        );

        resume.setAlignmentX(Component.CENTER_ALIGNMENT);
        setting.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerContain.add(resume);
        centerContain.add(Box.createVerticalStrut(10));
        centerContain.add(setting);
        centerContain.add(Box.createVerticalStrut(10));
        centerContain.add(menu);

        // ================= Center Layout =================
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        add(centerContain, gbc);

        // ================= Button Action =================

        resume.addActionListener(e ->
                mainFrame.getNavigator().toPage(mainFrame.getPreviousPage(), false)
        );

        setting.addActionListener(e ->
                mainFrame.getNavigator().toPage(MainFrame.SETTING, true)
        );

        menu.addActionListener(e ->
                mainFrame.getNavigator().toPage(MainFrame.MAIN_MENU, true)
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}