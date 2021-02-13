import edu.colorado.fantasticfour.Cell;
import org.junit.Test;

public class CellClassTest {
    Cell cell = new Cell();

    @Test
    public void isHit() {
        cell.isHit();
    }

    @Test
    public void hasCol() {
        cell.getCol();
    }

    public void hasRow(){
        cell.getRow();

    }

    public void hasShip(){
        cell.isShipOnMe();
    }
}
