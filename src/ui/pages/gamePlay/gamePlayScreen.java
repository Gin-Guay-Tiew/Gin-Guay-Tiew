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
    private int levelId;
    boolean isGameOver = false;

    private customerPanel customerPanel; //  ต้องเป็น field
    private MainFrame mainFrame;
    private GameTimer gameTimer;

    public gamePlayScreen(MainFrame mainFrame,int levelId){

        this.mainFrame = mainFrame;
        setLayout(new OverlayLayout(this));
        this.setBackground(Color.white);
        // status
        status = new WinLosePage(mainFrame);
        status.setBounds(0,0,800,520);
        status.setVisible(false);

        JPanel mainGameArea = new JPanel(new BorderLayout());
        mainGameArea.setOpaque(false);

        // layered
        JLayeredPane gameLayer = new JLayeredPane();
        gameLayer.setLayout(null);
        gameLayer.setBounds(0,0,800,600);

        // bg
        bgPanel = new bgPanel();
        bgPanel.setBounds(0,0,800,400);

        // top bar
        topBar = new TopBar(mainFrame);
        topBar.setBounds(0,0,800,90);

        // customer panel
        customerPanel = new customerPanel(topBar,this , mainFrame);
        customerPanel.setBounds(0,0,800,600);
        customerPanel.showCustomers(levelId);

        // counter
        counterBarPanel = new counterBar(mainFrame, customerPanel);
        counterBarPanel.setBounds(0,0,800,600);

        gameLayer.add(bgPanel,Integer.valueOf(0));
        gameLayer.add(topBar,Integer.valueOf(1));
        gameLayer.add(customerPanel, Integer.valueOf(2));
        gameLayer.add(counterBarPanel, Integer.valueOf(3));

        mainGameArea.add(gameLayer, BorderLayout.CENTER);

        add(status);
        add(mainGameArea);

        TimeDisplay screenTime = topBar.getTimeDisplay();
        if (levelId == 1){
            gameTimer = new GameTimer(180, this, screenTime,customerPanel);
        } else if (levelId == 2) {
            gameTimer = new GameTimer(225, this, screenTime,customerPanel);
        } else if (levelId == 3) {
            gameTimer = new GameTimer(270, this, screenTime,customerPanel);
        } else if (levelId == 4) {
            gameTimer = new GameTimer(315, this, screenTime,customerPanel);
        } else if (levelId == 5) {
            gameTimer = new GameTimer(360, this, screenTime,customerPanel);
        }
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
        this.levelId = levelId;
        switch (levelId) {
            case 1:
                bgPanel.setBackgroundImage("/images/gamePlay/bg/LV1.gif",-3,-100 , 800, 400);
                break;
            case 2:
                bgPanel.setBackgroundImage("/images/gamePlay/bg/LV2.gif",0, -50, 800, 400);
                break;
            case 3:
                bgPanel.setBackgroundImage("/images/gamePlay/bg/LV3.gif",0, -80, 800, 400);
                break;
            case 4:
                bgPanel.setBackgroundImage("/images/gamePlay/bg/LV4.gif",0, 0, 800, 400);
                break;
            case 5:
                bgPanel.setBackgroundImage("/images/gamePlay/bg/LV5.gif",0, 0, 800, 400);
                break;
        }

        counterBarPanel.setSlots(
                LevelFactory.getLevel(levelId, mainFrame.getPlayerData()).slots
        );
    }

    public void updateGame() {
        if (isGameOver) return;
        customerPanel.updateCustomers();
        int reqMoney = LevelFactory.getReqMoney(levelId);

        if (customerPanel.getHealth() <= 0) {
            gameWiner(false);
            isGameOver = true;
        }

        else if (customerPanel.isFinished()) {
            if (customerPanel.getcurrentMoney() < reqMoney){
                gameWiner(false);
            }else{
                gameWiner(true);
            }
            isGameOver = true;
        }
        else if ((gameTimer.getTimeLeft() <= 0)) {
            if (customerPanel.getcurrentMoney() < reqMoney){
                gameWiner(false);
            }else{
                gameWiner(true);
            }
            isGameOver = true;
        }
    }

    public void gameWiner(boolean state){
        if (isGameOver) return;

        isGameOver = true;
        pauseGame();

        int n = (customerPanel.getcurrentMoney()+customerPanel.getBonus());

        if (!state){
            mainFrame.getPlayerData().addMoney(n - ((int) Math.ceil((double) n / 2)));
        }else{
            mainFrame.getPlayerData().addMoney(n);
        }

        customerPanel.clearAllCustomers();

        int saveMoney = customerPanel.getcurrentMoney();
        int bonusMoney = customerPanel.getBonus();
        status.setState(state, saveMoney, bonusMoney);
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