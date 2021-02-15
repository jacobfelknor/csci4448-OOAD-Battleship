import edu.colorado.fantasticfour.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;

import static junit.framework.TestCase.fail;

public class GameClassTest {
    private Game game;
    private Player player1;
    private Player player2;

    public void placePlayerShips(Player player) {
        HashMap<String, int[]> shipMap = new HashMap<>();
        shipMap.put("Minesweeper", new int[]{0, 0, 0, 1});
        shipMap.put("Destroyer", new int[]{5, 5, 5, 7});
        shipMap.put("Battleship", new int[]{9, 9, 6, 9});
        for (Ship ship : player.getAllShips()) {
            ship.place(shipMap.get(ship.getName()));
            Assert.assertEquals(ship.getCoordinates(), shipMap.get(ship.getName()));
        }
    }

    @Before
    public void setUp(){
        game = new Game();
        player1 = game.getPlayer("1");
        player2 = game.getPlayer("2");
    }

    @Test
    public void gameAssignsTurn() {
//      assert that its player 1's turn
        Assert.assertSame(game.whoseTurn(), game.getPlayer("1"));
    }

    @Test
    public void canGetPlayers(){
        // this should throw exception
        try {
            Player player3 = game.getPlayer("3");
            fail(); // should never be reached
        } catch (IllegalArgumentException e){
            Assert.assertEquals(e.getMessage(), "Game only has player '1' and '2'");
        }
    }

    @Test
    public void canPlayGame(){
        // player1 places their ships
        placePlayerShips(player1);
        placePlayerShips(player2);
        // simulate turns for
        // minesweeper
        Assert.assertEquals(player1.takeShot(0,0), "HIT");
        Assert.assertEquals(player2.takeShot(0,1), "HIT");
        Assert.assertEquals(player1.takeShot(0,1), "SUNK");
        Assert.assertEquals(player2.takeShot(0,0), "SUNK");
        Assert.assertFalse(player2.mustSurrender());
        Assert.assertFalse(player1.mustSurrender());
        // destroyer
        Assert.assertEquals(player1.takeShot(5,5), "HIT");
        Assert.assertEquals(player2.takeShot(5,5), "HIT");
        Assert.assertEquals(player1.takeShot(5,6), "HIT");
        Assert.assertEquals(player2.takeShot(5,6), "HIT");
        Assert.assertEquals(player1.takeShot(5,7), "SUNK");
        Assert.assertEquals(player2.takeShot(5,7), "SUNK");
        Assert.assertFalse(player2.mustSurrender());
        Assert.assertFalse(player1.mustSurrender());
        //battleship
        Assert.assertEquals(player1.takeShot(9,9), "HIT");
        Assert.assertEquals(player2.takeShot(9,9), "HIT");
        Assert.assertEquals(player1.takeShot(8,9), "HIT");
        Assert.assertEquals(player2.takeShot(8,9), "HIT");
        Assert.assertEquals(player1.takeShot(7,9), "HIT");
        Assert.assertEquals(player2.takeShot(7,9), "HIT");
        Assert.assertEquals(player1.takeShot(6,9), "SUNK");
        // player 1 should have won
        Assert.assertTrue(player2.mustSurrender());
        Assert.assertFalse(player1.mustSurrender());

    }

}
