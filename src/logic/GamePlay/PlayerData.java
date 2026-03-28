package logic.GamePlay;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Set;

public class PlayerData {

    private int money = 0;
    private int level = 1;
    private int volumeLv = 100;
    private boolean stateSFX = true;

    private Set<String> unlockedItems = new HashSet<>();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public PlayerData() {

        unlockedItems.add("Yellow egg noodles");
        unlockedItems.add("Thin rice noodles");
        unlockedItems.add("Cola");
        unlockedItems.add("Meatball");
        unlockedItems.add("Vegetable");

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public int getMoney() {
        return money;
    }

    public int getLevel() {
        return level;
    }

    public int getVolumeLv() {
        return volumeLv;
    }

    public boolean isStateSFX() {
        return stateSFX;
    }

    public Set<String> getUnlockedItems() {
        return new HashSet<>(unlockedItems);
    }

    public boolean isItemUnlocked(String itemName) {
        return unlockedItems.contains(itemName);
    }

    public void setMoney(int newMoney) {
        int oldMoney = this.money;
        this.money = newMoney;
        support.firePropertyChange("money", oldMoney, this.money);
    }

    public void spendMoney(int amount) {
        setMoney(this.money - amount);
    }

    public void addMoney(int amount) {
        setMoney(this.money + amount);
    }

    public void setLevel(int level) {
        int oldLevel = this.level;
        this.level = level;
        support.firePropertyChange("level", oldLevel, this.level);
    }

    public void setVolumeLv(int volumeLv) {
        this.volumeLv = volumeLv;
    }

    public void setStateSFX(boolean stateSFX) {
        this.stateSFX = stateSFX;
    }

    public void unlockItem(String itemName) {
        if (!unlockedItems.contains(itemName)) {
            unlockedItems.add(itemName);
            support.firePropertyChange("unlockedItems", null, itemName);
        }
    }

    public void setUnlockedItems(Set<String> items) {
        this.unlockedItems = new HashSet<>(items);
    }
}