import edu.colorado.fantasticfour.Cell;
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

    @Test
    public void canCompareLocations(){
        Location location1 = new Location(5,7);
        Location location2 = new Location(5,7);
        Location location3 = new Location(4,3);
        Integer seven = 7;
        Assert.assertEquals(location1, location1);
        Assert.assertEquals(location2, location2);
        Assert.assertEquals(location3, location3);
        Assert.assertEquals(location1, location2);
        Assert.assertEquals(location2, location1);
        Assert.assertNotEquals(location1, location3);
        Assert.assertNotEquals(location3, location1);
        Assert.assertNotEquals(location2, location3);
        Assert.assertNotEquals(location3, location2);
        // test against an instance of a different class
        Assert.assertNotEquals(location1, seven);
        Assert.assertNotEquals(seven, location1);
    }
}
