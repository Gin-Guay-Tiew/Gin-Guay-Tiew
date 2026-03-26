package logic.GamePlay;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayerData {
    private int money = 0;
    private int level = 1;
    private int volumeLv = 100;
    private boolean stateSFX = true;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

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
}