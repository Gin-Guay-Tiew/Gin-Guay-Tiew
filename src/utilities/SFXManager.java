package utilities;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class SFXManager {

    private static float volume = 0.35f;
    private static final float MASTER_VOLUME = 0.45f;
    private static boolean sfxEnabled = true;

    static {
        new JFXPanel();
    }

    public static void play(String path) {

        if (!sfxEnabled) return;

        try {

            URL url = SFXManager.class.getResource(path);

            System.out.println("LOOKING FOR: " + path);

            if (url == null) {
                System.out.println("❌ SFX NOT FOUND: " + path);
                return;
            }

            StackTraceElement[] stack = Thread.currentThread().getStackTrace();
            if (stack.length > 2) {
                StackTraceElement caller = stack[2];
                System.out.println(
                        "[SFX] " + path +
                                " | called from: " +
                                caller.getClassName() +
                                "." +
                                caller.getMethodName()
                );
            }

            Media media = new Media(url.toExternalForm());
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

    public static void setSfxEnabled(boolean enabled) {
        sfxEnabled = enabled;
    }
}