package logic.GamePlay;

public class LevelConfig {

    private int levelID;
    private int timeLimit;
    private int customerCount;
    private int targetMoney;

    public LevelConfig(int levelID, int timeLimit, int customerCount, int targetMoney) {
        this.levelID = levelID;
        this.timeLimit = timeLimit;
        this.customerCount = customerCount;
        this.targetMoney = targetMoney;
    }

    public int getLevelID() {
        return levelID;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public int getTargetMoney() {
        return targetMoney;
    }
}
