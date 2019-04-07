import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javafx.util.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import players.BattleShipWarrior;
import players.Player;
import vessels.Ship;
import vessels.ShipFactory;
import vessels.ShipType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BattlePositiveTest {
    private final OutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void resetStream() {
        System.setOut(originalOut);
    }

    @Test
    public void testPlayer1Wins_OverPlayer2() {
        Pair<Character, Integer> battleArea = new Pair<>('E', 5);
        ShipFactory shipyard = new ShipFactory();

        Pair<Integer, Integer> typePShipSize = new Pair<>(2, 1);
        Pair<Integer, Integer> typeQShipSize = new Pair<>(1, 1);

        final Ship shipP1 = shipyard.getShip("P1", "P", typePShipSize, new Pair<>('D', 4));
        final Ship shipQ1 = shipyard.getShip("Q1", "Q", typeQShipSize, new Pair<>('A', 1));

        List<Ship> vesselsForPlayer1 = new ArrayList<Ship>() {{
            add(shipP1);
            add(shipQ1);
        }};
        Queue<Pair<Character, Integer>> missileTargets1 = new LinkedList<Pair<Character, Integer>>() {{
            add(new Pair<>('C', 3));
            add(new Pair<>('C', 4));
            add(new Pair<>('B', 2));
            add(new Pair<>('B', 2));
        }};

        Player warrior1 = new BattleShipWarrior("Player-1", vesselsForPlayer1, missileTargets1);

        final Ship shipP2 = shipyard.getShip("P2", "P", typePShipSize, new Pair<>('C', 3));
        final Ship shipQ2 = shipyard.getShip("Q2", "Q", typeQShipSize, new Pair<>('B', 2));

        List<Ship> vesselsForPlayer2 = new ArrayList<Ship>() {{
            add(shipP2);
            add(shipQ2);
        }};
        Queue<Pair<Character, Integer>> missileTargets2 = new LinkedList<Pair<Character, Integer>>() {{
            add(new Pair<>('A', 1));
            add(new Pair<>('B', 2));
            add(new Pair<>('B', 3));
            add(new Pair<>('A', 1));
            add(new Pair<>('D', 1));
            add(new Pair<>('E', 1));
            add(new Pair<>('D', 4));
            add(new Pair<>('D', 4));
            add(new Pair<>('D', 5));
            add(new Pair<>('D', 5));
        }};
        Player warrior2 = new BattleShipWarrior("Player-2", vesselsForPlayer2, missileTargets2);

        BattleSpace area = new BattleSpace(battleArea, warrior1, warrior2);

        area.init();

        assertEquals("Q 0 0 0 0 \n" +
                        "0 0 0 0 0 \n" +
                        "0 0 0 0 0 \n" +
                        "0 0 0 P P \n" +
                        "0 0 0 0 0 \n",
                print(warrior1.getWarZone()));
        assertEquals("0 0 0 0 0 \n" +
                        "0 Q 0 0 0 \n" +
                        "0 0 P P 0 \n" +
                        "0 0 0 0 0 \n" +
                        "0 0 0 0 0 \n",
                print(warrior2.getWarZone()));

        area.startWar();

        assertEquals("Player-1 fires a missile with target C3 which hit\n" +
                "Player-1 fires a missile with target C4 which hit\n" +
                "Player-1 fires a missile with target B2 which hit\n" +
                "Player-1 fires a missile with target B2 which hit\n" +
                "Player-1 won the battle\n", crlfToLf(outContent.toString()));

        assertTrue(warrior2.hasNoShips());
    }

    @Test
    public void testPlayer2Wins_OverPlayer1() throws IOException {
        Pair<Character, Integer> battleArea = new Pair<>('E', 5);
        ShipFactory shipyard = new ShipFactory();

        Pair<Integer, Integer> typePShipSize = new Pair<>(2, 1);
        Pair<Integer, Integer> typeQShipSize = new Pair<>(1, 1);

        final Ship shipP1 = shipyard.getShip("P1", "P", typePShipSize, new Pair<>('D', 4));
        final Ship shipQ1 = shipyard.getShip("Q1", "Q", typeQShipSize, new Pair<>('A', 1));

        List<Ship> vesselsForPlayer1 = new ArrayList<Ship>() {{
            add(shipP1);
            add(shipQ1);
        }};
        Queue<Pair<Character, Integer>> missileTargets1 = new LinkedList<Pair<Character, Integer>>() {{
            add(new Pair<>('A', 1));
            add(new Pair<>('B', 2));
            add(new Pair<>('B', 2));
            add(new Pair<>('B', 3));
        }};

        Player warrior1 = new BattleShipWarrior("Player-1", vesselsForPlayer1, missileTargets1);

        final Ship shipP2 = shipyard.getShip("P2", "P", typePShipSize, new Pair<>('C', 3));
        final Ship shipQ2 = shipyard.getShip("Q2", "Q", typeQShipSize, new Pair<>('B', 2));
        List<Ship> vesselsForPlayer2 = new ArrayList<Ship>() {{
            add(shipP2);
            add(shipQ2);
        }};
        Queue<Pair<Character, Integer>> missileTargets2 = new LinkedList<Pair<Character, Integer>>() {{
            add(new Pair<>('A', 1));
            add(new Pair<>('B', 2));
            add(new Pair<>('B', 3));
            add(new Pair<>('A', 1));
            add(new Pair<>('D', 1));
            add(new Pair<>('E', 1));
            add(new Pair<>('D', 4));
            add(new Pair<>('D', 4));
            add(new Pair<>('D', 5));
            add(new Pair<>('D', 5));
        }};
        Player warrior2 = new BattleShipWarrior("Player-2", vesselsForPlayer2, missileTargets2);

        BattleSpace area = new BattleSpace(battleArea, warrior1, warrior2);

        area.init();

        assertEquals("Q 0 0 0 0 \n" +
                        "0 0 0 0 0 \n" +
                        "0 0 0 0 0 \n" +
                        "0 0 0 P P \n" +
                        "0 0 0 0 0 \n",
                print(warrior1.getWarZone()));
        assertEquals("0 0 0 0 0 \n" +
                        "0 Q 0 0 0 \n" +
                        "0 0 P P 0 \n" +
                        "0 0 0 0 0 \n" +
                        "0 0 0 0 0 \n",
                print(warrior2.getWarZone()));

        area.startWar();

        assertEquals("Player-1 fires a missile with target A1 which missed\n" +
                "Player-2 fires a missile with target A1 which hit\n" +
                "Player-2 fires a missile with target B2 which missed\n" +
                "Player-1 fires a missile with target B2 which hit\n" +
                "Player-1 fires a missile with target B2 which hit\n" +
                "Player-1 fires a missile with target B3 which missed\n" +
                "Player-2 fires a missile with target B3 which missed\n" +
                "Player-1 has no more missiles left\n" +
                "Player-2 fires a missile with target A1 which hit\n" +
                "Player-2 fires a missile with target D1 which missed\n" +
                "Player-1 has no more missiles left\n" +
                "Player-2 fires a missile with target E1 which missed\n" +
                "Player-1 has no more missiles left\n" +
                "Player-2 fires a missile with target D4 which hit\n" +
                "Player-2 fires a missile with target D4 which missed\n" +
                "Player-1 has no more missiles left\n" +
                "Player-2 fires a missile with target D5 which hit\n" +
                "Player-2 won the battle\n", crlfToLf(outContent.toString()));

        assertTrue(warrior1.hasNoShips());

    }

    private String crlfToLf(String str) {
        return str.replace("\r\n", "\n");
    }

    private String print(int[][] area) {
        StringBuilder wz = new StringBuilder();
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[0].length; j++) {
                boolean typeFound = false;
                for (ShipType type : ShipType.values()) {
                    if (type.getResistance() == area[i][j]) {
                        wz.append(type.name()).append(" ");
                        typeFound = true;
                        break;
                    }
                }
                if (!typeFound) {
                    wz.append(area[i][j]).append(" ");
                }
            }
            wz.append("\n");
        }
        return wz.toString();
    }
}
