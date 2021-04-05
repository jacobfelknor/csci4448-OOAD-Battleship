package edu.colorado.fantasticfour.weapons;

import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;

import java.util.ArrayList;
import java.util.List;

public class MineField extends Weapon{

    List<Location> minefield;

    public MineField(Player owner) {
        super(owner);
        this.minefield  = new ArrayList<>();
    }

    @Override
    public String useAt(Location location) {
        this.minefield.add(location);
        return null;
    }

    public void placeMineAt(Location location){
        this.useAt(location);
    }

    public boolean hasMineAt(Location location){
        return this.minefield.contains(location);
    }
}
