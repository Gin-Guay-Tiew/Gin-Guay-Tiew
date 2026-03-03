//package ui.pages.shopUI;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import logic.GameController;
//import logic.UpgradeItem;
//import ui.components.BackBtn;
//import ui.components.MoneyDisplay;
//import ui.components.PopupWindow;
//import ui.components.ImageJButton;
//import main.MainFrame;
//
//public class ShopScreen extends JPanel {
//    private GameController controller;
//
//    public ShopScreen(GameController gm) {
//        this.controller = gm;
//        setLayout(new BorderLayout());
//        setBackground(new Color(245, 245, 245)); // พื้นหลังเทาอ่อนๆ
//
//        // --- 1. ส่วนหัว (Back และ Money) ---
//        JPanel northPanel = new JPanel(new BorderLayout());
//        northPanel.setOpaque(false);
//        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        JButton backBtn = new BackBtn(gm.getMainFrame(), MainFrame.MAIN_MENU);
//        northPanel.add(backBtn, BorderLayout.WEST);
//
//        MoneyDisplay moneyPanel = new MoneyDisplay(gm.getTotalMoney());
//        northPanel.add(moneyPanel, BorderLayout.EAST);
//
//        add(northPanel, BorderLayout.NORTH);
//
//        // --- 2. ส่วนรายการสินค้า (Grid) ---
//        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 15, 15));
//        gridPanel.setOpaque(false);
//        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 80, 20, 80));
//
//
//        for (UpgradeItem item : gm.getAvailableItems()) {
//            gridPanel.add(createItemCard(item));
//        }
//
//        JScrollPane scrollPane = new JScrollPane(gridPanel);
//        scrollPane.setBorder(null);
//        scrollPane.setOpaque(false);
//        scrollPane.getViewport().setOpaque(false);
//
//        add(scrollPane, BorderLayout.CENTER);
//    }
//
//    private JPanel createItemCard(UpgradeItem item) {
//        // สร้างกรอบการ์ด
//        JPanel card = new JPanel(new BorderLayout(15, 0));
//        card.setBackground(Color.WHITE);
//        card.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
//                BorderFactory.createEmptyBorder(10, 10, 10, 10)
//        ));
//
//        // [West] รูปสินค้า
//        ImageIcon itemIcon = utilities.IconImage.create(item.getImagePath(), 70, 70);
//        JLabel imageLabel = new JLabel(itemIcon);
//        card.add(imageLabel, BorderLayout.WEST);
//
//        // [Center] ชื่อและราคา
//        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
//        infoPanel.setOpaque(false);
//
//        Font jerseyFont = utilities.FontLoader.loadCustomFont("resources/font/Jersey10.ttf");
//
//        JLabel nameLabel = new JLabel(item.getName());
//        nameLabel.setFont(jerseyFont.deriveFont(Font.BOLD, 24f));
//
//        JLabel priceLabel = new JLabel(item.getPrice() + " N");
//        priceLabel.setFont(jerseyFont.deriveFont(18f));
//        priceLabel.setForeground(Color.GRAY);
//
//        infoPanel.add(nameLabel);
//        infoPanel.add(priceLabel);
//        card.add(infoPanel, BorderLayout.CENTER);
//
//        // [East] ปุ่ม BUY ตามสถานะ
//        String buttonPath;
//        boolean isClickable = true;
//
//        if (!item.isUnlocked()) {
//            buttonPath = "resources/images/shared/buttons/lockedBuy";
//            isClickable = false;
//        } else if (controller.getTotalMoney() < item.getPrice()) {
//            buttonPath = "resources/images/shared/buttons/noMoneyBuy";
//        } else {
//            buttonPath = "resources/images/shared/buttons/canBuy";
//        }
//
//        ImageJButton buyBtn = new ImageJButton(buttonPath, ".png", 30, 90, 40);
//        buyBtn.setEnabled(isClickable);
//
//        // --- Logic การซื้อ ---
//        buyBtn.addActionListener(e -> {
//            if (controller.purchaseItem(item)) {
//                // ซื้อสำเร็จ: Refresh หน้าจอโดยสั่งลบแล้ววาดใหม่
//                MainFrame mf = controller.getMainFrame();
//                mf.getNavigator().toPage(MainFrame.SHOP_UI, false);
//            } else {
//                new PopupWindow().createPopup(
//                        controller.getMainFrame(),
//                        "Not enough money!",
//                        "resources/images/shared/popups/Demo.png",
//                        new String[]{"resources/images/shared/buttons/No"},
//                        new String[]{"No"},
//                        null
//                );
//            }
//        });
//
//        // --- Animation (Hover & Click) ---
//        buyBtn.addMouseListener(new MouseAdapter() {
//            public void mouseEntered(MouseEvent e) {
//                if (buyBtn.isEnabled()) buyBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
//            }
//            public void mousePressed(MouseEvent e) {
//                if (buyBtn.isEnabled()) {
//                    // เอฟเฟกต์กดบุ๋มลงไป (เขยิบ Margin)
//                    buyBtn.setBorder(BorderFactory.createEmptyBorder(4, 4, 0, 0));
//                }
//            }
//            public void mouseReleased(MouseEvent e) {
//                buyBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//            }
//        });
//
//        card.add(buyBtn, BorderLayout.EAST);
//        return card;
//    }
//}

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
        setBackground(new Color(245, 245, 245));

        // --- 1. ส่วนหัว (North) ---
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setOpaque(false);
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton backBtn = new BackBtn(gm.getMainFrame(), MainFrame.MAIN_MENU);
        northPanel.add(backBtn, BorderLayout.WEST);

        MoneyDisplay moneyPanel = new MoneyDisplay(gm.getTotalMoney());
        northPanel.add(moneyPanel, BorderLayout.EAST);
        add(northPanel, BorderLayout.NORTH);

        // --- 2. ส่วนรายการ (Center) ---
        // ใช้ FlowLayout เพื่อให้ Card ไม่ขยายจนเต็มหน้าจอ และปรับ Gap ให้พอดี
        JPanel gridPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        gridPanel.setOpaque(false);
        // ปรับ Padding ด้านข้างเพื่อบีบให้ Card เรียงตัวสวยๆ และไม่เกิด Scroll แนวนอน
        gridPanel.setPreferredSize(new Dimension(750, 1000));

        for (UpgradeItem item : gm.getAvailableItems()) {
            gridPanel.add(createItemCard(item));
        }

        // ตั้งค่า ScrollPane ให้เลื่อนได้เฉพาะแนวตั้ง (อยู่ด้านขวา)
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createItemCard(UpgradeItem item) {
        // ปรับขนาด Card ให้เล็กลงและเพรียวขึ้น
        JPanel card = new JPanel(new BorderLayout(15, 0));
        card.setPreferredSize(new Dimension(320, 80));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // [West] รูปวัตถุดิบ (ขนาดเล็กลงเล็กน้อยให้สมดุล)
        ImageIcon itemIcon = utilities.IconImage.create(item.getImagePath(), 50, 50);
        JLabel imageLabel = new JLabel(itemIcon);
        card.add(imageLabel, BorderLayout.WEST);

        // [Center] ชื่อและราคา (จัดให้ชิดกัน)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        Font jerseyFont = utilities.FontLoader.loadCustomFont("resources/font/Jersey10.ttf");

        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setFont(jerseyFont.deriveFont(Font.BOLD, 20f));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel priceLabel = new JLabel(item.getPrice() + " N");
        priceLabel.setFont(jerseyFont.deriveFont(16f));
        priceLabel.setForeground(Color.GRAY);
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        infoPanel.add(Box.createVerticalGlue());
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(-5)); // ดึงราคาให้ขึ้นมาติดชื่อมากขึ้น
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalGlue());

        card.add(infoPanel, BorderLayout.CENTER);

        // [East] ปุ่ม BUY
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

        // ปรับขนาดปุ่มให้เล็กลงตาม Card
        ImageJButton buyBtn = new ImageJButton(buttonPath, ".png", 25, 75, 30);
        buyBtn.setEnabled(isClickable);

        buyBtn.addActionListener(e -> {
            if (controller.purchaseItem(item)) {
                controller.getMainFrame().getNavigator().toPage(MainFrame.SHOP_UI, false);
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

        // Animation เอฟเฟกต์กด
        buyBtn.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (buyBtn.isEnabled()) buyBtn.setBorder(BorderFactory.createEmptyBorder(3, 3, 0, 0));
            }
            public void mouseReleased(MouseEvent e) {
                buyBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            }
        });

        card.add(buyBtn, BorderLayout.EAST);
        return card;
    }
}