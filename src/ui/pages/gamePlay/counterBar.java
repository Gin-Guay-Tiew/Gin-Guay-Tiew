package ui.pages.gamePlay;

import main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class counterBar extends JPanel {
    private Image counterBarimage;

    public counterBar(MainFrame mainFrame){
        //set layout
        setLayout(null);// ให้มันกำหนดตำแหน่งจาก figma ได้เลย เลยตั้งค่าเป็น null
        setPreferredSize(new Dimension(800, 600));
        setOpaque(false);


        // record component
        List<SlotSpec> slots = new ArrayList<>();
        // down counter
        slots.add(new SlotSpec("pot",-86,360,387,387,"resources/images/gamePlay/counter/potter.png"));
        slots.add(new SlotSpec("bucket lv3",230,480,120,120,"resources/images/gamePlay/ingredients/drinks/lv3.png"));
        slots.add(new SlotSpec("trash",620,527,162,73,"resources/images/gamePlay/binn/empty.png"));

        //on counter

        //mai
        slots.add(new SlotSpec("mai1",-10,320,114,114,"resources/images/gamePlay/aquiment/mai1.png"));
        slots.add(new SlotSpec("mai2",86,320,120,120,"resources/images/gamePlay/aquiment/mai2.png"));

        //soup row 1
        slots.add(new SlotSpec("yenTafo",238,340,70,70,"resources/images/gamePlay/ingredients/soups/category/yenTaFo/idle.png"));
        slots.add(new SlotSpec("tomYum",308,340,70,70,"resources/images/gamePlay/ingredients/soups/category/tomYum/idle.png"));

        //soup row2
        slots.add(new SlotSpec("namTok",238,400,70,70,"resources/images/gamePlay/ingredients/soups/category/namTok/idle.png"));
        slots.add(new SlotSpec("mai2",308,400,70,70,"resources/images/gamePlay/ingredients/soups/category/braisedPork/idle.png"));

        //towel
        slots.add(new SlotSpec("towel",360,277,240,240,"resources/images/gamePlay/counter/towel.png"));

        //noodles ++ add another panel
        /* slots.add(new SlotSpec("noodle bowl",368,277,120,120,"resources/images/gamePlay/ingredients/noodles/finishedNoodles/namTok/greenEgg/meatball&PorkRind/haveVegetable.png")); */

        //add on
        slots.add(new SlotSpec("vegetable",575,308,120,120,"resources/images/gamePlay/ingredients/vegetables/idle.png"));
        slots.add(new SlotSpec("porkSlice",570,382,102,102,"resources/images/gamePlay/ingredients/addOn/porkSlices/idle.png"));
        slots.add(new SlotSpec("meatball",634,382,102,102,"resources/images/gamePlay/ingredients/addOn/meatball/idle.png"));
        slots.add(new SlotSpec("porkRind",694,382,102,102,"resources/images/gamePlay/ingredients/addOn/porkRind/idle.png"));

        //up counter

        slots.add(new SlotSpec("container",2,160,182,182,"resources/images/gamePlay/ingredients/noodles/container/lv5.png"));

        slots.add(new SlotSpec("noodles",143,260,107,92,"resources/images/gamePlay/ingredients/noodles/category/greenEgg/idle.png"));
        slots.add(new SlotSpec("decorate",226,265,76,76,"resources/images/gamePlay/bowl/decorate.png"));
        slots.add(new SlotSpec("chopsticks",282,253,86,86,"resources/images/gamePlay/counter/chopsticks.png"));
        slots.add(new SlotSpec("kanomTuay",670,290,113,55,"resources/images/gamePlay/ingredients/kanomTuay/idle.png"));




        // create a button and set button
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
