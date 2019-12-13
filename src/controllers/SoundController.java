package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;

public class SoundController {

    @FXML private CountdownTimerController countdownTimerController;

    @FXML private PlayerCardsController cardsController;

    private AudioInputStream clipNameAIS;
    private Clip clipNameClip;

    public SoundController() throws IOException {
        try {
            clipNameAIS = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("../resources/sound/button_sound.wav"));
            clipNameClip = AudioSystem.getClip();
            clipNameClip.open(clipNameAIS);
        }catch(Exception e){System.out.println("Failure to load sound");}

        Parent countdownTimerView = FXMLLoader.load(getClass().getResource("resources/view_fxml/countdowntimer.fxml"));
        Node btnStartPauseBtn = countdownTimerView.lookup("#btnStartPauseTimer");

        btnStartPauseBtn.setOnMouseClicked((MouseEvent e) -> {
            clipNameClip.setFramePosition(0);
            clipNameClip.start();
        });
    }



    public void initialize() throws IOException {


    }

//    public EventHandler<MouseEvent> generateButtonSound(){
//        clipNameClip.setFramePosition(0);
//        clipNameClip.start();
//    }
}
