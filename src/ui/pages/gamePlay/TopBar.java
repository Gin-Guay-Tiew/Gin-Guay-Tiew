package ui.pages.gamePlay;

import main.MainFrame;
import ui.components.BackBtn;
import ui.components.MoneyDisplay;
import utilities.FontLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// modify add a time box into it
public class TopBar extends JPanel {

    private final Font loadedFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");
    private JLabel timeLabel; // add timeLabel to update time

    public TopBar(MainFrame mainFrame) {

        // TopPanel
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 55)); // Padding :3
        setOpaque(false);

        // money and time panel
        JPanel mtPanel = new JPanel();
        mtPanel.setLayout(new GridLayout(1, 2, 20, 0));
        mtPanel.setOpaque(false);

        // Money box
        JPanel money = new MoneyDisplay(1000);
        JPanel time = new TimeDisplay();
        mtPanel.add(time);
        mtPanel.add(money);

        add(mtPanel, BorderLayout.EAST);


    }
}