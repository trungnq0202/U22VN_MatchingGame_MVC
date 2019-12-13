package models;

import java.util.ArrayList;

public class PlayerCardList {
    private ArrayList<PlayerCard> playerCardList;

    public PlayerCardList(){
        playerCardList = new ArrayList<PlayerCard>(20);
        int imageNo = 1;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 2; j ++) {
                PlayerCard playerCard = new PlayerCard(imageNo);
                playerCardList.add(playerCard);
            }
            imageNo++;
        }
    }

    public PlayerCard getPlayerCard(int cardNo){
        return playerCardList.get(cardNo);
    }


}
