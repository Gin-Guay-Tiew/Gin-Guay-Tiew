package ui.pages.engGame;

import main.MainFrame;
import utilities.FontLoader;
import utilities.IconFilter;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class WinLosePage extends JPanel {
    private final Font jerseyFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");
    private MainFrame mainFrame;
    private Image bgImage;
    private boolean isWin;
    private JLabel statusLabel;

    public WinLosePage(MainFrame mainFrame) {
        this(mainFrame,true,0,0);
    }

    public WinLosePage(MainFrame mainFrame, boolean isWin, double moneyEarned, double bonusMoney) {
        this.mainFrame = mainFrame;
        // background
        ImageIcon original = new ImageIcon(
                "resources/images/mainMenu/image-from-rawpixel-id-14653376-jpeg.jpg"
        );

        setLayout(new BorderLayout());
        setOpaque(false);

        this.isWin = isWin;

        // Status Win or Lose
        statusLabel = new JLabel();
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setVerticalAlignment(JLabel.TOP);
        statusLabel.setFont(jerseyFont.deriveFont(100f));
        statusLabel.setBorder(new EmptyBorder(20,0,0,0));

        if (isWin) { statusLabel.setText("You win!"); }
        else  { statusLabel.setText("You lose!"); }

        add(statusLabel,BorderLayout.NORTH);

        // ScorePanel
        ShowMeTheMoney scorePanel = new ShowMeTheMoney(mainFrame, moneyEarned, bonusMoney);
        add(scorePanel, BorderLayout.CENTER);

        // Botton
        ManageBtn manageBtn = new ManageBtn(mainFrame);
        add(manageBtn, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
