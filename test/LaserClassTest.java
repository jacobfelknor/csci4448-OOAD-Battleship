import edu.colorado.fantasticfour.game.Game;
import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LaserClassTest {
    Player player1, player2;
    @Before
    public void setUp(){
        Game game = new Game();
        player1 = game.getPlayer("1");
        player2 = game.getPlayer("2");
        // I need to sink a random ship before Laser can be used
        player1.placeShip("Minesweeper", new Location(0,0), "W");
        player2.takeShot(new Location(0,0));
    }

    @Test
    public void canBeUsedOnSurface(){
        player1.placeShip("Battleship", new Location(8,9), "E");
        Assert.assertEquals("HIT", player2.takeShot(new Location(9,9)));
    }

    @Test
    public void canBeUsedUnderSurface(){
        player1.placeShip("Destroyer", new Location(4,4), "N");
        player1.placeShip("Submarine", new Location(4,5,-1), "NW");
        // this HIT is on Destroyer, but Sub's captains q has armor
        Assert.assertEquals("HIT", player2.takeShot(new Location(4,5)));
        // this SUNK is sinking the Sub, but no changes with Destroyer
        Assert.assertEquals("SUNK", player2.takeShot(new Location(4,5)));
    }
}
