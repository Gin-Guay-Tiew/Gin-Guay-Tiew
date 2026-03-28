package ui.pages.gamePlay;

import main.MainFrame;
import utilities.IconImage;
import utilities.SFX;
import utilities.SFXManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class counterBar extends JPanel {
    private final Image counterBarimage;
    private customerPanel cstPanel;
    private int yOffset = 90;

    public counterBar(MainFrame mainFrame, customerPanel cstPanel) {
        //set layout
        setLayout(null);// ให้มันกำหนดตำแหน่งจาก figma ได้เลย เลยตั้งค่าเป็น null
        setOpaque(false);
        counterBarimage = new ImageIcon("resources/images/gamePlay/counter/counter_bar.png").getImage();
        this.cstPanel = cstPanel;
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
            btn.setBounds(s.getX(), s.getY()+yOffset, s.getWidth(), s.getHeight());
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
            WindowFocusListener focusListener;

            @Override
            public void mousePressed(MouseEvent e) {
                offset[0] = e.getPoint();
                originalPos[0] = c.getLocation();
                // ให้ตอนเลือกมันอยู่เหนือทุกตัวใน component
                Container parent = c.getParent();
                parent.setComponentZOrder(c,0);
                c.setVisible(true);

                Window window = SwingUtilities.getWindowAncestor(c);
                if (window != null) {
                    focusListener = new WindowAdapter() {
                        @Override
                        public void windowLostFocus(WindowEvent we) {
                            c.setLocation(originalPos[0]);
                            window.removeWindowFocusListener(this);
                        }
                    };
                    window.addWindowFocusListener(focusListener);
                }
            }

            // ตอนปล่อยแล้วจะให้กลับที่เดิม
            @Override
            public void mouseReleased(MouseEvent e){
                Window window = SwingUtilities.getWindowAncestor(c);
                if (window != null && focusListener != null) {
                    window.removeWindowFocusListener(focusListener);
                }

                // Only reset/hide if the component isn't meant to be deleted
                if (c.isVisible()) {
                    for (Component cc : getComponents()) {
                        if (cc instanceof JButton btn && cc != c) {
                            // btn is the where it drags to
                            // c is the one who got drag
                            try {
                                Rectangle itemBounds = c.getBounds();
                                String itemName = c.getName();
                                // Check for collision with the pot and ensure the item is a noodle-type takro
                                if (btn.getName().equals("pot") && itemBounds.intersects(btn.getBounds()) && itemName.contains("takronoodle_") && c instanceof JButton takro) {

                                    SFXManager.play(SFX.PICK_MATERIAL);
                                    // Update the Takro (the item being dragged)
                                    takro.setIcon(new ImageIcon("resources/images/gamePlay/aquiment/takronoodle.png"));
                                    takro.setName("takronoodle");
                                    // Define animation frames for the pot
                                    String[] boilingFrames = {
                                            "resources/images/gamePlay/ingredients/noodles/boilingPot/boiling1.png",
                                            "resources/images/gamePlay/ingredients/noodles/boilingPot/boiling2.png"
                                    };
                                    btn.setName("pot_boiling");
                                    // Create and add the progress indicator
                                    ImageIcon gifIcon = new ImageIcon("resources/images/gamePlay/ingredients/noodles/boilingPot/boilingProgress/progress_animation.GIF");
                                    JButton progress = new JButton(gifIcon);
                                    // me when image flush exist
                                    gifIcon.getImage().flush();
                                    progress.setBounds(45, 240+yOffset, 120, 120);
                                    progress.setBorderPainted(false);
                                    progress.setContentAreaFilled(false);
                                    progress.setFocusPainted(false);
                                    progress.setOpaque(false);

                                    add(progress);
                                    setComponentZOrder(progress, 0);

                                    // Trigger the pot animation
                                    animateButton(btn, boilingFrames, 380, 380, 844);// 843.75 // no reason

                                    // Final UI refresh after adding the progress button
                                    revalidate();
                                    repaint();

                                    // Timer to finish boiling after 3875 seconds
                                    Timer stopTimer = new Timer(3875, e1 -> {
                                        try {
                                            Timer animboil = (Timer) btn.getClientProperty("animationTimer");
                                            if (animboil != null) animboil.stop();

                                            Timer delayTimer = new Timer(625, e2 -> {
                                                remove(progress);
                                                revalidate();
                                                repaint();
                                            });

                                            delayTimer.setRepeats(false); // Important! So it only runs once
                                            delayTimer.start();

                                            btn.setName("pot");
                                            btn.setIcon(IconImage.create("resources/images/gamePlay/ingredients/noodles/boilingPot/not_boiling.png", 380, 380));
                                            revalidate();
                                            repaint();
                                            for (Component comp : getComponents()) {
                                                if (comp instanceof JButton bowl) {
                                                    if ("bowl_empty".equals(bowl.getName())){
                                                        try {
                                                            if (itemName.contains("green")){
                                                                updateBowlVisual(
                                                                        bowl,
                                                                        "resources/images/gamePlay/ingredients/noodles/finishedNoodles/justNoodle/greenEgg.png",
                                                                        "bowl_noodle_greenEgg"
                                                                );
                                                                enableDrag(bowl);
                                                            }else if (itemName.contains("yellow")){
                                                                updateBowlVisual(
                                                                        bowl,
                                                                        "resources/images/gamePlay/ingredients/noodles/finishedNoodles/justNoodle/yellow.png",
                                                                        "bowl_noodle_yellow"
                                                                );
                                                                enableDrag(bowl);
                                                            }else if (itemName.contains("wide")){
                                                                updateBowlVisual(
                                                                        bowl,
                                                                        "resources/images/gamePlay/ingredients/noodles/finishedNoodles/justNoodle/riceThinWideVermicelli.png",
                                                                        "bowl_noodle_wide"
                                                                );
                                                                enableDrag(bowl);
                                                            }else if (itemName.contains("rice")){
                                                                updateBowlVisual(
                                                                        bowl,
                                                                        "resources/images/gamePlay/ingredients/noodles/finishedNoodles/justNoodle/riceThinWideVermicelli.png",
                                                                        "bowl_noodle_rice"
                                                                );
                                                                enableDrag(bowl);
                                                            } else if (itemName.contains("thin")){
                                                                updateBowlVisual(
                                                                        bowl,
                                                                        "resources/images/gamePlay/ingredients/noodles/finishedNoodles/justNoodle/riceThinWideVermicelli.png",
                                                                        "bowl_noodle_thin"
                                                                );
                                                                enableDrag(bowl);
                                                            }
                                                            break;
                                                        } catch (Exception ex) {
                                                            System.err.println("the noodle is a badboy");
                                                        }
                                                    }
                                                }
                                            }
                                        } catch (Exception ex) {
                                            System.err.println("the pot not cooking");
                                        }
                                    });
                                    stopTimer.setRepeats(false);
                                    stopTimer.start();
                                }
                                // Serve Bowl
                                if (c instanceof JButton bowl && bowl.getName().contains("bowl_")) {
                                    int centerX = bowl.getX() + (bowl.getWidth() / 2);
                                    int sectionIndex = centerX / 200;

                                    if (sectionIndex >= 1 && sectionIndex <= 3 && bowl.getY() < 200) {
                                        String rawCurrentPath = (String) bowl.getClientProperty("foodPath");
                                        String rawTargetPath = cstPanel.getCustomerDataAt(sectionIndex - 1).foodPath;
                                        String currentFoodPath = (rawCurrentPath != null) ? rawCurrentPath.replace("\\", "/") : "";
                                        String targetFoodPath = (rawTargetPath != null) ? rawTargetPath.replace("\\", "/") : "";
                                        String noodle = cstPanel.getCustomerDataAt(sectionIndex - 1).noodles;

                                        System.out.println("Serving food from: " + currentFoodPath);

                                        String bowlName = bowl.getName().toLowerCase();
                                        String targetNoodle = noodle.toLowerCase().trim();
                                        String extractedNoodle = "";
                                        if (bowlName.contains("wide")) {
                                            extractedNoodle = "wide";
                                        } else if (bowlName.contains("thin")) {
                                            extractedNoodle = "thin";
                                        } else if (bowlName.contains("rice")) {
                                            extractedNoodle = "rice";
                                        }
                                        boolean checker = false;

                                        System.out.println(extractedNoodle+" / "+targetNoodle);

                                        if (targetNoodle.equals("else") || extractedNoodle.equals(targetNoodle)) {
                                            checker = true;
                                        }

                                        if (currentFoodPath.equals(targetFoodPath) && checker) {
                                            System.out.println("Match! Customer satisfied.");
                                            SFXManager.play(SFX.SERVED);

                                            remove(bowl);
                                            for (Component comp : getComponents()) {
                                                if (comp instanceof JButton p && "Occupied".equals(p.getName())) {
                                                    p.setName("placemat");
                                                }
                                            }

                                            revalidate();
                                            repaint();
                                            cstPanel.removeCustomer(sectionIndex - 1, cstPanel.getCustomerDataAt(sectionIndex - 1));
                                        } else {
                                            System.out.println("Wrong! Wanted: " + targetNoodle + " | Got: " + bowlName);
                                            cstPanel.wrongOrder(sectionIndex - 1);
                                        }

                                        c.setLocation(originalPos[0]);
                                        break;
                                    }
                                }
                                // Trash Can
                                if (c instanceof JButton bowl && bowl.getName().contains("bowl_") && btn.getName().contains("trash") && itemBounds.intersects(btn.getBounds())){
                                    SFXManager.play(SFX.THROW);
                                    updateBowlVisual(
                                            bowl,
                                            "resources/images/gamePlay/bowl/empty.png",
                                            "bowl_empty"
                                    );
                                    disableDrag(bowl);
                                    if (btn.getName().equals("trash")){
                                        btn.setIcon(new ImageIcon("resources/images/gamePlay/binn/trash.png"));
                                        //she so perfect bah bah
                                        //the + 5 is so on point
                                        btn.setBounds(btn.getX(),btn.getY() + 5, 162, 68);
                                        btn.setName("trashed");
                                    }
                                }
                                // Pot
                                if (c instanceof JButton ladle && itemBounds.intersects(btn.getBounds()) && btn.getName().contains("pot") && ladle.getName().equals("ladle")) {
                                    SFXManager.play(SFX.LADLE);
                                    for (Component comp : getComponents()) {
                                        boolean isRiceThinWideVermicelli = false;
                                        if (comp instanceof JButton bowl && bowl.getName().contains("bowl_noodle_")) {
                                            String bowlName = bowl.getName();
                                            // Check if it's a bowl that already has noodles but no soup yet
                                            String noodleType = "";
                                            // Identify the noodle type from the previous name set in the boiling timer
                                            if (bowlName.contains("greenEgg")) {
                                                noodleType = "greenEgg";
                                            } else if (bowlName.contains("yellow")) {
                                                noodleType = "yellow";
                                            } else if (bowlName.contains("rice")) {
                                                noodleType = "rice";
                                                isRiceThinWideVermicelli = true;
                                            } else if (bowlName.contains("thin")) {
                                                noodleType = "thin";
                                                isRiceThinWideVermicelli = true;
                                            } else if (bowlName.contains("wide")) {
                                                noodleType = "wide";
                                                isRiceThinWideVermicelli = true;
                                            }
                                            if (!noodleType.isEmpty()) {
                                                try {
                                                    // Construct path: clearBroth -> [noodleType] -> no_addon.png
                                                    String actualPath = "resources/images/gamePlay/ingredients/noodles/finishedNoodles/clearBroth/" + noodleType + "/no_addon.png";
                                                    if (isRiceThinWideVermicelli) {
                                                        actualPath = "resources/images/gamePlay/ingredients/noodles/finishedNoodles/clearBroth/riceThinWideVermicelli/no_addon.png";
                                                    }
                                                    updateBowlVisual(
                                                            bowl,
                                                            actualPath,
                                                            "bowl_clearBroth_" + noodleType // Update name to reflect it now has broth and the noodle type
                                                    );
                                                } catch (Exception ex) {
                                                    System.out.println("Failed to add broth to bowl");
                                                }
                                            }
                                        }
                                    }
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
                if ((e.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) == 0) {
                    c.setLocation(originalPos[0]);
                    return;
                }
                int x = c.getX() + e.getX() - offset[0].x;
                int y = c.getY() + e.getY() - offset[0].y;
                c.setLocation(x, y);
            }
        });

    }

    //method for item can spawn and item spawn can drag
    public void spawnItem(String imgPath, JButton sourceBtn, MouseEvent e) {

        if (imgPath.contains("drink") || imgPath.contains("cola")
                || imgPath.contains("sprite") || imgPath.contains("orange")) {
            SFXManager.play(SFX.PICK_DRINK);
        } else {
            SFXManager.play(SFX.PICK_MATERIAL);
        }

        JButton item = new JButton(new ImageIcon(imgPath));
        String[] parts = imgPath.split("/");
        String result = parts[parts.length - 2];
        // maybe bugged
        item.setSize(120, 120);
        item.setBorderPainted(false);
        item.setContentAreaFilled(false);
        item.setOpaque(false);
        item.setName(result);
        item.putClientProperty("foodPath", imgPath.replace("\\", "/"));

        Point mouseInPanel = SwingUtilities.convertPoint(sourceBtn, e.getPoint(), this);
        item.setLocation(mouseInPanel.x - 60, mouseInPanel.y - 60);

        add(item);
        setComponentZOrder(item, 0);

        Window window = SwingUtilities.getWindowAncestor(this);
        WindowFocusListener[] focusListener = new WindowFocusListener[1];

        MouseAdapter teleportDrag = new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                if ((me.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) == 0) {
                    if (window != null && focusListener[0] != null) {
                        window.removeWindowFocusListener(focusListener[0]);
                    }
                    sourceBtn.removeMouseMotionListener(this);
                    sourceBtn.removeMouseListener(this);
                    remove(item);
                    revalidate();
                    repaint();
                    return;
                }
                Point p = SwingUtilities.convertPoint(sourceBtn, me.getPoint(), sourceBtn.getParent());
                item.setLocation(p.x - 60, p.y - 60);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (window != null && focusListener[0] != null) {
                    window.removeWindowFocusListener(focusListener[0]);
                }
                sourceBtn.removeMouseMotionListener(this);
                sourceBtn.removeMouseListener(this);
                Rectangle itemBounds = item.getBounds();
                String itemName = item.getName();
                for (Component c : getComponents()) {
                    if (c instanceof JButton btn && c != item && c != sourceBtn ) {
                        try {
                            // btn is the victim (the one who last place item got dragged too)
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
                                } else if ("thinRice".equals(itemName)) {
                                    imagePath = "resources/images/gamePlay/ingredients/noodles/blanchNoodles/takronoodle_rice_thin_wide_vermicelli.png";
                                    newName = "takronoodle_thin";
                                } else if ("wideRice".equals(itemName)) {
                                    imagePath = "resources/images/gamePlay/ingredients/noodles/blanchNoodles/takronoodle_rice_thin_wide_vermicelli.png";
                                    newName = "takronoodle_wide";
                                } else if ("riceVermicelli".equals(itemName)) {
                                    imagePath = "resources/images/gamePlay/ingredients/noodles/blanchNoodles/takronoodle_rice_thin_wide_vermicelli.png";
                                    newName = "takronoodle_rice";
                                }
                                // Apply changes only if a match was found
                                if (imagePath != null) {
                                    btn.setIcon(new ImageIcon(imagePath));
                                    btn.setName(newName);
                                }
                                // TODO: if  the takro break delete this "break"
                                break;
                            }
                            if (btn.getName().equals("placemat") && itemBounds.intersects(btn.getBounds()) && itemName.equals("bowl")) {
                                ImageIcon icon;
                                icon = IconImage.create("resources/images/gamePlay/bowl/empty.png", 175, 175);
                                JButton bowl_empty = new JButton(icon);
                                bowl_empty.setBounds(390, 169+yOffset, 175, 175);
                                bowl_empty.setBorderPainted(false);
                                bowl_empty.setContentAreaFilled(false);
                                bowl_empty.setOpaque(false);
                                bowl_empty.setName("bowl_empty");
                                add(bowl_empty);

                                setComponentZOrder(bowl_empty, 0);
                                revalidate();
                                repaint();

                                SFXManager.play(SFX.PUT_ON_PLATE);

                                btn.setName("Occupied");
                                // TODO: if  the bowl placing break delete this "break"
                                break;
                            }
                            // Check if user are dropping a topping (meatball, porkSlices, porkRind) into a bowl
                            if (itemBounds.intersects(btn.getBounds()) && btn.getName().startsWith("bowl_")) {

                                SFXManager.play(SFX.PUT_ON_PLATE);

                                String bowlName = btn.getName();
                                String[] nameParts = bowlName.split("_");

                                // Safety check: Needs at least bowl_broth_noodle e.g. bowl_clearBroth_greenEgg
                                if (nameParts.length < 3) break;

                                String currentBroth = nameParts[1];
                                String noodleType = nameParts[2];

                                // Check if vegetables are already in bowl or being added now
                                boolean hasVeg = bowlName.contains("vegetables") || itemName.equals("vegetables");

                                // --- BROTH SWAP LOGIC ---
                                List<String> specialBroths = List.of("namTok", "tomYum", "yenTaFo", "braisedPork");
                                if (currentBroth.equals("clearBroth") && specialBroths.contains(itemName)) {
                                    String newBroth = itemName;
                                    String comboFolder = getUpdatedToppingsPath(bowlName, "");

                                    String finalPath = buildFinalPath(newBroth, noodleType, comboFolder, hasVeg);
                                    updateBowlVisual(btn, finalPath, bowlName.replace("clearBroth", newBroth));

                                    revalidate(); repaint(); break;
                                }

                                // --- TOPPING & VEGETABLE LOGIC ---
                                List<String> validToppings = List.of("meatball", "porkSlices", "porkRind", "vegetables");

                                if (validToppings.contains(itemName)) {
                                    String toppingToAdd = itemName.equals("vegetables") ? "" : itemName;
                                    String comboFolder = getUpdatedToppingsPath(bowlName, toppingToAdd);

                                    // Vegetable safety check
                                    boolean isIllegalVeg = itemName.equals("vegetables") && comboFolder.equals("no_addon");

                                    if (isIllegalVeg) {
                                        System.out.println("Vegetables ignored: No meat toppings present.");
                                    } else {
                                        boolean visualHasVeg = (bowlName.contains("vegetables") || itemName.equals("vegetables"));
                                        String finalPath = buildFinalPath(currentBroth, noodleType, comboFolder, visualHasVeg);

                                        String newName = bowlName;
                                        if (!bowlName.contains(itemName)) {
                                            newName = bowlName + "_" + itemName;
                                        }

                                        updateBowlVisual(btn, finalPath, newName);

                                        revalidate();
                                        repaint();
                                    }
                                    break;
                                }
                            }
                            // Serve Drinks & KanomTuay
                            if (item.getName().contains("cola") || item.getName().contains("sprite") || item.getName().contains("orange") || item.getName().contains("kanom")) {
                                int centerX = item.getX() + (item.getWidth() / 2);
                                int sectionIndex = centerX / 200;

                                if (sectionIndex >= 1 && sectionIndex <= 3 && item.getY() < 200) {

                                    CustomerData target = cstPanel.getCustomerDataAt(sectionIndex - 1);

                                    if (target != null) {
                                        String rawCurrentPath = (String) item.getClientProperty("foodPath");
                                        String rawTargetPath = target.foodPath;

                                        String currentDrinkPath = (rawCurrentPath != null) ? rawCurrentPath.replace("\\", "/") : "";
                                        String targetDrinkPath = (rawTargetPath != null) ? rawTargetPath.replace("\\", "/") : "";

                                        System.out.println("Serving drink: " + currentDrinkPath);

                                        if (currentDrinkPath.equals(targetDrinkPath)) {
                                            System.out.println("Drink served correctly!");
                                            SFXManager.play(SFX.SERVED);

                                            remove(item);
                                            cstPanel.removeCustomer(sectionIndex - 1, target);

                                            revalidate();
                                            repaint();
                                        } else {
                                            System.out.println("Wrong drink! Customer wanted: " + targetDrinkPath);
                                            cstPanel.wrongOrder(sectionIndex - 1);
                                        }
                                    }
                                    break;
                                }
                            }
                        } catch (NullPointerException npe) {
                            System.err.println("Error: One of the components or names was null during collision check.");
                        } catch (Exception e) {
                            System.err.println("An unexpected error occurred while swapping noodle icons: " + e.getMessage());
                        }
                    }
                }

                // TODO don't forgot to delete this in prod
                System.out.println("Dropped Item ID: " + item.getName());
                remove(item);
                revalidate();
                repaint();
            }
        };

        if (window != null) {
            focusListener[0] = new WindowAdapter() {
                @Override
                public void windowLostFocus(WindowEvent we) {
                    window.removeWindowFocusListener(this);
                    sourceBtn.removeMouseMotionListener(teleportDrag);
                    sourceBtn.removeMouseListener(teleportDrag);
                    remove(item);
                    revalidate();
                    repaint();
                }
            };
            window.addWindowFocusListener(focusListener[0]);
        }

        sourceBtn.addMouseMotionListener(teleportDrag);
        sourceBtn.addMouseListener(teleportDrag);

        revalidate();
        repaint();
    }

    private void updateBowlVisual(JButton bowl, String path, String newName) {
        bowl.setIcon(IconImage.create(path, 175, 175));
        bowl.setName(newName);
        bowl.putClientProperty("foodPath", path);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (counterBarimage != null) {
            g.drawImage(counterBarimage, 0, 90,800,520, this);
        }
    }

    private void animateButton(JButton btn, String[] frames, int width, int height, int delay) {
        // Index to track which frame we are on
        final int[] frameIndex = {0};

        // Create a timer that fires every delay
        Timer animationTimer = new Timer(delay, e -> {
            if (frameIndex[0] < frames.length) {
                // Use existing IconImage.create or a standard scaling method
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
        // also very sneaky don't forgot to concat to string before use tho
        btn.putClientProperty("animationTimer", animationTimer);
    }

    public void disableDrag(JComponent c) {
        // Remove all MouseListeners
        for (MouseListener ml : c.getMouseListeners()) {
            c.removeMouseListener(ml);
        }
        // Remove all MouseMotionListeners
        for (MouseMotionListener mml : c.getMouseMotionListeners()) {
            c.removeMouseMotionListener(mml);
        }
    }

    private String getUpdatedToppingsPath(String currentName, String newItem) {
        // 1. Identify which toppings are present (either already in the bowl or being dropped)
        boolean hasMeatball = currentName.contains("meatball") || newItem.equals("meatball");
        boolean hasPorkSlices = currentName.contains("porkSlices") || newItem.equals("porkSlices");
        boolean hasPorkRind = currentName.contains("porkRind") || newItem.equals("porkRind");

        StringBuilder folderName = new StringBuilder();

        // Build the string based on your Priority: meatball -> porkSlices -> porkRind
        // Except it's not fahhhhhhhhhhhhhhhhh
        // if all three it goes like this
        if (hasMeatball && hasPorkSlices && hasPorkRind) {
            return "meatball&porkRind&porkSlices";
        }

        // Check Priority 1: Meatball
        if (hasMeatball) {
            folderName.append("meatball");
        }

        // Check Priority 2: PorkSlices
        if (hasPorkSlices) {
            if (!folderName.isEmpty()) folderName.append("&");
            folderName.append("porkSlices");
        }

        // Check Priority 3: PorkRind
        if (hasPorkRind) {
            if (!folderName.isEmpty()) folderName.append("&");
            folderName.append("porkRind");
        }

        // 3. Return "no_addon" if the bowl is plain meat-wise
        return folderName.isEmpty() ? "no_addon" : folderName.toString();
    }

    private String buildFinalPath(String broth, String noodle, String combo, boolean hasVeg) {
        String basePath = "resources/images/gamePlay/ingredients/noodles/finishedNoodles/" + broth + "/" + noodle + "/";
        if (noodle.contains("thin") || noodle.contains("wide") || noodle.contains("rice")) {
            basePath = "resources/images/gamePlay/ingredients/noodles/finishedNoodles/" + broth + "/" + "riceThinWideVermicelli" + "/";
        }
        // If no meat toppings are present return the no_addon image directly.
        if (combo.equals("no_addon")) {
            return basePath + "no_addon.png";
        } else {
            // Only if a meat combo exists do we look for idle.png or haveVegetable.png
            String fileName = hasVeg ? "haveVegetable.png" : "idle.png";
            return basePath + combo + "/" + fileName;
        }
    }

}