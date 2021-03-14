import edu.colorado.fantasticfour.location.Location;
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

    @Test
    public void canAddSubtractLocations(){
        Location location2 = new Location(5,7);
        Location location3 = new Location(4,3);
        Assert.assertEquals(new Location(9,10), location2.plus(location3));
        Assert.assertEquals(location2, location2.plus(location3).minus(location3));
    }

    @Test
    public void canMultiplyLocations(){
        Location location = new Location(0,5);
        Assert.assertEquals(new Location(0,25), location.times(5));
        location = new Location(2,6);
        Assert.assertEquals(new Location(126, 378), location.times(63));
    }

    @Test
    public void canMixAddSubMult(){
        Location i = Location.iHat();
        Location j = Location.jHat();
        Assert.assertEquals(new Location(4,13), i.times(4).plus(j.times(13)));
        Location location = new Location(8,9);
        Assert.assertEquals(new Location(6, 9), location.minus(Location.iHat().times(2)));
    }

    @Test
    public void toStringTest(){
        Location location = new Location(7,8);
        Assert.assertEquals("Location<(7,8)>", location.toString());
    }
}
