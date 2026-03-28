package ui.pages.endGame;

import main.MainFrame;
import ui.components.CustomJLabel;
import utilities.FontLoader;
import utilities.IconImage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ShowMeTheMoney extends JPanel {
    private MainFrame mainFrame;
    private final Font jerseyFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");

    private JPanel OrderPanel, TotalPanel;
    private CustomJLabel Total;
    private JPanel totalField;

    public ShowMeTheMoney(MainFrame mainFrame, double moneyEarned, double bonusMoney, boolean isLost) {
        this.mainFrame = mainFrame;

        double netTotal = ((int) Math.ceil((double) (moneyEarned + bonusMoney) / 2));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setBorder(new EmptyBorder(25, 120, 25, 120));

        ImageIcon mainBg = IconImage.create("resources/images/shared/popups/Demo.png", 200, 200);
        ImageIcon fieldBg = IconImage.create("resources/images/shared/popups/Shop.png", 100, 100);

        // --- ส่วนบน: รายละเอียดเงิน ---
        OrderPanel = new NineSlicePanel(mainBg.getImage(), 30);
        // ถ้าแพ้ (isLost) ให้มี 3 แถวเพื่อแสดง Penalty ถ้าชนะมีแค่ 2 แถว
        int rows = isLost ? 3 : 2;
        OrderPanel.setLayout(new GridLayout(rows, 1, 0, 10));
        OrderPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
        OrderPanel.setMaximumSize(new Dimension(500, rows * 60));

        // 1. แถว Money (สีเขียว/เหลืองปกติ)
        OrderPanel.add(createDataRow("Money", moneyEarned, fieldBg.getImage(), new Color(214, 181, 88)));

        // 2. แถว Bonus (สีเขียว/เหลืองปกติ)
        OrderPanel.add(createDataRow("Bonus", bonusMoney, fieldBg.getImage(), new Color(214, 181, 88)));

        if (isLost) {
            OrderPanel.add(createDataRow("Penalty", -netTotal, fieldBg.getImage(), new Color(255, 102, 102)));
        }

        // --- ส่วนล่าง: ยอดรวมสุทธิ ---
        TotalPanel = new NineSlicePanel(mainBg.getImage(), 30);
        TotalPanel.setLayout(new BorderLayout(20, 0));
        TotalPanel.setBorder(new EmptyBorder(15, 40, 15, 40));
        TotalPanel.setMaximumSize(new Dimension(500, 90));

        Total = createLabel("Total", 50f);
        Total.setBorder(new EmptyBorder(0, 3, 0, 3));
        if (isLost){
            totalField = createValueBox((moneyEarned + bonusMoney)-netTotal, 45f, fieldBg.getImage(), new Color(230, 181, 42), true);
        } else {
            totalField = createValueBox((moneyEarned + bonusMoney), 45f, fieldBg.getImage(), new Color(230, 181, 42), true);
        }

        TotalPanel.add(Total, BorderLayout.WEST);
        TotalPanel.add(totalField, BorderLayout.CENTER);

        // ประกอบร่าง
        add(OrderPanel);
        add(Box.createVerticalStrut(15));
        add(TotalPanel);
    }

    // ฟังก์ชันช่วยสร้างแถวข้อมูล (Label + Box)
    private JPanel createDataRow(String title, double value, Image bg, Color numColor) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setOpaque(false);

        CustomJLabel label = createLabel(title, 35f);
        label.setPreferredSize(new Dimension(120, 40));

        JPanel box = createValueBox(value, 30f, bg, numColor, false);
        box.setPreferredSize(new Dimension(200, 45));

        row.add(label, BorderLayout.WEST);
        row.add(box, BorderLayout.CENTER);
        return row;
    }

    private CustomJLabel createLabel(String text, float fontSize) {
        CustomJLabel label = new CustomJLabel(text, 4f);
        label.setFont(jerseyFont.deriveFont(fontSize));
        label.setTextColor(Color.WHITE);
        label.setOutlineColor(new Color(67, 67, 67));
        return label;
    }

    private JPanel createValueBox(double value, float fontSize, Image bgImage, Color textColor, boolean isTotal) {
        String formattedValue = String.format("%.2f", value);
        if (value > 0) formattedValue = "+" + formattedValue;

        NineSlicePanel boxPanel = new NineSlicePanel(bgImage, 20);
        boxPanel.setLayout(new BorderLayout());

        CustomJLabel numberLabel = new CustomJLabel(formattedValue + " N", isTotal ? 5f : 4f);
        numberLabel.setFont(jerseyFont.deriveFont(fontSize));
        numberLabel.setTextColor(textColor);
        numberLabel.setOutlineColor(new Color(40, 40, 40));
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);

        boxPanel.add(numberLabel, BorderLayout.CENTER);
        return boxPanel;
    }

    static class NineSlicePanel extends JPanel {
        private final Image image;
        private final int margin;

        public NineSlicePanel(Image image, int margin) {
            this.image = image;
            this.margin = margin;
            setOpaque(false);
            setLayout(new BorderLayout());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image == null) return;

            int iw = image.getWidth(this);
            int ih = image.getHeight(this);
            int cw = getWidth();
            int ch = getHeight();

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

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
}