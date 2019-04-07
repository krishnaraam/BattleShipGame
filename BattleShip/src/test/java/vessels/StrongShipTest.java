package vessels;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StrongShipTest {

    @Test
    public void testShip() {
        ShipFactory shipyard = new ShipFactory();
        Ship vessel = shipyard.getShip("Strong-Ship", "Q", new Pair<>(1,1), new Pair<>('A', 1));

        assertEquals("Strong-Ship", vessel.getName());
        assertEquals("1=1", vessel.getDimension().toString());
        assertEquals(ShipType.Q,vessel.getType());
        assertEquals("A=1", vessel.getLocation().toString());
    }
}
