# 리팩토링 계획

## 현재 코드의 문제점

| # | 문제 | 위치 |
|---|------|------|
| 1 | 호환 규칙이 `isValidCheck()`와 `testProducedCar()`에 중복 구현 | Assemble.java:212, 244 |
| 2 | 부품 선택 상태를 `int[] stack` 전역 배열로 관리 — 의미 불명확 | Assemble.java:17 |
| 3 | 차량 타입·엔진·제동장치·조향장치를 매직 넘버(1, 2, 3)로 구분 | Assemble.java:12~15 |
| 4 | 고장난 엔진 처리가 유효성 검사와 분리되어 `runProducedCar()`에서 별도 처리 | Assemble.java:226 |
| 5 | UI 출력, 상태 저장, 유효성 검증이 단일 클래스에 혼재 | Assemble.java 전체 |

---

## 리팩토링 목표

1. **중복 제거** — 호환 규칙을 단일 지점에서 관리
2. **enum 도입** — 매직 넘버를 타입 안전한 열거형으로 교체
3. **관심사 분리** — UI / 상태 / 유효성 검증 레이어 분리
4. **가독성 향상** — 역할이 명확한 클래스와 메서드명 사용

---

## 단계별 작업

### Step 1. 테스트 환경 구축 (최우선)
- JUnit 5 의존성 추가 (빌드 도구 없을 시 jar 직접 추가)
- 현재 `isValidCheck()` 로직을 기준으로 호환 규칙 전체 케이스 테스트 작성
  - 유효한 조합 → `true`
  - 각 제약조건 위반 케이스 5가지 → `false`
  - 고장난 엔진 케이스
- 리팩토링 전 테스트가 모두 통과하는 것을 기준선(baseline)으로 확보

### Step 2. enum 정의
- `CarType` — SEDAN, SUV, TRUCK
- `Engine` — GM, TOYOTA, WIA, BROKEN
- `BrakeSystem` — MANDO, CONTINENTAL, BOSCH
- `SteeringSystem` — BOSCH, MOBIS

### Step 3. 조립 상태 클래스 분리
- `CarSpec` 클래스 도입 — 4가지 선택 값을 필드로 보유
- `int[] stack` 전역 배열 제거

### Step 4. 호환 규칙 단일화
- `Validator` 클래스(또는 메서드)로 규칙 통합
- `isValidCheck()` / `testProducedCar()` 중복 로직 제거
- 고장난 엔진 조건도 동일 Validator 안에서 처리
- 각 단계 후 Step 1에서 작성한 테스트로 회귀 확인

### Step 5. UI 분리
- 메뉴 출력 로직을 별도 메서드 또는 클래스로 분리
- `main()`은 흐름 제어만 담당

---

## 목표 파일 구조

```
java/
├── Assemble.java          # main() — 흐름 제어만 담당
├── CarSpec.java           # 조립 상태 (CarType, Engine, BrakeSystem, SteeringSystem)
├── Validator.java         # 호환 규칙 검증
├── Menu.java              # 메뉴 출력
└── enums/
    ├── CarType.java
    ├── Engine.java
    ├── BrakeSystem.java
    └── SteeringSystem.java

test/
└── ValidatorTest.java     # 호환 규칙 단위 테스트
```
