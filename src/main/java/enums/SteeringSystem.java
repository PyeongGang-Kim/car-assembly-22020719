package enums;

public enum SteeringSystem {
    BOSCH(1, "BOSCH"),
    MOBIS(2, "MOBIS");

    public final int code;
    public final String displayName;

    SteeringSystem(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public static SteeringSystem fromCode(int code) {
        for (SteeringSystem s : values()) {
            if (s.code == code) return s;
        }
        throw new IllegalArgumentException("Unknown SteeringSystem code: " + code);
    }
}
