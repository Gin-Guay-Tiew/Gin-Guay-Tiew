package utilities;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
import java.io.File;

public class TestSound {

    public static void main(String[] args) {

        new JFXPanel(); // start JavaFX

        Media media = new Media(
                new File("resources/audio/sfx/click_button.mp3")
                        .toURI()
                        .toString()
        );

        MediaPlayer player = new MediaPlayer(media);
        player.play();
    }
}
