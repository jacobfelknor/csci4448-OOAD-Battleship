import edu.colorado.fantasticfour.command.MoveFleetCommand;
import edu.colorado.fantasticfour.game.Game;
import edu.colorado.fantasticfour.game.LocalGame;
import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoveFleetCommandTest {
    Game game;
    Player player;

    @Before
    public void setUp(){
        game = new LocalGame();
        player = game.getPlayer("1");
    }

    public void generateTestCaseOne(){
        player.placeShip("Battleship", new Location(1,9), "W");
        player.placeShip("Minesweeper", new Location(2, 7), "W");
        player.placeShip("Destroyer", new Location(5, 2), "N");
        player.placeShip("Submarine", new Location(2,6,-1), "NE");
    }

    @Test
    public void canMoveFleetValid(){
        generateTestCaseOne();
        player.moveFleet("E");
        // assert new locations
        Assert.assertEquals(new Location(2,9), player.getShipByName("Battleship").getCaptainsQuarters());
        Assert.assertEquals(new Location(3,7), player.getShipByName("Minesweeper").getCaptainsQuarters());
        Assert.assertEquals(new Location(6,2), player.getShipByName("Destroyer").getCaptainsQuarters());
        Assert.assertEquals(new Location(3,6, -1), player.getShipByName("Submarine").getCaptainsQuarters());
        // move again
        player.moveFleet("E");
        Assert.assertEquals(new Location(3,9), player.getShipByName("Battleship").getCaptainsQuarters());
        Assert.assertEquals(new Location(4,7), player.getShipByName("Minesweeper").getCaptainsQuarters());
        Assert.assertEquals(new Location(7,2), player.getShipByName("Destroyer").getCaptainsQuarters());
        Assert.assertEquals(new Location(4,6, -1), player.getShipByName("Submarine").getCaptainsQuarters());
        // undo twice
        player.undoMoveFleet();
        player.undoMoveFleet();
        // should be original locations
        Assert.assertEquals(new Location(1,9), player.getShipByName("Battleship").getCaptainsQuarters());
        Assert.assertEquals(new Location(2,7), player.getShipByName("Minesweeper").getCaptainsQuarters());
        Assert.assertEquals(new Location(5,2), player.getShipByName("Destroyer").getCaptainsQuarters());
        Assert.assertEquals(new Location(2,6, -1), player.getShipByName("Submarine").getCaptainsQuarters());
        // redo once
        player.redoMoveFleet();
        // should be in intermediate locations
        Assert.assertEquals(new Location(2,9), player.getShipByName("Battleship").getCaptainsQuarters());
        Assert.assertEquals(new Location(3,7), player.getShipByName("Minesweeper").getCaptainsQuarters());
        Assert.assertEquals(new Location(6,2), player.getShipByName("Destroyer").getCaptainsQuarters());
        Assert.assertEquals(new Location(3,6, -1), player.getShipByName("Submarine").getCaptainsQuarters());
        // redo again
        player.redoMoveFleet();
        Assert.assertEquals(new Location(3,9), player.getShipByName("Battleship").getCaptainsQuarters());
        Assert.assertEquals(new Location(4,7), player.getShipByName("Minesweeper").getCaptainsQuarters());
        Assert.assertEquals(new Location(7,2), player.getShipByName("Destroyer").getCaptainsQuarters());
        Assert.assertEquals(new Location(4,6, -1), player.getShipByName("Submarine").getCaptainsQuarters());
        // move fleet south
        player.moveFleet("S");
        Assert.assertEquals(new Location(3,8), player.getShipByName("Battleship").getCaptainsQuarters());
        Assert.assertEquals(new Location(4,6), player.getShipByName("Minesweeper").getCaptainsQuarters());
        Assert.assertEquals(new Location(7,1), player.getShipByName("Destroyer").getCaptainsQuarters());
        Assert.assertEquals(new Location(4,5, -1), player.getShipByName("Submarine").getCaptainsQuarters());
        // try to redo... shouldn't throw error, but since we pushed to undo stack, redo stack should be cleared
        player.redoMoveFleet();
        Assert.assertEquals(new Location(3,8), player.getShipByName("Battleship").getCaptainsQuarters());
        Assert.assertEquals(new Location(4,6), player.getShipByName("Minesweeper").getCaptainsQuarters());
        Assert.assertEquals(new Location(7,1), player.getShipByName("Destroyer").getCaptainsQuarters());
        Assert.assertEquals(new Location(4,5, -1), player.getShipByName("Submarine").getCaptainsQuarters());
        // undo once more (to hit the move N case)
        player.undoMoveFleet();
        Assert.assertEquals(new Location(3,9), player.getShipByName("Battleship").getCaptainsQuarters());
        Assert.assertEquals(new Location(4,7), player.getShipByName("Minesweeper").getCaptainsQuarters());
        Assert.assertEquals(new Location(7,2), player.getShipByName("Destroyer").getCaptainsQuarters());
        Assert.assertEquals(new Location(4,6, -1), player.getShipByName("Submarine").getCaptainsQuarters());
    }

    @Test
    public void oppositeDirectionAccurate(){
        Assert.assertEquals("S", MoveFleetCommand.oppositeDirectionOf("N"));
        Assert.assertEquals("N", MoveFleetCommand.oppositeDirectionOf("S"));
        Assert.assertEquals("W", MoveFleetCommand.oppositeDirectionOf("E"));
        Assert.assertEquals("E", MoveFleetCommand.oppositeDirectionOf("W"));
        try{
            Assert.assertEquals("S", MoveFleetCommand.oppositeDirectionOf("X"));
        }catch (IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().startsWith("Direction must be one of N, S, E, W"));
        }
    }

    @Test
    public void canNotMoveShipInBadDirection(){
        generateTestCaseOne();
        try{
            player.moveFleet("P");
        }catch (IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().startsWith("Direction must be one of N, S, E, W"));
        }
    }

    @Test
    public void shipsStayWhenRequired(){
        generateTestCaseOne();
        player.moveFleet("W");
        // assert new locations. Battleship cant move west or north
        Assert.assertEquals(new Location(1,9), player.getShipByName("Battleship").getCaptainsQuarters());
        Assert.assertEquals(new Location(1,7), player.getShipByName("Minesweeper").getCaptainsQuarters());
        Assert.assertEquals(new Location(4,2), player.getShipByName("Destroyer").getCaptainsQuarters());
        Assert.assertEquals(new Location(1,6, -1), player.getShipByName("Submarine").getCaptainsQuarters());
        // undo
        player.undoMoveFleet();
        Assert.assertEquals(new Location(1,9), player.getShipByName("Battleship").getCaptainsQuarters());
        Assert.assertEquals(new Location(2,7), player.getShipByName("Minesweeper").getCaptainsQuarters());
        Assert.assertEquals(new Location(5,2), player.getShipByName("Destroyer").getCaptainsQuarters());
        Assert.assertEquals(new Location(2,6, -1), player.getShipByName("Submarine").getCaptainsQuarters());
        // Try west, followed by north
        player.redoMoveFleet();
        player.moveFleet("N");
        Assert.assertEquals(new Location(1,9), player.getShipByName("Battleship").getCaptainsQuarters());
        Assert.assertEquals(new Location(1,8), player.getShipByName("Minesweeper").getCaptainsQuarters());
        Assert.assertEquals(new Location(4,3), player.getShipByName("Destroyer").getCaptainsQuarters());
        Assert.assertEquals(new Location(1,7, -1), player.getShipByName("Submarine").getCaptainsQuarters());
        // undo twice, ensure at starting locations
        player.undoMoveFleet();
        player.undoMoveFleet();
        Assert.assertEquals(new Location(1,9), player.getShipByName("Battleship").getCaptainsQuarters());
        Assert.assertEquals(new Location(2,7), player.getShipByName("Minesweeper").getCaptainsQuarters());
        Assert.assertEquals(new Location(5,2), player.getShipByName("Destroyer").getCaptainsQuarters());
        Assert.assertEquals(new Location(2,6, -1), player.getShipByName("Submarine").getCaptainsQuarters());
    }
}
