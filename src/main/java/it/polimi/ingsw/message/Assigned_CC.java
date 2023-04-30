package it.polimi.ingsw.message;


import it.polimi.ingsw.Controller.MatchController;
import it.polimi.ingsw.modello.EffectiveCard;

/**
 * message from server to client when has reach common goal
 */
public class Assigned_CC extends  Message{
    public int point;
    public EffectiveCard card;
    public Assigned_CC(int x, String name, EffectiveCard card){
        super(name,"Has_reach_point");
        this.point=x;
        this.card=card;
    }

    public EffectiveCard getCard() {
        return card;
    }

    public int getPoint() {
        return point;
    }
    @Override
    public void visit(MatchController c) {
        // metodo che gestisce messaggio specifico
    }

}
