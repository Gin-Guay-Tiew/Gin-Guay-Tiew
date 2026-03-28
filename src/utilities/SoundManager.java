package utilities;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class SoundManager {

    private static MediaPlayer player;
    private static String currentMusic = null;
    private static boolean isLevelMusic = false;

    private static float volume = 0.35f;
    private static final float MASTER_VOLUME = 0.45f;

    static {
        new JFXPanel(); // start JavaFX
    }

    private static synchronized void playMusic(String path) {

        if (path.equals(currentMusic)) return;

        stopMusic();

        try {

            URL url = SoundManager.class.getResource(path);

            if (url == null) {
                throw new RuntimeException("AUDIO NOT FOUND: " + path);
            }

            Media media = new Media(url.toExternalForm());

            player = new MediaPlayer(media);
            player.setCycleCount(MediaPlayer.INDEFINITE); // loop
            player.setVolume(volume * MASTER_VOLUME);

            player.play();

            currentMusic = path;

            System.out.println("PLAY MUSIC: " + path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void stopMusic() {

        try {

            if (player != null) {
                player.stop();
                player.dispose();
                player = null;
            }

        } catch (Exception ignored) {}

        currentMusic = null;
    }

    // ================= MENU BACKGROUND =================

    public static void playMenuBackground() {

//        if (isLevelMusic) return;

        playMusic("/audio/bg/background1.mp3");

    }

    // ================= LEVEL =================

    public static void playLevelMusic(int level) {

        isLevelMusic = true;

        switch(level) {
            case 1 -> playMusic("/audio/bg/lv1.mp3");
            case 2 -> playMusic("/audio/bg/lv2.mp3");
            case 3 -> playMusic("/audio/bg/lv3.mp3");
            case 4 -> playMusic("/audio/bg/lv4.mp3");
            case 5 -> playMusic("/audio/bg/lv5.mp3");
        }

    }

    // ================= OTHER =================

    public static void playWaiting() {
        isLevelMusic = false;
        playMusic("/audio/bg/waitting.mp3");
    }

    public static void playWin() {
        isLevelMusic = false;
        playMusic("/audio/bg/win.mp3");
    }

    public static void playLose() {
        isLevelMusic = false;
        playMusic("/audio/bg/lose.mp3");
    }

    public static void backToMenu() {

        System.out.println("BACK TO MENU");
        isLevelMusic = false;
        playMenuBackground();

    }

    // ================= VOLUME =================

    public static void setVolume(float v) {
        float linearVolume = Math.max(0f, Math.min(1f, v));

        volume = linearVolume * linearVolume;

        if (player != null) {
            player.setVolume(volume * MASTER_VOLUME);
        }
    }

    public static float getVolume() {
        return volume;
    }
}