package models;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    private final String BUTTON_SOUND = "BUTTON_SOUND";
    private final String BACKGROUND_SOUND = "BACKGROUND_MUSIC";

    private Clip sound;

    public Sound(String handleType){
        if (handleType.equals(BUTTON_SOUND)){
            try {
                AudioInputStream soundInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("../resources/sound/button_sound.wav"));
                sound = AudioSystem.getClip();
                sound.open(soundInputStream);
            }catch(Exception e){System.out.println("Failure to load sound");}
        } else {

        }
    }

    public void makeBtnSound(){
        sound.setFramePosition(0);
        sound.start();
    }
}
