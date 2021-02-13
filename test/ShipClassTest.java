import edu.colorado.fantasticfour.Ship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShipClassTest {
    private Ship ship;

    public void createShip(int length){
        ship = new Ship(length);
    }

    @Test
    public void shipKnowsDimension(){
        createShip(2);
        Assert.assertEquals(ship.getLength(), 2);
    }

    @Test
    public void canSinkShip(){
        createShip(2);
        Assert.assertEquals(ship.getHitCount(), 0);
        ship.gotHit();
        Assert.assertEquals(ship.getHitCount(), 1);
        ship.gotHit();
        Assert.assertTrue(ship.isSunk());
    }
}
