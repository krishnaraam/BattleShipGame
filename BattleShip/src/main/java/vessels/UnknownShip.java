package vessels;

import exception.ShipException;
import javafx.util.Pair;

public class UnknownShip implements Ship {
    private static final ShipType UNKNOWN = ShipType.UNKNOWN;
    private final String name;

    public UnknownShip(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ShipType getType() {
        return UNKNOWN;
    }

    @Override
    public Pair<Integer, Integer> getDimension() {
        throw new ShipException("Cannot get dimension for unknown ship.");
    }

    @Override
    public Pair<Character, Integer> getLocation() {
        throw new ShipException("Cannot get the location for unknown ship.");
    }

}
