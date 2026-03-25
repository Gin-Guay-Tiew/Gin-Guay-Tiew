package logic;

public class Player {
    private int Noodle;
    private int LevelUnlocked;

    public Player() {
        this.Noodle = 0;
        this.LevelUnlocked = 1;
    }

    public int getNoodle() {
        return Noodle;
    }

    public int getLevelUnlocked() {
        return LevelUnlocked;
    }

    public void setNoodle(int Noodle) {
        this.Noodle = Noodle;
    }

    public void setLevelUnlocked(int LevelUnlocked) {
        this.LevelUnlocked = LevelUnlocked;
    }
}