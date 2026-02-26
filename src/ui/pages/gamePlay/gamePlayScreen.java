package ui.pages.gamePlay;

import main.MainFrame;
import javax.swing.*;
import java.awt.*;

public class gamePlayScreen extends JPanel {


    public gamePlayScreen(MainFrame mainFrame){
        this.setBackground(Color.DARK_GRAY);

        setLayout(new BorderLayout());
        setOpaque(true);


        add(new TopBar(mainFrame),BorderLayout.NORTH);
    }


}
