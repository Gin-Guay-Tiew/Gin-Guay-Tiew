package ui.pages.mainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.MainFrame;
import ui.components.PopupWindow;
import utilities.SFX;
import utilities.SFXManager;

public class MainBtn implements ActionListener {
    private MainFrame frame;
    PopupWindow pop = new PopupWindow();
    private int tutorialNoCount = 0;

    public MainBtn(MainFrame frame) {
        if (frame == null) return;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Start Game")) {
            tutorialNoCount = 0;
            frame.getNavigator().toPage("levelSelect", true, 250);
        }

        if (e.getActionCommand().equals("Tutorial")) {
            String[] btnPaths = {
                    "resources/images/shared/buttons/Yes",
                    "resources/images/shared/buttons/No"
            };
            String[] btnLabels = {"Yes", "No"};
            ActionListener[] btnActions = {
                    ex -> {
                        tutorialNoCount = 0;
                        frame.getNavigator().toPage("tutorial", true, 250);
                    },
                    ex -> tutorialNoCount++
            };
            pop.createPopup(
                    frame,
                    "Do you want to see the tutorial?",
                    "resources/images/shared/popups/Demo.png",
                    btnPaths,
                    btnLabels,
                    btnActions
            );
        }

        if (e.getActionCommand().equals("Shop")) {
            tutorialNoCount = 0;
            frame.getNavigator().toPage(MainFrame.SHOP_UI, true);
        }

        if (e.getActionCommand().equals("Setting")) {
            tutorialNoCount = 0;
            frame.getNavigator().toPage("setting", true, 250);
        }

        if (e.getActionCommand().equals("Exit")) {
            String[] btnPaths = {
                    "resources/images/shared/buttons/Yes",
                    "resources/images/shared/buttons/No"
            };
            String[] btnLabels = {"Yes", "No"};
            ActionListener[] btnActions = {
                    ex -> frame.closeApp(),
                    ex -> {
                        if (tutorialNoCount == 6) {
                            frame.getPlayerData().addMoney(10000);
                        }
                        tutorialNoCount = 0;
                    }
            };
            pop.createPopup(
                    frame,
                    "Are you sure you want to leave the kitchen?",
                    "resources/images/shared/popups/Demo.png",
                    btnPaths,
                    btnLabels,
                    btnActions
            );
        }
    }
}