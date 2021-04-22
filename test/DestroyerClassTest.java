import edu.colorado.fantasticfour.game.Game;
import edu.colorado.fantasticfour.game.LocalGame;
import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;
import edu.colorado.fantasticfour.ship.Destroyer;
import edu.colorado.fantasticfour.ship.Ship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public class DestroyerClassTest {
    private Player player1;
    private Player player2;


    @Before
    public void setUp(){
        Game game = new LocalGame();
        player1 = game.getPlayer("1");
        player2 = game.getPlayer("2");
    }

    @Test
    public void shipKnowsDimension(){
        Ship ship = new Destroyer();
        Assert.assertEquals(3, ship.getLength());
    }

    @Test
    public void canPlaceShip() {
        player1.placeShip("Destroyer", new Location(3,1), "E");
    }

    @Test
    public void canNotPlaceShipOffBoard() {
        try {
            player1.placeShip("Destroyer", new Location(0,0), "N");
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().startsWith("One or more locations do not exist on this board"));
        }
    }

    @Test
    public void canNotGiveBadOrientation(){
        try {
            player1.placeShip("Destroyer", new Location(0,0), "Z");
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().startsWith("Unknown orientation. Must be N,S,E, or W"));
        }
    }

    @Test
    public void testSunkCaptainsQLast() {
        player1.placeShip("Destroyer", new Location(8,9), "E");
        // Captain's quarters is at (8,9)
        Assert.assertEquals("HIT", player2.takeShot(new Location(9,9)));
        Assert.assertEquals("HIT", player2.takeShot(new Location(7,9)));
        // attempt captains quarters
        Assert.assertEquals("MISS", player2.takeShot(new Location(8,9)));
        Assert.assertEquals("SUNK", player2.takeShot(new Location(8,9)));
    }

    @Test
    public void testSunkCaptainsQFirst() {
        player1.placeShip("Destroyer", new Location(8,9), "E");
        // Captain's quarters is at (8,9)
        // attempt captains quarters
        Assert.assertEquals("MISS", player2.takeShot(new Location(8,9)));
        Assert.assertEquals("SUNK", player2.takeShot(new Location(8,9)));
    }

    @Test
    public void testSunkCaptainsQMiddle() {
        player1.placeShip("Destroyer", new Location(8,9), "E");
        // Captain's quarters is at (8,9)
        Assert.assertEquals("HIT", player2.takeShot(new Location(7,9)));
        // attempt captains quarters
        Assert.assertEquals("MISS", player2.takeShot(new Location(8,9)));
        Assert.assertEquals("SUNK", player2.takeShot(new Location(8,9)));
    }
}
