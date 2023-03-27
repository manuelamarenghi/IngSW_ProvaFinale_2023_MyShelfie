package it.polimi.ingsw.CommonGoalCard;

import it.polimi.ingsw.CommonGoalCard.CommonGoalCards;

public class EffectiveCard {
    private CommonGoalCards commonCard;
    private int[] allScores;

    public EffectiveCard(CommonGoalCards commoncard ){
        this.commonCard = commoncard;
    }

    public void update(Player player){
        commonCard.check(player.getLibrary());
    }

    public void show(){
        commonCard.getImage();
    }
}
