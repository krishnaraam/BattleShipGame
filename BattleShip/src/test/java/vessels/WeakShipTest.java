package vessels;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeakShipTest {

    @Test
    public void testShip() {
        ShipFactory shipyard = new ShipFactory();
        Ship vessel = shipyard.getShip("Weak-Ship", "P", new Pair<>(1,1), new Pair<>('A', 1));

        assertEquals("Weak-Ship", vessel.getName());
        assertEquals("1=1", vessel.getDimension().toString());
        assertEquals(ShipType.P,vessel.getType());
        assertEquals("A=1", vessel.getLocation().toString());
    }
}
