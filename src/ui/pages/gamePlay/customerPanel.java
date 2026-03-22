package ui.pages.gamePlay;

import main.MainFrame;

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
        }
        revalidate();
        repaint();

    }
    }
