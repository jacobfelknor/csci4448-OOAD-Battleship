import edu.colorado.fantasticfour.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import static junit.framework.TestCase.fail;

public class GameClassTest {
    private Game game;

    @Before
    public void setUp(){
        game = new Game();
    }

    @Test
    public void gameAssignsTurn() {
//      assert that its player 1's turn
        Assert.assertSame(game.whoseTurn(), game.getPlayer("1"));
    }

    @Test
    public void canGetPlayers(){
        Player player1 = game.getPlayer("1");
        Player player2 = game.getPlayer("2");
        // this should throw exception
        try {
            Player player3 = game.getPlayer("3");
            fail(); // should never be reached
        } catch (IllegalArgumentException e){
            Assert.assertEquals(e.getMessage(), "Game only has player '1' and '2'");
        }
    }

}
