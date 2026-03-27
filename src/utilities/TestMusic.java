package utilities;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class TestMusic {

    public static void main(String[] args) {

        new JFXPanel(); // start JavaFX

        String path = "resources/audio_bg/background1.mp3";

        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);

        player.setVolume(0.2);

        player.play();

        System.out.println("Playing...");

    }

}