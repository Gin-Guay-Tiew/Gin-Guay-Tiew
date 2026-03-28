package ui.pages.endGame;

import main.MainFrame;
import ui.components.CustomJLabel;
import utilities.FontLoader;
import utilities.SFX;
import utilities.SFXManager;

import javax.swing.*;
import java.awt.*;

public class WinLosePage extends JPanel {

    private static final Font jerseyFont = FontLoader.loadCustomFont("/font/Jersey10.ttf");
    private MainFrame mainFrame;

    public WinLosePage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);
        setOpaque(false);
    }

    public void setState(boolean isWin, double moneyEarned, double bonusMoney) {
        removeAll();

        // --- ส่วนหัวข้อ Status (Passed / Failed) ---
        CustomJLabel statusLabel = new CustomJLabel("", 10f);
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setFont(jerseyFont.deriveFont(85f));

        if (isWin) {
            utilities.SoundManager.playWin();
            statusLabel.setText("You Passed!");
            statusLabel.setTextColor(new Color(69, 236, 147)); // สีเขียว
            statusLabel.setOutlineColor(new Color(37, 90, 60));
        } else {
            utilities.SoundManager.playLose();
            statusLabel.setText("You Failed..");
            statusLabel.setTextColor(new Color(236, 69, 69)); // สีแดง
            statusLabel.setOutlineColor(new Color(90, 37, 37));
        }

        statusLabel.setBounds(0, 40, 800, 100);
        add(statusLabel);

        ShowMeTheMoney scorePanel = new ShowMeTheMoney(mainFrame, moneyEarned, bonusMoney, !isWin);
        scorePanel.setBounds(0, 130, 800, 350);
        add(scorePanel);


        ManageBtn manageBtn = new ManageBtn(mainFrame);
        manageBtn.setBounds(0, 425, 800, 120);
        add(manageBtn);

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // วาด Background มืดๆ โปร่งแสงด้านหลัง Popup
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(
                0, 0, new Color(0, 0, 0, 180),
                0, getHeight(), new Color(0, 0, 0, 220)
        );
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}