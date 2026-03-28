package utilities;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SFXManager {

    private static float volume = 0.35f;
    private static final float MASTER_VOLUME = 0.45f;

    static {
        new JFXPanel();
    }

    public static void play(String path) {

        try {

            File file = new File(path);

            System.out.println("LOOKING FOR: " + file.getAbsolutePath());

            if (!file.exists()) {
                System.out.println("❌ SFX NOT FOUND: " + path);
                return;
            }

            Media media = new Media(file.toURI().toString());
            MediaPlayer player = new MediaPlayer(media);

            player.setVolume(volume * MASTER_VOLUME);

            player.setOnEndOfMedia(player::dispose);

            player.play();

            System.out.println("PLAY SFX: " + path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setVolume(float v) {
        volume = Math.max(0f, Math.min(1f, v));
    }

    public static float getVolume() {
        return volume;
    }
}