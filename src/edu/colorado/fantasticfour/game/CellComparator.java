package edu.colorado.fantasticfour.game;

import java.util.Comparator;

public class CellComparator implements Comparator<Cell> {

    @Override
    public int compare(Cell a, Cell b){
        if(a.getLocation().getX() == b.getLocation().getX()){
            // if x's are equal, sort by y's
            return a.getLocation().getY() - b.getLocation().getY();
        }else{
            // otherwise, sort by x's (y's must be equal)
            assert a.getLocation().getY() == b.getLocation().getY();
            return a.getLocation().getX() - b.getLocation().getX();
        }
    }
}