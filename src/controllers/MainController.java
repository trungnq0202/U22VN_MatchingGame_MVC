package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import models.Sound;

public class MainController {
    @FXML private ImageView soundImage;
    @FXML private Button soundBtn;
    @FXML private CountdownTimerController countdownTimerController;
    @FXML private PlayerCardListController playerCardListController;

    private Sound backgroundMusic;
    private Sound btnSound;
    private boolean enableSound;
    private Image soundOnImg;
    private Image soundOffImg;
    private Background bgSoundOnImg;
    private Background bgSoundOffImg;

    @FXML private void initialize(){
        countdownTimerController.injectMainController(this);
        playerCardListController.injectMainController(this);
        bgSoundOnImg = new Background(new BackgroundImage(soundOnImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(soundBtn.getWidth(), soundBtn.getHeight(), true, true, true, false)));
        bgSoundOffImg = new Background(new BackgroundImage(soundOffImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(soundBtn.getWidth(), soundBtn.getHeight(), true, true, true, false)));
        soundBtn.setBackground(bgSoundOnImg);
    }

    public MainController(){
        enableSound = true;
        soundOnImg = new Image("file:src/resources/images/sound_on.jpg");
        soundOffImg = new Image("file:src/resources/images/sound_off.jpg");
        backgroundMusic = new Sound("BACKGROUND_MUSIC");
        btnSound = new Sound("BUTTON_SOUND");
    }

    public boolean getEnableSound(){
        return enableSound;
    }

    public void musicBtnHandle(MouseEvent mouseEvent) {
        btnSound.makeSound();
        if (enableSound){
            enableSound = false;
            backgroundMusic.pauseBackgroundMusic();
            soundBtn.setBackground(bgSoundOffImg);
        }else{
            enableSound = true;
            backgroundMusic.resumeBackgroundMusic();
            soundBtn.setBackground(bgSoundOnImg);
        }
    }



}
