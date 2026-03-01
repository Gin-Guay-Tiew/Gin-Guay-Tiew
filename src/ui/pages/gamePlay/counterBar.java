package ui.pages.gamePlay;

import main.MainFrame;

import javax.swing.*;
import java.awt.*;

public class counterBar extends JPanel {
    private Image counterBarimage;

    public counterBar(MainFrame mainFrame){
        //set layout
        setLayout(new BorderLayout());
        setBackground(Color.GREEN);
        setBorder(BorderFactory.createEmptyBorder(250,0,0,0));
        setOpaque(true);

    }



}
