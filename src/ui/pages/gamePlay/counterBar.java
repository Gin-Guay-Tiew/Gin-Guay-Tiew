package ui.pages.gamePlay;

import main.MainFrame;

import javax.swing.*;
import java.awt.*;

public class counterBar extends JPanel {
    public counterBar(MainFrame mainFrame){
        this.setBackground(Color.GREEN);
        setOpaque(true);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(250,0,0,0));
    }

}
