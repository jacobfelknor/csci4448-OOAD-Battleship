import edu.colorado.fantasticfour.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

}
