import edu.colorado.fantasticfour.Cell;
import org.junit.Assert;
import org.junit.Test;

public class CellClassTest {

    @Test
    public void canGetCooridates(){
        Cell cell = new Cell(5,7);
        Assert.assertEquals(5, cell.getX());
        Assert.assertEquals(7, cell.getY());
    }

    @Test
    public void canCompareCells(){
        Cell cell1 = new Cell(5,7);
        Cell cell2 = new Cell(5,7);
        Cell cell3 = new Cell(4,3);
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
