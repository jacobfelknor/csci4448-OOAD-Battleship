package edu.colorado.fantasticfour;

// This is the  baseclass for your ship.  Modify accordingly
// TODO: practice good OO design
public class Ship {
    private String name;
    private int length;
    private int hitCount;

    public Ship(int length) {
        this.length = length;
        this.hitCount = 0;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public void gotHit(){
        hitCount++;
    }

    public boolean isSunk(){
        if (getHitCount() >= getLength()){
            return true;
        }
        return false;
    }

    public void show() { // dunno why this is here maybe it is just an example method
        System.out.println("IF you can't see this then something is severely wrong!!");
    }
}
