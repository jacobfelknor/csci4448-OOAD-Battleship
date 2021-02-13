import edu.colorado.fantasticfour.Board;
import org.junit.Test;

public class BoardClassTest {

    @Test
    public void doesBoardExist() {
        Board board = new Board();
        board.construct();
    }
}
