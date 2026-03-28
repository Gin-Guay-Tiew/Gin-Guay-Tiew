package ui.pages.tutorialGame;

import main.MainFrame;
import ui.components.ImageJButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTutorialPage extends JPanel {
    private MainFrame mainFrame;

    private static final int TOTAL_PAGES = 8;
    private int currentPage = 1;

    private JLabel pageLabel;

    private AbstractNavButton ArrowLeft;
    private AbstractNavButton ArrowRight;

    private int btnWidth = 80;
    private int btnHeight = 80;

    private Image backgroundImage;
    private Image backgroundImage_Two;

    private JButton backToMenuBtn;

    public GameTutorialPage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);
        setPreferredSize(new Dimension(800, 600));

        backgroundImage = new ImageIcon(getClass().getResource("/images/mainMenu/Background.gif")).getImage();
        backgroundImage_Two = new ImageIcon(getClass().getResource("/images/Tutorial/BackGround_Tutorial.png")).getImage();

        pageLabel = new JLabel();
        pageLabel.setBounds(100, 90, 600, 380);
        pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pageLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(pageLabel);

        ArrowLeft = new ArrowButton(
                "/images/Tutorial/ArrowInTutorial/ArrowLeft.png",
                "/images/Tutorial/ArrowInTutorial/ArrowLeft_Hover.png",
                "/images/Tutorial/ArrowInTutorial/ArrowLeft_Click.png",
                btnWidth, btnHeight
        );

        registerNavigationButton(ArrowLeft, 10, 232, () -> {
            if (currentPage > 1) {
                currentPage--;
                updatePage();
            }
        });

        ArrowRight = new ArrowButton(
                "/images/Tutorial/ArrowInTutorial/ArrowRight.png",
                "/images/Tutorial/ArrowInTutorial/ArrowRight_Hover.png",
                "/images/Tutorial/ArrowInTutorial/ArrowRight_Click.png",
                btnWidth, 80
        );

        registerNavigationButton(ArrowRight, 710, 232, () -> {
            if (currentPage < TOTAL_PAGES) {
                currentPage++;
                updatePage();
            }
        });

        backToMenuBtn = new ImageJButton("/images/endGame/backToMenu", ".png", 30, 250, 40);
        backToMenuBtn.setBounds(275, 500, 250, 40);
        add(backToMenuBtn);
        backToMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getNavigator().toPage("mainMenu", true, 500);
            }
        });

        updatePage();
    }

    private void registerNavigationButton(AbstractNavButton navButton, int x, int y, Runnable action) {
        navButton.setBounds(x, y, btnWidth, btnHeight);
        navButton.setClickAction(action);
        add(navButton);
    }

    private void updatePage() {
        String pagePath = "/images/Tutorial/Tutorial_Page/Tutorial_page " + currentPage + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(pagePath));

        Image scaled = icon.getImage().getScaledInstance(
                pageLabel.getWidth(),
                pageLabel.getHeight(),
                Image.SCALE_SMOOTH
        );
        pageLabel.setIcon(new ImageIcon(scaled));

        ArrowLeft.setButtonEnabled(currentPage > 1);
        ArrowRight.setButtonEnabled(currentPage < TOTAL_PAGES);

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        if (backgroundImage_Two != null) {
            g.drawImage(backgroundImage_Two, 0, 0, getWidth(), getHeight(), this);
        }
    }
}