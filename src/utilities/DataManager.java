package utilities;

import logic.Player;

import java.io.*;
import java.util.Properties;

public class DataManager {
    private static final String SAVE_FILE = "saveData.dat";

    public static void savePlayerData(Player player) {
        Properties props = new Properties();
        props.setProperty("noodle", String.valueOf(player.getNoodle()));
        props.setProperty("levelUnlocked", String.valueOf(player.getLevelUnlocked()));

        try (FileOutputStream out = new FileOutputStream(SAVE_FILE)) {
            props.store(out, "Player Progress");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Player loadPlayerData() {
        Player player = new Player();
        player.setNoodle(5000);
        Properties props = new Properties();

        File file = new File(SAVE_FILE);
        if (!file.exists()) return player;

        try (FileInputStream in = new FileInputStream(SAVE_FILE)) {
            props.load(in);
            player.setNoodle(Integer.parseInt(props.getProperty("noodle", "0")));
            player.setLevelUnlocked(Integer.parseInt(props.getProperty("levelUnlocked", "1")));
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return player;
    }
}