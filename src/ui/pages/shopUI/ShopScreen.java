package ui.pages.shopUI;

import javax.swing.*;
import java.awt.*;
import logic.GameController;
import logic.UpgradeItem;
import ui.components.BackBtn;
import ui.components.MoneyDisplay;
import ui.components.PopupWindow;
import main.MainFrame; // อย่าลืม import MainFrame มาด้วย

public class ShopScreen extends JPanel {
    private GameController controller;

    public ShopScreen(GameController gm) {
        this.controller = gm;
        setLayout(new BorderLayout());

        // 1. ส่วนบน (North)
        JPanel northPanel = new JPanel(new BorderLayout());

        // แก้ไข: ส่งค่าตามที่ BackBtn ต้องการ
        JButton backBtn = new BackBtn(gm.getMainFrame(), MainFrame.MAIN_MENU);

        northPanel.add(backBtn, BorderLayout.WEST);

        // แก้ไข: ส่งค่าเงินจริงให้ MoneyDisplay
        northPanel.add(new MoneyDisplay(gm.getTotalMoney()), BorderLayout.EAST);
        add(northPanel, BorderLayout.NORTH);

        // 2. ส่วนกลาง (Center)
        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        for (UpgradeItem item : gm.getAvailableItems()) {
            gridPanel.add(createItemCard(item));
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createItemCard(UpgradeItem item) {
        // 1. กำหนดโครงสร้าง Card ตาม Wireframe (West: รูป, Center: ข้อมูล, East: ปุ่ม)
        JPanel card = new JPanel(new BorderLayout(20, 0));
        card.setPreferredSize(new Dimension(380, 110));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        // 2. ส่วนซ้าย (West): รูปภาพสินค้า
        // รูปสินค้าถูกดึงมาจาก item.getImagePath() ที่คุณตั้งค่าไว้ใน GameController
        ImageIcon itemIcon = utilities.IconImage.create(item.getImagePath(), 70, 70);
        JLabel imageLabel = new JLabel(itemIcon);
        card.add(imageLabel, BorderLayout.WEST);

        // 3. ส่วนกลาง (Center): ชื่อและราคา พร้อมใช้ Font Jersey10
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);

        // โหลด Font ภาษาไทยเพื่อให้ชื่อ "เส้นเล็ก" ไม่เป็นสี่เหลี่ยม
        Font jerseyFont = utilities.FontLoader.loadCustomFont("resources/font/Jersey10.ttf");

        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setFont(jerseyFont.deriveFont(Font.BOLD, 22f));

        JLabel priceLabel = new JLabel(item.getPrice() + " N");
        priceLabel.setFont(jerseyFont.deriveFont(18f));
        priceLabel.setForeground(new Color(120, 120, 120));

        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);
        card.add(infoPanel, BorderLayout.CENTER);

        // 4. ส่วนขวา (East): ปุ่ม BUY ตามสถานะเงินและเลเวล
        String buttonPath;
        boolean canClick = true;

        // ลอจิกเลือกรูปปุ่มตามที่คุณต้องการ:
        if (controller.getTotalMoney() < item.getPrice()) {
            // เลเวลถึงแต่ตังไม่พอ -> ใช้ปุ่มสีแดง (noMoneyBuy.png)
            buttonPath = "resources/images/shared/buttons/noMoneyBuy";
        } else {
            // ตังพอและปลดล็อคแล้ว -> ใช้ปุ่มปกติ (canBuy.png)
            buttonPath = "resources/images/shared/buttons/canBuy";
        }

        // หมายเหตุ: หากมีลอจิกเลเวล (Locked) ให้เช็คและใช้ "lockedBuy" สีเทา

        JButton buyBtn = new ui.components.ImageJButton(buttonPath, ".png", 30, 90, 40);

        buyBtn.addActionListener(e -> {
            if (controller.purchaseItem(item)) {
                // ซื้อสำเร็จ: Refresh หน้าเดิมเพื่ออัปเดตยอดเงิน
                MainFrame mf = controller.getMainFrame();
                mf.getNavigator().toPage(MainFrame.SHOP_UI, false);
            } else {
                // เงินไม่พอ: แสดง Popup ตามที่ออกแบบไว้
                new ui.components.PopupWindow().createPopup(
                        controller.getMainFrame(),
                        "Not enough money!",
                        "resources/images/shared/popups/Demo.png",
                        new String[]{"resources/images/shared/buttons/No"},
                        new String[]{"No"},
                        null
                );
            }
        });

        card.add(buyBtn, BorderLayout.EAST);
        return card;
    }
}