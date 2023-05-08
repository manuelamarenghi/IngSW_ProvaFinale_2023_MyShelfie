package it.polimi.ingsw.Controller;

import it.polimi.ingsw.message.*;
import it.polimi.ingsw.modello.*;
import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.network.SocketClient;
import it.polimi.ingsw.network.observer.Observer;
import it.polimi.ingsw.view.VirtualModel;

import javax.swing.text.View;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientController{
    private View view;
    private final SocketClient  socketClient;
    private VirtualModel virtualModel;
    //localhost
    //16000847
    public ClientController(View view , VirtualModel virtualModel) throws IOException {
        this.view = view;
        this.virtualModel=virtualModel;
        socketClient= new SocketClient("local host" , 16000847);
    }

    public ClientController(View view , VirtualModel virtualModel , SocketClient socketClient){
        this.view = view;
        this.virtualModel=virtualModel;
        this.socketClient=socketClient;
    }


    public void handleEnterPlayer (String nickname){
        EnterPlayer message = new EnterPlayer(nickname);
        socketClient.sendMessage(message);
    }

    /**
     * The method creates board depending on number of players
     */

    public void handleCreateBoard(int numeberOfPlayers , String name ){
        Numb_Player message = new Numb_Player(numeberOfPlayers , name);
        socketClient.sendMessage(message);
    }

    public void handleCreateMatch(Match match){
        Created_Match message = new Created_Match(match);
        socketClient.sendMessage(message);
    }

    /**
     * the sends a message to socket client in case it decides to pick a card from board
     */
    public void handleTakeCard(Position[] positions , String name ){
        int i ;
        Card cards[]={};
        for(i=0 ; i< positions.length ; i++){
            cards[i] = virtualModel.getBoard().getCard(positions[i].getX(),positions[i].getY());
        }
        TakeCardBoard message = new TakeCardBoard(cards , name);
        socketClient.sendMessage(message);
    }

    /**
     * The method sends a message to socket client to put a card in the library
     */
    public void handlePutInLibrary (int x , String name , ArrayList<Card> cards){
        PutInLib message = new PutInLib(x , name , cards);
        socketClient.sendMessage(message);
    }

    public void handleColoumnRequest(int numberOfCards , String name){
        ColumnRequest message = new ColumnRequest(numberOfCards , name);
        socketClient.sendMessage(message);
    }

    /**
     * the method sends a message to socket client to calculate points for the player
     */
    public void handleFinalPoint(String name){
        FinalPointRequest message =new FinalPointRequest(name);
        socketClient.sendMessage(message);
    }

    /**
     * The method tells the server to dissconect
     * @param name
     */

    public void handleDisconection(String name){
        Disconnection message = new Disconnection(name);
        socketClient.sendMessage(message);
    }
}
