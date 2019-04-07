package players;

import javafx.util.Pair;

public interface Player {

    void initBattleArea(Pair<Character, Integer> battleBoundary);

    int[][] getWarZone();

    void fireMissile(Player opponent);

    boolean hasNoShips();

    boolean hasShip(Pair<Character, Integer> location);

    boolean hasMissiles();

    void destroyCell(Pair<Character, Integer> cell);
}
