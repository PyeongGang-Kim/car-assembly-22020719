package enums;

public enum CarType {
    SEDAN(1),
    SUV(2),
    TRUCK(3);

    public final int code;

    CarType(int code) {
        this.code = code;
    }

    public static CarType fromCode(int code) {
        for (CarType t : values()) {
            if (t.code == code) return t;
        }
        throw new IllegalArgumentException("Unknown CarType code: " + code);
    }
}
