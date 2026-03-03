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
        setPreferredSize(new Dimension(800,600));
        setOpaque(false);


        // record component
        List<SlotSpec> slots = new ArrayList<>();
        // down counter
        slots.add(new SlotSpec("pot",-86,255,387,387,"resources/images/gamePlay/counter/potter.png"));
        slots.add(new SlotSpec("bucket lv3",230,380,120,120,"resources/images/gamePlay/ingredients/drinks/lv3.png"));
        slots.add(new SlotSpec("trash",620,430,162,73,"resources/images/gamePlay/binn/empty.png"));

        //on counter

        //mai
        slots.add(new SlotSpec("mai1",-8,220,114,114,"resources/images/gamePlay/aquiment/mai1.png"));
        slots.add(new SlotSpec("mai2",86,220,120,120,"resources/images/gamePlay/aquiment/mai2.png"));

        //soup row 1
        slots.add(new SlotSpec("yenTafo",238,240,70,70,"resources/images/gamePlay/ingredients/soups/category/yenTaFo/idle.png"));
        slots.add(new SlotSpec("tomYum",308,240,70,70,"resources/images/gamePlay/ingredients/soups/category/tomYum/idle.png"));

        //soup row2
        slots.add(new SlotSpec("namTok",238,300,70,70,"resources/images/gamePlay/ingredients/soups/category/namTok/idle.png"));
        slots.add(new SlotSpec("Pork",308,300,70,70,"resources/images/gamePlay/ingredients/soups/category/braisedPork/idle.png"));

        //towel
        slots.add(new SlotSpec("towel",360,180,240,240,"resources/images/gamePlay/counter/towel.png"));

        //add on
        slots.add(new SlotSpec("vegetable",575,205,120,120,"resources/images/gamePlay/ingredients/vegetables/idle.png"));
        slots.add(new SlotSpec("porkSlice",570,280,102,102,"resources/images/gamePlay/ingredients/addOn/porkSlices/idle.png"));
        slots.add(new SlotSpec("meatball",634,280,102,102,"resources/images/gamePlay/ingredients/addOn/meatball/idle.png"));
        slots.add(new SlotSpec("porkRind",694,280,102,102,"resources/images/gamePlay/ingredients/addOn/porkRind/idle.png"));

        //up counter

        slots.add(new SlotSpec("container",2,56,182,182,"resources/images/gamePlay/ingredients/noodles/container/lv5.png"));

        slots.add(new SlotSpec("noodles",143,158,107,92,"resources/images/gamePlay/ingredients/noodles/category/greenEgg/idle.png"));
        slots.add(new SlotSpec("decorate",226,163,76,76,"resources/images/gamePlay/bowl/decorate.png"));
        slots.add(new SlotSpec("chopsticks",282,151,86,86,"resources/images/gamePlay/counter/chopsticks.png"));
        slots.add(new SlotSpec("kanomTuay",670,191,113,55,"resources/images/gamePlay/ingredients/kanomTuay/idle.png"));




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
        // noodle area
        noodleArea noodleArea =  new noodleArea(mainFrame);
        add(noodleArea);
        // set ให้มันอยู่บนสุดจากตำแหน่งเดียวกับ pink towel ปล ที่ตั้ง  2 เพราะ เพราะ customer = 0 , counterBar = 1 ไปแล้ว เลยต้องตั้งให้เลขเยอะกว่ามันจะได้ดันขึนมาบนสุด
        setComponentZOrder(noodleArea,2);
        // เอาไว้คอยเปลี่ยนรูปก๋วยเตี๋ยว เวลาเขียน logic
        noodleArea.setNoodlesImage("resources/images/gamePlay/ingredients/noodles/finishedNoodles/namTok/greenEgg/meatball&PorkRind/haveVegetable.png");
    }



}
