package pages.home;

import pages.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPage extends JPanel implements ActionListener {

    MainFrame frame;
    JButton next = new JButton("NEXT");
    JButton exit = new JButton("EXIT");

    public MainMenuPage(MainFrame frame){
        this.frame = frame;
        setLayout(new FlowLayout());
        add(next);
        add(exit);

        next.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(next)){
            frame.showPage("levelSelect");
        }
    }
}
