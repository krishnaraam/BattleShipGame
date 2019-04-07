package vessels;

import javafx.util.Pair;

public class StrongShip implements Ship {
    private static final ShipType Q = ShipType.Q;
    private final String name;
    private final Pair<Integer, Integer> dimension;
    private final Pair<Character, Integer> location;

    StrongShip(String name, Pair<Integer, Integer> dimension, Pair<Character, Integer> location) {
        this.name = name;
        this.dimension = dimension;
        this.location = location;
    }

    @Override
    public Pair<Character, Integer> getLocation() {
        return location;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ShipType getType() {
        return Q;
    }

    @Override
    public Pair<Integer, Integer> getDimension() {
        return dimension;
    }
}
