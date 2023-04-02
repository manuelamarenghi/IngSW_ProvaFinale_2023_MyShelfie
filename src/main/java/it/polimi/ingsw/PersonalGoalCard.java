package it.polimi.ingsw;

import java.util.Arrays;

public class PersonalGoalCard {
    private static final int DIMENSION = 6;
    private Card[] personalObjective=new Card[DIMENSION];
    public PersonalGoalCard(Card[] personalObjective){
        for(int i=0;i<DIMENSION;i++){
            this.personalObjective[i]=new Card(personalObjective[i].getColour(),personalObjective[i].getCoordinates());
        }
    }

    public Card[] getPersonalObjective() {
        return Arrays.copyOf(personalObjective,personalObjective.length);
    }

    public void showPersonalGoalCard(){
        boolean printed;
        for(int i=0;i<6;i++){
            for (int j=0;j<5;j++){
                printed=false;
                for(Card card:this.personalObjective){
                    if(card.getCoordinates().getX()==i&&card.getCoordinates().getY()==j){
                        System.out.print("["+card.getColour()+"] ");
                        printed=true;
                    }

                }
                if(!printed) System.out.print("[ ]");

            }
            System.out.println();
        }
    }
}