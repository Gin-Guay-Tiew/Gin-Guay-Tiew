package logic;

import main.MainFrame; // import เพิ่ม
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private MainFrame mainFrame; // เก็บอ้างอิงของหน้าต่างหลักไว้
    private int totalMoney = 1000;

    // Constructor รับค่า MainFrame มาเก็บไว้
    public GameController(MainFrame frame) {
        this.mainFrame = frame;
    }

    // สร้าง Method นี้เพื่อให้หน้า Shop ดึง MainFrame ไปใช้กับปุ่ม Back และ Popup
    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public int getTotalMoney() { return totalMoney; }

    public List<UpgradeItem> getAvailableItems() {
        List<UpgradeItem> items = new ArrayList<>();
        items.add(new UpgradeItem("เส้นเล็ก", 50, "resources/images/loadingScreen/Money.png"));
        return items;
    }

    public boolean purchaseItem(UpgradeItem item) {
        if (totalMoney >= item.getPrice()) {
            totalMoney -= item.getPrice();
            return true;
        }
        return false;
    }
}