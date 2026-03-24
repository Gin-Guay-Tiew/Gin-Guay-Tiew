package logic;

import main.MainFrame;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private MainFrame mainFrame;
    private int totalMoney = 300; // ลองตั้งเงินเยอะๆ ไว้ทดสอบ

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
        items.add(new UpgradeItem("Rice vermicelli noodles", 150, "resources/images/gamePlay/ingredients/noodles/category/riceVermicelli/idle.png", true));
        items.add(new UpgradeItem("Yellow egg noodles", 150, "resources/images/gamePlay/ingredients/noodles/category/yellowEgg/idle.png", true));
        items.add(new UpgradeItem("Vegetable", 150, "resources/images/gamePlay/ingredients/vegetables/picked.png", true));
        items.add(new UpgradeItem("Cola", 150, "resources/images/gamePlay/ingredients/drinks/picked/cola.png", true));
        items.add(new UpgradeItem("Orange", 150, "resources/images/gamePlay/ingredients/drinks/picked/orange.png", true));
        items.add(new UpgradeItem("Sprite", 150, "resources/images/gamePlay/ingredients/drinks/picked/sprite.png", true));
        items.add(new UpgradeItem("Kanom tuay", 150, "resources/images/gamePlay/ingredients/kanomTuay/idle.png", true));
        items.add(new UpgradeItem("Meatball", 150, "resources/images/gamePlay/ingredients/addOn/meatball/idle.png", true));
        items.add(new UpgradeItem("Pork rind", 150, "resources/images/gamePlay/ingredients/addOn/porkRind/idle.png", true));
        items.add(new UpgradeItem("Pork slices", 150, "resources/images/gamePlay/ingredients/addOn/porkSlices/idle.png", true));
        return items;
    }

    private int playerLevel = 1;

    public int getPlayerLevel() {
        return playerLevel;
    }

    public boolean isItemUnlocked(String itemName) {
        int level = playerLevel;

        switch(itemName) {
            case "Thin rice noodles":
            case "Yellow egg noodles":
            case "Vegetable":
            case "Meatball":
            case "Cola":
                return level >= 1;

            case "Rice vermicelli noodles":
            case "Pork slices":
            case "Sprite":
                return level >= 2;

            case "Wide rice noodles":
            case "Orange":
                return level >= 3;

            case "Green egg noodles":
            case "Kanom tuay":
                return level >= 4;

            case "Pork rind":
                return level >= 5;

            default:
                return false;
        }
    }

    public boolean purchaseItem(UpgradeItem item) {
        if (totalMoney >= item.getPrice() && item.isUnlocked()) {
            totalMoney -= item.getPrice();
            return true;
        }
        return false;
    }
}