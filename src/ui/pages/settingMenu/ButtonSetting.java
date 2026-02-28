package ui.pages.settingMenu;

import ui.components.PopupWindow;
import utilities.IconImage;
import main.MainFrame;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonSetting implements ChangeListener, ActionListener {


    PopupWindow pop = new PopupWindow();

    String path = "resources/images/settingMenu/tree";
    ImageIcon slider100 = IconImage.create(path+"100"+".png", 50, 50);
    ImageIcon slider75 = IconImage.create(path+"75"+".png", 50, 50);
    ImageIcon slider50 = IconImage.create(path+"50"+".png", 50, 50);
    ImageIcon slider25 = IconImage.create(path+"25"+".png", 50, 50);

    ImageIcon off = IconImage.create("resources/images/settingMenu/off.png", 90, 35);
    ImageIcon on = IconImage.create("resources/images/settingMenu/on.png", 90, 35);

    private JSlider slider;
    private JLabel label;
    private JButton btn;
    private boolean state =  true;
    private MainFrame frame;
    public ButtonSetting(JButton btn) {
        this.btn = btn;
    }

    public ButtonSetting(MainFrame frame,JButton btn,JSlider slider,JLabel label) {
        this.btn = btn;
        this.slider = slider;
        this.label = label;
        this.frame = frame;
    }

    public void setvalue(){
        /*
        slider.setValue(50);
        label.setIcon(slider50);
        btn.setIcon(off);
         */
        String[] btnPaths = {
                "resources/images/shared/buttons/No"
        };
        String[] btnLabels = {"Ok"};
        ActionListener[] btnActions = {
                ex ->frame.getNavigator().toPage("mainMenu", true, 250)
                ,
                null // Use "Null" if btnLabels == "No"
        };
        pop.createPopup(
                frame,
                "There is no information to reset.", // Message
                "resources/images/shared/popups/Demo.png", // Background Path
                btnPaths,
                btnLabels,
                btnActions
        );
     }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == slider) {
            System.out.println("pop "+slider.getValue());
            if (slider.getValue() == 100) {
                label.setIcon(slider100);
            }
            else if (slider.getValue() >= 50) {
                label.setIcon(slider75);
            }
            else if (slider.getValue() >= 25) {
                label.setIcon(slider50);
            }
            else if (slider.getValue() >= 0) {
                label.setIcon(slider25);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("fx")){
            if (state){
                btn.setIcon(on);
                state = false;
            }
            else {
                btn.setIcon(off);
                state = true;
            }
        }
        if (e.getActionCommand().equals("reset")){
            String[] btnPaths = {
                    "resources/images/shared/buttons/Yes",
                    "resources/images/shared/buttons/No"
            };
            String[] btnLabels = {"Yes", "No"};
            ActionListener[] btnActions = {
                    ex ->setvalue()
                    ,
                    null // Use "Null" if btnLabels == "No"
            };
            pop.createPopup(
                    frame,
                    "Are you confirm to reset the game?", // Message
                    "resources/images/shared/popups/Demo.png", // Background Path
                    btnPaths,
                    btnLabels,
                    btnActions
            );
        }
    }
}
