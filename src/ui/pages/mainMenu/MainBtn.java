package ui.pages.mainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.MainFrame;
import ui.components.PopupWindow;

public class MainBtn implements ActionListener {
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
                    "resources/images/shared/buttons/Yes",
                    "resources/images/shared/buttons/No"
            };
            String[] btnLabels = {"Yes", "No"};
            ActionListener[] btnActions = {
                    ex -> frame.getNavigator().toPage("loadingScreen", true, 250),
                    null // Use "Null" if btnLabels == "No"
            };
            pop.createPopup(
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
            frame.getNavigator().toPage("setting", true, 250);
        }

        if (e.getActionCommand().equals("Exit")) {
            // Create popUp
            String[] btnPaths = {
                    "resources/images/shared/buttons/Yes",
                    "resources/images/shared/buttons/No"
            };
            String[] btnLabels = {"Yes", "No"};
            ActionListener[] btnActions = {
                    ex -> frame.closeApp(),
                    null
            };
            pop.createPopup(
                    frame,
                    "Are you sure you want to leave the kitchen?", // Message
                    "resources/images/shared/popups/Demo.png", // Background Path
                    btnPaths,
                    btnLabels,
                    btnActions
            );
        }
    }
}