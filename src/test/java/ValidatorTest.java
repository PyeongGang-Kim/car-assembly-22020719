import enums.BrakeSystem;
import enums.CarType;
import enums.Engine;
import enums.SteeringSystem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    private CarSpec spec(CarType carType, Engine engine, BrakeSystem brake, SteeringSystem steering) {
        CarSpec s = new CarSpec();
        s.carType  = carType;
        s.engine   = engine;
        s.brake    = brake;
        s.steering = steering;
        return s;
    }

    // ── 유효 조합 ──────────────────────────────────────────────

    @Test
    @DisplayName("Sedan + GM + Mando + Bosch → 유효")
    void valid_sedan_gm_mando_bosch() {
        assertTrue(Validator.isValid(spec(CarType.SEDAN, Engine.GM, BrakeSystem.MANDO, SteeringSystem.BOSCH)));
    }

    @Test
    @DisplayName("Sedan + GM + Mando + Mobis → 유효")
    void valid_sedan_gm_mando_mobis() {
        assertTrue(Validator.isValid(spec(CarType.SEDAN, Engine.GM, BrakeSystem.MANDO, SteeringSystem.MOBIS)));
    }

    @Test
    @DisplayName("Sedan + GM + Bosch 제동 + Bosch 조향 → 유효")
    void valid_sedan_gm_bosch_bosch() {
        assertTrue(Validator.isValid(spec(CarType.SEDAN, Engine.GM, BrakeSystem.BOSCH, SteeringSystem.BOSCH)));
    }

    @Test
    @DisplayName("SUV + GM + Mando + Bosch → 유효")
    void valid_suv_gm_mando_bosch() {
        assertTrue(Validator.isValid(spec(CarType.SUV, Engine.GM, BrakeSystem.MANDO, SteeringSystem.BOSCH)));
    }

    @Test
    @DisplayName("SUV + WIA + Continental + Mobis → 유효")
    void valid_suv_wia_continental_mobis() {
        assertTrue(Validator.isValid(spec(CarType.SUV, Engine.WIA, BrakeSystem.CONTINENTAL, SteeringSystem.MOBIS)));
    }

    @Test
    @DisplayName("Truck + GM + Continental + Bosch → 유효")
    void valid_truck_gm_continental_bosch() {
        assertTrue(Validator.isValid(spec(CarType.TRUCK, Engine.GM, BrakeSystem.CONTINENTAL, SteeringSystem.BOSCH)));
    }

    @Test
    @DisplayName("Truck + Toyota + Bosch 제동 + Bosch 조향 → 유효")
    void valid_truck_toyota_bosch_bosch() {
        assertTrue(Validator.isValid(spec(CarType.TRUCK, Engine.TOYOTA, BrakeSystem.BOSCH, SteeringSystem.BOSCH)));
    }

    // ── 차량 타입별 제약 위반 ───────────────────────────────────

    @Test
    @DisplayName("Sedan + Continental 제동 → 불가")
    void invalid_sedan_continental() {
        assertFalse(Validator.isValid(spec(CarType.SEDAN, Engine.GM, BrakeSystem.CONTINENTAL, SteeringSystem.BOSCH)));
    }

    @Test
    @DisplayName("Sedan + Continental 제동 → 실패 사유 메시지 포함")
    void invalid_sedan_continental_reason() {
        String reason = Validator.getFailReason(spec(CarType.SEDAN, Engine.GM, BrakeSystem.CONTINENTAL, SteeringSystem.BOSCH));
        assertNotNull(reason);
        assertTrue(reason.contains("Sedan"));
    }

    @Test
    @DisplayName("SUV + Toyota 엔진 → 불가")
    void invalid_suv_toyota() {
        assertFalse(Validator.isValid(spec(CarType.SUV, Engine.TOYOTA, BrakeSystem.MANDO, SteeringSystem.BOSCH)));
    }

    @Test
    @DisplayName("Truck + WIA 엔진 → 불가")
    void invalid_truck_wia() {
        assertFalse(Validator.isValid(spec(CarType.TRUCK, Engine.WIA, BrakeSystem.CONTINENTAL, SteeringSystem.BOSCH)));
    }

    @Test
    @DisplayName("Truck + Mando 제동 → 불가")
    void invalid_truck_mando() {
        assertFalse(Validator.isValid(spec(CarType.TRUCK, Engine.GM, BrakeSystem.MANDO, SteeringSystem.BOSCH)));
    }

    // ── 부품 간 제약 위반 ───────────────────────────────────────

    @Test
    @DisplayName("Bosch 제동 + Mobis 조향 → 불가")
    void invalid_bosch_brake_mobis_steering() {
        assertFalse(Validator.isValid(spec(CarType.SEDAN, Engine.GM, BrakeSystem.BOSCH, SteeringSystem.MOBIS)));
    }

    // ── 고장난 엔진 ─────────────────────────────────────────────

    @Test
    @DisplayName("고장난 엔진 → Validator FAIL")
    void broken_engine_fails_validator() {
        assertFalse(Validator.isValid(spec(CarType.SEDAN, Engine.BROKEN, BrakeSystem.MANDO, SteeringSystem.BOSCH)));
    }

    @Test
    @DisplayName("고장난 엔진 → 실패 사유 메시지 포함")
    void broken_engine_fail_reason() {
        String reason = Validator.getFailReason(spec(CarType.SEDAN, Engine.BROKEN, BrakeSystem.MANDO, SteeringSystem.BOSCH));
        assertNotNull(reason);
        assertTrue(reason.contains("고장난 엔진"));
    }
}
