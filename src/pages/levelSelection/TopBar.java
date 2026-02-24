package pages.levelSelection;

import components.BackBtn;
import components.MoneyDisplay;
import pages.MainFrame;

import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {

    private MainFrame mainFrame;

    public TopBar(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        // TopPanel
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 55)); // Padding :3
        setOpaque(false);

        // Components
        JButton backBtn = new BackBtn();
        JPanel moneyDisplay = new MoneyDisplay();

        backBtn.addActionListener(e -> {
            mainFrame.showPage("mainMenu");
        });

        add(backBtn, BorderLayout.WEST);
        add(moneyDisplay, BorderLayout.EAST);
    }
}