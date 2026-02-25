package pages.mainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pages.MainFrame;
import utilities.PopupWindow;

public class MainBtn implements ActionListener {
    private JDialog dialog;
    private MainFrame frame;
    PopupWindow pop = new PopupWindow();

    public MainBtn(MainFrame frame) {
        if (frame == null) return;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Start Game")) {

            frame.getNavigator().toPage("levelSelect", true, 250);
        }

        if (e.getActionCommand().equals("Tutorial")) {

            System.out.println("Tutorial clicked");

           dialog = pop.createPopup(
                   frame,
                   "tutorial",
                    true,
                   "Do you want to play Tutorial",
                   "resources/images/mainMenu/8bit-pixel-graphic-blue-sky-background-with-clouds-vector.jpg",
                   new String[]{"resources/images/mainMenu/BtnShort",null},
                   new String[]{"Yes", "No"},
                   new ActionListener[]{
                          ev -> {
                               frame.getNavigator().toPage("tutorial",true,250);
                            },
                           null
                  }
           );
        }

        if (e.getActionCommand().equals("Shop")) {
            System.out.println("Shop");
        }

        if (e.getActionCommand().equals("Setting")) {
            System.out.println("Setting");
        }

        if (e.getActionCommand().equals("Exit")) {
            System.out.println("Exit");
        }
    }
}