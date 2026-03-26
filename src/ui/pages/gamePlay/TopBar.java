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
    private TimeDisplay timeDisplay;

    public TopBar(MainFrame mainFrame) {

        // TopPanel
        setLayout(null);
        setOpaque(false);

        // money and time panel
        JPanel mtPanel = new JPanel();
        mtPanel.setLayout(new BorderLayout());
        mtPanel.setOpaque(false);
        mtPanel.setBounds(0, -10, 775, 90);

        // Money box
        JPanel money = new MoneyDisplay(mainFrame);
        timeDisplay = new TimeDisplay();
        mtPanel.add(timeDisplay, BorderLayout.WEST);
        mtPanel.add(money, BorderLayout.EAST);

        add(mtPanel);

    }

    public TimeDisplay getTimeDisplay() {
        return timeDisplay;
    }
}