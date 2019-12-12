package controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.time.LocalDateTime;
import javax.swing.Timer;

public class CountdownTimerController {
    public Button buttonStartPauseTimer;
    public Button buttonResetTimer;
    public TextField timerViewer;

    private LocalDateTime startTime;
    private Timer timer;



    public void timerStartPause(MouseEvent mouseEvent) {

    }

    public void timerReset(MouseEvent mouseEvent) {

    }
}
