package it.polimi.ingsw.CommonGoalCard;

import it.polimi.ingsw.CommonGoalCards;
import it.polimi.ingsw.Library;
import it.polimi.ingsw.Player;

import java.util.ArrayList;

public class Col3G implements CommonGoalCards {
    @Override
    public boolean check(Library library) {
        int r , c , i;
        int [] differentColours = {0,0,0,0,0,0};
        int differentCols=0 ,totalTypes , numberOfCards=0;
        for(c=0 ;c<5 ; c++){
            numberOfCards=0;
            totalTypes=0;
            for(i=0 ; i<6 ; i++){
                differentColours[i]=0;
            }
            for(r=0 ;r<6 ; r++){
                if(library.getCardinPos(r,c).getColour()=="Green"){
                    differentColours[0]++;
                    numberOfCards++;
                }
                else if(library.getCardinPos(r,c).getColour()=="White"){
                    differentColours[1]++;
                    numberOfCards++;
                }
                else if(library.getCardinPos(r,c).getColour()=="Yellow"){
                    differentColours[2]++;
                    numberOfCards++;
                }
                else if(library.getCardinPos(r,c).getColour()=="Navy"){
                    differentColours[3]++;
                    numberOfCards++;
                }
                else if(library.getCardinPos(r,c).getColour()=="Blue"){
                    differentColours[4]++;
                    numberOfCards++;
                }
                else if(library.getCardinPos(r,c).getColour()=="Pink"){
                    differentColours[5]++;
                    numberOfCards++;
                }

            }
            for(i=0 ; i<6 ; i++){
                if(differentColours[i]!=0){
                    totalTypes++;
                }
            }
            if(totalTypes<=3 && numberOfCards==6){
                differentCols++;
            }


        }
        if(differentCols>=3){
            return true;
        }
        return false;
    }

    @Override
    public boolean expired(Library library) {
        int r , c , i ;
        int [] differentTypes= {0,0,0,0,0,0};
        int differentCols=5 , totalTypes;
        for(c=0 ; c<5 ; c++){
            totalTypes=0;
            for(i=0 ; i<6 ; i++){
                differentTypes[i]=0;
            }
            for(r=0 ;r<6 ; r++){
                if(library.getCardinPos(r,c).getColour()=="Green"){
                    differentTypes[0]++;
                }
                else if(library.getCardinPos(r,c).getColour()=="White"){
                    differentTypes[1]++;
                }
                else if(library.getCardinPos(r,c).getColour()=="Yellow"){
                    differentTypes[2]++;
                }
                else if(library.getCardinPos(r,c).getColour()=="Navy"){
                    differentTypes[3]++;
                }
                else if(library.getCardinPos(r,c).getColour()=="Blue"){
                    differentTypes[4]++;
                }
                else if(library.getCardinPos(r,c).getColour()=="Pink"){
                    differentTypes[5]++;
                }
            }
            for(i=0 ; i<6 ; i++){
                if(differentTypes[i]!=0){
                    totalTypes++;
                }
            }
            if(totalTypes<=3){
                differentCols--;
            }
        }
        if(differentCols>=3){
            return true;
        }

        return false;
    }

    @Override
    public void getImage() {
        Library l=new Library();
        l.getCardinPos(0,0).setColour("Green");
        l.getCardinPos(0,1).setColour("Yellow");
        l.getCardinPos(0,2).setColour("Navy");
        l.getCardinPos(0,3).setColour("Green");
        l.getCardinPos(0,4).setColour("Navy");
        l.getCardinPos(0,5).setColour("Green");

        l.getCardinPos(2,0).setColour("Green");
        l.getCardinPos(2,1).setColour("White");
        l.getCardinPos(2,2).setColour("White");
        l.getCardinPos(2,3).setColour("White");
        l.getCardinPos(2,4).setColour("Green");
        l.getCardinPos(2,5).setColour("Green");

        l.getCardinPos(4,0).setColour("Green");
        l.getCardinPos(4,1).setColour("Navy");
        l.getCardinPos(4,2).setColour("Blue");
        l.getCardinPos(4,3).setColour("Blue");
        l.getCardinPos(4,4).setColour("Blue");
        l.getCardinPos(4,5).setColour("Green");
        System.out.println("This is an example of a library that respects this goal");
        l.showLibrary();
    }
}
