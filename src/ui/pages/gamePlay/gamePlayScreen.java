package ui.pages.gamePlay;

import main.MainFrame;
import ui.pages.endGame.WinLosePage;

import javax.swing.*;
import java.awt.*;

public class gamePlayScreen extends JPanel {
    private Image counterBarimage;
    private WinLosePage status;


    public gamePlayScreen(MainFrame mainFrame){

        setLayout(new OverlayLayout(this));

        // background
        this.setBackground(Color.white);
        ImageIcon counter = new ImageIcon("resources/images/gamePlay/counter/counter_bar.png");
        counterBarimage = counter.getImage();

        // status endgame
        status = new WinLosePage(mainFrame);
        status.setBounds(0,0,800,520);
        status.setVisible(false); // โชว์ตอนจบเกม

        JPanel mainGameArea = new JPanel(new BorderLayout());
        mainGameArea.setOpaque(false);

        // top bar
        TopBar topBar = new TopBar(mainFrame);
        mainGameArea.add(topBar,BorderLayout.NORTH);

        // Game layer
        JLayeredPane gameLayer = new JLayeredPane();
        gameLayer.setLayout(null);
        gameLayer.setPreferredSize(new Dimension(800,520));

        //Layer 0 : customer
        customer customerPanel =  new customer(mainFrame);
        customerPanel.setBounds(0,0,800, 520);

        //Layer 1 : counter bar
        counterBar counterBarPanel = new counterBar(mainFrame);
        counterBarPanel.setBounds(0,0,800,520);

        gameLayer.add(customerPanel, Integer.valueOf(0));
        gameLayer.add(counterBarPanel, Integer.valueOf(1));

        mainGameArea.add(gameLayer, BorderLayout.CENTER);

        add(status);
        add(mainGameArea);

        // ดึง timeDisplay จาก topBar
        TimeDisplay screenTime = topBar.getTimeDisplay();

        GameTimer myTimer = new GameTimer(10,this,screenTime);
        myTimer.startTimer();

    }

    // โชว์หน้า status จบเกม
    public void gameOver(){
        status.setVisible(true);
    }

    //background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(counterBarimage, 0, 0, getWidth(), getHeight(), this);
    }


}
