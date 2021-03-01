import edu.colorado.fantasticfour.Cell;
import edu.colorado.fantasticfour.Location;
import org.junit.Assert;
import org.junit.Test;

public class CellClassTest {

    @Test
    public void canGetCooridates(){
        Location location = new Location(5,7);
        Cell cell = new Cell(location);
        Assert.assertEquals(location, cell.getLocation());
    }

    @Test
    public void canCompareCells(){
        Cell cell1 = new Cell(new Location(5,7));
        Cell cell2 = new Cell(new Location(5,7));
        Cell cell3 = new Cell(new Location(4,3));
        Integer seven = 7;
        Assert.assertEquals(cell1, cell1);
        Assert.assertEquals(cell2, cell2);
        Assert.assertEquals(cell3, cell3);
        Assert.assertEquals(cell1, cell2);
        Assert.assertEquals(cell2, cell1);
        Assert.assertNotEquals(cell1, cell3);
        Assert.assertNotEquals(cell3, cell1);
        Assert.assertNotEquals(cell2, cell3);
        Assert.assertNotEquals(cell3, cell2);
        // test against an instance of a different class
        Assert.assertNotEquals(cell1, seven);
        Assert.assertNotEquals(seven, cell1);
    }
}
