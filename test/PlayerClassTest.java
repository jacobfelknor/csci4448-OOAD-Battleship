import edu.colorado.fantasticfour.Game;
import edu.colorado.fantasticfour.Ship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PlayerClassTest {
    private Game game;

    @Before
    public void setUp(){
        game = new Game();
    }

    @Test
    public void playerCreatesBoard() {
        // assert that player1's board(s) are initialized
        Assert.assertNotNull(game.getPlayer("1").getMyBoard());
    }

    @Test
    public void playerHasShips() {
        List<Ship> ships = game.getPlayer("1").getAfloatShips();
        Assert.assertEquals(ships.size(), 5);
    }

    @Test
    public void playerCanPlaceShip() {

    }
}
