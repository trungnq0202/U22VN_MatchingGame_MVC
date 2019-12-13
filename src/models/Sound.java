package models;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    private final String BUTTON_SOUND = "BUTTON_SOUND";
    private final String BACKGROUND_MUSIC = "BACKGROUND_MUSIC";
    private final String CARDS_FLIPPING_SOUND = "CARD_FLIPPING_SOUND";

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
        } else if (handleType.equals(BACKGROUND_MUSIC)) {
            try {
                URL resource = getClass().getResource("../resources/sound/backgroundsong.mp3");
                backgroundMusic = new MediaPlayer(new Media(resource.toString()));
                backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
                backgroundMusic.setVolume(0.1);
                backgroundMusic.play();

            }catch (Exception e){
                System.out.println("Cannot load background music");
            }
        } else {
            try {
                AudioInputStream soundInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("../resources/sound/cardflip_sound.wav"));
                sound = AudioSystem.getClip();
                sound.open(soundInputStream);
            }catch(Exception e){
                System.out.println("Cannot load card flip sound");
            }
        }
    }

    public void makeSound(){
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
