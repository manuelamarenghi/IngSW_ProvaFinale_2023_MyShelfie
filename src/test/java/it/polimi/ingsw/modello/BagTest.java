package it.polimi.ingsw.modello;
import it.polimi.ingsw.modello.Bag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    @Test
    public void checkNumberOfCardsWith2PlayerTest(){
        Bag bag = new Bag();
        ArrayList<String> cards = new ArrayList<>();
        cards=bag.extract(2 , 0);
        assertEquals(29 , cards.size());
    }

    @Test
    public void checkNumberOfCardsWith3PlayerTest(){
        Bag bag = new Bag();
        ArrayList<String> cards = new ArrayList<>();
        cards=bag.extract(3 , 0);
        assertEquals(37 , cards.size());
    }

    @Test
    public void checkNumberOfCardsWith4PlayerTest(){
        Bag bag = new Bag();
        ArrayList<String> cards = new ArrayList<>();
        cards=bag.extract(4 , 2);
        assertEquals(45-2 , cards.size());
    }
}