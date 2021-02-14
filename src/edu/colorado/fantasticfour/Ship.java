package edu.colorado.fantasticfour;

// This is the  baseclass for your ship.  Modify accordingly
// TODO: practice good OO design
public class Ship {
    private String name;
    private int length;
    private int hitCount;
    private int[] coordinates;

    public Ship(String name, int length) {
        this.name = name;
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
        return getHitCount() >= getLength();
    }

    private int getLengthFromCoordinates(int x0, int y0, int x1, int y1) throws IllegalArgumentException{
        if(x0 - x1 == 0){
            return Math.abs(y0 - y1) + 1;
        }else if(y0 - y1 == 0){
            return Math.abs(x0 - x1) + 1;
        }
        throw new IllegalArgumentException("Coordinates are not on a straight line");
    }

    public int[] getCoordinates() {
        return this.coordinates;
    }

    public void place(int[] coordinates) throws IllegalArgumentException{
        int x0 = coordinates[0];
        int y0 = coordinates[1];
        int x1 = coordinates[2];
        int y1 = coordinates[3];
        if (!(x0 <= 9 && x1 <= 9 && y0 <= 9 && y1 <= 9)){
            throw new IllegalArgumentException("Ship needs to be placed within 10x10 grid");
        }
        if (getLengthFromCoordinates(x0, y0, x1, y1) != getLength()){
            throw new IllegalArgumentException("Coordinates given are not of correct length");
        }
        this.coordinates = coordinates;
    }

}
