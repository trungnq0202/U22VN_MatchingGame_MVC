package models;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class PlayerCard extends Pane {
    private ImageView imageViewer;
    private Image defaultImage;
    private Image playerImage;
//    private int playerID;
//    private CountDownTimer timer;
    private boolean isPlayerDisclosed;
//    private boolean isMatched;

    public PlayerCard(int imageNo){
        isPlayerDisclosed = false;
//        isMatched = false;
        this.getStyleClass().add("playerCard");
        defaultImage = new Image("file:src/resources/images/soccerball.png");
        playerImage = new Image("file:src/resources/images/player"+ Integer.toString(imageNo) +".jpg");
        imageViewer = new ImageView(defaultImage);
        imageViewer.setFitWidth(80);
        imageViewer.setFitHeight(65);
        imageViewer.setX(33);
        imageViewer.setY(38);
        getChildren().add(imageViewer);
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

    public void flipPlayerCard() {
        RotateTransition animation = new RotateTransition(Duration.millis(200), this);
        animation.setAxis(Rotate.Y_AXIS);
        animation.setFromAngle(0);
        animation.setToAngle(360);
        animation.setInterpolator(Interpolator.LINEAR);
        animation.setCycleCount(1);
        animation.play();
        if (!isPlayerDisclosed) { setPlayerImage(); isPlayerDisclosed = true;}
        else { setDefaultImage(); isPlayerDisclosed = false; }
    }

}
