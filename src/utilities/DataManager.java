package utilities;

import logic.GamePlay.PlayerData;
import java.io.*;

public class DataManager {
    private static final String SAVE_FILE = "saveData.dat";

    public static void savePlayerData(PlayerData player) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(SAVE_FILE))) {
            dos.writeInt(player.getMoney()); // Save money
            dos.writeInt(player.getLevel()); // Save level
            dos.writeInt(player.getVolumeLv()); // Save volume
            dos.writeBoolean(player.isStateSFX()); // Save SFX toggle
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PlayerData loadPlayerData() {
        PlayerData player = new PlayerData();
        File file = new File(SAVE_FILE);

        if (!file.exists()) return player;

        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            player.setMoney(dis.readInt());
            player.setLevel(dis.readInt());
            player.setVolumeLv(dis.readInt());
            player.setStateSFX(dis.readBoolean());
        } catch (IOException e) {
            System.err.println("Could not load save data. Starting fresh.");
        }
        return player;
    }
}