import edu.colorado.fantasticfour.location.Location;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

public class LocationClassTest {

    @Test
    public void canCreateLocation(){
        Location location = new Location(3,5, 7);
        Assert.assertEquals(3, location.getX());
        Assert.assertEquals(5, location.getY());
        Assert.assertEquals(7, location.getZ());
    }

    @Test
    public void canCompareLocations(){
        Location location1 = new Location(5,7);
        Location location2 = new Location(5,7, 0);
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
        Location k = Location.kHat();
        Assert.assertEquals(new Location(4,13, -15), i.times(4).plus(j.times(13)).minus(k.times(15)));
        Location location = new Location(8,9);
        Assert.assertEquals(new Location(6, 9), location.minus(Location.iHat().times(2)));
    }

    @Test
    public void canParseFromString(){
        String locStr = "2 -5 14";
        Assert.assertEquals(new Location(2,-5,14), Location.parseLocationString(locStr));
        String locStr2 = "4 5";
        Assert.assertEquals(new Location(4,5,0), Location.parseLocationString(locStr2));
    }

    private void parseStringThrowsNumberFormatExceptionHelper(String badLocStr){
        try{
            Location.parseLocationString(badLocStr);
            fail();
        }catch (NumberFormatException e){
            Assert.assertEquals("Input for Location string must contain integers", e.getMessage());
        }
    }

    @Test
    public void parseStringThrowsNumberFormatException(){
        parseStringThrowsNumberFormatExceptionHelper("33m d3 3");
        parseStringThrowsNumberFormatExceptionHelper("3 4 d");
        parseStringThrowsNumberFormatExceptionHelper("0 0 .2");
    }

    private void parseStringThrowsIllegalArgumentExceptionHelper(String badLocStr){
        try{
            Location.parseLocationString(badLocStr);
            fail();
        }catch (IllegalArgumentException e){
            Assert.assertEquals("Location string is invalid. Must be 'X Y'", e.getMessage());
        }
    }

    @Test
    public void parseStringThrowsIllegalArgumentException(){
        parseStringThrowsIllegalArgumentExceptionHelper("3");
        parseStringThrowsIllegalArgumentExceptionHelper("3 4 5 6");
    }

    @Test
    public void toStringTest(){
        Location location = new Location(7,8);
        Assert.assertEquals("Location<(7,8,0)>", location.toString());
    }
}
