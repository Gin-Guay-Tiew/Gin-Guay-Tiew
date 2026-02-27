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
        // 1. สร้าง Card และตั้งขนาดให้พอดี (ตามภาพคือ 2 คอลัมน์)
        JPanel card = new JPanel(new BorderLayout(15, 0));
        card.setPreferredSize(new Dimension(350, 100)); // กำหนดขนาด Card
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // 2. ส่วนซ้าย: รูปภาพไอเทม (ตาม Wireframe)
        // ใช้ IconImage ที่คุณมีในโปรเจกต์
        ImageIcon itemIcon = utilities.IconImage.create(item.getImagePath(), 60, 60);
        JLabel imageLabel = new JLabel(itemIcon);
        imageLabel.setPreferredSize(new Dimension(60, 60));
        card.add(imageLabel, BorderLayout.WEST);

        // 3. ส่วนกลาง: ชื่อและราคา (ใช้ Font Jersey10 ให้เหมือนส่วนอื่น)
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18)); // หรือใช้ FontLoader ของคุณ

        JLabel priceLabel = new JLabel(item.getPrice() + " N");
        priceLabel.setForeground(new Color(100, 100, 100));

        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);
        card.add(infoPanel, BorderLayout.CENTER);

        // 4. ส่วนขวา: ปุ่ม BUY (ใช้ ImageJButton เพื่อความสวยงาม)
        // เปลี่ยนจาก JButton ธรรมดา เป็น ImageJButton เหมือนที่หน้า MainMenu ใช้
        JButton buyBtn = new ui.components.ImageJButton(
                "resources/images/shared/buttons/Yes", ".png",
                25, 80, 35
        );

        // เช็คเงิน (ถ้าเงินไม่พอ ให้ปุ่มดูจางลงหรือใช้ Filter)
        if (controller.getTotalMoney() < item.getPrice()) {
            buyBtn.setEnabled(false); // หรือเปลี่ยนสีตาม Logic เดิม
        }

        buyBtn.addActionListener(e -> {
            if (controller.purchaseItem(item)) {
                // ซื้อสำเร็จ: Refresh
                Window win = SwingUtilities.getWindowAncestor(this);
                if (win instanceof MainFrame) {
                    ((MainFrame) win).getNavigator().toPage(MainFrame.SHOP_UI, false);
                }
            } else {
                new PopupWindow().createPopup(
                        controller.getMainFrame(),
                        "Not enough money!",
                        "resources/images/shared/popups/Demo.png",
                        new String[]{"resources/images/shared/buttons/Ok"},
                        new String[]{"No"},
                        null
                );
            }
        });

        card.add(buyBtn, BorderLayout.EAST);
        return card;
    }
}