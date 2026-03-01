package logic;

import main.MainFrame;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private MainFrame mainFrame;
    private int totalMoney = 2000; // ลองตั้งเงินเยอะๆ ไว้ทดสอบ

    public GameController(MainFrame frame) {
        this.mainFrame = frame;
    }

    public MainFrame getMainFrame() { return mainFrame; }
    public int getTotalMoney() { return totalMoney; }

    public List<UpgradeItem> getAvailableItems() {
        List<UpgradeItem> items = new ArrayList<>();
        // (ชื่อ, ราคา, Path รูป, ปลดล็อคหรือยัง)
        items.add(new UpgradeItem("Thin rice noodles", 50, "resources/images/gamePlay/ingredients/noodles/category/thinRice/idle.png", true));
        items.add(new UpgradeItem("Wide rice noodles", 200, "resources/images/gamePlay/ingredients/noodles/category/wideRice/idle.png", false));
        items.add(new UpgradeItem("Green egg noodles", 150, "resources/images/gamePlay/ingredients/noodles/category/greenEgg/idle.png", true));
        return items;
    }

    public boolean purchaseItem(UpgradeItem item) {
        if (totalMoney >= item.getPrice() && item.isUnlocked()) {
            totalMoney -= item.getPrice();
            return true;
        }
        return false;
    }
}