package logic;

public class UpgradeItem {
    private String name;
    private int price;
    private String imagePath;
    private boolean isUnlocked; // เพิ่มตัวแปรสถานะ
    private int levelRequired;  // (เผื่อไว้) ถ้าอยากให้ปลดตามเลเวล

    public UpgradeItem(String name, int price, String imagePath, boolean isUnlocked) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.isUnlocked = isUnlocked; // กำหนดค่าเริ่มต้น
    }

    // เพิ่ม Getter สำหรับเช็คสถานะ
    public boolean isUnlocked() {
        return isUnlocked;
    }

    // เพิ่ม Setter ไว้สำหรับเปลี่ยนสถานะเมื่อเลเวลถึงหรือซื้อแล้ว
    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getImagePath() { return imagePath; }
}