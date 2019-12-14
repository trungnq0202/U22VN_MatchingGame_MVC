package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import models.PlayerCard;
import models.PlayerCardList;
import models.Sound;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerCardListController {

    @FXML private GridPane playerCardListView;
    @FXML private MainController mainController;

    private Sound flipSound;
    private ArrayList<Integer> cardRow;
    private ArrayList<Integer> cardCol;
    private PlayerCardList playerCardList;
    private int cardFlippedCount;
    private PlayerCard prevCardFlipped;

    public void injectMainController(MainController mainController){this.mainController = mainController;}

    public PlayerCardListController(){
        cardFlippedCount = 0;
        prevCardFlipped = null;

        flipSound = new Sound("CARD_FLIPPING_SOUND");

        //Create and shuffle the rows of cards
        cardRow = new ArrayList<Integer>(4);
        for (int i = 0; i < 4; i++) cardRow.add(i);

        //Create and shuffle the columns of cards
        cardCol = new ArrayList<Integer>(5);
        for (int i = 0; i < 5; i++) cardCol.add(i);

        //Shuffle the order of rows and cols
        Collections.shuffle(cardCol); Collections.shuffle(cardRow);

        //Create list of 20 player cards
        playerCardList = new PlayerCardList();
    }

    @FXML private void initialize(){
        int tempCardNo = 0;
        for (Integer row : cardRow) {
            for (Integer col : cardCol) {
                PlayerCard playerCard = playerCardList.getPlayerCard(tempCardNo);
                setMouseEventHandler(playerCard);
                playerCardListView.add(playerCard, col, row);
                tempCardNo++;
            }
        }
    }

    private void setMouseEventHandler(PlayerCard playerCard){
        playerCard.setOnMouseClicked((MouseEvent e)->{
            if (cardFlippedCount == 0) {
                if (mainController.getEnableSound()) flipSound.makeSound();
                prevCardFlipped = playerCard;
                playerCard.flipPlayerCard();
                cardFlippedCount++;

            } else if (cardFlippedCount == 1){
                if (mainController.getEnableSound()) flipSound.makeSound();
                
            }
        });
    }
}
