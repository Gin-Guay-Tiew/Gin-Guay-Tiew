package ui.pages.gamePlay;

import main.MainFrame;
import utilities.IconImage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class customerPanel extends JPanel {
    public customerPanel(MainFrame mainFrame){
        setLayout(null);
        setPreferredSize(new Dimension(800,600));
        setBackground(Color.GREEN);
        setOpaque(false);

        // record component


        }
    public void showCustomers(int levelID){
        removeAll();

        List<CustomerData> dataList = CustomerFactory.getCustomer(levelID);

        for (CustomerData d : dataList){
            customerComponent c = new customerComponent(d.imgPath,d.patiencePath);
            c.setBounds(d.x,d.y,197,240);
            add(c);

            JPanel bubblePanel = new JPanel();
            bubblePanel.setLayout(null);
            bubblePanel.setOpaque(false);
            bubblePanel.setBounds(d.x - 80,d.y-50,120,120);

            JLabel bubble = new JLabel(
                    IconImage.create("resources/images/gamePlay/customer/message.png",140,140)
            );

            bubble.setBounds(0,0,120,120);

            JLabel food = new JLabel();
            food.setBounds(25,15,76,76);

            food.setIcon(IconImage.create(d.foodPath,76,76));

            bubblePanel.add(bubble);
            bubblePanel.add(food);

            bubblePanel.setComponentZOrder(food,0);
            bubblePanel.setComponentZOrder(bubble,1);

            add(bubblePanel);

        }

        revalidate();
        repaint();


    }
    }
