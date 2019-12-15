package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import models.CountDownTimer;
import models.Sound;

import java.util.ArrayList;

public class CountdownTimerController {
    @FXML private ProgressBar countdownProgressBar;
    @FXML private Button btnStartPause;
    @FXML private Button btnNewGame;
    @FXML private Label minutesLabel;      //Label displaying minutes
    @FXML private Label secondsLabel;      //Label displaying seconds
    @FXML private Label hundthsecsLabel;   //Label displaying hundredth of a second
    @FXML private MainController mainController;

    private Sound sound;
    private Timeline timeline;
    private CountDownTimer timer;

    public void injectMainController(MainController mainController){this.mainController = mainController;}

    public CountdownTimerController(){
        System.out.println("Countdowntimercontroller.constructor");
        timer = new CountDownTimer(2,0,0);
        sound = new Sound("BUTTON_SOUND");
        timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void updateTimer(){
        if (!timer.countdown()) timeline.stop();
        setTimerLabelAndProgressBar();
    }

    private void setTimerLabelAndProgressBar(){
        minutesLabel.setText(String.format("%02d :", timer.getMinutes()));
        secondsLabel.setText(String.format("%02d :", timer.getSeconds()));
        hundthsecsLabel.setText(String.format("%02d", timer.getHundthsecs()));
        countdownProgressBar.setProgress(getTimerProgress());
    }

    private double getTimerProgress(){
        double timeElapsed = timer.getMinutes() * 60 * 60 + timer.getSeconds() * 60 + timer.getHundthsecs();
        return timeElapsed / (2 * 60 * 60);
    }

    @FXML private void timerStartPause(MouseEvent mouseEvent) {
        if (mainController.getEnableSound()) sound.makeSound();
        if (!timer.isTimerRunning()){
            timer.setTimerRunning(true);
            timeline.play();
            btnStartPause.setText("Pause");
        } else {
            timer.setTimerRunning(false);
            timeline.pause();
            btnStartPause.setText("Start");
        }
    }

    @FXML private void timerReset(MouseEvent mouseEvent) {
        if (mainController.getEnableSound()) sound.makeSound();
        timeline.stop();
        timer.setTimerRunning(false);
        timer.setMinutes(2); timer.setSeconds(0); timer.setHundthsecs(0);
        btnStartPause.setText("Start");
        setTimerLabelAndProgressBar();
    }


}
