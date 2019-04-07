import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javafx.util.Pair;

import exception.IllegalInputException;
import exception.ShipException;
import org.junit.jupiter.api.Test;
import players.BattleShipWarrior;
import players.Player;
import vessels.Ship;
import vessels.ShipFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BattleNegativeTest extends AbstractBattleTest{

    @Test
    public void whenTwoShipsPositionedForPlayer1AreOverlapped_ExpectException() {
        Pair<Character, Integer> battleArea = new Pair<>('E', 5);
        ShipFactory shipyard = new ShipFactory();

        Pair<Integer, Integer> typePShipSize = new Pair<>(2, 1);
        Pair<Integer, Integer> typeQShipSize = new Pair<>(1, 1);

        /**
         * shipP1 and shipQ1 for player-1 is overlapped at the co-ordinate D4.
         * shipQ1 is positioned after positioning the sipP1
         * So shipQ1 will not be able to position at D4
         */
        final Ship shipP1 = shipyard.getShip("P1","P", typePShipSize, new Pair<>('A', 2));
        final Ship shipQ1 = shipyard.getShip("Q1","Q", typeQShipSize, new Pair<>('A', 2));

        List<Ship> vessels = new ArrayList<Ship>() {{
            add(shipP1);
            add(shipQ1);
        }};
        Queue<Pair<Character, Integer>> missileTargets = new LinkedList<Pair<Character, Integer>>() {{
            add(new Pair<>('A', 1));
            add(new Pair<>('B', 2));
            add(new Pair<>('B', 2));
            add(new Pair<>('B', 3));
        }};

        Player p1 = new BattleShipWarrior("Player-1", vessels, missileTargets);
        Player p2 = new BattleShipWarrior("Player-2", vessels, missileTargets);

        BattleSpace area = new BattleSpace(battleArea, p1, p2);

        ShipException thrown = assertThrows(ShipException.class,
                ()-> area.init(),
                "Expected ShipException to throw");

        assertEquals("Cannot position the ship Q1 at the location A2", thrown.getMessage());
    }

    @Test
    public void whenShipLocationDonotFallUnderTheBattleArea_ExpectException() {
        Pair<Character, Integer> boundary = new Pair<>('C', 2);
        ShipFactory shipyard = new ShipFactory();

        Pair<Integer, Integer> typeQShipSize = new Pair<>(1, 1);

        final Ship shipQ1 = shipyard.getShip("Q1","Q", typeQShipSize, new Pair<>('A', 1));
        final Ship shipQ2 = shipyard.getShip("Q2","Q", typeQShipSize, new Pair<>('A', 2));
        final Ship shipQ3 = shipyard.getShip("Q3","Q", typeQShipSize, new Pair<>('B', 1));
        final Ship shipQ4 = shipyard.getShip("Q4","Q", typeQShipSize, new Pair<>('B', 2));
        final Ship shipQ5 = shipyard.getShip("Q5","Q", typeQShipSize, new Pair<>('C', 1));
        final Ship shipQ6 = shipyard.getShip("Q6","Q", typeQShipSize, new Pair<>('C', 2));
        //This ship has no valid location in the batlle area
        final Ship shipQ7 = shipyard.getShip("Q7","Q", typeQShipSize, new Pair<>('C', 3));

        List<Ship> vessels= new ArrayList<Ship>() {{
            add(shipQ1);
            add(shipQ2);
            add(shipQ3);
            add(shipQ4);
            add(shipQ5);
            add(shipQ6);
            add(shipQ7);
        }};

        Queue<Pair<Character, Integer>> targets = new LinkedList<Pair<Character, Integer>>() {{
            add(new Pair<>('A', 1));
            add(new Pair<>('B', 2));
            add(new Pair<>('B', 2));
            add(new Pair<>('B', 3));
        }};

        Player p1 = new BattleShipWarrior("Player-1", vessels, targets);
        Player p2 = new BattleShipWarrior("Player-2", vessels, targets);

        BattleSpace area = new BattleSpace(boundary, p1, p2);


        ShipException thrown = assertThrows(ShipException.class,
                ()-> area.init(),
                "Expected ShipException to throw");

        assertEquals("Cannot place the ship in the location: C3. The ship location must be within the battle area [C2]",
                thrown.getMessage());
    }

    @Test
    public void whenBattleBoundaryExceedsTheAllowableSize_ExpectException() {
        Pair<Character, Integer> boundary = new Pair<>('A', 22);
        ShipFactory shipyard = new ShipFactory();

        Pair<Integer, Integer> typeQShipSize = new Pair<>(1, 1);

        final Ship shipQ1 = shipyard.getShip("Q1","Q", typeQShipSize, new Pair<>('A', 1));
        final Ship shipQ2 = shipyard.getShip("Q2","Q", typeQShipSize, new Pair<>('A', 2));

        List<Ship> vessels= new ArrayList<Ship>() {{
            add(shipQ1);
            add(shipQ2);
        }};

        Queue<Pair<Character, Integer>> targets = new LinkedList<Pair<Character, Integer>>() {{
            add(new Pair<>('A', 1));
            add(new Pair<>('B', 2));
            add(new Pair<>('B', 2));
            add(new Pair<>('B', 3));
        }};

        Player p1 = new BattleShipWarrior("Player-1", vessels, targets);
        Player p2 = new BattleShipWarrior("Player-2", vessels, targets);

        IllegalInputException thrown = assertThrows(IllegalInputException.class,
                ()-> new BattleSpace(boundary, p1, p2),
                "Expected ShipException to throw");

        assertEquals("Invalid battle area: A22.\n" +
                        "The battle area must be bound within [A-Z,1-9]",
                thrown.getMessage());
    }

    @Test
    public void whenAttemptedToRetrieveTheLocationOfUnknownShip_ExpectExeption() {
        Pair<Character, Integer> boundary = new Pair<>('B', 2);
        ShipFactory shipyard = new ShipFactory();
        Pair<Integer, Integer> shipSize = new Pair<>(1, 1);

        //Ship type T is not defined in the factory
        Ship unknownShip = shipyard.getShip("T","T", shipSize, new Pair<>('A', 1));
        ShipException thrown = assertThrows(ShipException.class,
                () -> unknownShip.getLocation(),
                        "Expected exception since ship type is unknown");

        assertEquals("Cannot get the location for unknown ship.", thrown.getMessage());
    }

    @Test
    public void whenDimensionOfShipWidthTooLarge_ExpectException() {
        Pair<Character, Integer> battleArea = new Pair<>('E', 5);
        ShipFactory shipyard = new ShipFactory();

        Pair<Integer, Integer> typePShipSize = new Pair<>(10, 1);
        final Ship shipP1 = shipyard.getShip("P1","P", typePShipSize, new Pair<>('D', 4));
        List<Ship> vessels = new ArrayList<Ship>() {{
            add(shipP1);
        }};
        Queue<Pair<Character, Integer>> missileTargets = new LinkedList<Pair<Character, Integer>>() {{
            add(new Pair<>('A', 1));
        }};

        Player p1 = new BattleShipWarrior("Player-1", vessels, missileTargets);
        Player p2 = new BattleShipWarrior("Player-2", vessels, missileTargets);

        BattleSpace area = new BattleSpace(battleArea, p1, p2);

        ShipException thrown = assertThrows(ShipException.class,
                ()-> area.init(),
                "Expected ShipException to throw");

        assertEquals("Cannot place the ship P1 in the battle zone as width exceeds the boundary", thrown.getMessage());
    }

    @Test
    public void whenDimensionOfShipHeightTooLarge_ExpectException() {
        Pair<Character, Integer> battleArea = new Pair<>('E', 5);
        ShipFactory shipyard = new ShipFactory();

        Pair<Integer, Integer> typePShipSize = new Pair<>(1, 10);

        final Ship shipP1 = shipyard.getShip("P1","P", typePShipSize, new Pair<>('D', 4));

        List<Ship> vessels = new ArrayList<Ship>() {{
            add(shipP1);
        }};
        Queue<Pair<Character, Integer>> missileTargets = new LinkedList<Pair<Character, Integer>>() {{
            add(new Pair<>('A', 1));
        }};

        Player p1 = new BattleShipWarrior("Player-1", vessels, missileTargets);
        Player p2 = new BattleShipWarrior("Player-2", vessels, missileTargets);

        BattleSpace area = new BattleSpace(battleArea, p1, p2);

        ShipException thrown = assertThrows(ShipException.class,
                ()-> area.init(),
                "Expected ShipException to throw");

        assertEquals("Cannot place the ship P1 in the battle zone as height exceeds the boundary", thrown.getMessage());
    }

    @Test
    public void whenUnknownShipIsTriedPlaceInTheBattleZone_ExpectException() {
        Pair<Character, Integer> battleArea = new Pair<>('E', 5);
        ShipFactory shipyard = new ShipFactory();

        Pair<Integer, Integer> typePShipSize = new Pair<>(1, 10);

        final Ship shipP1 = shipyard.getShip("P1","T", typePShipSize, new Pair<>('D', 4));

        List<Ship> vessels = new ArrayList<Ship>() {{
            add(shipP1);
        }};
        Queue<Pair<Character, Integer>> missileTargets = new LinkedList<Pair<Character, Integer>>() {{
            add(new Pair<>('A', 1));
        }};

        Player p1 = new BattleShipWarrior("Player-1", vessels, missileTargets);
        Player p2 = new BattleShipWarrior("Player-2", vessels, missileTargets);

        BattleSpace area = new BattleSpace(battleArea, p1, p2);

        ShipException thrown = assertThrows(ShipException.class,
                ()-> area.init(),
                "Expected ShipException to throw");

        assertEquals("Cannot position the ship P1 in the battle area since it is of unknown type", thrown.getMessage());
    }

}
