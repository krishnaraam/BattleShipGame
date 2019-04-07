package util;

public enum HeightConstraint {
    LHEIGHT('A'), UHEIGHT('Z');

    private final Character height;

    HeightConstraint(Character height) {
        this.height = height;
    }

    public Character getHeight() {
        return height;
    }
}
