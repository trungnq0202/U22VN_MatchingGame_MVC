package controllers;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import models.PlayerCard;
import models.PlayerCardList;
import models.Sound;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerCardListController {

    @FXML private GridPane playerCardListView;
    @FXML private MainController mainController;

    private Sound flipSound;
    private PlayerCardList playerCardList;
    private int clickCount;
    private PlayerCard prevCardFlipped;
    private int matchCount;
    private int level;

    public void injectMainController(MainController mainController){this.mainController = mainController;}

    public PlayerCardListController(){
        System.out.println("Playercardlist.constructor");
        clickCount = 2;
        prevCardFlipped = null;
        matchCount = 0;
        flipSound = new Sound("CARD_FLIPPING_SOUND");
        playerCardList = null;
    }

    @FXML public void initialize(){
        System.out.println("Playercardlist.constructor");
    }

    private void setCardFlipEventHandler(PlayerCard playerCard){
        playerCard.setOnMouseClicked((MouseEvent e)->{
            //If (the card is already flipped open) or (maximum click count is reached)
            if (playerCard.getIsFlippedOpen() || clickCount == 0) return; // do nothing

            //Note that 1 click has executed
            clickCount--;

            //If there is no previous card being flipped
            if (prevCardFlipped == null) {
                prevCardFlipped = playerCard;   //Remember this card as the "previous card being flipped" for comparing with the next flipped open card
                flipOpen(playerCard, ()->{});   //Flip open this card
                setCloseTimer(playerCard);           //Set timer for this card to close if not successfully matched with the other same card

            //If there is a previous card being flipped to make the comparison
            } else {
                flipOpen(playerCard, () -> {    //Flip this card open
                    if (prevCardFlipped != null && playerCard.getPlayerCardID() == prevCardFlipped.getPlayerCardID()){ //If this card is the same as the previous flipped one
                        prevCardFlipped.setMatchedStatus(true);                             //Set these 2 cards' matched status to true so that it wont be automatically flipped close
                        playerCard.setMatchedStatus(true);
                        matchCount++;
                    } else { //If this card is no the same as the previous flipped one
                        if (prevCardFlipped != null) flipClose(prevCardFlipped); //Flip close the previous card
                        flipClose(playerCard);      //Flip close this card
                    }
                    prevCardFlipped = null;
                    clickCount = 2;
                });
            }
        });
    }

    public void setCloseTimer(PlayerCard playerCard){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (playerCard.getIsFlippedOpen() && !playerCard.getMatchedStatus()){
                    prevCardFlipped = null;
                    flipClose(playerCard);
                    clickCount++;
                }
            }
        };
        int delay = 1000;
        Timer timer = new Timer("Timer");
        timer.schedule(timerTask, delay);
    }

    public RotateTransition createflipAnimation(PlayerCard playerCard){
        RotateTransition animation = new RotateTransition(Duration.millis(300), playerCard);
        animation.setAxis(Rotate.Y_AXIS);
        animation.setFromAngle(0);
        animation.setToAngle(180);
        animation.setInterpolator(Interpolator.LINEAR);
        animation.setCycleCount(1);
        return animation;
    }

    public void flipOpen(PlayerCard playerCard , Runnable action){
        RotateTransition flip = createflipAnimation(playerCard);
        flip.setOnFinished(e -> action.run());
        playerCard.setIsFlippedOpen(true);
        flip.play();
        if (mainController.getEnableSound()) flipSound.makeSound();
        playerCard.setPlayerImage();
    }

    public void flipClose(PlayerCard playerCard){
        RotateTransition flip = createflipAnimation(playerCard);
        playerCard.setIsFlippedOpen(false);
        flip.play();
        if (mainController.getEnableSound()) flipSound.makeSound();
        playerCard.setDefaultImage();
    }

    public void removePrevCardList(){
        for (int i = 0; i < playerCardList.getPlayerCardListSize(); i++){
            playerCardListView.getChildren().remove(playerCardList.getPlayerCard(i));
        }
    }

    public void createNewCardListView(){
        if (playerCardList != null) removePrevCardList();
        playerCardList = new PlayerCardList();
        int tempCardNo = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                PlayerCard playerCard = playerCardList.getPlayerCard(tempCardNo);
                setCardFlipEventHandler(playerCard);
                playerCardListView.add(playerCard, col, row);
                tempCardNo++;
            }
        }
    }
}
