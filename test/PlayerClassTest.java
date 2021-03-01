import edu.colorado.fantasticfour.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;

public class PlayerClassTest {
    private Game game;
    private Player player1;
    private Player player2;

    @Before
    public void setUp(){
        game = new Game();
        player1 = game.getPlayer("1");
        player2 = game.getPlayer("2");
    }

    @Test
    public void playerCreatesOpponentBoard() {
        // assert that player1's board(s) are initialized
        Board board2 = player1.getTheirBoard();
        Board board1 = player2.getTheirBoard();
        Assert.assertNotNull(board2);
        Assert.assertNotNull(board1);
        // ensure board assignments are correct
        Assert.assertEquals(player1, board1.getPlayer());
        Assert.assertEquals(player2, board2.getPlayer());
    }

    @Test
    public void canGetAllShips(){
        Assert.assertEquals(3, player1.getAllShips().size());
    }

    @Test
    public void canGetAfloatShips(){
        Assert.assertEquals(0, player1.getAfloatShips().size());
        player1.placeShip("Battleship", new Location(9,9), new Location(8,9), new Location(7,9), new Location(6,9));
        Assert.assertEquals(1, player1.getAfloatShips().size());
    }

    @Test
    public void canPlaceShipAndGetHit() {
        player1.placeShip("Battleship", new Location(9,9), new Location(8,9), new Location(7,9), new Location(6,9));
        Assert.assertEquals("HIT", player2.takeShot(8,9));
    }

    @Test
    public void canPlaceShipAndSink(){
        Assert.assertNotNull(player1.getShipByName("Battleship"));
        player1.placeShip("Battleship", new Location(3,0), new Location(3,1), new Location(3,2), new Location(3,3));
        Assert.assertEquals("MISS", player2.takeShot(0,0));
        Assert.assertEquals("MISS", player2.takeShot(5,7));
        Assert.assertEquals("HIT", player2.takeShot(3,0));
        Assert.assertEquals("HIT", player2.takeShot(3,2));
        Assert.assertEquals("HIT", player2.takeShot(3,3));
        Assert.assertEquals("SUNK", player2.takeShot(3,1));
        // sunk
        Assert.assertTrue(player1.getShipByName("Battleship").isSunk());
    }

    @Test
    public void canGetShipByName(){
        Assert.assertNotNull(player1.getShipByName("Minesweeper"));
    }

    @Test
    public void canGetShipByLocation(){
        player1.placeShip("Battleship", new Location(3,0), new Location(3,1), new Location(3,2), new Location(3,3));
        Assert.assertNotNull(player1.getShipAt(new Location(3,1)));
    }

    @Test
    public void canNotGetBogusShipName(){
        try{
            player1.getShipByName("not a name");
            fail();
        }catch (IllegalArgumentException e){
            Assert.assertEquals("Ship not found", e.getMessage());
        }
    }

    @Test
    public void canNotGetBogusShipLocation(){
        Ship battleship = player1.getShipByName("Battleship");
        Assert.assertNotNull(battleship);
        player1.placeShip("Battleship", new Location(3,0), new Location(3,1), new Location(3,2), new Location(3,3));
        try{
            player1.getShipAt(new Location(5,5));
            fail();
        }catch (IllegalArgumentException e){
            Assert.assertEquals("Ship not found", e.getMessage());
        }
    }
}
