package enums;

public enum Engine {
    GM(1, "GM"),
    TOYOTA(2, "TOYOTA"),
    WIA(3, "WIA"),
    BROKEN(4, "고장난 엔진");

    public final int code;
    public final String displayName;

    Engine(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public static Engine fromCode(int code) {
        for (Engine e : values()) {
            if (e.code == code) return e;
        }
        throw new IllegalArgumentException("Unknown Engine code: " + code);
    }
}
