package vessels;

import exception.ShipException;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShipFactoryTest {

    @Test
    public void testStrongShip() {
        ShipFactory shipyard = new ShipFactory();

        Ship vessel = shipyard.getShip("Strong-Ship", "Q", new Pair<>(1,1), null);
        assertEquals("Strong-Ship", vessel.getName());
        assertEquals("1=1", vessel.getDimension().toString());
        assertEquals(ShipType.Q,vessel.getType());
        assertEquals(null, vessel.getLocation());
    }

    @Test
    public void testWeakShip() {
        ShipFactory shipyard = new ShipFactory();

        Ship vessel = shipyard.getShip("Weak-Ship", "P", new Pair<>(2,2), null);
        assertEquals("Weak-Ship", vessel.getName());
        assertEquals("2=2", vessel.getDimension().toString());
        assertEquals(ShipType.P,vessel.getType());
        assertEquals(null, vessel.getLocation());
    }

    @Test
    public void testUnknownShip() {
        ShipFactory shipyard = new ShipFactory();

        Ship vessel = shipyard.getShip("Unknown-Ship", "R", new Pair<>(2,2), null);
        assertEquals("Unknown-Ship", vessel.getName());
        assertEquals(ShipType.UNKNOWN,vessel.getType());

        ShipException e2 = assertThrows(ShipException.class,
                ()-> vessel.getDimension());
        assertEquals("Cannot get dimension for unknown ship.", e2.getMessage());

        ShipException e3 = assertThrows(ShipException.class,
                ()-> vessel.getLocation());
        assertEquals("Cannot get the location for unknown ship.", e3.getMessage());

    }
}
