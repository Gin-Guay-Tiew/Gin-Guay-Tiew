package ui.pages.gamePlay;

import utilities.IconImage;

import javax.swing.*;

public class customerComponent extends JPanel {
    private  JLabel imgLabel, patienceLabel;
    public  customerComponent(String imgPath ,String patiencePath,String requestFood){
        setLayout(null);
        setOpaque(false);
        setSize(500,500);

        //customer label
        imgLabel = new JLabel(new ImageIcon((imgPath)));
        imgLabel.setBounds(0,0,160,240);

        //customer patience label
        patienceLabel = new JLabel(new ImageIcon(patiencePath));
        patienceLabel.setBounds(137,83,60,107);

        add(imgLabel);
        add(patienceLabel);
    }

}
