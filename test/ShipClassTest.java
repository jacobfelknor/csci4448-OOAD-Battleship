import edu.colorado.fantasticfour.Game;
import edu.colorado.fantasticfour.Player;
import edu.colorado.fantasticfour.Ship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.fail;

public class ShipClassTest {
    private Ship ship;
    private Player player1;
    private Game game;

    public void createShip(String name,int length){
        ship = new Ship(name, length);
    }

    @Before
    public void setUp(){
        game = new Game();
        player1 = new Player();
        ship = player1.getShipByName("Battleship");
    }

    @Test
    public void shipKnowsDimension(){
        createShip("Minesweeper",2);
        Assert.assertEquals(ship.getLength(), 2);
    }

    @Test
    public void canSinkShip(){
        createShip("Minesweeper", 2);
        Assert.assertEquals(ship.getHitCount(), 0);
        ship.gotHit();
        Assert.assertEquals(ship.getHitCount(), 1);
        ship.gotHit();
        Assert.assertTrue(ship.isSunk());
    }

    @Test
    public void canPlaceShip() {
        ship.place(new int[]{3, 0, 3, 3});
    }

    @Test
    public void canNotPlaceShipInBadLine() {
        try {
            ship.place(new int[]{3, 0, 5, 3});
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertEquals(e.getMessage(), "Coordinates are not on a straight line");
        }

    }

    @Test
    public void canNotPlaceShipOffBoard() {
        try {
            ship.place(new int[]{6,7,12,4});
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertEquals(e.getMessage(), "Ship needs to be placed within 10x10 grid");
        }
    }

    @Test
    public void canNotPlaceShipOfWrongLength() {
        try {
            ship.place(new int[]{6,0,6,5});
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertEquals(e.getMessage(), "Coordinates given are not of correct length");
        }
    }
}
