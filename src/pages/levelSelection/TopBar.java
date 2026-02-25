package pages.levelSelection;

import components.BackBtn;
import components.MoneyDisplay;
import pages.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TopBar extends JPanel {

    public TopBar(MainFrame mainFrame) {

        // TopPanel
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 55)); // Padding :3
        setOpaque(false);

        // Components
        JButton backBtn = new BackBtn();
        JPanel moneyDisplay = new MoneyDisplay();

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getNavigator().toPage("mainMenu", true);
            }
        });

        add(backBtn, BorderLayout.WEST);
        add(moneyDisplay, BorderLayout.EAST);
    }
}