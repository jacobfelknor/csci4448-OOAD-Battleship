package edu.colorado.fantasticfour.game;

import edu.colorado.fantasticfour.location.Location;
import edu.colorado.fantasticfour.observer.Observer;
import edu.colorado.fantasticfour.observer.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cell implements Subject {
    private List<Observer> observers;
    private Location location;
    public Cell(Location location) throws IllegalArgumentException{
        this.location = location;
        this.observers = new ArrayList<>();
    }

    public Location getLocation(){
        return this.location;
    }

    @Override
    public void addObserver(Observer o) {
        // In this specific use case, only makes sense to have one observer (being the ship that is here)
        // no two ships can exist in the same cell.
        if(this.observers.size() == 1){
            throw new IllegalStateException("Cell already has a Ship observer. Are Ships colliding?");
        }
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public boolean hasObservers() {
        return this.observers.size() > 0;
    }

    @Override
    public String notifyObservers() {
        if(!this.observers.isEmpty()){
            Observer target = this.observers.get(0); // a Cell only has a single observer, its ship
            return target.update(this);
        }
        return "MISS";
    }

    @Override
    public String toString(){
        return "Cell<" + getLocation().toString() + ">";
    }

    @Override
    public boolean equals(Object o){
        /* from https://www.geeksforgeeks.org/overriding-equals-method-in-java/ */
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Cell or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Cell)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Cell c = (Cell) o;

        return this.location.equals(c.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }
}