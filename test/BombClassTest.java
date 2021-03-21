import edu.colorado.fantasticfour.game.Game;
import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BombClassTest {
    Player player1, player2;
    @Before
    public void setUp(){
        Game game = new Game();
        player1 = game.getPlayer("1");
        player2 = game.getPlayer("2");
    }

    @Test
    public void canBeUsedOnSurface(){
        player1.placeShip("Battleship", new Location(8,9), "E");
        Assert.assertEquals("HIT", player2.takeShot(new Location(9,9)));
    }

    @Test
    public void canNotBeUsedUnderSurface(){
        player1.placeShip("Submarine", new Location(4,5,-1), "NW");
        try{
            player2.takeShot(new Location(4,5,-1));
        }catch (IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().startsWith("A Bomb can only be used on the surface"));
        }
    }
}
