package ui.pages.gamePlay;

import main.MainFrame;
import javax.swing.*;
import java.awt.*;

public class gamePlayScreen extends JPanel {
    private Image counterBarimage;


    public gamePlayScreen(MainFrame mainFrame){

        setLayout(new BorderLayout());

        //top bar
        add(new TopBar(mainFrame),BorderLayout.NORTH);

        //background
        this.setBackground(Color.white);
        ImageIcon counter = new ImageIcon("resources/images/gamePlay/counter/counter_bar.png");
        counterBarimage = counter.getImage();

        // customer and counter_bar
        JLayeredPane gameLayer = new JLayeredPane();
        gameLayer.setLayout(null);
        gameLayer.setPreferredSize(new Dimension(800,520));

        //customer
        customer customerPanel =  new customer(mainFrame);
        customerPanel.setBounds(0,0,800, 520);

        //counter bar
        counterBar counterBarPanel = new counterBar(mainFrame);
        counterBarPanel.setBounds(0,0,800,520);

        gameLayer.add(customerPanel, Integer.valueOf(0));
        gameLayer.add(counterBarPanel, Integer.valueOf(1));

        add(gameLayer, BorderLayout.CENTER);


    }

    //background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(counterBarimage, 0, 0, getWidth(), getHeight(), this);
    }


}
