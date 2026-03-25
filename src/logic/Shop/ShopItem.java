package logic.Shop;

public class ShopItem {

    private final String name;
    private final int price;
    private final String imagePath;
    private final int levelRequired;

    public ShopItem(String name, int price, String imagePath, int levelRequired) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.levelRequired = levelRequired;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getLevelRequired() {
        return levelRequired;
    }
}