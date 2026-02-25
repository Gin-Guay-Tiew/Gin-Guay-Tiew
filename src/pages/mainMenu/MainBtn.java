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
            // Create popUp
            String[] btnPaths = {
                    "resources/images/shared/buttons/No",
                    "resources/images/shared/buttons/Yes"
            };
            String[] btnLabels = {"No", "Yes"};
            ActionListener[] btnActions = {
                    null, // The "No" button logic is handled internally (dispose)
                    ex -> frame.getNavigator().toPage("levelSelect", true, 250)
            };
            dialog = pop.createPopup(
                    frame,
                    "Do you want to play the tutorial?", // Message
                    "resources/images/shared/popups/Demo.png", // Background Path
                    btnPaths,
                    btnLabels,
                    btnActions
            );
        }

        if (e.getActionCommand().equals("Shop")) {
            System.out.println("Shop");
        }

        if (e.getActionCommand().equals("Setting")) {
            System.out.println("Setting");
        }

        if (e.getActionCommand().equals("Exit")) {
            // Create popUp
            String[] btnPaths = {
                    "resources/images/shared/buttons/No",
                    "resources/images/shared/buttons/Yes"
            };
            String[] btnLabels = {"No", "Yes"};
            ActionListener[] btnActions = {
                    null, // The "No" button logic is handled internally (dispose)
                    ex -> frame.closeApp()
            };
            dialog = pop.createPopup(
                    frame,
                    "Do you want to close the game?", // Message
                    "resources/images/shared/popups/Demo.png", // Background Path
                    btnPaths,
                    btnLabels,
                    btnActions
            );
        }
    }
}