import enums.BrakeSystem;
import enums.CarType;
import enums.Engine;
import enums.SteeringSystem;

import java.util.Scanner;

public class Assemble {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    private static CarSpec spec = new CarSpec();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int step = Menu.CAR_TYPE;

        while (true) {
            System.out.print(CLEAR_SCREEN);
            System.out.flush();
            Menu.show(step);

            System.out.print("INPUT > ");
            String buf = sc.nextLine().trim();

            if (buf.equalsIgnoreCase("exit")) {
                System.out.println("바이바이");
                break;
            }

            int answer;
            try {
                answer = Integer.parseInt(buf);
            } catch (NumberFormatException e) {
                System.out.println("ERROR :: 숫자만 입력 가능");
                delay(800);
                continue;
            }

            if (!Menu.isValidRange(step, answer)) {
                delay(800);
                continue;
            }

            if (answer == 0) {
                if (step == Menu.RUN_TEST) {
                    step = Menu.CAR_TYPE;
                } else if (step > Menu.CAR_TYPE) {
                    step--;
                }
                continue;
            }

            switch (step) {
                case Menu.CAR_TYPE:
                    selectCarType(answer);
                    delay(800);
                    step = Menu.ENGINE;
                    break;
                case Menu.ENGINE:
                    selectEngine(answer);
                    delay(800);
                    step = Menu.BRAKE;
                    break;
                case Menu.BRAKE:
                    selectBrakeSystem(answer);
                    delay(800);
                    step = Menu.STEERING;
                    break;
                case Menu.STEERING:
                    selectSteeringSystem(answer);
                    delay(800);
                    step = Menu.RUN_TEST;
                    break;
                case Menu.RUN_TEST:
                    if (answer == 1) {
                        runProducedCar();
                        delay(2000);
                    } else if (answer == 2) {
                        System.out.println("Test...");
                        delay(1500);
                        testProducedCar();
                        delay(2000);
                    }
                    break;
            }
        }

        sc.close();
    }

    private static void selectCarType(int a) {
        spec.carType = CarType.fromCode(a);
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.%n", spec.carType.displayName);
    }
    private static void selectEngine(int a) {
        spec.engine = Engine.fromCode(a);
        System.out.printf("%s 엔진을 선택하셨습니다.%n", spec.engine.displayName);
    }
    private static void selectBrakeSystem(int a) {
        spec.brake = BrakeSystem.fromCode(a);
        System.out.printf("%s 제동장치를 선택하셨습니다.%n", spec.brake.displayName);
    }
    private static void selectSteeringSystem(int a) {
        spec.steering = SteeringSystem.fromCode(a);
        System.out.printf("%s 조향장치를 선택하셨습니다.%n", spec.steering.displayName);
    }

    private static void runProducedCar() {
        if (!Validator.isValid(spec)) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (spec.engine == Engine.BROKEN) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }
        System.out.printf("Car Type : %s%n", spec.carType.displayName);
        System.out.printf("Engine   : %s%n", spec.engine.displayName);
        System.out.printf("Brake    : %s%n", spec.brake.displayName);
        System.out.printf("Steering : %s%n", spec.steering.displayName);
        System.out.println("자동차가 동작됩니다.");
    }

    private static void testProducedCar() {
        String reason = Validator.getFailReason(spec);
        if (reason != null) {
            System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
            System.out.println(reason);
        } else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
    }

    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}
