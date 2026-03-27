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
        setLayout(new BorderLayout());
        setOpaque(false);
    }

    public void setState(boolean isWin, double moneyEarned, double bonusMoney) {
        removeAll();

        CustomJLabel statusLabel = new CustomJLabel("status", 15f);
        statusLabel.setOutlineColor(Color.WHITE);
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setFont(jerseyFont.deriveFont(100f));
        statusLabel.setBorder(new EmptyBorder(20,0,0,0));
        if (isWin){
            statusLabel.setText("You win!");
        } else {
            statusLabel.setText("You lose!");
        }

        add(statusLabel, BorderLayout.NORTH);


        ShowMeTheMoney scorePanel = new ShowMeTheMoney(mainFrame, moneyEarned, bonusMoney);
        add(scorePanel, BorderLayout.CENTER);


        ManageBtn manageBtn = new ManageBtn(mainFrame);
        add(manageBtn, BorderLayout.SOUTH);

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