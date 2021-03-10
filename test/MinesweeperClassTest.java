import edu.colorado.fantasticfour.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public class MinesweeperClassTest {
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
        Ship ship = new Minesweeper();
        Assert.assertEquals(2, ship.getLength());
    }

    @Test
    public void canPlaceShip() {
        player1.placeShip("Minesweeper", new Location(3,0), new Location(3,1));
    }

    @Test
    public void canNotPlaceShipInBadLine() {
        try {
            player1.placeShip("Minesweeper", new Location(4,0), new Location(3,1));
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertEquals("Cells are not on a straight line", e.getMessage());
        }

    }

    @Test
    public void canNotPlaceShipOffBoard() {
        try {
            player1.placeShip("Minesweeper", new Location(9,12), new Location(10,12));
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertEquals("One or more locations do not exist on this board", e.getMessage());
        }
    }

    @Test
    public void canNotPlaceShipOfWrongLength() {
        try {
            // this is a minesweeper. Should need 2 Cells
            player1.placeShip("Minesweeper", new Location(3,0), new Location(3,1), new Location(3,2));
            fail(); //never should get here
        }catch (IllegalArgumentException e){
            Assert.assertEquals("Number of cells must match ship length", e.getMessage());
        }
    }

    @Test
    public void testSunkCaptainsQLast() {
        player1.placeShip("Minesweeper", new Location(9,9), new Location(8,9));
        // Captain's quarters is at (8,9)
        Assert.assertEquals("HIT", player2.takeShot(new Location(9,9)));
        // attempt captains quarters
        Assert.assertEquals("SUNK", player2.takeShot(new Location(8,9)));
    }

    @Test
    public void testSunkCaptainsQFirst() {
        player1.placeShip("Minesweeper", new Location(9,9), new Location(8,9));
        // Captain's quarters is at (8,9)
        // attempt captains quarters
        Assert.assertEquals("SUNK", player2.takeShot(new Location(8,9)));
    }
}
