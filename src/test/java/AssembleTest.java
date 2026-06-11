import enums.BrakeSystem;
import enums.CarType;
import enums.Engine;
import enums.SteeringSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssembleTest {

    @BeforeEach
    void resetSpec() {
        Assemble.spec = new CarSpec();
    }

    private void set(CarType carType, Engine engine, BrakeSystem brake, SteeringSystem steering) {
        Assemble.spec.carType  = carType;
        Assemble.spec.engine   = engine;
        Assemble.spec.brake    = brake;
        Assemble.spec.steering = steering;
    }

    // ── 유효 조합 ──────────────────────────────────────────────

    @Test
    @DisplayName("Sedan + GM + Mando + Bosch → 유효")
    void valid_sedan_gm_mando_bosch() {
        set(CarType.SEDAN, Engine.GM, BrakeSystem.MANDO, SteeringSystem.BOSCH);
        assertTrue(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("Sedan + GM + Mando + Mobis → 유효")
    void valid_sedan_gm_mando_mobis() {
        set(CarType.SEDAN, Engine.GM, BrakeSystem.MANDO, SteeringSystem.MOBIS);
        assertTrue(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("Sedan + GM + Bosch 제동 + Bosch 조향 → 유효")
    void valid_sedan_gm_bosch_bosch() {
        set(CarType.SEDAN, Engine.GM, BrakeSystem.BOSCH, SteeringSystem.BOSCH);
        assertTrue(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("SUV + GM + Mando + Bosch → 유효")
    void valid_suv_gm_mando_bosch() {
        set(CarType.SUV, Engine.GM, BrakeSystem.MANDO, SteeringSystem.BOSCH);
        assertTrue(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("SUV + WIA + Continental + Mobis → 유효")
    void valid_suv_wia_continental_mobis() {
        set(CarType.SUV, Engine.WIA, BrakeSystem.CONTINENTAL, SteeringSystem.MOBIS);
        assertTrue(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("Truck + GM + Continental + Bosch → 유효")
    void valid_truck_gm_continental_bosch() {
        set(CarType.TRUCK, Engine.GM, BrakeSystem.CONTINENTAL, SteeringSystem.BOSCH);
        assertTrue(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("Truck + Toyota + Bosch 제동 + Bosch 조향 → 유효")
    void valid_truck_toyota_bosch_bosch() {
        set(CarType.TRUCK, Engine.TOYOTA, BrakeSystem.BOSCH, SteeringSystem.BOSCH);
        assertTrue(Assemble.isValidCheck());
    }

    // ── 차량 타입별 제약 위반 ───────────────────────────────────

    @Test
    @DisplayName("Sedan + Continental 제동 → 불가")
    void invalid_sedan_continental() {
        set(CarType.SEDAN, Engine.GM, BrakeSystem.CONTINENTAL, SteeringSystem.BOSCH);
        assertFalse(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("SUV + Toyota 엔진 → 불가")
    void invalid_suv_toyota() {
        set(CarType.SUV, Engine.TOYOTA, BrakeSystem.MANDO, SteeringSystem.BOSCH);
        assertFalse(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("Truck + WIA 엔진 → 불가")
    void invalid_truck_wia() {
        set(CarType.TRUCK, Engine.WIA, BrakeSystem.CONTINENTAL, SteeringSystem.BOSCH);
        assertFalse(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("Truck + Mando 제동 → 불가")
    void invalid_truck_mando() {
        set(CarType.TRUCK, Engine.GM, BrakeSystem.MANDO, SteeringSystem.BOSCH);
        assertFalse(Assemble.isValidCheck());
    }

    // ── 부품 간 제약 위반 ───────────────────────────────────────

    @Test
    @DisplayName("Bosch 제동 + Mobis 조향 → 불가")
    void invalid_bosch_brake_mobis_steering() {
        set(CarType.SEDAN, Engine.GM, BrakeSystem.BOSCH, SteeringSystem.MOBIS);
        assertFalse(Assemble.isValidCheck());
    }

    // ── 고장난 엔진 ─────────────────────────────────────────────

    @Test
    @DisplayName("고장난 엔진은 isValidCheck 통과 (runProducedCar에서 별도 처리)")
    void broken_engine_passes_isValidCheck() {
        set(CarType.SEDAN, Engine.BROKEN, BrakeSystem.MANDO, SteeringSystem.BOSCH);
        assertTrue(Assemble.isValidCheck());
    }
}
