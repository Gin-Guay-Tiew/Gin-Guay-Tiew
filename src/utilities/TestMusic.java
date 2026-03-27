package utilities;

import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class TestMusic {

    public static void main(String[] args) {

        try {

            FileInputStream fis = new FileInputStream("resources/audio_bg/background1.mp3");
            Player player = new Player(fis);

            player.play();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
