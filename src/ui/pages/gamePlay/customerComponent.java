package ui.pages.gamePlay;

import javax.swing.*;

public class customerComponent extends JPanel {
    private  JLabel imgLabel;
    private JLabel patienceLabel;
    public  customerComponent(String imgPath ,String patiencePath){
        setLayout(null);
        setOpaque(false);
        setSize(197,240);

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
