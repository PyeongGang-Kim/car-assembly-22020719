package enums;

public enum Engine {
    GM(1),
    TOYOTA(2),
    WIA(3),
    BROKEN(4);

    public final int code;

    Engine(int code) {
        this.code = code;
    }

    public static Engine fromCode(int code) {
        for (Engine e : values()) {
            if (e.code == code) return e;
        }
        throw new IllegalArgumentException("Unknown Engine code: " + code);
    }
}
