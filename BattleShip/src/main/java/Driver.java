import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

import javafx.util.Pair;

import players.BattleShipWarrior;
import players.Player;
import util.InputValidator;
import vessels.Ship;
import vessels.ShipFactory;

public class Driver {
    private static final ShipFactory shipyard = new ShipFactory();
    private static Pair<Character, Integer> battleArea;
    private static String ship1Type;
    private static Pair<Integer, Integer> battleShip1Size;
    private static Pair<Character, Integer> playerAShip1Loc;
    private static Pair<Character, Integer> playerBShip1Loc;
    private static String ship2Type;
    private static Pair<Integer, Integer> battleShip2Size;
    private static Pair<Character, Integer> playerAShip2Loc;
    private static Pair<Character, Integer> playerBShip2Loc;
    private static List<String> playerATargets;
    private static List<String> playerBTargets;

    public static void main(String[] args) {
        readInputs();
        Player warrior1 = getWarriorA();
        Player warrior2 = getWarriorB();
        BattleSpace area = new BattleSpace(battleArea, warrior1, warrior2);
        area.init();
        area.startWar();
    }

    private static void readInputs() {
        Scanner sc = new Scanner(System.in);

        readBoundary(sc);

        readShip1Type(sc);

        readShip1Size(sc);

        readShip1LocationForPlayerA(sc);

        readShip1LocationForPlayerB(sc);

        readShip2Type(sc);

        readShip2Size(sc);

        readShip2LocationForPlayerA(sc);

        readShip2LocationForPlayerB(sc);

        readMissileTargetsOfPlayerA(sc);

        readMissileTargetsOfPlayerB(sc);
    }

    private static Player getWarriorA() {
        final Ship ship11 = shipyard.getShip("s11", ship1Type, battleShip1Size, playerAShip1Loc);
        final Ship ship21 = shipyard.getShip("s21", ship2Type, battleShip2Size, playerAShip2Loc);

        List<Ship> vesselsForPlayer = new ArrayList<Ship>() {{
            add(ship11);
            add(ship21);
        }};

        Queue<Pair<Character, Integer>> missileTargets = new LinkedList<>();
        playerATargets.forEach((target) -> {
            InputValidator.checkForValidTwoDimensionPair(target);
            Character xCoordinate = target.charAt(0);
            Integer yCoordinate = Character.getNumericValue(target.charAt(1));
            missileTargets.add(new Pair<>(xCoordinate, yCoordinate));
        });

        return new BattleShipWarrior("Player-1", vesselsForPlayer, missileTargets);
    }

    private static Player getWarriorB() {
        final Ship ship12 = shipyard.getShip("s12", ship1Type, battleShip1Size, playerBShip1Loc);
        final Ship ship22 = shipyard.getShip("s22", ship2Type, battleShip2Size, playerBShip2Loc);

        List<Ship> vesselsForPlayer = new ArrayList<Ship>() {{
            add(ship12);
            add(ship22);
        }};

        Queue<Pair<Character, Integer>> missileTargets = new LinkedList<>();
        playerBTargets.forEach((target) -> {
            InputValidator.checkForValidTwoDimensionPair(target);
            Character xCoordinate = target.charAt(0);
            Integer yCoordinate = Character.getNumericValue(target.charAt(1));
            missileTargets.add(new Pair<>(xCoordinate, yCoordinate));
        });

        return new BattleShipWarrior("Player-2", vesselsForPlayer, missileTargets);
    }

    private static void readBoundary(Scanner sc) {
        System.out.println("Enter area boundaries:");
        String bd = sc.next();

        InputValidator.checkForValidTwoDimensionPair(bd);

        Character x = bd.charAt(0);
        Integer y = Character.getNumericValue(bd.charAt(1));
        battleArea = new Pair<>(x, y);
    }

    private static void readShip1Type(Scanner sc) {
        System.out.println("Type for battleship 1:");
        ship1Type = sc.next();
    }

    private static void readMissileTargetsOfPlayerB(Scanner sc) {
        System.out.println("Missile targets for player B:");
        String tempB = sc.nextLine();
        playerBTargets = Arrays.asList(tempB.split(" "));
    }

    private static void readShip2Size(Scanner sc) {
        System.out.println("Dimension for battleship 2:");
        Integer bs2x = sc.nextInt();
        Integer bs2y = sc.nextInt();
        battleShip2Size = new Pair<>(bs2x, bs2y);
    }

    private static void readShip2Type(Scanner sc) {
        System.out.println("Type for battleship 2:");
        ship2Type = sc.next();
    }

    private static void readShip1LocationForPlayerB(Scanner sc) {
        System.out.println("Location of battleship 1 for player B:");
        String loc = sc.next();

        InputValidator.checkForValidTwoDimensionPair(loc);
        Character x = loc.charAt(0);
        Integer y = Character.getNumericValue(loc.charAt(1));

        playerBShip1Loc = new Pair<>(x, y);
    }

    private static void readShip1LocationForPlayerA(Scanner sc) {
        System.out.println("Location of battleship 1 for player A:");
        String loc = sc.next();

        InputValidator.checkForValidTwoDimensionPair(loc);
        Character x = loc.charAt(0);
        Integer y = Character.getNumericValue(loc.charAt(1));

        playerAShip1Loc = new Pair<>(x, y);
    }

    private static void readShip1Size(Scanner sc) {
        System.out.println("Dimension for battleship 1:");
        Integer bs1x = sc.nextInt();
        Integer bs1y = sc.nextInt();
        battleShip1Size = new Pair<>(bs1x, bs1y);
    }

    private static void readShip2LocationForPlayerA(Scanner sc) {
        System.out.println("Location of battleship 2 for player A:");
        String loc = sc.next();

        InputValidator.checkForValidTwoDimensionPair(loc);
        Character x = loc.charAt(0);
        Integer y = Character.getNumericValue(loc.charAt(1));

        playerAShip2Loc = new Pair<>(x, y);
    }

    private static void readMissileTargetsOfPlayerA(Scanner sc) {
        System.out.println("Missile targets for player A:");
        sc.nextLine(); // To swallow previous <Enter>
        String tempA = sc.nextLine();
        playerATargets = Arrays.asList(tempA.split(" "));
    }

    private static void readShip2LocationForPlayerB(Scanner sc) {
        System.out.println("Location of battleship 2 for player B:");
        String loc = sc.next();

        InputValidator.checkForValidTwoDimensionPair(loc);
        Character x = loc.charAt(0);
        Integer y = Character.getNumericValue(loc.charAt(1));

        playerBShip2Loc = new Pair<>(x, y);
    }
}
