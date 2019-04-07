package util;

import exception.IllegalInputException;

import static util.HeightConstraint.LHEIGHT;
import static util.HeightConstraint.UHEIGHT;
import static util.WidthConstraint.LWIDTH;
import static util.WidthConstraint.UWIDTH;

public final class InputValidator {

    private InputValidator() {
    }

    public static void checkForValidTwoDimensionPair(String pair) {
        if (pair.length() != 2) {
            throw new IllegalInputException(pair + ": is not a valid two dimension pair.\n" +
                    "Expected <Character,Integer> with bound [" + LHEIGHT.getHeight() + "-" + UHEIGHT.getHeight() + ","
                    + LWIDTH.getWidth() + "-" + UWIDTH.getWidth() + "]");
        }

        Character x = pair.charAt(0);
        if (x < LHEIGHT.getHeight() || x > UHEIGHT.getHeight()) {
            throw new IllegalInputException(pair + ": is not a valid two dimension pair.\n" +
                    "Height must be bounded within [" + LHEIGHT.getHeight() + "-" + UHEIGHT.getHeight() + "]");
        }

        Integer y = Character.getNumericValue(pair.charAt(1));
        if (y < LWIDTH.getWidth() || y > UWIDTH.getWidth()) {
            throw new IllegalInputException(pair + ": is not a valid two dimension pair.\n" +
                    "Width must be bounded within [" + LWIDTH.getWidth() + "-" + UWIDTH.getWidth() + "]");
        }
    }
}
