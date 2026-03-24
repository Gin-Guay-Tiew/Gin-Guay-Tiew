package logic.GamePlay;

public class PlayerData {

    private int money = 300;
    private int level = 1;

    public int getMoney() {
        return money;
    }

    public int getLevel() {
        return level;
    }

    public void spendMoney(int amount) {
        money -= amount;
    }

}