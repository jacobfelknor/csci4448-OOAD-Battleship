import edu.colorado.fantasticfour.location.Location;
import edu.colorado.fantasticfour.ship.ShipOrientationDescriptor;
import org.junit.Assert;
import org.junit.Test;

public class ShipOrientationDescriptorTest {
    @Test
    public void canCreateAndGet(){
        ShipOrientationDescriptor sd = new ShipOrientationDescriptor(new Location(5,6), "N");
        Assert.assertEquals("N", sd.getOrientation());
        Assert.assertEquals(new Location(5,6), sd.getLocation());
    }
}
