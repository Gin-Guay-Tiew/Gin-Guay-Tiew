package ui.pages.pressESC;

import main.MainFrame;
import ui.components.ImageJButton;
import ui.components.PopupWindow;
import utilities.IconImage;
import utilities.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PauseScreen extends JPanel {

    private Image backgroundImage;
    private float alpha = 0f;
    PopupWindow pop = new PopupWindow();

    public PauseScreen(MainFrame mainFrame) {

        setLayout(new GridBagLayout());

        // ================= Background =================
        ImageIcon original = new ImageIcon("resources/images/shared/levelBackgrounds/Main_Dark.gif");
        backgroundImage = original.getImage();

        // ================= Center Container =================
        JPanel centerContain = new JPanel();
        centerContain.setLayout(new BoxLayout(centerContain, BoxLayout.Y_AXIS));
        centerContain.setOpaque(false);

        // ================= Logo =================
        ImageIcon icon = IconImage.create(
                "resources/images/mainMenu/LogoGame.png",
                280, 280
        );

        JLabel logo = new JLabel(icon);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerContain.add(logo);
        centerContain.add(Box.createVerticalStrut(20));

        // ================= Buttons =================
        JButton resume = new ImageJButton(
                "resources/images/endGame/BackToTheGame", ".png", 30, 250, 40
        );

        JButton setting = new ImageJButton(
                "resources/images/mainMenu/buttons/Settings", ".png", 30, 250, 40
        );

        JButton menu = new ImageJButton(
                "resources/images/endGame/backToMenu", ".png", 30, 250, 40
        );

        resume.setAlignmentX(Component.CENTER_ALIGNMENT);
        setting.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerContain.add(resume);
        centerContain.add(Box.createVerticalStrut(10));
        centerContain.add(setting);
        centerContain.add(Box.createVerticalStrut(10));
        centerContain.add(menu);

        // ================= Center Layout =================
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        add(centerContain, gbc);

        // ================= Button Action =================

        resume.addActionListener(e ->
                fadeOut(() -> {
                    mainFrame.getGamePanel().resumeGame();
                    mainFrame.getNavigator().toPage(
                            mainFrame.getPreviousPage(), false
                    );
                })
        );

        setting.addActionListener(e -> {
            mainFrame.setBackBtnPage(MainFrame.PAUSE);
            mainFrame.getNavigator().toPage(MainFrame.SETTING, true);
        });

        menu.addActionListener(e -> {
            String[] btnPaths = {
                    "resources/images/shared/buttons/Yes",
                    "resources/images/shared/buttons/No"
            };
            String[] btnLabels = {"Yes", "No"};
            ActionListener[] btnActions = {
                    ex -> {
                        SoundManager.backToMenu();
                        mainFrame.getNavigator().toPage(MainFrame.MAIN_MENU, true);
                    },
                    null
            };
            pop.createPopup(
                    mainFrame,
                    "Are you sure you want to return?\nYour progress will not be saved.", // Message
                    "resources/images/shared/popups/Demo.png", // Background Path
                    btnPaths,
                    btnLabels,
                    btnActions
            );
        });
    }

    public void fadeIn() {
        alpha = 0f;
        Timer timer = new Timer(15, null);
        timer.addActionListener(e -> {
            alpha += 0.05f;
            if (alpha >= 1f) {
                alpha = 1f;
                timer.stop();
            }

            repaint();
        });

        timer.start();
    }

    public void fadeOut(Runnable afterFade) {
        Timer timer = new Timer(15, null);
        timer.addActionListener(e -> {
            alpha -= 0.05f;
            if (alpha <= 0f) {
                alpha = 0f;
                timer.stop();
                SwingUtilities.invokeLater(afterFade);
            }

            repaint();
        });

        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha
        ));

        super.paint(g2);

        g2.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}