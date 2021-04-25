import edu.colorado.fantasticfour.game.Player;
import org.junit.Test;

public class TerminalGridTest {
    Player player = new Player();

    @Test
    public void printsGrid(){
        player.tGrid.FillGrid();

    }
}