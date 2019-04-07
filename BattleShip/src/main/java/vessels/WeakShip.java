package vessels;

import javafx.util.Pair;

public class WeakShip implements Ship {
    private static final ShipType P = ShipType.P;
    private final String name;
    private final Pair<Integer, Integer> dimension;
    private final Pair<Character, Integer> location;

    WeakShip(String name, Pair<Integer, Integer> dimension, Pair<Character, Integer> location) {
        this.name = name;
        this.dimension = dimension;
        this.location = location;
    }

    @Override
    public String getName() {
        return name;
    }

    public ShipType getType(){
        return P;
    }

    @Override
    public Pair<Integer, Integer> getDimension() {
        return dimension;
    }

    @Override
    public Pair<Character, Integer> getLocation() {
        return location;
    }

}
