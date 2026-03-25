package ui.pages.gamePlay;

import utilities.IconImage;

import javax.swing.*;

public class customerComponent extends JPanel {
    private  JLabel imgLabel, patienceLabel,bubble,food;
    private JPanel requestPanel;
    private ImageIcon icon;
    public  customerComponent(String imgPath ,String patiencePath,String requestFood){
        setLayout(null);
        setOpaque(false);
        setSize(197,240);

        //customer label
        imgLabel = new JLabel(new ImageIcon((imgPath)));
        imgLabel.setBounds(0,0,160,240);

        //customer patience label
        patienceLabel = new JLabel(new ImageIcon(patiencePath));
        patienceLabel.setBounds(137,83,60,107);

        //Bubble customer
        requestPanel = new JPanel();
        requestPanel.setLayout(null);
        requestPanel.setOpaque(false);
        requestPanel.setBounds(-5,23,140,140);

        icon = IconImage.create("resources/images/gamePlay/customer/message.png",140,140);
        bubble = new JLabel(icon);
        bubble.setBounds(0,0,140,140);

        food = new JLabel();
        food.setBounds(40,40,76,76);

        switch (requestFood){
            case "NOODLE":
                icon = IconImage.create("resources/images/gamePlay/ingredients/noodles/finishedNoodles/braisedPork/yellow/meatball&porkRind&porkSlices/haveVegetable.png",76,76);
                food.setIcon(new ImageIcon("resources/images/gamePlay/ingredients/noodles/finishedNoodles/braisedPork/yellow/meatball&porkRind&porkSlices/haveVegetable.png"));
                break;
        }

        requestPanel.add(bubble);
        requestPanel.add(food);



        add(imgLabel);
        add(patienceLabel);
    }

}
