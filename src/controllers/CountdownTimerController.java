package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import models.CountDownTimer;
import models.Sound;

public class CountdownTimerController {
    @FXML private ProgressBar countdownProgressBar;
    @FXML private Button btnStartPauseTimer;
    @FXML private Button btnResetTimer;
    @FXML private Label minutesLabel;      //Label displaying minutes
    @FXML private Label secondsLabel;      //Label displaying seconds
    @FXML private Label hundthsecsLabel;   //Label displaying hundredth of a second
    @FXML private MainController mainController;

    private Sound sound;
    private Timeline timeline;
    private CountDownTimer timer;

    public void injectMainController(MainController mainController){this.mainController = mainController;}

    public CountdownTimerController(){
        timer = new CountDownTimer();
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
        if (mainController.getEnableSound()) sound.makeBtnSound();
        if (!timer.isTimerRunning()){
            timer.setTimerRunning(true);
            timeline.play();
            btnStartPauseTimer.setText("Pause");
        } else {
            timer.setTimerRunning(false);
            timeline.pause();
            btnStartPauseTimer.setText("Start");
        }
    }


    @FXML private void timerReset(MouseEvent mouseEvent) {
        if (mainController.getEnableSound()) sound.makeBtnSound();
        timeline.stop();
        timer.setTimerRunning(false);
        timer.setMinutes(2); timer.setSeconds(0); timer.setHundthsecs(0);
        btnStartPauseTimer.setText("Start");
        setTimerLabelAndProgressBar();
    }


}
