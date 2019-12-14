package models;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class PlayerCard extends Pane {
    private ImageView imageViewer;
    private Image defaultImage;
    private Image playerImage;
    private int playerCardID;
    private CountDownTimer timer;
    private boolean isPlayerDisclosed;
    private boolean isMatched;
    private Timeline timeline;
    private RotateTransition flipAnimation;

    public PlayerCard(int imageNo){
        playerCardID = imageNo;
        isPlayerDisclosed = false;
        isMatched = false;
        this.getStyleClass().add("playerCard");
        defaultImage = new Image("file:src/resources/images/soccerball.png");
        playerImage = new Image("file:src/resources/images/player"+ Integer.toString(imageNo) +".jpg");
        imageViewer = new ImageView(defaultImage);
        setDefaultImage();
        getChildren().add(imageViewer);
        flipAnimation = createflipAnimation();
    }

    public void setTimer(){
        timer = new CountDownTimer(0, 1, 0);
        timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timer.setTimerRunning(true);
        timeline.play();
    }

    private void updateTimer(){
        if (!timer.countdown()) { timeline.stop(); timer.setTimerRunning(false); flip();}
    }

    public void setDefaultImage(){
        imageViewer.setImage(defaultImage);
        imageViewer.setFitWidth(80);
        imageViewer.setFitHeight(65);
        imageViewer.setX(33);
        imageViewer.setY(38);
    }

    public void setPlayerImage(){
        imageViewer.setImage(playerImage);
        imageViewer.setFitWidth(135.9);
        imageViewer.setFitHeight(142);
        imageViewer.setX(5);
        imageViewer.setY(5);
    }

    public void setTrueIsMatched(){
        isMatched = true;
    }

    public int getPlayerCardID(){
        return playerCardID;
    }

    public RotateTransition createflipAnimation(){
        RotateTransition animation = new RotateTransition(Duration.millis(200), this);
        animation.setAxis(Rotate.Y_AXIS);
        animation.setFromAngle(0);
        animation.setToAngle(360);
        animation.setInterpolator(Interpolator.LINEAR);
        animation.setCycleCount(1);
        return animation;
    }

    public void flipPlayerCard(){
        if (!isPlayerDisclosed) {
            flip();
            setTimer();
        }
    }

    public void flip() {
        if (!isPlayerDisclosed){
            flipAnimation.play();
            setPlayerImage();
            isPlayerDisclosed = true;
        } else if (!isMatched){
            flipAnimation.play();
            setDefaultImage();
            isPlayerDisclosed = false;
        }
    }
}
