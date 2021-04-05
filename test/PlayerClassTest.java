import edu.colorado.fantasticfour.game.Board;
import edu.colorado.fantasticfour.game.Game;
import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;
import edu.colorado.fantasticfour.ship.Ship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
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
    public void canPlayerPlaceShip(){
        player1.placeShip("Battleship", new Location(8,9), "E");
    }

    @Test
    public void canPlayerNotPlaceTwoShipsInSameCell(){
//      (6,9), (7,9), (8,9), (9,9)
        player1.placeShip("Battleship", new Location(8,9), "E");
        try{
            player1.placeShip("Minesweeper", new Location(5,9), "W");
        }catch (IllegalStateException e){
            Assert.assertEquals("Cell already has a Ship observer. Are Ships colliding?", e.getMessage());
        }
    }

    @Test
    public void canGetAllShips(){
        Assert.assertEquals(4, player1.getAllShips().size());
    }

    @Test
    public void canGetAfloatShips(){
        Assert.assertEquals(0, player1.getAfloatShips().size());
        player1.placeShip("Battleship", new Location(8,9), "E");
        Assert.assertEquals(1, player1.getAfloatShips().size());
    }

    @Test
    public void canGetShipByName(){
        Assert.assertNotNull(player1.getShipByName("Minesweeper"));
    }

    @Test
    public void canGetShipByLocation(){
        player1.placeShip("Battleship", new Location(3,2), "N");
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
        player1.placeShip("Battleship", new Location(3,1), "S");
        try{
            player1.getShipAt(new Location(5,5));
            fail();
        }catch (IllegalArgumentException e){
            Assert.assertEquals("Ship not found", e.getMessage());
        }
    }

    @Test
    public void canNotTakeDoubleShot(){
        player1.placeShip("Battleship", new Location(8,9), "E");
        player1.placeShip("Destroyer", new Location(2,3), "E");
        try{
            player1.takeDoubleShot(new Location(8, 9), new Location(5, 5));
        }catch (IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().startsWith("Double shot can only be used with one ship remaining"));
        }
    }

    @Test
    public void canTakeDoubleShot(){
        player1.placeShip("Battleship", new Location(8,9), "E");

        List<String> expected = Arrays.asList("MISS", "MISS");
        List<String> actual = Arrays.asList(player1.takeDoubleShot(new Location(3, 4), new Location(5, 3)));
        Assert.assertEquals(expected, actual);
    }

}
