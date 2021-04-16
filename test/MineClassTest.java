import edu.colorado.fantasticfour.game.Game;
import edu.colorado.fantasticfour.game.LocalGame;
import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MineClassTest {
    Game game;
    Player player1;
    Player player2;

    @Before
    public void setUp(){
        game = new LocalGame();
        player1 = game.getPlayer("1");
        player2 = game.getPlayer("2");
    }

    @Test
    public void canPlaceMine(){
        player1.placeMine(new Location(5,5));
    }

    @Test
    public void mineWorks(){
        Location target = new Location(5,5);
        player2.placeMine(target);
        player1.placeShip("Minesweeper", target, "N");
        Assert.assertNotNull(player1.getShipAt(target));
        // since we placed a Ship where a Mine was, this ship should now be sunk
        Assert.assertTrue(player1.getShipByName("Minesweeper").isSunk());
    }

}
