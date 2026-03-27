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
    private double TotalMoney;

    private JPanel OrderPanel, MoneyPanel, BonusPanel, TotalPanel;
    private CustomJLabel Money, Bonus, Total;
    private JPanel moneyField, bonusField, totalField;

    // 9-Slice Background Panel
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

    public ShowMeTheMoney(MainFrame mainFrame, double moneyEarned, double bonusMoney) {
        this.mainFrame = mainFrame;
        this.TotalMoney = moneyEarned + bonusMoney;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setBorder(new EmptyBorder(25, 120, 25, 120));

        // Background images for the 9-slice panels
        ImageIcon mainBg = IconImage.create("resources/images/shared/popups/Demo.png", 200, 200);
        ImageIcon fieldBg = IconImage.create("resources/images/shared/popups/Shop.png", 100, 100);

        // Top Box
        OrderPanel = new NineSlicePanel(mainBg.getImage(), 30);
        OrderPanel.setLayout(new GridLayout(2, 1, 20, 15));
        OrderPanel.setBorder(new EmptyBorder(15, 40, 15, 20));
        OrderPanel.setMaximumSize(new Dimension(450, 120));

        MoneyPanel = new JPanel();
        MoneyPanel.setLayout(new BorderLayout());
        MoneyPanel.setOpaque(false);
        Money = createLabel("Money", 40f);
        moneyField = createNumberBox(moneyEarned, 35f, fieldBg.getImage());
        moneyField.setPreferredSize(new Dimension(250, 45));
        MoneyPanel.add(Money, BorderLayout.WEST);
        MoneyPanel.add(moneyField, BorderLayout.EAST);
        OrderPanel.add(MoneyPanel);

        BonusPanel = new JPanel();
        BonusPanel.setLayout(new BorderLayout());
        BonusPanel.setOpaque(false);
        Bonus = createLabel("Bonus", 40f);
        bonusField = createNumberBox(bonusMoney, 35f, fieldBg.getImage());
        bonusField.setPreferredSize(new Dimension(250, 45));
        BonusPanel.add(Bonus, BorderLayout.WEST);
        BonusPanel.add(bonusField, BorderLayout.EAST);
        OrderPanel.add(BonusPanel);

        // Bottom Box
        TotalPanel = new NineSlicePanel(mainBg.getImage(), 30);
        TotalPanel.setLayout(new GridLayout(1, 2, 20, 15));
        TotalPanel.setBorder(new EmptyBorder(15, 30, 15, 20));
        TotalPanel.setMaximumSize(new Dimension(450, 80));

        Total = createLabel("Total", 50f);
        totalField = createTotalBox(TotalMoney, 45f, fieldBg.getImage());
        TotalPanel.add(Total);
        TotalPanel.add(totalField);

        add(OrderPanel);
        add(Box.createVerticalStrut(20));
        add(TotalPanel);
    }

    // ฟังก์ชันสร้างข้อความด้านซ้าย (Money, Bonus, Total)
    private CustomJLabel createLabel(String text, float fontSize) {
        CustomJLabel label = new CustomJLabel(text, 4f);
        label.setFont(jerseyFont.deriveFont(fontSize));
        label.setTextColor(Color.WHITE);
        label.setOutlineColor(new Color(67, 67, 67));
        label.setHorizontalAlignment(SwingConstants.CENTER); // จัดตัวหนังสือตรงกลาง
        label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        return label;
    }

    // ฟังก์ชันสร้างกล่องตัวเลขด้านขวา (0.00)
    private JPanel createNumberBox(double value, float fontSize, Image bgImage) {
        String formattedValue = String.format("%.2f", value);

        NineSlicePanel boxPanel = new NineSlicePanel(bgImage, 20);
        boxPanel.setLayout(new BorderLayout());
        boxPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        CustomJLabel numberLabel = new CustomJLabel(formattedValue+" N", 4f);
        numberLabel.setFont(jerseyFont.deriveFont(fontSize));
        numberLabel.setTextColor(new Color(214, 181, 88));
        numberLabel.setOutlineColor(new Color(90, 62, 44));
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER); // ตัวเลขอยู่ตรงกลางกล่อง
        numberLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        boxPanel.add(numberLabel, BorderLayout.CENTER);
        return boxPanel;
    }

    private JPanel createTotalBox(double value, float fontSize, Image bgImage) {
        String formattedValue = String.format("%.2f", value);

        NineSlicePanel boxPanel = new NineSlicePanel(bgImage, 20);
        boxPanel.setLayout(new BorderLayout());
        boxPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        CustomJLabel numberLabel = new CustomJLabel("+"+formattedValue+" N", 5f);
        numberLabel.setFont(jerseyFont.deriveFont(fontSize));
        numberLabel.setTextColor(new Color(230, 181, 42));
        numberLabel.setOutlineColor(new Color(115, 51, 12));
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER); // ตัวเลขอยู่ตรงกลางกล่อง
        numberLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        boxPanel.add(numberLabel, BorderLayout.CENTER);
        return boxPanel;
    }
}