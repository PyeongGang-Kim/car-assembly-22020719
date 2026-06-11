import enums.BrakeSystem;
import enums.CarType;
import enums.Engine;
import enums.SteeringSystem;

public class Menu {

    static final int CAR_TYPE  = 0;
    static final int ENGINE    = 1;
    static final int BRAKE     = 2;
    static final int STEERING  = 3;
    static final int RUN_TEST  = 4;

    static void show(int step) {
        switch (step) {
            case CAR_TYPE:  showCarType();  break;
            case ENGINE:    showEngine();   break;
            case BRAKE:     showBrake();    break;
            case STEERING:  showSteering(); break;
            case RUN_TEST:  showRunTest();  break;
        }
    }

    static boolean isValidRange(int step, int ans) {
        switch (step) {
            case CAR_TYPE:
                if (ans < 1 || ans > CarType.values().length) {
                    System.out.printf("ERROR :: 차량 타입은 1 ~ %d 범위만 선택 가능%n", CarType.values().length);
                    return false;
                }
                break;
            case ENGINE:
                if (ans < 0 || ans > Engine.values().length) {
                    System.out.printf("ERROR :: 엔진은 1 ~ %d 범위만 선택 가능%n", Engine.values().length);
                    return false;
                }
                break;
            case BRAKE:
                if (ans < 0 || ans > BrakeSystem.values().length) {
                    System.out.printf("ERROR :: 제동장치는 1 ~ %d 범위만 선택 가능%n", BrakeSystem.values().length);
                    return false;
                }
                break;
            case STEERING:
                if (ans < 0 || ans > SteeringSystem.values().length) {
                    System.out.printf("ERROR :: 조향장치는 1 ~ %d 범위만 선택 가능%n", SteeringSystem.values().length);
                    return false;
                }
                break;
            case RUN_TEST:
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
                    return false;
                }
                break;
        }
        return true;
    }

    private static void showCarType() {
        System.out.println("        ______________");
        System.out.println("       /|            |");
        System.out.println("  ____/_|_____________|____");
        System.out.println(" |                      O  |");
        System.out.println(" '-(@)----------------(@)--'");
        System.out.println("===============================");
        System.out.println("어떤 차량 타입을 선택할까요?");
        for (CarType t : CarType.values()) {
            System.out.printf("%d. %s%n", t.code, t.displayName);
        }
        System.out.println("===============================");
    }

    private static void showEngine() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");
        for (Engine e : Engine.values()) {
            System.out.printf("%d. %s%n", e.code, e.displayName);
        }
        System.out.println("===============================");
    }

    private static void showBrake() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        for (BrakeSystem b : BrakeSystem.values()) {
            System.out.printf("%d. %s%n", b.code, b.displayName);
        }
        System.out.println("===============================");
    }

    private static void showSteering() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        for (SteeringSystem s : SteeringSystem.values()) {
            System.out.printf("%d. %s%n", s.code, s.displayName);
        }
        System.out.println("===============================");
    }

    private static void showRunTest() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("===============================");
    }
}
