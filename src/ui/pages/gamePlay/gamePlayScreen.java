package ui.pages.gamePlay;

import main.MainFrame;
import ui.pages.endGame.WinLosePage;

import javax.swing.*;
import java.awt.*;

public class gamePlayScreen extends JPanel {

    private WinLosePage status;
    private bgPanel bgPanel;
    private counterBar counterBarPanel;
    private TopBar topBar;
    private MainFrame mainFrame;

    private customerPanel customerPanel; //  ต้องเป็น field

    private GameTimer gameTimer;

    public gamePlayScreen(MainFrame mainFrame, int levelId) {

        setLayout(new OverlayLayout(this));
        this.setBackground(Color.white);
        this.mainFrame = mainFrame;

        // status
        status = new WinLosePage(mainFrame);
        status.setBounds(0, 0, 800, 520);
        status.setVisible(false);

        JPanel mainGameArea = new JPanel(new BorderLayout());
        mainGameArea.setOpaque(false);

        // layered
        JLayeredPane gameLayer = new JLayeredPane();
        gameLayer.setLayout(null);
        gameLayer.setBounds(0, 0, 800, 600);

        // bg
        bgPanel = new bgPanel();
        bgPanel.setBounds(0, 0, 800, 400);

        // top bar
        topBar = new TopBar(mainFrame);
        topBar.setBounds(0, 0, 800, 90);

        // customer panel
        customerPanel = new customerPanel(topBar, mainFrame);
        customerPanel.setBounds(0, 0, 800, 600);
        customerPanel.showCustomers(levelId);

        // counter
        counterBarPanel = new counterBar(mainFrame, customerPanel);
        counterBarPanel.setBounds(0, 0, 800, 600);

        gameLayer.add(bgPanel, Integer.valueOf(0));
        gameLayer.add(topBar, Integer.valueOf(1));
        gameLayer.add(customerPanel, Integer.valueOf(2));
        gameLayer.add(counterBarPanel, Integer.valueOf(3));

        mainGameArea.add(gameLayer, BorderLayout.CENTER);

        add(status);
        add(mainGameArea);

        TimeDisplay screenTime = topBar.getTimeDisplay();
        gameTimer = new GameTimer(400, this, screenTime);
        gameTimer.startTimer();

//        GameTimer myTimer = new GameTimer(400, this, screenTime){
//            public void onTick() {
//                customerPanel.updateCustomers();
//
//                if (customerPanel.isFinished()) {
//                    gameOver();
//                }
//            }
//        };
//
//        myTimer.startTimer();

        showLevel(levelId);
    }

    public void showLevel(int levelId) {
        switch (levelId) {
            case 1:
                bgPanel.setBackgroundImage("resources/images/gamePlay/bg/LV1.gif", -3, -100, 800, 400);
                break;
            case 2:
                bgPanel.setBackgroundImage("resources/images/gamePlay/bg/LV2.gif", 0, -50, 800, 400);
                break;
            case 3:
                bgPanel.setBackgroundImage("resources/images/gamePlay/bg/LV3.gif", 0, -80, 800, 400);
                break;
            case 4:
                bgPanel.setBackgroundImage("resources/images/gamePlay/bg/LV4.gif", 0, 0, 800, 400);
                break;
            case 5:
                bgPanel.setBackgroundImage("resources/images/gamePlay/bg/LV5.gif", 0, 0, 800, 400);
                break;
        }

        counterBarPanel.setSlots(
                LevelFactory.getLevel(levelId, mainFrame.getPlayerData()).slots
        );
    }

    public void updateGame() {
        customerPanel.updateCustomers();
        if (customerPanel.isFinished()) {
            gameOver();
        }
    }

    public void gameOver() {
        status.setVisible(true);
    }

    public void pauseGame() {
        if (gameTimer != null) {
            gameTimer.pauseTimer();
        }
    }

    public void resumeGame() {
        if (gameTimer != null) {
            gameTimer.resumeTimer();
        }
    }

}