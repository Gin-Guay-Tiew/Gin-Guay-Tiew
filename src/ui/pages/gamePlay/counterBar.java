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
                    Rectangle itemBounds = c.getBounds();
                    String itemName = c.getName();
                    for (Component cc : getComponents()) {
                        if (cc instanceof JButton btn && cc != c ) {
                            if (btn.getName().equals("pot") && itemBounds.intersects(btn.getBounds()) && itemName.contains("noodle_")) {
                                String[] boilingFrames = {
                                        "resources/images/gamePlay/ingredients/noodles/boilingPot/boiling1.png",
                                        "resources/images/gamePlay/ingredients/noodles/boilingPot/boiling2.png"
                                };
                                btn.setName("boiling");
                                JButton progress = new JButton(new ImageIcon("resources/images/gamePlay/ingredients/noodles/boilingPot/boilingProgress/progress_animation.GIF"));
                                progress.setBounds(45,240,120,120);
                                progress.setBorderPainted(false);
                                progress.setContentAreaFilled(false);
                                progress.setFocusPainted(false);
                                progress.setOpaque(false);
                                add(progress);
                                setComponentZOrder(progress, 0);
                                revalidate();
                                repaint();
                                animateButton(btn, boilingFrames, 380, 380);
                                // Stop boiling after 4 seconds
                                Timer stopTimer = new Timer(4500, e1 -> {
                                    Timer anim = (Timer) btn.getClientProperty("animationTimer");
                                    if (anim != null) anim.stop();
                                    remove(progress);
                                    btn.setName("pot");
                                    btn.setIcon(IconImage.create("resources/images/gamePlay/ingredients/noodles/boilingPot/not_boiling.png", 380, 380));
                                });
                                stopTimer.setRepeats(false);
                                stopTimer.start();
                            }
                        }
                    }
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
        // maybe bugged
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
                        if (btn.getName().contains("takronoodle") && itemBounds.intersects(btn.getBounds())) {
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

    private void animateButton(JButton btn, String[] frames, int width, int height) {
        // Index to track which frame we are on
        final int[] frameIndex = {0};

        // Create a timer that fires every 500ms (0.5 seconds)
        Timer animationTimer = new Timer(500, e -> {
            if (frameIndex[0] < frames.length) {
                // Use your existing IconImage.create or a standard scaling method
                btn.setIcon(IconImage.create(frames[frameIndex[0]], width, height));
                frameIndex[0]++;
            } else {
                // Loop back to the start
                frameIndex[0] = 0;
                btn.setIcon(IconImage.create(frames[0], width, height));
            }
            btn.repaint();
        });

        animationTimer.start();

        // Store the timer in the button so we can stop it later if needed
        btn.putClientProperty("animationTimer", animationTimer);
    }

}

