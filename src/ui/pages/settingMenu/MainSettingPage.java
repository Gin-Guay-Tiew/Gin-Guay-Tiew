package ui.pages.settingMenu;

import main.MainFrame;
import utilities.FontLoader;
import ui.components.ImageJButton;

import javax.swing.*;
import java.awt.*;

public class MainSettingPage extends JPanel{

    MainFrame frame;
    private final Font loadedFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");

    public MainSettingPage(){
        setLayout(new BorderLayout());
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        JPanel main_container = new JPanel();
        JLabel textAudio = new JLabel("Audio");
        textAudio.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        textAudio.setFont(loadedFont.deriveFont(50f));
        JPanel con_2 = new JPanel();
        JPanel con_3 = new JPanel();
        con_2.setBackground(Color.GREEN);
        con_3.setBackground(Color.BLUE);


        // for con_2
        JPanel images01 = new JPanel();
        JLabel text2 = new JLabel();
        JSlider slider = new JSlider(0, 100, 100);

        JButton startGame = new ImageJButton("resources/images/mainMenu/buttons/StartGame", ".png", 30, 250, 40);

        main_container.setLayout(new BorderLayout());

        container.add(textAudio);
        container.add(con_2);
        container.add(con_3);

        textAudio.setAlignmentX(Component.LEFT_ALIGNMENT);
        con_2.setAlignmentX(Component.LEFT_ALIGNMENT);
        con_3.setAlignmentX(Component.LEFT_ALIGNMENT);

        container.setBorder(BorderFactory.createEmptyBorder(0,0,60,0));
        main_container.setBorder(BorderFactory.createEmptyBorder(20, 70, 20, 70));

        main_container.add(container, BorderLayout.CENTER);
        main_container.add(startGame, BorderLayout.SOUTH);


        add(new TopBar(frame,"Setting"), BorderLayout.NORTH);
        add(main_container, BorderLayout.CENTER);
    }
}
