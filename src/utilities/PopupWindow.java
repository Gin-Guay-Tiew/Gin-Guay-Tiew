package utilities;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;

public class PopupWindow {

    private JDialog dialog;
    private final Font loadedFont = CustomFontLoader.loadCustomFont("resources/Jersey10.ttf");
    private final Border customBorder = BorderFactory.createLineBorder(Color.WHITE, 4);

    /* ===================== IMAGE BUTTONS ===================== */

    /* ===================== BACKGROUND PANEL ===================== */
    class BackgroundPanel extends JPanel {

        private final Image image;

        public BackgroundPanel(ImageIcon icon) {
            this.image = icon.getImage();
            setLayout(new BorderLayout());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /* ===================== BOUNCE EFFECT ===================== */
    private void showWithBounceEffect(JFrame frame) {

        final int targetWidth = Math.min(dialog.getWidth()+100,550);
        final int targetHeight = dialog.getHeight();

        final double[] scale = {0.1};
        final double[] velocity = {0.15};

        final double spring = 0.08;
        final double damping = 0.75;

        Point frameCenter = frame.getLocationOnScreen();
        int centerX = frameCenter.x + frame.getWidth() / 2;
        int centerY = frameCenter.y + frame.getHeight() / 2;

        dialog.setSize(1, 1);
        dialog.setLocation(centerX, centerY);

        Timer timer = new Timer(16, null);

        timer.addActionListener(e -> {

            double force = (1 - scale[0]) * spring;
            velocity[0] += force;
            velocity[0] *= damping;
            scale[0] += velocity[0];

            int currentWidth = (int) (targetWidth * scale[0]);
            int currentHeight = (int) (targetHeight * scale[0]);

            dialog.setSize(currentWidth, currentHeight);
            dialog.setLocation(
                    centerX - currentWidth / 2,
                    centerY - currentHeight / 2
            );

            if (Math.abs(velocity[0]) < 0.01 && Math.abs(1 - scale[0]) < 0.01) {

                dialog.setSize(targetWidth, targetHeight);
                dialog.setLocation(
                        centerX - targetWidth / 2,
                        centerY - targetHeight / 2
                );

                timer.stop();
            }
        });

        timer.start();
        dialog.setVisible(true);
    }


    /* ===================== CREATE POPUP ===================== */

    public JDialog createPopup(JFrame frame, String title, boolean modal, String text, String bgPath,String[] images, String[] buttonTexts,ActionListener[] actions) {

        if (frame == null)
            throw new IllegalArgumentException("Frame cannot be null");

        dialog = new JDialog(frame, title, modal);
        dialog.setUndecorated(true);

        /* ===== BACKGROUND IMAGE ===== */
        ImageIcon backgroundIcon = IconImage.create(bgPath, 400, 250);
        BackgroundPanel bgPanel = new BackgroundPanel(backgroundIcon);

        /* ===== TEXT ===== */
        JTextPane question = new JTextPane();
        question.setText(text);
        question.setEditable(false);
        question.setOpaque(false);
        question.setFocusable(false);
        question.setMargin(new Insets(20, 20, 0, 20));

        StyledDocument doc = question.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        if (loadedFont != null) {
            question.setFont(loadedFont.deriveFont(35f));
        } else {
            question.setFont(new Font("Arial", Font.BOLD, 20));
        }

        /* ===== BUTTON PANEL ===== */
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 18, 0));
        btnPanel.setBorder(new EmptyBorder(40,0,20,0));
        btnPanel.setOpaque(false);

        for (int i = 0; i < buttonTexts.length; i++) {
            JButton btn;
            if (images == null || images[i] == null || i >= images.length){
                btn = new ImageJButton("resources/images/mainMenu/BtnLong", ".png", 30,150,50);
            }
            else {
                btn = new ImageJButton(images[i], ".png", 30,150,50);
            }

            // ถ้าเป็น No → ปิด dialog
            if (buttonTexts[i].equalsIgnoreCase("No")) {
                btn.addActionListener(e -> dialog.dispose());
            }

            // ถ้ามี action ส่งมา
            if (actions != null && i < actions.length && actions[i] != null) {
                int index = i;
                btn.addActionListener( e -> {
                    dialog.dispose();
                    actions[index].actionPerformed(e);
                });
            }

            btnPanel.add(btn);
        }

        /* ===== ADD TO BACKGROUND ===== */
        bgPanel.add(question, BorderLayout.CENTER);
        bgPanel.add(btnPanel, BorderLayout.SOUTH);

        dialog.setContentPane(bgPanel);
        ((JComponent) dialog.getContentPane()).setBorder(customBorder);

        dialog.pack();
        showWithBounceEffect(frame);

        return dialog;
    }
}