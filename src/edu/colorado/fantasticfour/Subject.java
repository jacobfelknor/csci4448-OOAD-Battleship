package edu.colorado.fantasticfour;

public interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    boolean hasObservers();
    String notifyObservers();
}
