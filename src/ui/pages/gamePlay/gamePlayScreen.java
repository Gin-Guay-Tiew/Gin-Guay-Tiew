package ui.pages.gamePlay;

import main.MainFrame;
import javax.swing.*;
import java.awt.*;

public class gamePlayScreen extends JPanel {
    private Image counterBarimage;


    public gamePlayScreen(MainFrame mainFrame){


        setLayout(new BorderLayout());
        setOpaque(true);

        //background
        this.setBackground(Color.white);
        ImageIcon counter = new ImageIcon("resources/images/gamePlay/counter/counter_bar.png");
        counterBarimage = counter.getImage();


        add(new TopBar(mainFrame),BorderLayout.NORTH);
        add(new counterBar(mainFrame),BorderLayout.SOUTH);
    }

    //background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(counterBarimage, 0, 0, getWidth(), getHeight(), this);
    }


}
