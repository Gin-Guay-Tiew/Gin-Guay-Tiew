package utilities;

import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class SoundManager {

    private static Thread musicThread;
    private static Player player;
    private static String currentMusic = null;
    private static boolean isLevelMusic = false;

    private static synchronized void playMusic(String path) {

        if (path.equals(currentMusic)) return;

        stopMusic();

        currentMusic = path;
        System.out.println("PLAY MUSIC: " + path);
        System.out.println("PLAY MUSIC FROM: " + Thread.currentThread().getStackTrace()[3]);

        musicThread = new Thread(() -> {
            try {
                while (path.equals(currentMusic)) {
                    FileInputStream fis = new FileInputStream(path);
                    player = new Player(fis);
                    player.play();
                }
            } catch (Exception ignored) {}
        });

        musicThread.start();
    }

    public static synchronized void stopMusic() {
        try {
            if (player != null) {
                player.close();
                player = null;
            }

            if (musicThread != null) {
                musicThread.interrupt();
                musicThread = null;
            }

        } catch (Exception ignored) {}

        currentMusic = null;
    }

    // ================= MENU BACKGROUND =================

    public static void playMenuBackground() {
        if (isLevelMusic) return;
        playMusic("resources/audio_bg/background1.mp3");
    }

    // ================= LEVEL =================

    public static void playLevelMusic(int level) {

        isLevelMusic = true;

        switch(level) {
            case 1 -> playMusic("resources/audio_bg/lv1.mp3");
            case 2 -> playMusic("resources/audio_bg/lv2.mp3");
            case 3 -> playMusic("resources/audio_bg/lv3.mp3");
            case 4 -> playMusic("resources/audio_bg/lv4.mp3");
            case 5 -> playMusic("resources/audio_bg/lv5.mp3");
        }

    }

    // ================= OTHER =================

    public static void playWaiting() {
        playMusic("resources/audio_bg/waitting.mp3");
    }

    public static void playWin() {
        playMusic("resources/audio_bg/win.mp3");
    }

    public static void playLose() {
        playMusic("resources/audio_bg/lose.mp3");
    }

    public static void backToMenu() {

        isLevelMusic = false;
        playMenuBackground();

    }
}