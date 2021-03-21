import edu.colorado.fantasticfour.game.Board;
import edu.colorado.fantasticfour.game.Player;
import org.junit.Test;

public class BoardClassTest {

    @Test
    public void doesBoardExist() {
        Board board = new Board(new Player());
    }
}
