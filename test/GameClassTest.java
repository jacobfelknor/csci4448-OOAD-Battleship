import edu.colorado.fantasticfour.game.Game;
import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;
import edu.colorado.fantasticfour.ship.Battleship;
import edu.colorado.fantasticfour.ship.Destroyer;
import edu.colorado.fantasticfour.ship.Minesweeper;
import edu.colorado.fantasticfour.ship.Submarine;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static junit.framework.TestCase.fail;

public class GameClassTest {
    private Game game;
    private Player player1;
    private Player player2;

    public void placePlayerShips(Player player) {
        // captainsQ at 0,0
        player.placeShip("Minesweeper", new Location(0,0), "S");
        // captainsQ at 5,6
        player.placeShip("Destroyer", new Location(5,6), "S");
        // captainsQ at 8,9
        player.placeShip("Battleship", new Location(8,9), "E");
        // captainsQ at (0,9)
        player.placeShip("Submarine", new Location(0,9), "WS");
    }

    @Before
    public void setUp(){
        game = new Game();
        player1 = game.getPlayer("1");
        player2 = game.getPlayer("2");
    }

    public void customInputSetUp(InputStream in){
        // called by tests that need custom input
        game = new Game(in);
        player1 = game.getPlayer("1");
        player2 = game.getPlayer("2");
    }

    @Test
    public void gameAssignsTurn() {
//      assert that its player 1's turn
        Assert.assertSame(game.whoseTurn(), game.getPlayer("1"));
        // toggle turn
        game.toggleTurn();
        Assert.assertSame(game.whoseTurn(), game.getPlayer("2"));
        game.toggleTurn();
        Assert.assertSame(game.whoseTurn(), game.getPlayer("1"));
    }

    @Test
    public void canNotGetBadPlayers(){
        // this should throw exception
        try {
            game.getPlayer("3");
            fail(); // should never be reached
        } catch (IllegalArgumentException e){
            Assert.assertEquals("Game only has player '1' and '2'", e.getMessage());
        }
    }

    public void testShipsWerePlacedForPlayer(Player player){
        Assert.assertEquals(
                player.getShipAt(new Location(0,0)),
                player.getShipByName("Minesweeper")
        );
        Assert.assertEquals(
                player.getShipAt(new Location(3,4)),
                player.getShipByName("Destroyer")
        );
        Assert.assertEquals(
                player.getShipAt(new Location(9,8)),
                player.getShipByName("Battleship")
        );
        Assert.assertEquals(
                player.getShipAt(new Location(3,4, -1)),
                player.getShipByName("Submarine")
        );
    }

    @Test
    public void testCollectShipLocationsFromPlayer(){
//      assuming placement in this order
//        Minesweeper,
//        Destroyer,
//        Battleship,
//        Submarine
        // all our individual input streams
        String playerShipLocations = "0 0\nS\n3 4\nE\n9 8\nN\n3 4 -1\nNW\n";
        // final input stream to send to Game (two ship locations for each player)
        String inputString = playerShipLocations + playerShipLocations;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        customInputSetUp(inputStream);
        game.start();
        // from our given custom inputStream, player1 should now have a battleship placed at 5 5 facing north
        testShipsWerePlacedForPlayer(player1);
        testShipsWerePlacedForPlayer(player2);
    }

    @Test
    public void canSimulateGame(){
        // player1 places their ships
        placePlayerShips(player1);
        placePlayerShips(player2);
        // minesweeper, hit captainsQ on first try
        Assert.assertEquals("SUNK", player1.takeShot(new Location(0,0)));
        Assert.assertEquals("HIT", player2.takeShot(new Location(0,1)));
        Assert.assertEquals("MISS", player1.takeShot(new Location(3,4)));
        Assert.assertEquals("SUNK", player2.takeShot(new Location(0,0)));
        Assert.assertFalse(player2.mustSurrender());
        Assert.assertFalse(player1.mustSurrender());
        // destroyer
        Assert.assertEquals("HIT", player1.takeShot(new Location(5,5)));
        Assert.assertEquals("HIT", player2.takeShot(new Location(5,5)));
        // hit captainsQ
        Assert.assertEquals("MISS", player1.takeShot(new Location(5,6)));
        Assert.assertEquals("MISS", player2.takeShot(new Location(5,6)));
        Assert.assertEquals("SUNK", player1.takeShot(new Location(5,6)));
        Assert.assertEquals("SUNK", player2.takeShot(new Location(5,6)));
        Assert.assertFalse(player2.mustSurrender());
        Assert.assertFalse(player1.mustSurrender());
        //battleship
        Assert.assertEquals("HIT", player1.takeShot(new Location(9,9)));
        Assert.assertEquals("HIT", player2.takeShot(new Location(9,9)));
        Assert.assertEquals("HIT", player1.takeShot(new Location(7,9)));
        Assert.assertEquals("HIT", player2.takeShot(new Location(7,9)));
        // hit captainsQ
        Assert.assertEquals("MISS", player1.takeShot(new Location(8,9)));
        Assert.assertEquals("MISS", player2.takeShot(new Location(8,9)));
        Assert.assertEquals("SUNK", player1.takeShot(new Location(8,9)));
        // submarine
        Assert.assertEquals("HIT", player2.takeShot(new Location(1,9)));
        Assert.assertEquals("MISS", player1.takeShot(new Location(0,9)));
        Assert.assertEquals("MISS", player2.takeShot(new Location(0,9)));
        // hit captainsQ
        Assert.assertEquals("SUNK", player1.takeShot(new Location(0,9)));
        // player 1 should have won
        Assert.assertTrue(player2.mustSurrender());
        Assert.assertFalse(player1.mustSurrender());

    }

}
