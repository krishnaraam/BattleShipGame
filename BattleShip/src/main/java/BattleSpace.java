import javafx.util.Pair;

import players.Player;
import util.ConstraintValidator;

public class BattleSpace {
    private final Pair<Character, Integer> boundary;
    private final Player p1;
    private final Player p2;

    BattleSpace(Pair<Character, Integer> boundary, Player p1, Player p2) {
        this.boundary = boundary;
        ConstraintValidator.validateBattleArea(boundary);
        this.p1 = p1;
        this.p2 = p2;
    }

    public void init() {
        p1.initBattleArea(boundary);
        p2.initBattleArea(boundary);
    }

    public void startWar() {
        p1.fireMissile(p2);
    }
}
