package vessels;

public enum ShipType {
    P(1), Q(2), UNKNOWN(-1);

    private final int resistance;

    ShipType(int resistance) {
        this.resistance = resistance;
    }

    public int getResistance() {
        return resistance;
    }

}
