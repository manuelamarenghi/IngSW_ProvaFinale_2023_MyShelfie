package it.polimi.ingsw.message;

import it.polimi.ingsw.network.MessageHandler;

import java.io.Serializable;

public class Numb_Player_Answer extends Message implements Serializable {
    int x;
    public Numb_Player_Answer(int x) {
        super("Server", "Numb_Player_Set");
        this.x=x;
    }
    public int getX() {
        return x;
    }
    public void visit(MessageHandler controller){
        controller.handle(this);
    }
}
