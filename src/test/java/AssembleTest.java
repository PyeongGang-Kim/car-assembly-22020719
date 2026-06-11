import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssembleTest {

    // 인덱스 상수 (Assemble.java 기준)
    private static final int CAR_TYPE  = 0;
    private static final int ENGINE    = 1;
    private static final int BRAKE     = 2;
    private static final int STEERING  = 3;

    // 차량 타입
    private static final int SEDAN = 1, SUV = 2, TRUCK = 3;
    // 엔진
    private static final int GM = 1, TOYOTA = 2, WIA = 3, BROKEN = 4;
    // 제동장치
    private static final int MANDO = 1, CONTINENTAL = 2, BOSCH_B = 3;
    // 조향장치
    private static final int BOSCH_S = 1, MOBIS = 2;

    @BeforeEach
    void resetStack() {
        Assemble.stack = new int[5];
    }

    private void set(int carType, int engine, int brake, int steering) {
        Assemble.stack[CAR_TYPE] = carType;
        Assemble.stack[ENGINE]   = engine;
        Assemble.stack[BRAKE]    = brake;
        Assemble.stack[STEERING] = steering;
    }

    // ── 유효 조합 ──────────────────────────────────────────────

    @Test
    @DisplayName("Sedan + GM + Mando + Bosch → 유효")
    void valid_sedan_gm_mando_bosch() {
        set(SEDAN, GM, MANDO, BOSCH_S);
        assertTrue(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("Sedan + GM + Mando + Mobis → 유효")
    void valid_sedan_gm_mando_mobis() {
        set(SEDAN, GM, MANDO, MOBIS);
        assertTrue(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("Sedan + GM + Bosch 제동 + Bosch 조향 → 유효")
    void valid_sedan_gm_bosch_bosch() {
        set(SEDAN, GM, BOSCH_B, BOSCH_S);
        assertTrue(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("SUV + GM + Mando + Bosch → 유효")
    void valid_suv_gm_mando_bosch() {
        set(SUV, GM, MANDO, BOSCH_S);
        assertTrue(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("SUV + WIA + Continental + Mobis → 유효")
    void valid_suv_wia_continental_mobis() {
        set(SUV, WIA, CONTINENTAL, MOBIS);
        assertTrue(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("Truck + GM + Continental + Bosch → 유효")
    void valid_truck_gm_continental_bosch() {
        set(TRUCK, GM, CONTINENTAL, BOSCH_S);
        assertTrue(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("Truck + Toyota + Bosch 제동 + Bosch 조향 → 유효")
    void valid_truck_toyota_bosch_bosch() {
        set(TRUCK, TOYOTA, BOSCH_B, BOSCH_S);
        assertTrue(Assemble.isValidCheck());
    }

    // ── 차량 타입별 제약 위반 ───────────────────────────────────

    @Test
    @DisplayName("Sedan + Continental 제동 → 불가")
    void invalid_sedan_continental() {
        set(SEDAN, GM, CONTINENTAL, BOSCH_S);
        assertFalse(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("SUV + Toyota 엔진 → 불가")
    void invalid_suv_toyota() {
        set(SUV, TOYOTA, MANDO, BOSCH_S);
        assertFalse(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("Truck + WIA 엔진 → 불가")
    void invalid_truck_wia() {
        set(TRUCK, WIA, CONTINENTAL, BOSCH_S);
        assertFalse(Assemble.isValidCheck());
    }

    @Test
    @DisplayName("Truck + Mando 제동 → 불가")
    void invalid_truck_mando() {
        set(TRUCK, GM, MANDO, BOSCH_S);
        assertFalse(Assemble.isValidCheck());
    }

    // ── 부품 간 제약 위반 ───────────────────────────────────────

    @Test
    @DisplayName("Bosch 제동 + Mobis 조향 → 불가")
    void invalid_bosch_brake_mobis_steering() {
        set(SEDAN, GM, BOSCH_B, MOBIS);
        assertFalse(Assemble.isValidCheck());
    }

    // ── 고장난 엔진 ─────────────────────────────────────────────

    @Test
    @DisplayName("고장난 엔진은 isValidCheck 통과 (runProducedCar에서 별도 처리)")
    void broken_engine_passes_isValidCheck() {
        set(SEDAN, BROKEN, MANDO, BOSCH_S);
        assertTrue(Assemble.isValidCheck());
    }
}
