package ui.pages.engGame;

import main.MainFrame;
import utilities.FontLoader;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ShowMeTheMoney extends JPanel {
    private MainFrame mainFrame;
    private final Font jerseyFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");
    private double TotalMoney;

    private JPanel OrderPanel, MoneyPanel, BonusPanel, TotalPanel;
    private JLabel Money, Bonus, Total;
    private JTextField moneyField, bonusField, totalField;

    public ShowMeTheMoney(MainFrame mainFrame,double moneyEarned, double bonusMoney) {
        this.mainFrame = mainFrame;
        this.TotalMoney = moneyEarned + bonusMoney;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setBorder(new EmptyBorder(25,120,25,120));
        Color panelColor = new Color(220, 220, 220);

        // Top Box
        OrderPanel = new JPanel();
        OrderPanel.setLayout(new GridLayout(2,1,20,15));
        OrderPanel.setBackground(panelColor);
        OrderPanel.setBorder(new EmptyBorder(15,40,15,20));
        OrderPanel.setMaximumSize(new Dimension(450,100));

        MoneyPanel = new JPanel();
        MoneyPanel.setLayout(new BorderLayout());
        MoneyPanel.setOpaque(false);
        Money = createLabel("Money",40f);
        moneyField = createTextField(moneyEarned,35f,false);
        moneyField.setPreferredSize(new Dimension(250,45));
        MoneyPanel.add(Money, BorderLayout.WEST);
        MoneyPanel.add(moneyField, BorderLayout.EAST);
        OrderPanel.add(MoneyPanel);

        BonusPanel = new JPanel();
        BonusPanel.setLayout(new BorderLayout());
        BonusPanel.setOpaque(false);
        Bonus = createLabel("Bonus",40f);
        bonusField = createTextField(bonusMoney,35f,false);
        bonusField.setPreferredSize(new Dimension(250,45));
        BonusPanel.add(Bonus, BorderLayout.WEST);
        BonusPanel.add(bonusField, BorderLayout.EAST);
        OrderPanel.add(BonusPanel);


        // Bottom Box
        TotalPanel = new JPanel();
        TotalPanel.setLayout(new GridLayout(1,2,20,15));
        TotalPanel.setBackground(panelColor);
        TotalPanel.setBorder(new EmptyBorder(15,30,15,20));
        TotalPanel.setMaximumSize(new Dimension(450,70));

        Total = createLabel("Total",50f);
        totalField = createTextField(TotalMoney,45f,false);
        TotalPanel.add(Total);
        TotalPanel.add(totalField);

        add(OrderPanel);
        add(Box.createVerticalStrut(20));
        add(TotalPanel);
    }

    // ฟังก์ชันสร้างข้อความด้านซ้าย (Money, Bonus, Total)
    private JLabel createLabel(String text, float fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(jerseyFont.deriveFont(fontSize));
        label.setForeground(Color.BLACK); // ตัวหนังสือสีดำ
        label.setHorizontalAlignment(SwingConstants.CENTER); // จัดตัวหนังสือตรงกลาง
        return label;
    }

    // ฟังก์ชันสร้างกล่องตัวเลขด้านขวา (0.00)
    private JTextField createTextField(double value, float fontSize, boolean isCursorShow) {
        String formattedValue = String.format("%.2f", value);

        JTextField textField = new JTextField(formattedValue);
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.CENTER);// ตัวเลขอยู่ตรงกลางกล่อง
        textField.setFont(jerseyFont.deriveFont(fontSize));
        textField.setFocusable(isCursorShow);

        // ตกแต่งให้เหมือนรูปเป๊ะๆ (กล่องสีขาว ไม่มีขอบดำ)
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        textField.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2)); // ลบกรอบดำของ Windows ออก
        return textField;
    }
}
