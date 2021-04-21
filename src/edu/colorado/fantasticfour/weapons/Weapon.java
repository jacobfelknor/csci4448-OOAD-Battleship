package edu.colorado.fantasticfour.weapons;

import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;

public abstract class Weapon {
    Player owner;
    private String name = "Bomb";

    //public String getName(){return this.name;}
    public Weapon(Player owner){
        this.owner = owner;
    }
    public abstract String useAt(Location location);
}
