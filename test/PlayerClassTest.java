import edu.colorado.fantasticfour.Board;
import edu.colorado.fantasticfour.Game;
import edu.colorado.fantasticfour.Player;
import edu.colorado.fantasticfour.Ship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

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
        Assert.assertEquals(board1.getPlayer(), player1);
        Assert.assertEquals(board2.getPlayer(), player2);
    }

    @Test
    public void canGetAllShips(){
        Assert.assertEquals(player1.getAllShips().size(), 3);
    }

    @Test
    public void canGetAfloatShips(){
        Assert.assertEquals(player1.getAfloatShips().size(), 0);
        Ship battleship = player1.getShipByName("Battleship");
        battleship.place(new int[]{9,9,6,9});
        Assert.assertEquals(player1.getAfloatShips().size(), 1);
    }

    @Test
    public void canPlaceShipAndGetHit() {
        HashMap<String, int[]> shipMap = new HashMap<>();
        shipMap.put("Minesweeper", new int[]{0, 0, 0, 1});
        shipMap.put("Destroyer", new int[]{5,5,5,7});
        shipMap.put("Battleship", new int[]{9,9,6,9});
        for(Ship ship : player1.getAllShips()){
            ship.place(shipMap.get(ship.getName()));
            Assert.assertEquals(ship.getCoordinates(), shipMap.get(ship.getName()));
            // get a coordinate for this ship
            int x, y;
            if (ship.getName().equals("Minesweeper")){
                x = 0;
                y = 1;
            }else if (ship.getName().equals("Destroyer")){
                x = 5;
                y = 6;
            }else{
                // Battleship
                x = 8;
                y = 9;
            }
            Assert.assertEquals(player2.takeShot(x,y), "HIT");
            Assert.assertEquals(ship.getHitCount(), 1);
        }
    }

    @Test
    public void canPlaceShipAndSink(){
        Ship battleship = player1.getShipByName("Battleship");
        Assert.assertNotNull(battleship);
        battleship.place(new int[]{3, 0, 3, 3});
        Assert.assertEquals(player2.takeShot(0,0), "MISS");
        Assert.assertEquals(player2.takeShot(5,7), "MISS");
        Assert.assertEquals(player2.takeShot(3,0), "HIT");
        Assert.assertEquals(player2.takeShot(3,2), "HIT");
        Assert.assertEquals(player2.takeShot(3,3), "HIT");
        Assert.assertEquals(player2.takeShot(3,1), "SUNK");
        // sunk
        Assert.assertTrue(battleship.isSunk());
    }

    @Test
    public void canNotShootSamePlaceTwice(){
        Ship battleship = player1.getShipByName("Battleship");
        Assert.assertNotNull(battleship);
        battleship.place(new int[]{3, 0, 3, 3});
        Assert.assertEquals(player2.takeShot(0,0), "MISS");
        try {
            player2.takeShot(0,0);
            fail(); //should never reach
        }catch (IllegalArgumentException e){
            Assert.assertEquals(e.getMessage(), "This coordinate has already been shot at");
        }
    }

    @Test
    public void canNotGetBogusShipName(){
        Ship battleship = player1.getShipByName("Battleship");
        Assert.assertNotNull(battleship);
        battleship.place(new int[]{3, 0, 3, 3});
        Assert.assertNull(player1.getShipByName("not a name"));
    }

    @Test
    public void canNotGetBogusShipLocation(){
        Ship battleship = player1.getShipByName("Battleship");
        Assert.assertNotNull(battleship);
        battleship.place(new int[]{3, 0, 3, 3});
        Assert.assertNull(player1.getShipAt(5,5));
    }
}
