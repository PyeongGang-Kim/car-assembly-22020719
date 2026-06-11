package enums;

public enum BrakeSystem {
    MANDO(1, "MANDO"),
    CONTINENTAL(2, "CONTINENTAL"),
    BOSCH(3, "BOSCH");

    public final int code;
    public final String displayName;

    BrakeSystem(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public static BrakeSystem fromCode(int code) {
        for (BrakeSystem b : values()) {
            if (b.code == code) return b;
        }
        throw new IllegalArgumentException("Unknown BrakeSystem code: " + code);
    }
}
