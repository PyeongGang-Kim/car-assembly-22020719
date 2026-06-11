package enums;

public enum CarType {
    SEDAN(1, "Sedan"),
    SUV(2, "SUV"),
    TRUCK(3, "Truck");

    public final int code;
    public final String displayName;

    CarType(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public static CarType fromCode(int code) {
        for (CarType t : values()) {
            if (t.code == code) return t;
        }
        throw new IllegalArgumentException("Unknown CarType code: " + code);
    }
}
