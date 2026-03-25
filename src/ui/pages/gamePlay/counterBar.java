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
//                case "CLICK":
//                    btn.addMouseListener(new MouseAdapter() {
//                        @Override
//                        public void mousePressed(MouseEvent e){
//                            JButton progress = new JButton(new );
//                            // me when image flush exist
//                            gifIcon.getImage().flush();
//                            progress.setBounds(45, 240, 120, 120);
//                            progress.setBorderPainted(false);
//                            progress.setContentAreaFilled(false);
//                            progress.setFocusPainted(false);
//                            progress.setOpaque(false);
//
//                            add(progress);
//                            setComponentZOrder(progress, 0);
//                            enableDrag(btn)
//                        }
//                    }
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
                    for (Component cc : getComponents()) {
                        if (cc instanceof JButton btn && cc != c) {
                            // btn is the where it drag to
                            // c is the one who got drag
                            try {
                                Rectangle itemBounds = c.getBounds();
                                String itemName = c.getName();
                                // Check for collision with the pot and ensure the item is a noodle-type takro
                                if (btn.getName().equals("pot") && itemBounds.intersects(btn.getBounds()) && itemName.contains("takronoodle_") && c instanceof JButton takro) {

                                    // Update the Takro (the item being dragged)
                                    takro.setIcon(new ImageIcon("resources/images/gamePlay/aquiment/takronoodle.png"));
                                    takro.setName("takronoodle");
                                    // Define animation frames for the pot
                                    String[] boilingFrames = {
                                            "resources/images/gamePlay/ingredients/noodles/boilingPot/boiling1.png",
                                            "resources/images/gamePlay/ingredients/noodles/boilingPot/boiling2.png"
                                    };
                                    // to avoid GIF fuckery (it start where it last stops) (obsolete)
//                                    String[] progressFrame = {
//                                            "resources/images/gamePlay/ingredients/noodles/boilingPot/boilingProgress/stage1.png",
//                                            "resources/images/gamePlay/ingredients/noodles/boilingPot/boilingProgress/stage2.png",
//                                            "resources/images/gamePlay/ingredients/noodles/boilingPot/boilingProgress/stage3.png",
//                                            "resources/images/gamePlay/ingredients/noodles/boilingPot/boilingProgress/stage4.png",
//                                            "resources/images/gamePlay/ingredients/noodles/boilingPot/boilingProgress/stage5.png",
//                                            "resources/images/gamePlay/ingredients/noodles/boilingPot/boilingProgress/finished.png"
//                                    };
                                    btn.setName("boiling");
                                    // Create and add the progress indicator
                                    ImageIcon gifIcon = new ImageIcon("resources/images/gamePlay/ingredients/noodles/boilingPot/boilingProgress/progress_animation.GIF");
                                    JButton progress = new JButton(gifIcon);
                                    // me when image flush exist
                                    gifIcon.getImage().flush();
                                    progress.setBounds(45, 240, 120, 120);
                                    progress.setBorderPainted(false);
                                    progress.setContentAreaFilled(false);
                                    progress.setFocusPainted(false);
                                    progress.setOpaque(false);

                                    add(progress);
                                    setComponentZOrder(progress, 0);

                                    // Trigger the pot animation
//                                    animateButton(progress, progressFrame, 120, 120, 1000);
                                    animateButton(btn, boilingFrames, 380, 380, 500);

                                    // Final UI refresh after adding the progress button
                                    revalidate();
                                    repaint();

                                    // Timer to finish boiling after 4.5 seconds
                                    Timer stopTimer = new Timer(4500, e1 -> {
                                        try {
//                                            Timer animpro = (Timer) progress.getClientProperty("animationTimer");
                                            Timer animboil = (Timer) btn.getClientProperty("animationTimer");
                                            if (animboil != null) animboil.stop();
//                                            if (animpro != null) animpro.stop();

                                            remove(progress);
                                            btn.setName("pot");
                                            btn.setIcon(IconImage.create("resources/images/gamePlay/ingredients/noodles/boilingPot/not_boiling.png", 380, 380));
                                            revalidate();
                                            repaint();
                                            for (Component comp : getComponents()) {
                                                if (comp instanceof JButton bowl && "bowl_empty".equals(bowl.getName())) {
                                                    try {
                                                        if (itemName.contains("green")){
                                                            bowl.setIcon(IconImage.create("resources/images/gamePlay/ingredients/noodles/finishedNoodles/justNoodle/greenEgg.png", 175, 175));
                                                            bowl.setName("bowl_greenEgg");
                                                        }else if (itemName.contains("yellow")){
                                                            bowl.setIcon(IconImage.create("resources/images/gamePlay/ingredients/noodles/finishedNoodles/justNoodle/yellow.png", 175, 175));
                                                            bowl.setName("bowl_yellowEgg");
                                                        }else{
                                                            bowl.setIcon(IconImage.create("resources/images/gamePlay/ingredients/noodles/finishedNoodles/justNoodle/riceThinWideVermicelli.png", 175, 175));
                                                            bowl.setName("bowl_white");
                                                        }
                                                        // Update the bowl to show it now has cooked noodles
                                                        bowl.setIcon(IconImage.create("resources/images/gamePlay/bowl/noodles_only.png", 175, 175));
                                                        bowl.setName("bowl_with_noodles");
                                                        break;
                                                    } catch (Exception ex) {
                                                        // Silent catch as requested
                                                    }
                                                }
                                            }
                                        } catch (Exception ex) {
                                            // Silently handle issues during timer execution
                                        }
                                    });
                                    stopTimer.setRepeats(false);
                                    stopTimer.start();
                                }
                            } catch (Exception exd) {
                                // Log a simple message instead of the full stack trace
                                System.out.println("Boiling interaction failed");
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
                for (Component c : getComponents()) {
                    if (c instanceof JButton btn && c != item && c != sourceBtn ) {
                        try {
                            // btn is the victim
                            // item is the one who got spawn
                            if (btn.getName() != null && btn.getName().contains("takronoodle") && itemBounds.intersects(btn.getBounds())) {

                                String imagePath = null;
                                String newName = null;

                                if ("greenEgg".equals(itemName)) {
                                    imagePath = "resources/images/gamePlay/ingredients/noodles/blanchNoodles/takronoodle_green_egg.png";
                                    newName = "takronoodle_green_egg";
                                } else if ("yellowEgg".equals(itemName)) {
                                    imagePath = "resources/images/gamePlay/ingredients/noodles/blanchNoodles/takronoodle_yellow.png";
                                    newName = "takronoodle_yellow";
                                } else if ("thinRice".equals(itemName) || "wideRice".equals(itemName) || "riceVermicelli".equals(itemName)) {
                                    imagePath = "resources/images/gamePlay/ingredients/noodles/blanchNoodles/takronoodle_rice_thin_wide_vermicelli.png";
                                    newName = "takronoodle_rice_thin_wide_vermicelli";
                                }

                                // Apply changes only if a match was found
                                if (imagePath != null) {
                                    btn.setIcon(new ImageIcon(imagePath));
                                    btn.setName(newName);
                                }
                            } else if (btn.getName().equals("placemat") && itemBounds.intersects(btn.getBounds()) && itemName.equals("bowl")) {
                                ImageIcon icon;
                                icon = IconImage.create("resources/images/gamePlay/bowl/empty.png", 175, 175);
                                JButton bowl_empty = new JButton(icon);
                                bowl_empty.setBounds(390, 169, 175, 175);
                                bowl_empty.setBorderPainted(false);
                                bowl_empty.setContentAreaFilled(false);
                                bowl_empty.setOpaque(false);
                                bowl_empty.setName("bowl_empty");
                                add(bowl_empty);

                                setComponentZOrder(bowl_empty, 0);
                                revalidate();
                                repaint();

                                btn.setName("Occupied");

                            }
                        } catch (NullPointerException npe) {
                            System.err.println("Error: One of the components or names was null during collision check.");
                        } catch (Exception e) {
                            System.err.println("An unexpected error occurred while swapping noodle icons: " + e.getMessage());
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

    private void animateButton(JButton btn, String[] frames, int width, int height, int delay) {
        // Index to track which frame we are on
        final int[] frameIndex = {0};

        // Create a timer that fires every delay
        Timer animationTimer = new Timer(delay, e -> {
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

