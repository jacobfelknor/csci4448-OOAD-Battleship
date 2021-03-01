import edu.colorado.fantasticfour.Location;
import edu.colorado.fantasticfour.Game;
import edu.colorado.fantasticfour.Player;
import edu.colorado.fantasticfour.Ship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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
        Assert.assertEquals(2, ship.getLength());
    }

//    @Test
//    public void canSinkShip(){
//        createShip("Minesweeper", 2);
//        Assert.assertEquals(0, ship.getHitCount());
//        ship.gotHit();
//        Assert.assertEquals(1, ship.getHitCount());
//        ship.gotHit();
//        Assert.assertTrue(ship.isSunk());
//    }

    @Test
    public void canPlaceShip() {
        player1.placeShip("Battleship", new Location(3,0), new Location(3,1), new Location(3,2), new Location(3,3));
    }

    @Test
    public void canNotPlaceShipInBadLine() {
        try {
            player1.placeShip("Battleship", new Location(4,0), new Location(3,1), new Location(3,2), new Location(3,3));
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertEquals("Cells are not on a straight line", e.getMessage());
        }

    }

    @Test
    public void canNotPlaceShipOffBoard() {
        try {
            player1.placeShip("Battleship", new Location(9,12), new Location(10,12), new Location(11,12), new Location(12,12));
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertEquals("Cell needs to exist within 10x10 grid", e.getMessage());
        }
    }

    @Test
    public void canNotPlaceShipOfWrongLength() {
        try {
            // this is a battleship. Should need 4 Cells
            player1.placeShip("Battleship", new Location(3,0), new Location(3,1), new Location(3,2));
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertEquals("Number of cells must match ship length", e.getMessage());
        }
    }
}
