package ui.pages.gamePlay;

import main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
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

            switch (s.getType()) {
                case "SPAWN":
                    btn.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            spawnItem(s.getSpawnPath(),btn,e);
                        }
                    });
                    break;

                case "DRAG":
                    enableDrag(btn);
                    break;
                case "SPAWNDRAG":

            }

            add(btn);
        }

        revalidate();
        repaint();
    }

    // method for item can drag
    public void enableDrag(JComponent c) {
        final Point[] offset = {null};
        final Point[] originalPos = {c.getLocation()};

        // ตอนลากให้จำตำแหน่งเดิมไว้
        c.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                offset[0] = e.getPoint();
                originalPos[0] = c.getLocation();
                // ให้ตอนเลือกมันอยู่เหนือทุกตัวใน component
                Container parent = c.getParent();
                parent.setComponentZOrder(c,0);
                c.setVisible(true);
            }

            // ตอนปล่อยแล้วจะให้กลับที่เดิม
            @Override
            public void mouseReleased(MouseEvent e){
                c.setLocation(originalPos[0]);
                c.setVisible(false);
            }
        });



        c.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int x = c.getX() + e.getX() - offset[0].x;
                int y = c.getY() + e.getY() - offset[0].y;
                c.setLocation(x, y);
            }
        });

    }

    //method for item can spawn and item spawn can drag
    public void spawnItem(String imgPath, JButton sourceBtn, MouseEvent e) {
        JButton item = new JButton(new ImageIcon(imgPath));
        item.setSize(120, 120);
        item.setBorderPainted(false);
        item.setContentAreaFilled(false);
        item.setOpaque(false);

        // 1. Teleport: Convert the click point to the panel's coordinate system
        Point mouseInPanel = SwingUtilities.convertPoint(sourceBtn, e.getPoint(), this);
        item.setLocation(mouseInPanel.x - 60, mouseInPanel.y - 60);

        add(item);
        setComponentZOrder(item, 0);
        enableDrag(item);

        // 2. Continuous Hijack: Keep teleporting while dragging
        MouseMotionListener teleportDrag = new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                Point p = SwingUtilities.convertPoint(sourceBtn, me.getPoint(), sourceBtn.getParent());
                item.setLocation(p.x - 60, p.y - 60);
                repaint();
            }
        };

        sourceBtn.addMouseMotionListener(teleportDrag);

        // 3. Cleanup: Stop teleporting when mouse is released
        sourceBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                sourceBtn.removeMouseMotionListener(teleportDrag);
                sourceBtn.removeMouseListener(this);
                remove(item);
            }
        });

        revalidate();
        repaint();
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (counterBarimage != null) {
            g.drawImage(counterBarimage, 0, 0,800,520, this);
        }
    }


}

