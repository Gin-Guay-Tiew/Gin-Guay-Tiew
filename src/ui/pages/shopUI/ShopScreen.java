package ui.pages.shopUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// เปลี่ยนจาก GameManager เป็น GameController ตามชื่อไฟล์จริงในโฟลเดอร์ logic
import logic.GameController;
// คุณต้องสร้างคลาสนี้เพิ่มใน package logic ด้วยนะครับ
//import logic.UpgradeItem;

import ui.components.BackBtn;
import ui.components.MoneyDisplay;
import ui.components.PopupWindow;

public class ShopScreen extends JPanel {
    // ใช้ชื่อคลาสที่ถูกต้อง
    private GameController controller;

    public ShopScreen(GameController gm) {
        this.controller = gm;
        setLayout(new BorderLayout());

        // 1. ส่วนบน (North)
        JPanel northPanel = new JPanel(new BorderLayout());

        // ตรวจสอบว่าคลาส BackBtn ของเพื่อนรับ parameter อะไรไหม
        // ถ้า error ตรง new BackBtn() ให้ลองเช็ค constructor ในไฟล์นั้นดูครับ
        JButton backBtn = new BackBtn();

        northPanel.add(backBtn, BorderLayout.WEST);

        // ตรวจสอบใน GameController ว่าชื่อ method คือ getTotalMoney() หรือไม่
        northPanel.add(new MoneyDisplay(gm.getTotalMoney()), BorderLayout.EAST);
        add(northPanel, BorderLayout.NORTH);

        // 2. ส่วนกลาง (Center)
        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        // ต้องมั่นใจว่าใน GameController มี method getAvailableItems() ที่คืนค่าเป็น List<UpgradeItem>
        for (UpgradeItem item : gm.getAvailableItems()) {
            gridPanel.add(createItemCard(item));
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createItemCard(UpgradeItem item) {
        JPanel card = new JPanel();
        // ต้องมั่นใจว่าใน UpgradeItem มี method getPrice()
        JButton buyBtn = new JButton("BUY " + item.getPrice() + " N");

        if (controller.getTotalMoney() < item.getPrice()) {
            buyBtn.setBackground(Color.RED);
        }

        buyBtn.addActionListener(e -> {
            // ต้องมั่นใจว่าใน GameController มี method purchaseItem(UpgradeItem i)
            if (controller.purchaseItem(item)) {
                // อย่าลืมสร้าง method updateShopContent() เพื่อวาดหน้าจอใหม่หลังซื้อ
                revalidate();
                repaint();
            } else {
                new PopupWindow("Not enough money").setVisible(true);
            }
        });
        return card;
    }
}