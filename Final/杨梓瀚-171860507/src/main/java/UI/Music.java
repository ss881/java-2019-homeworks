package UI;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
    private MediaPlayer startMediaPlayer;

    private static final String filename = "resources/music/background.mp3";

    public Music(){
        startMediaPlayer = new MediaPlayer(new Media(filename));
    }

    public void start(){
        startMediaPlayer.setAutoPlay(true);
    }
}
