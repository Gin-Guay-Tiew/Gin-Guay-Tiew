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
        items.add(new UpgradeItem("Thin rice noodles", 50, "resources/images/gamePlay/ingredients/noodles/category/thinRice/picked.png", true));
        items.add(new UpgradeItem("Wide rice noodles", 200, "resources/images/gamePlay/ingredients/noodles/category/wideRice/picked.png", false));
        items.add(new UpgradeItem("Green egg noodles", 150, "resources/images/gamePlay/ingredients/noodles/category/greenEgg/picked.png", true));
        items.add(new UpgradeItem("Rice vermicelli noodles", 150, "resources/images/gamePlay/ingredients/noodles/category/riceVermicelli/picked.png", true));
        items.add(new UpgradeItem("Yellow egg noodles", 150, "resources/images/gamePlay/ingredients/noodles/category/yellowEgg/picked.png", true));
        items.add(new UpgradeItem("Vegetable", 150, "resources/images/gamePlay/ingredients/vegetables/picked.png", true));
        items.add(new UpgradeItem("Cola", 150, "resources/images/gamePlay/ingredients/drinks/picked/cola.png", true));
        items.add(new UpgradeItem("Orange", 150, "resources/images/gamePlay/ingredients/drinks/picked/orange.png", true));
        items.add(new UpgradeItem("Sprite", 150, "resources/images/gamePlay/ingredients/drinks/picked/sprite.png", true));
        items.add(new UpgradeItem("Kanom tuay", 150, "resources/images/gamePlay/ingredients/kanomTuay/picked.png", true));
        items.add(new UpgradeItem("Meatball", 150, "resources/images/gamePlay/ingredients/addOn/meatball/picked.png", true));
        items.add(new UpgradeItem("Pork rind", 150, "resources/images/gamePlay/ingredients/addOn/porkRind/picked.png", true));
        items.add(new UpgradeItem("Pork slices", 150, "resources/images/gamePlay/ingredients/addOn/porkSlices/picked.png", true));
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