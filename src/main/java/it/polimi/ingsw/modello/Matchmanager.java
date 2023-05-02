package it.polimi.ingsw.modello;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class Matchmanager{

  /**
   * this class manage actions in a match
   * the match class initialized one of his subclasses according to player's number
   */
  public Matchmanager(){

  }

  /**
   * Choose the common card and personal card
   * fill the board
   * start game
   */

  public void startGame(Match match){

    setPersonalGoal(match.getPlayers());
    setEffectiveCards(match);

    createBoard(match);
    int position = (int)(Math.random() * 4);
    match.setChair(match.getPlayers().get(position));
    for(Player p: match.getPlayers()){
      p.getView().CreateMatch(match);
      p.getView().assignedChair(match.getChair().getNickname());
    }
  }
  /**
   * results() calculates all players' scores
   */
  public HashMap<String,Integer> results(Match m) {
    HashMap<String,Integer> scores = new HashMap<>();

    int i=0;
    for(Player p : m.getPlayers())
    {
      i=p.getPlayerManager().showProgressScore(p);
      if(p.equals(m.getFirstFinish()))
        i++;

      scores.put(p.getNickname(),i);
    }
    return scores;
    /*
    int[] scores = new int[m.getPlayers().size()];
    ArrayList<Player> p = m.getPlayers();
    int j = 0;
    for (Integer i : scores) {
      i = p.get(j).getPlayerManager().showProgressScore(p.get(j));
      if (p.get(j).equals(m.getFirstFinish())) {
        i++;
      }
    }
    return scores;*/

  }
  /**
   * checkState() returns the state of a player's connection
   */
  public Boolean checkState(Player p){return null;};
  /**
   * setPersonalGoal() assigns to each player inside the given array a random PersonalGoalCard, randomly extracted
   * by the PersonalGoalCards.json File
   */
  public void setPersonalGoal(ArrayList<Player> p){
    int max_rnd=12;
    ArrayList<PersonalGoalCard> rand=generateArrayFromJSON();
    for(Player player:p){
      int r=(int)Math.floor(Math.random() * (max_rnd) );
      //take an objective from a PersonalGoal in rand
      player.setPersonalCard(new PersonalGoalCard(rand.get(r).getPersonalObjective()));
      //remove the object just taken and remove it from the ArrayList; then it reduce by one the value of the
      //possible value that could be generated by random() by one.
      rand.remove(r);
      max_rnd--;
    }
  }
  private ArrayList<PersonalGoalCard> generateArrayFromJSON(){
    String jsonPath;
    ArrayList<PersonalGoalCard> arrayList=new ArrayList<>(12);
    Card[] cards;

    {
      try {
        jsonPath = new String(Files.readAllBytes(Paths.get("./src/json/PersonalGoalCards.json")));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      JSONArray jsonArray=new JSONArray(jsonPath);
      for(int i=0;i<jsonArray.length();i++) {
        cards = new Card[6];
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        JSONArray obj = jsonObject.getJSONArray("objective");
        for (int j = 0; j < obj.length(); j++) {
          JSONObject pos = obj.getJSONObject(j).getJSONObject("coordinates");
          Position position = new Position(pos.getInt("x"), pos.getInt("y"));
          cards[j] = new Card(obj.getJSONObject(j).getString("colour"), position);
        }
        arrayList.add(new PersonalGoalCard(cards));
      }
      return arrayList;
    }
  }

  /**
   * turn() manage a player's turn, a is for knowing if the player wants to pass his turn
   */
  public void turn(Player p, Match m,int a) {
    if (a != -1) {
      p.getLibrary().showLibrary();
      p.getPlayerManager().selectCard(p, m.getBoard());
      p.getPlayerManager().notifyAllObservers(p);
      p.getLibrary().showLibrary();
      int i = 0;
      for (Card c : p.getLibrary()) {
        if (c.getColour() == null) {
          break;
        } else {
          i++;
        }
      }
      if (i == 30) {
        m.setFirstFinish(p);
        System.out.println("Your library is full, the game continues until the player sitting to the right to the player holding the first player seat");
      }
    }
  }
  /**
   * IsEmpyBoard() checks if board is empty
   */
  public void IsEmptyBoard(Match m) {
    int countSingolCard = 0;
    ArrayList<Integer> group = m.getBoard().Group();
    for (Integer i : group) {
      if (i == 1)
        countSingolCard++;
    }
    if (countSingolCard == group.size())
      m.getBoard().fill(countSingolCard);
  }
  /**
   * classification() returns an arraylist of players based on their scores
   */
  public ArrayList<Player> classification(Match m){
    ArrayList<Player> classification=new ArrayList<>(m.getPlayers().size());
    int j=1;
    HashMap<String,Integer> score = m.getMatchmanager().results(m);

    int[]scores = new int[m.getPlayerNumber()];
    int k=0;
    for(Integer s : score.values())
    {
      scores[k] = s;
      k++;
    }
    int[] position= new int[]{0, 1, 2, 3};
    for (int i = 0; i < 4; ++i) {
      int key = scores[i];
      while(j!=4 && key>scores[j]){
        int temp=position[i];
        position[i]=position[j];
        position[j]=temp;
        temp=scores[j];
        scores[j]=scores[i];
        scores[i]=temp;
        j++;
      }
      j=i+1;
    }
    for(Integer i: position){
      classification.add(m.getPlayers().get(i));
    }
    return classification;
  }
  /**
   * showCommGoal() let you see CommonCards in a given match
   */
  public void showCommGoal(Match match){
    EffectiveCard[]  cards=match.getCommonCards();
    cards[0].getCommonCard().getImage();
    cards[1].getCommonCard().getImage();
  }
  /**
   * createBoard() create a board with different allowed position according to number of players
   */

  public abstract void createBoard(Match m);
  /**
   * setEffectiveCards() choose two card per match
   */
  public abstract void setEffectiveCards(Match m);
}
