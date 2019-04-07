package players;

import java.util.List;
import java.util.Queue;

import javafx.util.Pair;

import util.ConstraintValidator;
import exception.ShipException;
import vessels.Ship;
import vessels.ShipType;

import static util.HeightConstraint.LHEIGHT;

public class BattleShipWarrior implements Player {
    private final String name;
    private final List<Ship> vessels;
    private final Queue<Pair<Character, Integer>> targets;
    private int[][] warZone;

    public BattleShipWarrior(String name, List<Ship> vessels, Queue<Pair<Character, Integer>> targets) {
        this.name = name;
        this.vessels = vessels;
        this.targets = targets;
    }

    @Override
    public void initBattleArea(Pair<Character, Integer> battleBoundary) {
        positionTheShipsInWarZone(battleBoundary);
    }

    @Override
    public int[][] getWarZone() {
        return warZone;
    }

    @Override
    public void fireMissile(Player opponent) {
        if (opponent.hasNoShips()) {
            System.out.println(this.name + " won the battle");
            return;
        }

        if (this.hasMissiles()) {
            Pair<Character, Integer> target = targets.remove();
            if (opponent.hasShip(target)) {
                System.out.println(this.name + " fires a missile with target " + target.getKey() + target.getValue() + " which hit");
                opponent.destroyCell(target);
                this.fireMissile(opponent);
            } else {
                System.out.println(this.name + " fires a missile with target " + target.getKey() + target.getValue() + " which missed");
                opponent.fireMissile(this);
            }
        } else {
            System.out.println(this.name + " has no more missiles left");
            if (opponent.hasMissiles()) {
                opponent.fireMissile(this);
            }
        }
    }

    @Override
    public boolean hasNoShips() {
        for (int i = 0; i < warZone.length; i++) {
            for (int j = 0; j < warZone[i].length; j++) {
                if (warZone[i][j] != 0)
                    return false;
            }
        }

        return true;
    }

    @Override
    public boolean hasShip(Pair<Character, Integer> location) {
        int x = Character.toUpperCase(location.getKey()) - LHEIGHT.getHeight();
        int y = location.getValue() - 1;
        return this.warZone[x][y] > 0;
    }

    @Override
    public boolean hasMissiles() {
        return !targets.isEmpty();
    }

    @Override
    public void destroyCell(Pair<Character, Integer> cell) {
        int x = Character.toUpperCase(cell.getKey()) - LHEIGHT.getHeight();
        int y = cell.getValue() - 1;

        if (warZone[x][y] > 0) {
            warZone[x][y]--;
        }
    }

    private void positionTheShipsInWarZone(Pair<Character, Integer> battleBoundary) {
        int w = battleBoundary.getValue();
        int h = Character.toUpperCase(battleBoundary.getKey()) - LHEIGHT.getHeight() + 1;
        warZone = new int[h][w];
        for (Ship vessel : vessels) {
            if(vessel.getType().equals(ShipType.UNKNOWN)) {
                throw new ShipException("Cannot position the ship " + vessel.getName() +
                " in the battle area since it is of unknown type");
            }
            ConstraintValidator.checkForValidShipLocation(vessel.getLocation(), battleBoundary);
            ConstraintValidator.checkForValidDimension(vessel, battleBoundary);

            int width = vessel.getDimension().getKey();
            int height = vessel.getDimension().getValue();

            int row = Character.toUpperCase(vessel.getLocation().getKey()) - LHEIGHT.getHeight();
            int col = vessel.getLocation().getValue() - 1;

            for (int i = row; i < row + height; i++) {
                for (int j = col; j < col + width; j++) {
                    if (warZone[i][j] != 0) {
                        throw new ShipException("Cannot position the ship " + vessel.getName() +
                                " at the location " + getXCoordinate(i) + (j + 1));
                    }
                    warZone[i][j] = vessel.getType().getResistance();
                }
            }
        }
    }

    private Character getXCoordinate(int i) {
        return (char) (LHEIGHT.getHeight() + i);
    }
}
