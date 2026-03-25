package ui.pages.gamePlay;

import main.MainFrame;
import utilities.IconImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class counterBar extends JPanel {
    private final Image counterBarimage;

    public counterBar(MainFrame mainFrame) {
        //set layout
        setLayout(null);// ให้มันกำหนดตำแหน่งจาก figma ได้เลย เลยตั้งค่าเป็น null
        setOpaque(false);
        counterBarimage = new ImageIcon("resources/images/gamePlay/counter/counter_bar.png").getImage();

    }


    public void setSlots(List<SlotSpec> slots) {
        removeAll();

        for (SlotSpec s : slots) {
            ImageIcon icon;
            if (s.getIconPath() != null) {
                icon = IconImage.create(s.getIconPath(), s.getWidth(), s.getHeight());
            }else{
                icon = new ImageIcon(s.getIconPath());
            }
            JButton btn = new JButton(icon);
            btn.setBounds(s.getX(), s.getY(), s.getWidth(), s.getHeight());
            btn.setName(s.getId());
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
                // Only reset/hide if the component isn't meant to be deleted
                if (c.isVisible()) {
                    c.setLocation(originalPos[0]);
                }
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

        String[] parts = imgPath.split("/");
        String result = parts[parts.length - 2];

        item.setSize(120, 120);
        item.setBorderPainted(false);
        item.setContentAreaFilled(false);
        item.setOpaque(false);
        item.setName(result);

        Point mouseInPanel = SwingUtilities.convertPoint(sourceBtn, e.getPoint(), this);
        item.setLocation(mouseInPanel.x - 60, mouseInPanel.y - 60);

        add(item);
        setComponentZOrder(item, 0);

        MouseMotionListener teleportDrag = new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                Point p = SwingUtilities.convertPoint(sourceBtn, me.getPoint(), sourceBtn.getParent());
                item.setLocation(p.x - 60, p.y - 60);
                repaint();
            }
        };

        sourceBtn.addMouseMotionListener(teleportDrag);

        sourceBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                sourceBtn.removeMouseMotionListener(teleportDrag);
                sourceBtn.removeMouseListener(this);

                Rectangle itemBounds = item.getBounds();
                String itemName = item.getName();

                // noodle mai swapper
                for (Component c : getComponents()) {
                    if (c instanceof JButton btn && c != item && c != sourceBtn) {
                        if ("takronoodle".equals(btn.getName()) && itemBounds.intersects(btn.getBounds())) {
                            if ("greenEgg".equals(itemName)) {
                                btn.setIcon(new ImageIcon("resources/images/gamePlay/ingredients/noodles/blanchNoodles/takronoodle_green_egg.png"));
                                btn.setName("takronoodle_green_egg");
                            } else if ("yellowEgg".equals(itemName)) {
                                btn.setIcon(new ImageIcon("resources/images/gamePlay/ingredients/noodles/blanchNoodles/takronoodle_yellow.png"));
                                btn.setName("takronoodle_yellow");
                            } else if ("thinRice".equals(itemName) || "wideRice".equals(itemName) || "riceVermicelli".equals(itemName)) {
                                btn.setIcon(new ImageIcon("resources/images/gamePlay/ingredients/noodles/blanchNoodles/takronoodle_rice_thin_wide_vermicelli.png"));
                                btn.setName("takronoodle_rice_thin_wide_vermicelli");
                            }
                        }
                    }
                }
                System.out.println("Dropped Item ID: " + item.getName());
                remove(item);
                revalidate();
                repaint();
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

