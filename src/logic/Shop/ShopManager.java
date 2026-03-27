package logic.Shop;

import logic.GamePlay.PlayerData;
import main.MainFrame;

import java.util.ArrayList;
import java.util.List;

public class ShopManager {

    private PlayerData player;
    private MainFrame mainFrame;

    public ShopManager(MainFrame frame, PlayerData player) {
        this.mainFrame = frame;
        this.player = player;
    }

    public boolean isItemStageReached(ShopItem item) {
        return player.getLevel() >= item.getLevelRequired();
    }

    public boolean isItemPurchased(String name) {
        return player.isItemUnlocked(name);
    }

    public boolean purchaseItem(ShopItem item) {
        if (isItemPurchased(item.getName())) {
            return false;
        }

        if (!isItemStageReached(item)) {
            return false;
        }

        if (player.getMoney() < item.getPrice()) {
            return false;
        }

        player.spendMoney((int) item.getPrice());
        player.unlockItem(item.getName());
        return true;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public int getTotalMoney() {
        return player.getMoney();
    }

    public List<ShopItem> getAvailableItems() {
        return getItems();
    }

    public List<ShopItem> getItems() {
        List<ShopItem> items = new ArrayList<>();

        items.add(new ShopItem("Thin rice noodles", 100, "resources/images/gamePlay/ingredients/noodles/category/thinRice/idle.png", 1));
        items.add(new ShopItem("Wide rice noodles", 200, "resources/images/gamePlay/ingredients/noodles/category/wideRice/idle.png", 3));
        items.add(new ShopItem("Green egg noodles", 250, "resources/images/gamePlay/ingredients/noodles/category/greenEgg/idle.png", 4));
        items.add(new ShopItem("Rice vermicelli noodles", 150, "resources/images/gamePlay/ingredients/noodles/category/riceVermicelli/idle.png", 2));
        items.add(new ShopItem("Yellow egg noodles", 100, "resources/images/gamePlay/ingredients/noodles/category/yellowEgg/idle.png", 1));
        items.add(new ShopItem("Vegetable", 100, "resources/images/gamePlay/ingredients/vegetables/picked.png", 1));
        items.add(new ShopItem("Cola", 100, "resources/images/gamePlay/ingredients/drinks/cola/cola.png", 1));
        items.add(new ShopItem("Orange", 200, "resources/images/gamePlay/ingredients/drinks/orange/orange.png", 3));
        items.add(new ShopItem("Sprite", 150, "resources/images/gamePlay/ingredients/drinks/sprite/sprite.png", 2));
        items.add(new ShopItem("Kanom tuay", 250, "resources/images/gamePlay/ingredients/kanomTuay/idle.png", 4));
        items.add(new ShopItem("Meatball", 100, "resources/images/gamePlay/ingredients/addOn/meatball/idle.png", 1));
        items.add(new ShopItem("Pork rind", 300, "resources/images/gamePlay/ingredients/addOn/porkRind/idle.png", 5));
        items.add(new ShopItem("Pork slices", 150, "resources/images/gamePlay/ingredients/addOn/porkSlices/idle.png", 2));

        return items;
    }
}