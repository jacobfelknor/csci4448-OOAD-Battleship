import edu.colorado.fantasticfour.game.Game;
import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;
import edu.colorado.fantasticfour.ship.Submarine;
import edu.colorado.fantasticfour.ship.Ship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public class SubmarineClassTest {
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
        Ship ship = new Submarine();
        Assert.assertEquals(5, ship.getLength());
    }

    @Test
    public void canPlaceShip() {
        // try all different orientations
        player1.placeShip("Submarine", new Location(3,2), "EN");
        player1.placeShip("Submarine", new Location(3,2), "ES");
        player1.placeShip("Submarine", new Location(0,0), "WN");
        player1.placeShip("Submarine", new Location(3,9), "WS");
        // for the remaining, ensure they can be placed under water
        player1.placeShip("Submarine", new Location(5,6, -1), "NW");
        player1.placeShip("Submarine", new Location(5,6, -1), "NE");
        player1.placeShip("Submarine", new Location(5,6, -1), "SE");
        player1.placeShip("Submarine", new Location(5,6, -1), "SW");
    }

    @Test
    public void canNotPlaceShipOffBoard() {
        try {
            player1.placeShip("Submarine", new Location(0,0), "WS");
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().startsWith("One or more locations do not exist on this board"));
        }
    }

    @Test
    public void canNotGiveBadOrientation(){
        // give unknown characters
        try {
            player1.placeShip("Submarine", new Location(0,0), "ZS");
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().startsWith("Unknown orientation."));
        }
        // only give one direction (sub needs two)
        try {
            player1.placeShip("Submarine", new Location(0,0), "N");
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().startsWith("Unknown orientation."));
        }
        // give two directions on same axis
        try {
            player1.placeShip("Submarine", new Location(0,0), "NS");
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().startsWith("Unknown orientation."));
        }
        try {
            player1.placeShip("Submarine", new Location(0,0), "SN");
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().startsWith("Unknown orientation."));
        }
        // other same axis
        try {
            player1.placeShip("Submarine", new Location(0,0), "EW");
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().startsWith("Unknown orientation."));
        }
        try {
            player1.placeShip("Submarine", new Location(0,0), "WE");
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().startsWith("Unknown orientation."));
        }
    }

    @Test
    public void testSunkCaptainsQLast() {
        //        (4,5)
        // (3,4), (4,4), (5,4), (6,4)
        player1.placeShip("Submarine", new Location(3,4), "WN");
        // Captain's quarters is at (3,4)
        Assert.assertEquals("HIT", player2.takeShot(new Location(4,4)));
        Assert.assertEquals("HIT", player2.takeShot(new Location(4,5)));
        Assert.assertEquals("HIT", player2.takeShot(new Location(5,4)));
        Assert.assertEquals("HIT", player2.takeShot(new Location(6,4)));
        // attempt captains quarters
        Assert.assertEquals("MISS", player2.takeShot(new Location(3,4)));
        Assert.assertEquals("SUNK", player2.takeShot(new Location(3,4)));
    }

    @Test
    public void testSunkCaptainsQFirst() {
        //        (4,5)
        // (3,4), (4,4), (5,4), (6,4)
        player1.placeShip("Submarine", new Location(3,4), "WN");
        // Captain's quarters is at (3,4)
        // attempt captains quarters
        Assert.assertEquals("MISS", player2.takeShot(new Location(3,4)));
        Assert.assertEquals("SUNK", player2.takeShot(new Location(3,4)));
    }

    @Test
    public void testSunkCaptainsQMiddle() {
        //        (4,5)
        // (3,4), (4,4), (5,4), (6,4)
        player1.placeShip("Submarine", new Location(3,4), "WN");
        // Captain's quarters is at (3,4)
        Assert.assertEquals("HIT", player2.takeShot(new Location(4,4)));
        // attempt captains quarters
        Assert.assertEquals("MISS", player2.takeShot(new Location(3,4)));
        Assert.assertEquals("SUNK", player2.takeShot(new Location(3,4)));
    }
}
