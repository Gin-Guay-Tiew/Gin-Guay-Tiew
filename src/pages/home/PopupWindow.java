package pages.home;


import javax.swing.BorderFactory;
import javax.swing.border.Border;
import utilities.CustomFontLoader;
import javax.swing.*;
import java.awt.*;

public class PopupWindow {

    JDialog dialog;
    private final Font loadedFont = CustomFontLoader.loadCustomFont("resources/Jersey10.ttf");
    JLabel question = new JLabel();

    JPanel btn = new JPanel();

    JPanel container_content = new JPanel();

    Color color = Color.BLACK;
    int borderWidth = 4;
    Border customBorder = BorderFactory.createLineBorder(color,borderWidth);
    public JDialog PopupTutorial(JFrame frame,String title, boolean model,String text, JButton btncenter) {
        try {
            if (frame == null) {
                throw new IllegalArgumentException("Frame cannot be null");
            }

            if (btncenter == null) {
                throw new IllegalArgumentException("Buttons cannot be null");
            }

            dialog = new JDialog(frame, title, model);
            dialog.setSize(442, 108);
            dialog.setUndecorated(true);
            dialog.setLocationRelativeTo(frame);
            dialog.setVisible(true);

            // Create text button
            btn.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
            btncenter.setPreferredSize(new Dimension(97, 32));
            btn.add(btncenter);

            // Create text Question
            question.setText(text);

            if (loadedFont != null) {
                question.setFont(loadedFont.deriveFont(35f));
            } else {
                question.setFont(new Font("Arial", Font.BOLD, 20)); // fallback font
            }

            question.setHorizontalAlignment(SwingConstants.CENTER);

            // Layout
            container_content.setLayout(new GridLayout(2, 1));
            container_content.add(question);
            container_content.add(btn);

            dialog.add(container_content);
            ((JComponent) dialog.getContentPane()).setBorder(customBorder);

            return dialog;

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error creating popup: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public JDialog PopupTutorial(JFrame frame,String title, boolean model,String text, JButton btnleft, JButton btnright) {
        try {
            if (frame == null) {
                throw new IllegalArgumentException("Frame cannot be null");
            }

            if (btnleft == null || btnright == null) {
                throw new IllegalArgumentException("Buttons cannot be null");
            }

            dialog = new JDialog(frame, title, model);
            dialog.setSize(442, 108);
            dialog.setUndecorated(true);
            dialog.setLocationRelativeTo(frame);

            // Create text button
            btn.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
            btnleft.setPreferredSize(new Dimension(97, 32));
            btnright.setPreferredSize(new Dimension(97, 32));
            btn.add(btnleft);
            btn.add(btnright);

            // Create text Question
            question.setText(text);
            if (loadedFont != null) {
                question.setFont(loadedFont.deriveFont(35f));
            } else {
                question.setFont(new Font("Arial", Font.BOLD, 20));
            }
            question.setHorizontalAlignment(SwingConstants.CENTER);
            // Layout
            container_content.setLayout(new GridLayout(2, 1));
            container_content.add(question);
            container_content.add(btn);

            dialog.add(container_content);
            ((JComponent) dialog.getContentPane()).setBorder(customBorder);

            return dialog;

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error creating popup: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
