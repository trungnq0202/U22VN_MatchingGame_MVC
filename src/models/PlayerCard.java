package models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PlayerCard extends Pane {
    private ImageView imageViewer;
    private Image defaultImage;
    private Image playerImage;
    private int playerCardID;
    private boolean isFlippedOpen;
    private boolean matchedStatus;

    public PlayerCard(int imageNo){
        playerCardID = imageNo;
        isFlippedOpen = false;
        matchedStatus = false;
        this.getStyleClass().add("playerCard");
        defaultImage = new Image("file:src/resources/images/soccerball.png");
        playerImage = new Image("file:src/resources/images/player"+ Integer.toString(imageNo) +".jpg");
        imageViewer = new ImageView(defaultImage);
        setDefaultImage();
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

    public void setMatchedStatus(boolean matchedStatus){
        this.matchedStatus = matchedStatus;
    }

    public boolean getMatchedStatus(){
        return this.matchedStatus;
    }

    public int getPlayerCardID(){
        return playerCardID;
    }

    public boolean getIsFlippedOpen(){
        return isFlippedOpen;
    }

    public void setIsFlippedOpen(boolean isFlippedOpen){
        this.isFlippedOpen = isFlippedOpen;
    }

}
