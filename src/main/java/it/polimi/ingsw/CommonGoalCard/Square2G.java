package it.polimi.ingsw.CommonGoalCard;

import it.polimi.ingsw.CommonGoalCards;
import it.polimi.ingsw.Library;

import java.util.ArrayList;

public class Square2G implements CommonGoalCards {

    @Override
    public boolean check(Library library) {
        int r , c , i , indexToSkip1=-1 , indexToSkip2=-1;
        //ArrayList<Integer> numOfSquares = new ArrayList<>(6);
        int [] numOfSquares={0,0,0,0,0,0};
        for(r=0 ; r<5 ; r++){
            for(c=0 ; c<4 ;c++){
                if(c==indexToSkip1){
                    indexToSkip1=-1;
                    continue;
                }
                else if (c==indexToSkip2){
                    indexToSkip2=-1;
                    continue;
                }
                if((library.getCardinPos(r, c).getColour().equals(library.getCardinPos(r, c + 1).getColour()) )&&
                        (library.getCardinPos(r, c).getColour().equals(library.getCardinPos(r + 1, c).getColour())) &&
                        (library.getCardinPos(r, c).getColour()==library.getCardinPos(r + 1, c + 1).getColour())){
                    if(library.getCardinPos(r, c).getColour().equals("Green")){
                        numOfSquares[0]++;
                    }
                    else if(library.getCardinPos(r, c).getColour().equals("White")){
                        numOfSquares[1]++;
                    }
                    else if(library.getCardinPos(r, c).getColour().equals("Yellow")){
                        numOfSquares[2]++;
                    }
                    else if(library.getCardinPos(r, c).getColour().equals("Navy")){
                        numOfSquares[3]++;
                    }
                    else if(library.getCardinPos(r, c).getColour().equals("Blue")){
                        numOfSquares[4]++;
                    }
                    else if(library.getCardinPos(r, c).getColour().equals("Pink")){
                        numOfSquares[5]++;
                    }
                    indexToSkip1=c;
                    if(c!=5){
                        indexToSkip2=c+1;
                    }
                    c++;
                }

            }
        }
        for(i=0 ; i<6 ; i++){
            if(numOfSquares[i]>=2){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean expired(Library library) {
        return false;
    }

    @Override
    public void getImage() {

    }
}
