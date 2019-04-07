package vessels;

import javafx.util.Pair;

public interface Ship {

    String getName();

    ShipType getType();

    Pair<Integer, Integer> getDimension();

    Pair<Character, Integer> getLocation();
}
