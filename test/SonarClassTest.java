import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public class SonarClassTest {
    private Player player;
    private Player player2;
    private boolean result;
    private boolean[] resultList = new boolean[13];
    private Location location1;
    private Location location2;


    @Before
    public void setUp(){
        player = new Player();
        player2 = new Player();
        player.setOpponent(player2);
        player.sonar.setTarget(new Location(5,5));
        location1 = new Location(5,4);
        location2 = new Location(6, 4);
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
        Assert.assertEquals(1, player.sonar.movesRemain());
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
    public void sonarDetectsShip() {
        result = player.sonar.getSonarAt(new Location(5,5));
        Assert.assertFalse(result);
    }

    @Test
    public void checkSonarResults() {
        player2.placeShip("Minesweeper", location1, "W");
        player.sonar.useSonar();
        resultList = player.sonar.getSonarResults();
        for (int i = 0; i < resultList.length; i++){
            // added == 4 condition to verify process, not permanent
            if ((i == 2) || (i == 3)){
                Assert.assertTrue(resultList[i]);
            }else {
                Assert.assertFalse(resultList[i]);
            }
        }
    }

    @Test
    public void checkTargetInBounds() {
        try{
            player.sonar.setTarget(new Location(11,13));
            fail();
        }catch (IllegalArgumentException e) {
            Assert.assertEquals("Location does not exist on this board", e.getMessage());
        }
    }

    @Test
    public void checkOutOfBoundsIsFalse() {
        player.sonar.setTarget(new Location(0,0));
        player.sonar.useSonar();
        resultList = player.sonar.getSonarResults();

        Assert.assertFalse(resultList[0]);
    }


}