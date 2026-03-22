package ui.pages.gamePlay;

import main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class customer extends JPanel {
    public customer(MainFrame mainFrame){
        setLayout(null);
        setPreferredSize(new Dimension(800,600));
        setBackground(Color.GREEN);
        setOpaque(false);

        // record component
        List<SlotSpec> slots = new ArrayList<>();

        //Customer
        slots.add(new SlotSpec("Customer 1",220,30,172,259,"resources/images/gamePlay/customer/Alien_angry2.png",false));
        slots.add(new SlotSpec("Customer 2",400,30,172,259,"resources/images/gamePlay/customer/JarnBank3 1.png",false));
        slots.add(new SlotSpec("Customer 3",594,35,172,259,"resources/images/gamePlay/customer/CollegeStudent_female01_angry 1.png",false));

        // create a customer
        for (SlotSpec s : slots){
            JButton btn = new JButton(new ImageIcon(s.getIconPath()));
            btn.setBounds(s.getX(),s.getY(),s.getWidth(),s.getHeight());

            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            btn.setOpaque(false);
            btn.setBorder(null);
            add(btn);
        }

    }
}
