import enums.BrakeSystem;
import enums.CarType;
import enums.Engine;
import enums.SteeringSystem;

public class Menu {

    static void show(Step step) {
        switch (step) {
            case CAR_TYPE:  showCarType();  break;
            case ENGINE:    showEngine();   break;
            case BRAKE:     showBrake();    break;
            case STEERING:  showSteering(); break;
            case RUN_TEST:  showRunTest();  break;
        }
    }

    static boolean isValidRange(Step step, int ans) {
        switch (step) {
            case CAR_TYPE: {
                CarType[] types = CarType.values();
                if (ans < 1 || ans > types.length) {
                    System.out.printf("ERROR :: 차량 타입은 1 ~ %d 범위만 선택 가능%n", types.length);
                    return false;
                }
                break;
            }
            case ENGINE: {
                Engine[] engines = Engine.values();
                if (ans < 0 || ans > engines.length) {
                    System.out.printf("ERROR :: 엔진은 1 ~ %d 범위만 선택 가능%n", engines.length);
                    return false;
                }
                break;
            }
            case BRAKE: {
                BrakeSystem[] brakes = BrakeSystem.values();
                if (ans < 0 || ans > brakes.length) {
                    System.out.printf("ERROR :: 제동장치는 1 ~ %d 범위만 선택 가능%n", brakes.length);
                    return false;
                }
                break;
            }
            case STEERING: {
                SteeringSystem[] steerings = SteeringSystem.values();
                if (ans < 0 || ans > steerings.length) {
                    System.out.printf("ERROR :: 조향장치는 1 ~ %d 범위만 선택 가능%n", steerings.length);
                    return false;
                }
                break;
            }
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
