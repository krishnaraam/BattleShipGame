package util;

import javafx.util.Pair;

import exception.IllegalInputException;
import exception.ShipException;
import vessels.Ship;

import static util.HeightConstraint.LHEIGHT;
import static util.HeightConstraint.UHEIGHT;
import static util.WidthConstraint.LWIDTH;
import static util.WidthConstraint.UWIDTH;

public class ConstraintValidator {
    private ConstraintValidator() {
    }

    public static void validateBattleArea(Pair<Character, Integer> battleArea) {
        Integer width = battleArea.getValue();
        Character height = battleArea.getKey();

        if (height < LHEIGHT.getHeight() || height > UHEIGHT.getHeight() || width < LWIDTH.getWidth() || width > UWIDTH.getWidth()) {
            throw new IllegalInputException("Invalid battle area: " + battleArea.getKey() + battleArea.getValue() + ".\n" +
                    "The battle area must be bound within [" + LHEIGHT.getHeight() + "-" + UHEIGHT.getHeight() + "," +
                    LWIDTH.getWidth() + "-" + UWIDTH.getWidth() + "]");
        }
    }

    public static void checkForValidShipLocation(Pair<Character, Integer> location, Pair<Character, Integer> battleArea) {
        Character x = location.getKey();
        Integer y = location.getValue();

        Character xBound = battleArea.getKey();
        Integer yBound = battleArea.getValue();

        if (x > xBound || y > yBound) {
            throw new ShipException("Cannot place the ship in the location: " + location.getKey() + location.getValue() +
                    ". The ship location must be within the battle area [" + xBound + yBound + "]");
        }
    }

    public static void checkForValidDimension(Ship vessel,Pair<Character, Integer> battleArea){
        Pair<Integer, Integer> dimension = vessel.getDimension();
        Pair<Character, Integer> location = vessel.getLocation();

        Integer width = dimension.getKey();
        Integer height = dimension.getValue();

        Character x = location.getKey();
        Integer y = location.getValue();

        int shipHeightSpan = height + Character.getNumericValue(x - LHEIGHT.getHeight());
        int shipWidthSpan = width + y - 1;

        if(shipHeightSpan > battleArea.getKey() - LHEIGHT.getHeight()) {
            throw new ShipException("Cannot place the ship " + vessel.getName() +
                    " in the battle zone as height exceeds the boundary");
        }

        if(shipWidthSpan > battleArea.getValue()) {
            throw new ShipException("Cannot place the ship " + vessel.getName() +
                    " in the battle zone as width exceeds the boundary");
        }
    }

}
