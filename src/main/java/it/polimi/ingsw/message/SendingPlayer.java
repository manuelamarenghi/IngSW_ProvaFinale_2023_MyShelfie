package it.polimi.ingsw.message;

import it.polimi.ingsw.modello.Player;
import it.polimi.ingsw.network.MessageHandler;

/**
 * Class to send a message that sends a player
 */
public class SendingPlayer extends Message {
    Player p;

    public SendingPlayer(Player p) {
        super(p.getNickname(), "updating the player");
        this.p = p;
    }

    public void visit(MessageHandler controller) {
        controller.handle(this);
    }
}
