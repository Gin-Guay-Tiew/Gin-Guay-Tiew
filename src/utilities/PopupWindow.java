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

    /* ===================== BACKGROUND PANEL ===================== */
    static class BackgroundPanel extends JPanel {
        private final Image image;
        private final int margin; // The width/height of the corner slices in pixels

        public BackgroundPanel(ImageIcon icon, int margin) {
            this.image = icon.getImage();
            this.margin = margin;
            setOpaque(false);
            setLayout(new BorderLayout());
        }

        @Override
        protected void paintComponent(Graphics g) {
            int iw = image.getWidth(this);
            int ih = image.getHeight(this);
            int cw = getWidth();
            int ch = getHeight();

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            /* 9-Slice Logic */
            // Top row
            g.drawImage(image, 0, 0, margin, margin, 0, 0, margin, margin, this);
            g.drawImage(image, margin, 0, cw - margin, margin, margin, 0, iw - margin, margin, this);
            g.drawImage(image, cw - margin, 0, cw, margin, iw - margin, 0, iw, margin, this);

            // Middle row
            g.drawImage(image, 0, margin, margin, ch - margin, 0, margin, margin, ih - margin, this);
            g.drawImage(image, margin, margin, cw - margin, ch - margin, margin, margin, iw - margin, ih - margin, this);
            g.drawImage(image, cw - margin, margin, cw, ch - margin, iw - margin, margin, iw, ih - margin, this);

            // Bottom row
            g.drawImage(image, 0, ch - margin, margin, ch, 0, ih - margin, margin, ih, this);
            g.drawImage(image, margin, ch - margin, cw - margin, ch, margin, ih - margin, iw - margin, ih, this);
            g.drawImage(image, cw - margin, ch - margin, cw, ch, iw - margin, ih - margin, iw, ih, this);
        }
    }

    /* ===================== BOUNCE EFFECT ===================== */
    private void showWithBounceEffect(JFrame frame) {

        final int targetWidth = Math.min(dialog.getWidth() + 25, 550);
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

        Timer timer = new Timer(1, null);

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

    public JDialog createPopup(JFrame frame, String title, boolean modal, String text, String bgPath, String[] images, String[] buttonTexts, ActionListener[] actions) {

        if (frame == null)
            throw new IllegalArgumentException("Frame cannot be null");

        dialog = new JDialog(frame, title, modal);
        dialog.setUndecorated(true);

        /* ===== BACKGROUND IMAGE ===== */
        ImageIcon backgroundIcon = IconImage.create(bgPath, 200, 200);
        BackgroundPanel bgPanel = new BackgroundPanel(backgroundIcon, 30);

        /* ===== TEXT ===== */
        JTextPane msg = new JTextPane();
        msg.setText(text);
        msg.setEditable(false);
        msg.setOpaque(false);
        msg.setFocusable(false);
        msg.setMargin(new Insets(20, 20, 0, 20));

        StyledDocument doc = msg.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        msg.setFont(loadedFont.deriveFont(30f));

        /* ===== + PANEL ===== */
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 18, 0));
        btnPanel.setBorder(new EmptyBorder(15, 0, 20, 0));
        btnPanel.setOpaque(false);

        for (int i = 0; i < buttonTexts.length; i++) {
            JButton btn;
            if (images == null || images[i] == null) {
                btn = new ImageJButton("resources/images/shared/buttons/Yes", ".png", 30, 70, 40);
            } else {
                btn = new ImageJButton(images[i], ".png", 30, 70, 40);
            }

            // ถ้าเป็น No → ปิด dialog
            if (buttonTexts[i].equalsIgnoreCase("No")) {
                btn.addActionListener(e -> dialog.dispose());
            }

            // ถ้ามี action ส่งมา
            if (actions != null && i < actions.length && actions[i] != null) {
                int index = i;
                btn.addActionListener(e -> {
                    dialog.dispose();
                    actions[index].actionPerformed(e);
                });
            }

            btnPanel.add(btn);
        }

        /* ===== ADD TO BACKGROUND ===== */
        bgPanel.add(msg, BorderLayout.CENTER);
        bgPanel.add(btnPanel, BorderLayout.SOUTH);

        dialog.setContentPane(bgPanel);
        dialog.setBackground(new Color(0, 0, 0, 0));
        dialog.pack();
        showWithBounceEffect(frame);

        return dialog;
    }
}