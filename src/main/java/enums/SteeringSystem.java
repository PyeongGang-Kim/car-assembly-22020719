package enums;

public enum SteeringSystem {
    BOSCH(1),
    MOBIS(2);

    public final int code;

    SteeringSystem(int code) {
        this.code = code;
    }

    public static SteeringSystem fromCode(int code) {
        for (SteeringSystem s : values()) {
            if (s.code == code) return s;
        }
        throw new IllegalArgumentException("Unknown SteeringSystem code: " + code);
    }
}
