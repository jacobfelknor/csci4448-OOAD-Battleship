import edu.colorado.fantasticfour.game.Game;
import edu.colorado.fantasticfour.game.LocalGame;
import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public class SonarClassTest {
    private Player player;
    private Player player2;
    private Location target;
    private Location location1;

    @Before
    public void setUp(){
        Game game = new LocalGame();
        player = game.getPlayer("1");
        player2 = game.getPlayer("2");
        target = new Location(5,5);
        location1 = new Location(5,4);
    }

    @Test
    public void sonarObjectExists() {
        Assert.assertNotNull(player.getSonar());
    }

    @Test
    public void startWithMovesRemaining() {
        Assert.assertEquals(2, player.getSonar().movesRemain());
    }

    @Test
    public void decrementsMoveCount() {
        // place and hit an opponent ship
        player2.placeShip("Minesweeper", new Location(0,0), "W");
        player.takeShot(new Location(0,0));
        player.getSonar().useAt(target);
        Assert.assertEquals(1, player.getSonar().movesRemain());
    }

    @Test
    public void noSonarLeft() {
        // place and hit an opponent ship
        player2.placeShip("Minesweeper", new Location(0,0), "W");
        player.takeShot(new Location(0,0));
        player.getSonar().useAt(target);
        Assert.assertEquals(1, player.getSonar().movesRemain());
        player.getSonar().useAt(target);
        Assert.assertEquals(0, player.getSonar().movesRemain());
        try{
            player.getSonar().useAt(target);
            fail();
        }catch (IllegalArgumentException e){
            Assert.assertEquals("No moves left", e.getMessage());
        }
    }

    @Test
    public void haveNotSunkShip(){
        // attempt to use sonar without sinking opponent ship first
        try{
            player.getSonar().useAt(target);
        }catch (IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().contains("Have not sunk opponent ship yet"));
        }
    }

    @Test
    public void checkSonarResults() {
        // place and hit an opponent ship
        player2.placeShip("Destroyer", new Location(0,1), "N");
        player.takeShot(new Location(0,1));
        player.takeShot(new Location(0,1));
        // now I can use sonar
        player2.placeShip("Minesweeper", location1, "W");
        player.getSonar().useAt(target);
        boolean[] resultList = player.getSonar().getSonarResults();
        Assert.assertTrue(resultList.length > 0);
        Assert.assertTrue(resultList.length < 14);
    }

    @Test
    public void useUnderSurface(){
        Location location = new Location(1, 1, -1);
        try{
            player.getSonar().useAt(location);
        }catch (IllegalArgumentException e) {
            Assert.assertTrue(e.getMessage().startsWith("Sonar can only be used on the surface"));
        }
    }

    @Test
    public void useAtSetTarget(){
        Location location = new Location(0,0,0);
        player2.placeShip("Minesweeper", location, "W");
        player.takeShot(location);
        player.getSonar().useAt(location);
        Assert.assertEquals(location, player.getSonar().getTarget());
    }

    @Test
    public void checkInBoundsOnBoard(){
        Location location = new Location(1, 50, 0);
        try{
            player.getSonar().useAt(location);
        }catch (IllegalArgumentException e) {
            Assert.assertTrue(e.getMessage().startsWith("Location does not exist on this board"));
        }
    }

    @Test
    public void returnTarget(){
        Location location = new Location(0,0,0);
        player.getSonar().setTarget(location);
        Assert.assertEquals(location, player.getSonar().getTarget());
    }

    @Test
    public void isTargetSet(){
        Location location = new Location(1, 1, 0);
        player.getSonar().setTarget(location);
        Assert.assertEquals(location, player.getSonar().getTarget());
    }

    @Test
    public void targetNotSet(){
        Location location = new Location(1, 50, 0);
        try {
            player.getSonar().setTarget(location);
        }catch(IllegalArgumentException e) {
            Assert.assertTrue(e.getMessage().startsWith("Location does not exist on this board"));
        }
    }
}