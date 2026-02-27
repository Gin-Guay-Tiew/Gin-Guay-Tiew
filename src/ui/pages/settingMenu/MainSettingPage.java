package ui.pages.settingMenu;

import main.MainFrame;
import utilities.FontLoader;
import ui.components.ImageJButton;
import utilities.IconImage;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class MainSettingPage extends JPanel{

    JSlider slider;
    JLabel images01;


    private final Font loadedFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");

    public MainSettingPage(MainFrame frame){
        setLayout(new BorderLayout());
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        JPanel main_container = new JPanel();
        JLabel textAudio = new JLabel("Audio");
        textAudio.setBorder(BorderFactory.createEmptyBorder(10,45,10,10));
        textAudio.setFont(loadedFont.deriveFont(50f));
        JPanel con_1 = new JPanel();
        con_1.setLayout(new FlowLayout(FlowLayout.LEFT,50,5));
        JPanel con_2 = new JPanel();
        con_2.setLayout(new FlowLayout(FlowLayout.LEFT,50,5));
        con_1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        con_2.setPreferredSize(new Dimension(0, 60));
        con_2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JButton startGame = new ImageJButton("resources/images/mainMenu/buttons/StartGame", ".png", 30, 250, 40);
        main_container.setLayout(new BorderLayout());


        // ===================================== IMAGE ======================================

        ImageIcon iconMusic = IconImage.create("resources/images/settingMenu/tree50.png", 50, 50);
        ImageIcon iconFX = IconImage.create("resources/images/settingMenu/sound_fx.png", 50, 50);
        ImageIcon off = IconImage.create("resources/images/settingMenu/off.png", 90, 35);

        // ===================================== ENDING ======================================


        // ===================================== for con_1 =====================================

        images01 = new JLabel(iconMusic);
        images01.setPreferredSize(new Dimension(50,50));
        JLabel text2 = new JLabel("Music");
        text2.setFont(loadedFont.deriveFont(30f));
        slider = new JSlider(0, 100, 100);
        slider.setValue(50);

        con_1.add(images01);
        con_1.add(text2);
        con_1.add(slider);
        // =====================================================================================

        // ===================================== for con_2 =====================================

        JLabel images02 = new JLabel(iconFX);
        images02.setPreferredSize(new Dimension(50,50));
        JLabel text03 = new JLabel("Sound FX");
        text03.setFont(loadedFont.deriveFont(30f));
        JButton btn2 = new JButton(off);
        btn2.setBorderPainted(false);
        btn2.setContentAreaFilled(false);
        btn2.setFocusPainted(false);
        btn2.setOpaque(false);

        con_2.add(images02);
        con_2.add(text03);
        con_2.add(btn2);

        // =====================================================================================

        // ======================== BUTTON ========================
        btn2.setActionCommand("fx");

        slider.addChangeListener(new ButtonSetting(slider,images01));


        // ====================== CONTAINER =================

        container.add(textAudio);
        container.add(Box.createVerticalStrut(20));
        container.add(con_1);
        container.add(Box.createVerticalStrut(20));
        container.add(con_2);

        // ======================= END =======================

        textAudio.setAlignmentX(Component.LEFT_ALIGNMENT);
        con_1.setAlignmentX(Component.LEFT_ALIGNMENT);
        con_2.setAlignmentX(Component.LEFT_ALIGNMENT);

        container.setBorder(BorderFactory.createEmptyBorder(0,0,40,0));
        main_container.setBorder(BorderFactory.createEmptyBorder(20, 70, 20, 70));

        main_container.add(container, BorderLayout.CENTER);
        main_container.add(startGame, BorderLayout.SOUTH);


        add(new TopBar(frame,"Setting"), BorderLayout.NORTH);
        add(main_container, BorderLayout.CENTER);
    }
}
