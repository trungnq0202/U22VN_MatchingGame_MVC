package models;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    private final String BUTTON_SOUND = "BUTTON_SOUND";
    private final String BACKGROUND_SOUND = "BACKGROUND_MUSIC";

    private Clip sound;
    private MediaPlayer backgroundMusic ;

    public Sound(String handleType){
        if (handleType.equals(BUTTON_SOUND)){
            try {
                AudioInputStream soundInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("../resources/sound/button_sound.wav"));
                sound = AudioSystem.getClip();
                sound.open(soundInputStream);
            }catch(Exception e){
                System.out.println("Cannot load button sound");
            }
        } else {
            try {
                URL resource = getClass().getResource("../resources/sound/backgroundsong.mp3");
                backgroundMusic = new MediaPlayer(new Media(resource.toString()));
                backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
                backgroundMusic.play();
            }catch (Exception e){
                System.out.println("Cannot load background music");
                System.out.println(e);
            }
        }
    }

    public void makeBtnSound(){
        sound.setFramePosition(0);
        sound.start();
    }

    public void pauseBackgroundMusic(){
        backgroundMusic.pause();
    }

    public void resumeBackgroundMusic(){
        backgroundMusic.play();
    }
}
