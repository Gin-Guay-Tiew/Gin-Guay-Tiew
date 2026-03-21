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


    public gamePlayScreen(MainFrame mainFrame,int levelId){

        setLayout(new OverlayLayout(this));

        // background
        this.setBackground(Color.white);


        // status endgame
        status = new WinLosePage(mainFrame);
        status.setBounds(0,0,800,520);
        status.setVisible(false); // โชว์ตอนจบเกม

        JPanel mainGameArea = new JPanel(new BorderLayout());
        mainGameArea.setOpaque(false);



        // Game layer
        JLayeredPane gameLayer = new JLayeredPane();
        gameLayer.setLayout(null);
        gameLayer.setBounds(0,0,800,520);



        //Layer 0 : background
        bgPanel = new bgPanel();
        bgPanel.setBounds(0,0,800,400);

        //Layer 1 :top bar
        topBar = new TopBar(mainFrame);
        topBar.setBounds(0,0,800,100);

        //Layer 2 : customer
        customer customerPanel =  new customer(mainFrame);
        customerPanel.setBounds(0,0,800, 600);

        //Layer 3 : counter bar
        counterBarPanel = new counterBar(mainFrame);
        counterBarPanel.setBounds(0,90,800,600);


        gameLayer.add(bgPanel,Integer.valueOf(0));
        gameLayer.add(topBar,Integer.valueOf(1));
        gameLayer.add(customerPanel, Integer.valueOf(2));
        gameLayer.add(counterBarPanel, Integer.valueOf(3));


        mainGameArea.add(gameLayer, BorderLayout.CENTER);

        add(status);
        add(mainGameArea);

        // ดึง timeDisplay จาก topBar
        TimeDisplay screenTime = topBar.getTimeDisplay();

        GameTimer myTimer = new GameTimer(10,this,screenTime);
        myTimer.startTimer();

        //UI level
        showLevel(levelId);


    }

    public void showLevel(int levelId) {
        switch (levelId) {
            case 1:
                bgPanel.setBackgroundImage(
                        "resources/images/gamePlay/bg/LV1.gif",
                        -3,-100 , 800, 400
                );
                break;

            case 2:
                bgPanel.setBackgroundImage(
                        "resources/images/gamePlay/bg/LV2.gif",
                        0, -50, 800, 400
                );
                break;

            case 3:
                bgPanel.setBackgroundImage(
                        "resources/images/gamePlay/bg/LV3.gif",
                        0, -80, 800, 400
                );
                break;
            case 4:
                bgPanel.setBackgroundImage(
                        "resources/images/gamePlay/bg/LV4.gif",
                        0, 0, 800, 400
                );
                break;
            case 5:
                bgPanel.setBackgroundImage(
                        "resources/images/gamePlay/bg/LV5.gif",
                        0, 0, 800, 400
                );
                break;
        }
        counterBarPanel.setSlots(
                LevelFactory.getlevel(levelId).slots
        );
    }
    // โชว์หน้า status จบเกม
    public void gameOver(){
        status.setVisible(true);
    }

}


