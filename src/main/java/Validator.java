import enums.BrakeSystem;
import enums.CarType;
import enums.Engine;
import enums.SteeringSystem;

public class Validator {

    public static boolean isValid(CarSpec spec) {
        return getFailReason(spec) == null;
    }

    public static String getFailReason(CarSpec spec) {
        if (spec.engine == Engine.BROKEN)
            return "고장난 엔진은 사용 불가";
        if (spec.carType == CarType.SEDAN && spec.brake == BrakeSystem.CONTINENTAL)
            return "Sedan에는 Continental제동장치 사용 불가";
        if (spec.carType == CarType.SUV && spec.engine == Engine.TOYOTA)
            return "SUV에는 TOYOTA엔진 사용 불가";
        if (spec.carType == CarType.TRUCK && spec.engine == Engine.WIA)
            return "Truck에는 WIA엔진 사용 불가";
        if (spec.carType == CarType.TRUCK && spec.brake == BrakeSystem.MANDO)
            return "Truck에는 Mando제동장치 사용 불가";
        if (spec.brake == BrakeSystem.BOSCH && spec.steering != SteeringSystem.BOSCH)
            return "Bosch제동장치에는 Bosch조향장치 이외 사용 불가";
        return null;
    }
}
