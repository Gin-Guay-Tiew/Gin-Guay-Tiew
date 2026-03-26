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

    int pay = 1000;
    MoneyDisplay money;

    private final Font loadedFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");
    private TimeDisplay timeDisplay;

    public TopBar(MainFrame mainFrame) {

        // TopPanel
        setLayout(null);
        setOpaque(false);

        // money and time panel
        JPanel mtPanel = new JPanel();
        mtPanel.setLayout(new GridLayout(1, 2, 20, 0));
        mtPanel.setOpaque(false);
        mtPanel.setBounds(450,-10,320,90);

        // Money box
        money = new MoneyDisplay(pay);
        timeDisplay = new TimeDisplay();
        mtPanel.add(timeDisplay);
        mtPanel.add(money);
        add(mtPanel);
    }

    public void setMoney(){
        pay+=45;
        money.updateMoney(pay);
    }

    public TimeDisplay getTimeDisplay() {
        return timeDisplay;
    }
}