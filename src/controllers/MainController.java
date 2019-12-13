package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import models.Sound;

public class MainController {
    @FXML private Button musicBtn;

    @FXML private CountdownTimerController countdownTimerController;

    @FXML private PlayerCardsController cardsController;

    private Sound sound;

    public MainController(){
        sound = new Sound("BACKGROUND_MUSIC");

    }

    public void initialize(){

    }

    public void musicBtnHandle(MouseEvent mouseEvent) {

    }
}
