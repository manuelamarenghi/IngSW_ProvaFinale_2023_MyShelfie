package it.polimi.ingsw.message;

import it.polimi.ingsw.Controller.ClientController;
import it.polimi.ingsw.Controller.MatchController;
import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.network.ClientHandler;


import java.io.Serializable;

/**
 * this class create a generic message serializable
 */

public abstract class Message implements Serializable {
    protected String nickname;
    protected String type;

    public Message(String c, String m) {
        this.nickname = c;
        this.type =m;
    }

    public String getnickname() {
        return nickname;
    }

    public String getType() {
        return type;

    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setType(String type) {
        this.type = type;
    }

    /**
     * visit() let controller use the specific method to Handle this message
     *
     * @param c
     */
    public void visit(MatchController c) {
        // metodo che gestisce messaggio
    }

    public void visit(ClientController clientController){
        //Gestisce i messaggi
    }


    public void MextoClientHandler(ClientHandler c) {
    }

    abstract void setPayload(String s);

    abstract void redirectAnswer();
}
