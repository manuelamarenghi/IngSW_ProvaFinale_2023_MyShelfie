package it.polimi.ingsw.Controller;

import it.polimi.ingsw.enumeration.GameState;
import it.polimi.ingsw.enumeration.TurnPhase;
import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.Numb_Player;
import it.polimi.ingsw.message.TakeCardBoard;
import it.polimi.ingsw.modello.Card;
import it.polimi.ingsw.modello.Player;
import it.polimi.ingsw.view.VirtualView;
import it.polimi.ingsw.modello.Match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MatchController {
    private Match match;
    private int numberOfPlayers;
    private ArrayList<String> players;
    private TurnController turnController;
    private ArrayList<String> disconnectClients;
    private Map<String, VirtualView> connectClients;
    private GameState gameState;
    private TurnPhase turnPhase;

    public MatchController(){
        this.match = new Match();
        this.connectClients = Collections.synchronizedMap(new HashMap<>());
    }

    /**
     * Set the State of the Game.
     *
     * @param gameState State of the current Game.
     */
    private void setGameState(GameState gameState) {
        this.gameState = gameState;
    }


    /**
     * connect new player and his virtualview
     * the first player, he needs choose how many player play
     *  other player, add virtualview and wait the game start
     */
    public void loginHandler(String nickname,VirtualView virtualView){
        if(connectClients.isEmpty()){
            addVirtualView(nickname,virtualView);
            match.setPlayers(new Player(nickname));

            //chiamata virtualview

        }
        else if(connectClients.size() < match.getPlayerNumber()){
            addVirtualView(nickname,virtualView);
            match.setPlayers(new Player(nickname));
        }
        else{
            //avviso virtualview che ci sono già abbastanza giocatori

        }
    }

    /**
     * Start game
     */
    public void startGame(){
        for(String name : players){
            this.match.setPlayers(new Player(name));
        }

        match.getMatchmanager().startGame();
        //virtualview per far vedere le personal card e far iniziare il turno al primo giocatore.

        this.turnController = new TurnController(this,players,match.getChair().getNickname(),match);

    }

    //------------------------On message received-------------------------------------------

    /**
     * received generic message
     * @param m message
     */
    public void messageHandler (Message m){
        if(turnController.getActivePlayer().equals(m.getnickname())){
            m.visit(this);
        }
        else{
            //virtualview avvisa al cliente che non è il sup turno.
        }
    }

    /**
     * this message received, the first player decide how many players play the game
     * @param numberPlayer number of player who play the game
     */
    public void handler(Numb_Player numberPlayer){
        match.setMatch(numberPlayer.getNumb());

        //virtualview che dice che attende gli altri giocatori
    }
    /**
     * this message the server received the card chosen by the player
     * @param m ArrayList of Card choose by the player
     */
    public void handler(TakeCardBoard m){
        ArrayList<Card> cardSelect = m.getCards();
        if(match.getBoard().allow(cardSelect)){
            for(Card card : cardSelect){
                match.getBoard().takeCard(card.getCoordinates());
            }

            Player player = match.getPlayerByNickname(m.getnickname());

            int[] coloum = match.getPlayerByNickname(m.getnickname()).getLibrary().showColumn(cardSelect.size());
            //messaggio virtualview per dire al giocatore le colonne possibili
        }
        else{
            //messagio virtualview non può prenderli
        }

    }

    //----------------------VIRTUALVIEW METHODS----------------
    public void addVirtualView(String nickname,VirtualView virtualView){
        connectClients.put(nickname,virtualView);
        //se si vuole aggiungere l'observer della virtualview
    }
}
