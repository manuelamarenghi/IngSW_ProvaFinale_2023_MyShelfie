package it.polimi.ingsw;

<<<<<<< HEAD
public class Library {
    private Card library[][];
    public Library (){}
=======
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
 * this class is about personal library
 */
public class Library {
    /*
     * library is a matrix of cards that are insert from library's player
     */
    private Card[][] library=new Card[6][5];
    /*
     * the constructor initialize 6x5 matrix
     */
    public Library() {
        for (int i=0;i<6;i++)
        { for(int j=0;j<5;j++){
            library[i][j]=new Card();}
        }
    }
    /*
     * getCardinPos() returns the card in the position you asked for
     */
    public Card getCardinPos(int x, int y){
        if(library[x][y]!=null)
        {return library[x][y];}
        return null;
    }
    /*
     * showColumn() returns where you can put n cards ( chosen from the player)
     */
    public int[] showColumn (int n){
        int[] temp= new int[]{1,2,3,4,5};
        int size=0;
        int i=0;
        int j=0;
        while(i<5){
            if(!library[n-1][i].getColour().isEmpty()){
                temp[i]=-1;
                size++;
            }
            i++;
        }
        int[] results=new int[5-size];
        for(i=0;i<5;i++){
            if(temp[i]!=-1){
                results[j]=temp[i];
                j++;
            }
        }
        return results;}
    /*
     * showLibrary() let you see your library
     */
    public void showLibrary(){
        System.out.println(" *** Your library ***");
        for(int i=0;i<6;i++){
            for(int j=0;j<5;j++){
                if( library[i][j]!=null){
                    System.out.print(" | "+library[i][j].getColour());
                }
                else {  System.out.print(" | "+"  "+"  ");
            }
        }
            System.out.print(" | ");
            System.out.println();
    }}
    /*
     * setColumn() puts n cards in a chosen column
     */
    public void setColumn(Card[] cards,int n){
        int i=5;
        int l=0;
        while(i>0 && l!= cards.length){
            if(library[i-1][n].getColour().isEmpty()){
                library[i-1][n]=cards[l];
                l++;
            }
            i--;
        }
    }
    /*
     * takeAction() makes the player decide where to put the selected cards
     */
    public void takeAction(Card[] cards){
        Scanner in=new Scanner(System.in);
        int a;
        List<Integer> list=Arrays.stream(showColumn(cards.length)).boxed().collect(Collectors.toList());
        showLibrary();
        System.out.println("You can put your cards in columns:"+ Arrays.toString(showColumn(cards.length)));
        do {
            System.out.println("Select column or -1 to stop:");
             a = in.nextInt();
             if(a==-1){ break;}
            if (list.contains(a)){
                setColumn(cards,a);
            }
        }while(!list.contains(a));
    }
    /*
     * getgroup() returns adjacent items in a given library
     */
    public void getgroup(){
        List<List<Object>> list;
        for(int i=0;i<6;i++){
          for(int j=0;j<5;j++){

          }
        }

    }
>>>>>>> origin/testb
}
