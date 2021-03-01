import edu.colorado.fantasticfour.Location;
import org.junit.Assert;
import org.junit.Test;

public class LocationClassTest {

    @Test
    public void canCreateLocation(){
        Location location = new Location(3,5);
        Assert.assertEquals(3, location.getX());
        Assert.assertEquals(5, location.getY());
    }
}
