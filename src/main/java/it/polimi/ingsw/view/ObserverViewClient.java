package it.polimi.ingsw.view;

import it.polimi.ingsw.modello.Match;
import it.polimi.ingsw.modello.Position;


public interface ObserverViewClient {
    void handleEnterPlayer(String nickname);

    void handleCreateBoard(int numeberOfPlayers);

    void handleCreateMatch(Match match);

    void handleTakeCard(Position[] positions);

    void handlePutInLibrary(int x);

    void handleColoumnRequest(int numberOfCards, String name);

    void handleFinalPoint(String name);

    void handleDisconection(String name);


    void setNickname(String nickname);

    void SeeSomeoneLibrary(String nickname);
    void handleSeeBoard();
    void handleSeePersonalCard();
    void handleSeeCommonCard();
}
