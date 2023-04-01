package it.polimi.ingsw;

import java.util.ArrayList;

public class Board {
    private int row=9 , col=9 , numOfPlayers;
    private Card[][] board = new Card[row][col];
    private Card ca;
    private Bag bag;
    private int c , r;
    private Card emptyCard = new Card();
    private Card notUsableCard=new Card();

    public Board(int numOfPlayers){
        bag = new Bag();
        this.numOfPlayers=numOfPlayers;
        for(c=0 ; c<9 ; c++){//To put Null the first line
            if(c!=4 && c!=3){
                board[0][c]=notUsableCard;
            }
        }
        for(c=0 ; c<9 ; c++){//To put Null the second line
            if(c!=3 && c!=4 && c!=5){
                board[1][c]=notUsableCard;
            }
        }
        for(c=0 ; c<9 ; c++){//To put Null the third line
            if(c!=2 && c!=3 && c!=4 && c!=5 && c!=6){
                board[2][c]=notUsableCard;
            }
        }
        board[3][0]=notUsableCard;//To put Null the Fourth line
        board[5][8]=notUsableCard;//To put null the sixth line

        for(c=0 ; c<9 ; c++){//To put Null the seventh line
            if(c!=2 && c!=3 && c!=4 && c!=5 && c!=6){
                board[6][c]=notUsableCard;
            }
        }
        for(c=0 ; c<9 ; c++){//To put Null the second line
            if(c!=3 && c!=4 && c!=5){
                board[7][c]=notUsableCard;
            }
        }
        for(c=0 ; c<9 ; c++){//To put Null the first line
            if(c!=5 && c!=4){
                board[8][c]=notUsableCard;
            }
        }
        if(numOfPlayers==2){//Positions that can not be used with 2 players playing
            board[3][8]=notUsableCard;//Positions with 3 points
            board[2][6]=notUsableCard;
            board[0][3]=notUsableCard;
            board[2][2]=notUsableCard;
            board[5][0]=notUsableCard;
            board[6][2]=notUsableCard;
            board[8][5]=notUsableCard;
            board[6][6]=notUsableCard;

            board[4][8]=notUsableCard;//Positions with 4 points
            board[0][4]=notUsableCard;
            board[3][1]=notUsableCard;
            board[4][0]=notUsableCard;
            board[7][3]=notUsableCard;
            board[8][4]=notUsableCard;
            board[5][7]=notUsableCard;
            board[1][5]=notUsableCard;

        }
        else if(numOfPlayers==3){
            board[4][8]=notUsableCard;//Positions with 4 points
            board[0][4]=notUsableCard;
            board[3][1]=notUsableCard;
            board[4][0]=notUsableCard;
            board[7][3]=notUsableCard;
            board[8][4]=notUsableCard;
            board[5][7]=notUsableCard;
            board[1][5]=notUsableCard;
        }

    }

    public void fill(){
        int indexOfCards=0 , indexOfColours=0;
        //ArrayList<Card> cardsForBoard  = new ArrayList<Card>();
        ArrayList<String> arrayOfColours = new ArrayList<String>();
        Card tempCard;
        arrayOfColours=bag.extract(numOfPlayers);
        for(c=0 ; c<9 ; c++){
            for(r=0 ; r<9 ; r++){
                if(board[r][c]!=notUsableCard){
                    tempCard = new Card(arrayOfColours.get(indexOfColours) , new Position(r,c));
                    //cardsForBoard.set(indexOfCards , tempCard);
                    board[r][c]=tempCard;
                    indexOfColours++;
                }
            }
        }

    }

    public Boolean allow(ArrayList<Card> cards ){
        if(cards.size()==2){
            if(cards.get(1).getCoordinates().getX()==cards.get(0).getCoordinates().getX()+1 ||
                cards.get(1).getCoordinates().getX()==cards.get(0).getCoordinates().getX()-1 ||
                cards.get(1).getCoordinates().getY()==cards.get(0).getCoordinates().getY()-1 ||
                cards.get(1).getCoordinates().getY()==cards.get(0).getCoordinates().getY()+1 ){
                //ok crads are adicent
            }
            else{
                return false;
            }
        }
        if(cards.size()==3){
            if(((cards.get(1).getCoordinates().getX()==cards.get(0).getCoordinates().getX()+1)&&(cards.get(2).getCoordinates().getX()==cards.get(1).getCoordinates().getX()+1)) ||
                ((cards.get(2).getCoordinates().getX()==cards.get(0).getCoordinates().getX()+1)&&(cards.get(1).getCoordinates().getX()==cards.get(2).getCoordinates().getX()+1))||
                ((cards.get(0).getCoordinates().getX()==cards.get(1).getCoordinates().getX()+1)&&(cards.get(2).getCoordinates().getX()==cards.get(0).getCoordinates().getX()+1))||
                ((cards.get(2).getCoordinates().getX()==cards.get(1).getCoordinates().getX()+1)&&(cards.get(0).getCoordinates().getX()==cards.get(2).getCoordinates().getX()+1))||
                ((cards.get(0).getCoordinates().getX()==cards.get(2).getCoordinates().getX()+1)&&(cards.get(1).getCoordinates().getX()==cards.get(0).getCoordinates().getX()+1))||
                ((cards.get(1).getCoordinates().getX()==cards.get(2).getCoordinates().getX()+1)&&(cards.get(0).getCoordinates().getX()==cards.get(1).getCoordinates().getX()+1))
            ){
                //ok Cards are adicent
            }
            else{
                return false;
            }
        }

        if(cards.size()>3){//You cant extract more than 3 cards
           return false;
        }

        for(Card ca : cards){
            if(board[ca.getCoordinates().getX()+1][ca.getCoordinates().getY()]==notUsableCard ||
                board[ca.getCoordinates().getX()+1][ca.getCoordinates().getY()]==null ||
                board[ca.getCoordinates().getX()-1][ca.getCoordinates().getY()]==notUsableCard ||
                board[ca.getCoordinates().getX()-1][ca.getCoordinates().getY()]==null ||
                board[ca.getCoordinates().getX()][ca.getCoordinates().getY()+1]==notUsableCard ||
                board[ca.getCoordinates().getX()][ca.getCoordinates().getY()+1]==null ||
                board[ca.getCoordinates().getX()][ca.getCoordinates().getY()-1]==notUsableCard ||
                board[ca.getCoordinates().getX()][ca.getCoordinates().getY()-1]==null){


            }
            else{
                return false;
            }
            return true;
        }

        return null;
    }
    public int getFreeposition(){
        int l=0 , row,col ;
        for(row=0 ; row<9 ; row++){
            for(col=0 ; col<9 ; col++){
                if(board[row][col]!=notUsableCard){
                    l++;
                }
            }
        }
        return l;
    }

    public void takeCard (Position position){
        if(board[position.getX()][position.getY()].equals(notUsableCard) || board[position.getX()][position.getY()].equals(emptyCard)){
            return;
        }
        board[position.getX()][position.getY()]=emptyCard;
    }
    public Bag getBag(){
        return bag;
    }
    public Card[][] getBoard(){
        return board;
    }
    public int getNumOfPlayers(){
        return numOfPlayers;
    }
    public Card getCard(int x , int y){ return board[x][y];}

}
