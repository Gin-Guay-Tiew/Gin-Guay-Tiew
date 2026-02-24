package pages.home;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pages.MainFrame;

public class MainBtn implements ActionListener { ;

    private JDialog dialog;
    private MainFrame frame;

    public MainBtn(){
        this(null,null);
    }

    public MainBtn(JDialog dialog, MainFrame frame) {
        this.dialog = dialog;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start Game")){
            frame.showPage("levelSelect");
        }
        if (e.getActionCommand().equals("Tutorial")){
            dialog.setVisible(true);
        }
        if (e.getActionCommand().equals("Shop")){
            System.out.println("Shop");
        }
        if (e.getActionCommand().equals("Setting")){
            System.out.println("Setting");
        }
        if (e.getActionCommand().equals("Exit")){
            System.out.println("Exit");
        }
    }
}
