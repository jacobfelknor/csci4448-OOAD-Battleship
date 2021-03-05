import edu.colorado.fantasticfour.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public class SonarClassTest {
    private Player player;
    private Player player2;
    private boolean result;
    private boolean[] resultList = new boolean[13];

    @Before
    public void setUp(){
        player = new Player();
        player2 = new Player();
        player.setOpponent(player2);
        player.sonar.setTarget(5, 5);
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
    public void sonarDetectsShip() {
        result = player.sonar.getSonarAt(5,5);
        Assert.assertFalse(result);
    }

    @Test
    public void checkSonarResults() {
        player.sonar.useSonar();
        resultList = player.sonar.getSonarResults();
        for (int i = 0; i < resultList.length; i++){
            // added == 4 condition to verify process, not permanent
            if (i == 4){
                Assert.assertEquals(true, resultList[i]);
            }else {
                Assert.assertEquals(false, resultList[i]);
            }
        }
    }

    @Test
    public void checkTargetInBounds() {
        try{
            player.sonar.setTarget(11,13);
            player.sonar.setTarget(-1,-1);
            player.sonar.setTarget(-1,3);
            player.sonar.setTarget(3,-1);
            player.sonar.setTarget(5,21);

            fail();
        }catch (IllegalArgumentException e) {
            Assert.assertEquals("Target out of Bounds", e.getMessage());
        }
    }

    @Test
    public void checkOutOfBoundsIsFalse() {
        player.sonar.setTarget(0,0);
        player.sonar.useSonar();
        resultList = player.sonar.getSonarResults();

        Assert.assertEquals(false, resultList[0]);


    }
}