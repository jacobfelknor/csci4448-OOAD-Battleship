import edu.colorado.fantasticfour.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public class BattleshipClassTest {
    private Player player1;
    private Player player2;

    @Before
    public void setUp(){
        Game game = new Game();
        player1 = game.getPlayer("1");
        player2 = game.getPlayer("2");
    }

    @Test
    public void shipKnowsDimension(){
        Ship ship = new Battleship();
        Assert.assertEquals(4, ship.getLength());
    }

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
            Assert.assertEquals("One or more locations do not exist on this board", e.getMessage());
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

    @Test
    public void testSunkCaptainsQLast() {
        player1.placeShip("Battleship", new Location(9,9), new Location(8,9), new Location(7,9), new Location(6,9));
        // Captain's quarters is at (8,9)
        Assert.assertEquals("HIT", player2.takeShot(new Location(9,9)));
        Assert.assertEquals("HIT", player2.takeShot(new Location(7,9)));
        Assert.assertEquals("HIT", player2.takeShot(new Location(6,9)));
        // attempt captains quarters
        Assert.assertEquals("MISS", player2.takeShot(new Location(8,9)));
        Assert.assertEquals("SUNK", player2.takeShot(new Location(8,9)));
    }

    @Test
    public void testSunkCaptainsQFirst() {
        player1.placeShip("Battleship", new Location(9,9), new Location(8,9), new Location(7,9), new Location(6,9));
        // Captain's quarters is at (8,9)
        // attempt captains quarters
        Assert.assertEquals("MISS", player2.takeShot(new Location(8,9)));
        Assert.assertEquals("SUNK", player2.takeShot(new Location(8,9)));
    }

    @Test
    public void testSunkCaptainsQMiddle() {
        player1.placeShip("Battleship", new Location(9,9), new Location(8,9), new Location(7,9), new Location(6,9));
        // Captain's quarters is at (8,9)
        Assert.assertEquals("HIT", player2.takeShot(new Location(6,9)));
        // attempt captains quarters
        Assert.assertEquals("MISS", player2.takeShot(new Location(8,9)));
        Assert.assertEquals("SUNK", player2.takeShot(new Location(8,9)));
    }
}
