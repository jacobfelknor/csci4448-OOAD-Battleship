import edu.colorado.fantasticfour.Board;
import edu.colorado.fantasticfour.Player;
import org.junit.Test;

public class BoardClassTest {

    @Test
    public void doesBoardExist() {
        Board board = new Board(new Player());
    }
}
