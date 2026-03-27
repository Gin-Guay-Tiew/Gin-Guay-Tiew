package ui.pages.endGame;

import main.MainFrame;
import ui.components.CustomJLabel;
import utilities.FontLoader;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class WinLosePage extends JPanel {

    private static final Font jerseyFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");
    private MainFrame mainFrame;

    public WinLosePage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);
        setOpaque(false);
    }

    public void setState(boolean isWin, double moneyEarned, double bonusMoney) {
        removeAll();

        CustomJLabel statusLabel = new CustomJLabel("status", 10f);
        statusLabel.setOutlineColor(Color.WHITE);
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setFont(jerseyFont.deriveFont(85f));

        if (isWin){
            statusLabel.setText("You Passed!");
            statusLabel.setTextColor(new Color(69, 236, 147));
            statusLabel.setOutlineColor(new Color(37, 90, 60));
        } else {
            statusLabel.setText("You Failed..");
            statusLabel.setTextColor(new Color(236, 69, 69));
            statusLabel.setOutlineColor(new Color(90, 37, 37));
        }

        statusLabel.setBounds(0, 50, 800, 100);
        add(statusLabel);

        ShowMeTheMoney scorePanel = new ShowMeTheMoney(mainFrame, moneyEarned, bonusMoney);
        scorePanel.setBounds(0, 145, 800, 350);
        add(scorePanel);

        ManageBtn manageBtn = new ManageBtn(mainFrame);
        manageBtn.setBounds(0, 400, 800, 120);
        add(manageBtn);

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(
                0, 0, new Color(0, 0, 0, 150),
                0, getHeight(), new Color(0, 0, 0, 200)
        );
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}