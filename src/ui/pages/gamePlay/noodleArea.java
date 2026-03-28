package ui.pages.gamePlay;

import main.MainFrame;

import javax.swing.*;
import java.awt.*;

// for noodles on pink towel
public class noodleArea extends JPanel {

    private JButton noodleBtn; // เอ่าปุ่มไปใช้งานในอนาคต

    public noodleArea(MainFrame mainFrame){
        setLayout(null);
        setBounds(390, 200,175,175); // set ให้ panel มันอยู่ตำแหน่งเดียวกับ towel
        setOpaque(false);

        noodleBtn = new JButton();
        noodleBtn.setBounds(0,0,175,175);  // ระบุตำแหน่งของรูปก๋วยเตี๊ยวที่ถูกยัดเข้าไปในผ้าอีกทีนึง
        noodleBtn.setBorderPainted(false);
        noodleBtn.setContentAreaFilled(false);
        noodleBtn.setFocusPainted(false);
        noodleBtn.setOpaque(false);
        noodleBtn.setBorder(null);
        add(noodleBtn);
    }

    // method for dynamic noodles pic
    public void setNoodlesImage(String imagePath){
        noodleBtn.setIcon(new ImageIcon(getClass().getResource((imagePath))));
    }
}
