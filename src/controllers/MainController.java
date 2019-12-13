package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import models.Sound;

public class MainController {
    @FXML private ImageView soundImage;
    @FXML private GridPane playerscards;
    @FXML private Button musicBtn;
    @FXML private HBox countdownTimer;
    @FXML private CountdownTimerController countdownTimerController;

    private Sound backgroundMusic;
    private Sound btnSound;
    private boolean enableSound;
    private Image soundOnImg;
    private Image soundOffImg;

    @FXML private void initialize(){
        countdownTimerController.injectMainController(this);
        soundImage.setImage(soundOnImg);
    }

    public MainController(){
        enableSound = true;
        soundOnImg = new Image("../images/sound_on.jpg");
        soundOffImg = new Image("../images/sound_off.jpg");
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
