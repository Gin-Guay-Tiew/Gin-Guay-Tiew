package ui.components;

import main.MainFrame;

import java.awt.event.*;

public class BackBtn extends ImageJButton {

    public BackBtn(MainFrame mainFrame, String pageName) {
        super("resources/images/shared/buttons/BackMini", ".png", 30, 100, 35);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getNavigator().toPage(pageName, true);
            }
        });
    }
}