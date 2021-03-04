import edu.colorado.fantasticfour.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;

//import static junit.framework.TestCase.assertNotNull;
//import static junit.framework.TestCase.fail;

public class SonarClassTest {
    private Player player;
    private Player player2;
    //private Cell cell;
    private boolean centerResult;

    @Before
    public void setUp(){
        player = new Player();
        player2 = new Player();
    }

    @Test
    public void sonarObjectExists() {
        Assert.assertNotNull(player.sonar);
    }

    @Test
    public void startWithMovesRemaining() {
        Assert.assertEquals(2, player.sonar.movesRemain());
    }

    @Test
    public void decrementsMoveCount() {
        player.sonar.useSonar();
        Assert.assertEquals(1, player.sonar.movesRemain());
    }

    @Test
    public void noSonarLeft() {
        player.sonar.useSonar();
        player.sonar.useSonar();
        Assert.assertEquals(0, player.sonar.movesRemain());
        try{
            player.sonar.useSonar();
            fail();
        }catch (IllegalArgumentException e){
            Assert.assertEquals("No moves left", e.getMessage());
        }
    }

    @Test
    public void returnsShipAtCenterOfSonar() {
        player.setOpponent(player2);
        centerResult = player.sonar.getSonarAt(5,5);
        //Location location = new Location(5,5);
        //cell = player.sonar.getLocations();
        //Assert.assertEquals(null, cell.getLocation().getX());
        //Assert.assertEquals(null, cell.getLocation().getY());
        Assert.assertFalse(centerResult);
    }


}