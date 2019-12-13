package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import models.Sound;

public class MainController {
    @FXML private GridPane playerscards;
    @FXML private Button musicBtn;
    @FXML private HBox countdownTimer;
    @FXML private CountdownTimerController countdownTimerController;

    private Sound backgroundMusic;
    private Sound btnSound;
    private boolean enableSound;

    @FXML private void initialize(){
        countdownTimerController.injectMainController(this);
    }

    public MainController(){
        enableSound = true;
        backgroundMusic = new Sound("BACKGROUND_MUSIC");
        btnSound = new Sound("BUTTON_SOUND");
    }

    public boolean getEnableSound(){
        return enableSound;
    }

    public void musicBtnHandle(MouseEvent mouseEvent) {
        btnSound.makeBtnSound();
        if (enableSound){
            enableSound = false;
            backgroundMusic.pauseBackgroundMusic();
        }else{
            enableSound = true;
            backgroundMusic.resumeBackgroundMusic();
        }
    }

}
