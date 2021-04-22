import edu.colorado.fantasticfour.game.Board;
import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

public class BoardClassTest {
    Board board;
    @Before
    public void setUp(){
        board = new Board(new Player());
    }

    @Test
    public void testGetCell(){
        Assert.assertNotNull(board.getCellAt(new Location(5,5)));
        Assert.assertNotNull(board.getCellAt(new Location(1,4, -1)));
    }

    @Test
    public void testCellNotOnBoard(){
        try{
            board.getCellAt(new Location(5,14,1));
            fail();
        }catch (IllegalArgumentException e){
            Assert.assertEquals("Cell does not exist on this board", e.getMessage());
        }
    }
}
