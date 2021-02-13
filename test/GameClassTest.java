import edu.colorado.fantasticfour.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class GameClassTest {
    private Game game;

    @Before
    public void setUp(){
        game = new Game();
    }

    @Test
    public void gameAssignsTurn() {
//      assert that its player 1's turn
        Assert.assertTrue(game.whoseTurn() == game.getPlayer("1"));
    }

}
