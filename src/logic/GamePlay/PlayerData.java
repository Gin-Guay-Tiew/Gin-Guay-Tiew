package logic.GamePlay;

import ui.components.MoneyDisplay;

public class PlayerData {

    private int money = 300;
    private int level = 1;
    private MoneyDisplay moneyDisplay;

    public int getMoney() {
        return money;
    }

    public int getLevel() {
        return level;
    }

    public MoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }

    public void setMoney(int money) {
        this.money = money;
        if (this.moneyDisplay != null) {
            this.moneyDisplay.updateMoney(this.money);
        }
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMoneyDisplay(MoneyDisplay display) {
        this.moneyDisplay = display;
    }

    public void spendMoney(int amount) {
        money -= amount;
        if (this.moneyDisplay != null) {
            this.moneyDisplay.updateMoney(this.money);
        }
    }

}