package util;

public enum  WidthConstraint {
    LWIDTH(1), UWIDTH(9);

    private final int width;

    WidthConstraint(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }
}
