package logic.GamePlay;

import logic.GamePlay.LevelConfig;
import logic.GamePlay.LevelFactory;

public class GameController {

    private LevelConfig levelConfig;
    private int currentMoney = 0;

    public GameController(int levelID){
        levelConfig = LevelFactory.getLevel(levelID);
    }

    public int getTargetMoney(){
        return levelConfig.getTargetMoney();
    }

    public int getTimeLimit(){
        return levelConfig.getTimeLimit();
    }

    public int getCustomerCount(){
        return levelConfig.getCustomerCount();
    }

    public void addMoney(int money){
        currentMoney += money;
    }

    public int getCurrentMoney(){
        return currentMoney;
    }

}
