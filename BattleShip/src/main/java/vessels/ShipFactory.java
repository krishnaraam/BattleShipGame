package vessels;

import javafx.util.Pair;

public class ShipFactory {

    public Ship getShip(String name, String type, Pair<Integer, Integer> dimension, Pair<Character, Integer> location){
        if(type.equalsIgnoreCase("P"))
            return new WeakShip(name, dimension, location);

        if(type.equalsIgnoreCase("Q"))
            return new StrongShip(name, dimension, location);

        return new UnknownShip(name);
    }
}
