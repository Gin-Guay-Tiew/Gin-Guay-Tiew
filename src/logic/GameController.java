package logic;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    // จำลองเงินเริ่มต้น
    private int totalMoney = 1000;

    public int getTotalMoney() {
        return totalMoney;
    }

    public List<UpgradeItem> getAvailableItems() {
        List<UpgradeItem> items = new ArrayList<>();
        // ลองเพิ่มของเล่นๆ 1 ชิ้นดูว่าขึ้นไหม
        items.add(new UpgradeItem("เส้นเล็ก", 50, "resources/images/shared/items/noodle.png"));
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