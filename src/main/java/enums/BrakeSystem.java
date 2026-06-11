package enums;

public enum BrakeSystem {
    MANDO(1),
    CONTINENTAL(2),
    BOSCH(3);

    public final int code;

    BrakeSystem(int code) {
        this.code = code;
    }

    public static BrakeSystem fromCode(int code) {
        for (BrakeSystem b : values()) {
            if (b.code == code) return b;
        }
        throw new IllegalArgumentException("Unknown BrakeSystem code: " + code);
    }
}
