package logic.GamePlay;

public class LevelFactory {

    public static LevelConfig getLevel(int levelID){

        switch(levelID){

            case 1:
                return new LevelConfig(
                        1,
                        60,  // seconds
                        3,   // customers
                        200  // target money
                );

            case 2:
                return new LevelConfig(
                        2,
                        60,
                        4,
                        350
                );

            case 3:
                return new LevelConfig(
                        3,
                        70,
                        5,
                        500
                );

        }

        return new LevelConfig(1,60,3,200);
    }
}
