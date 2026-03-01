package ui.pages.shopUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import logic.GameController;
import logic.UpgradeItem;
import ui.components.BackBtn;
import ui.components.MoneyDisplay;
import ui.components.PopupWindow;
import ui.components.ImageJButton;
import main.MainFrame;

public class ShopScreen extends JPanel {
    private GameController controller;

    public ShopScreen(GameController gm) {
        this.controller = gm;
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245)); // พื้นหลังเทาอ่อนๆ

        // --- 1. ส่วนหัว (Back และ Money) ---
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setOpaque(false);
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backBtn = new BackBtn(gm.getMainFrame(), MainFrame.MAIN_MENU);
        northPanel.add(backBtn, BorderLayout.WEST);

        MoneyDisplay moneyPanel = new MoneyDisplay(gm.getTotalMoney());
        northPanel.add(moneyPanel, BorderLayout.EAST);

        add(northPanel, BorderLayout.NORTH);

        // --- 2. ส่วนรายการสินค้า (Grid) ---
        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        gridPanel.setOpaque(false);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 80, 20, 80));


        for (UpgradeItem item : gm.getAvailableItems()) {
            gridPanel.add(createItemCard(item));
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createItemCard(UpgradeItem item) {
        // สร้างกรอบการ์ด
        JPanel card = new JPanel(new BorderLayout(15, 0));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // [West] รูปสินค้า
        ImageIcon itemIcon = utilities.IconImage.create(item.getImagePath(), 70, 70);
        JLabel imageLabel = new JLabel(itemIcon);
        card.add(imageLabel, BorderLayout.WEST);

        // [Center] ชื่อและราคา
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);

        Font jerseyFont = utilities.FontLoader.loadCustomFont("resources/font/Jersey10.ttf");

        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setFont(jerseyFont.deriveFont(Font.BOLD, 24f));

        JLabel priceLabel = new JLabel(item.getPrice() + " N");
        priceLabel.setFont(jerseyFont.deriveFont(18f));
        priceLabel.setForeground(Color.GRAY);

        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);
        card.add(infoPanel, BorderLayout.CENTER);

        // [East] ปุ่ม BUY ตามสถานะ
        String buttonPath;
        boolean isClickable = true;

        if (!item.isUnlocked()) {
            buttonPath = "resources/images/shared/buttons/lockedBuy";
            isClickable = false;
        } else if (controller.getTotalMoney() < item.getPrice()) {
            buttonPath = "resources/images/shared/buttons/noMoneyBuy";
        } else {
            buttonPath = "resources/images/shared/buttons/canBuy";
        }

        ImageJButton buyBtn = new ImageJButton(buttonPath, ".png", 30, 90, 40);
        buyBtn.setEnabled(isClickable);

        // --- Logic การซื้อ ---
        buyBtn.addActionListener(e -> {
            if (controller.purchaseItem(item)) {
                // ซื้อสำเร็จ: Refresh หน้าจอโดยสั่งลบแล้ววาดใหม่
                MainFrame mf = controller.getMainFrame();
                mf.getNavigator().toPage(MainFrame.SHOP_UI, false);
            } else {
                new PopupWindow().createPopup(
                        controller.getMainFrame(),
                        "Not enough money!",
                        "resources/images/shared/popups/Demo.png",
                        new String[]{"resources/images/shared/buttons/No"},
                        new String[]{"No"},
                        null
                );
            }
        });

        // --- Animation (Hover & Click) ---
        buyBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (buyBtn.isEnabled()) buyBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mousePressed(MouseEvent e) {
                if (buyBtn.isEnabled()) {
                    // เอฟเฟกต์กดบุ๋มลงไป (เขยิบ Margin)
                    buyBtn.setBorder(BorderFactory.createEmptyBorder(4, 4, 0, 0));
                }
            }
            public void mouseReleased(MouseEvent e) {
                buyBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            }
        });

        card.add(buyBtn, BorderLayout.EAST);
        return card;
    }
}