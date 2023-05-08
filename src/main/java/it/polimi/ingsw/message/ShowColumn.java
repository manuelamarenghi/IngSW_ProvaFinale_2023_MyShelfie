package it.polimi.ingsw.message;

import it.polimi.ingsw.network.MessageHandler;

/**
 * response to a modify on the board and request for column in library selectable
 */
public class ShowColumn extends Message{
    int[] x;
    public ShowColumn(String name,int[] x){
        super(name,"selectable_column");
        this.x=x;
    }
    public int[] getX() {
        return x;
    }
    public void visit(MessageHandler controller){
        controller.handle(this);
    }
}
