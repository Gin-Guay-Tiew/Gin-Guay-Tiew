package ui.pages.gamePlay;

import main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

public class counterBar extends JPanel {
    private Image counterBarimage;

    public counterBar(MainFrame mainFrame) {
        //set layout
        setLayout(null);// ให้มันกำหนดตำแหน่งจาก figma ได้เลย เลยตั้งค่าเป็น null
        setOpaque(false);
        counterBarimage = new ImageIcon("resources/images/gamePlay/counter/counter_bar.png").getImage();


    }


    public void setSlots(List<SlotSpec> slots) {
        removeAll();

        for (SlotSpec s : slots) {
            JButton btn = new JButton(new ImageIcon(s.getIconPath()));
            btn.setBounds(s.getX(), s.getY(), s.getWidth(), s.getHeight());

            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            btn.setOpaque(false);
            btn.setBorder(null);

            if (s.isDraggable()){
                enableDrag(btn);
            }

            add(btn);
        }

        revalidate();
        repaint();
    }

    public void enableDrag(JComponent comp) {
        final Point[] offset = {null};
        final Point[] originalPos = {comp.getLocation()};

        // ตอนลากให้จำตำแหน่งเดิมไว้
        comp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                offset[0] = e.getPoint();
                originalPos[0] = comp.getLocation();
            }

            // ตอนปล่อยแล้วจะให้กลับที่เดิม

            @Override
            public void mouseReleased(MouseEvent e) {
                comp.setLocation(originalPos[0]);
            }
        });



        comp.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int x = comp.getX() + e.getX() - offset[0].x;
                int y = comp.getY() + e.getY() - offset[0].y;
                comp.setLocation(x, y);
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (counterBarimage != null) {
            g.drawImage(counterBarimage, 0, 0,800,520, this);
        }
    }


}

