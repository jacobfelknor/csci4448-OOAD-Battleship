package edu.colorado.fantasticfour.observer;

public interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    boolean hasObservers();
    String notifyObservers();
}
