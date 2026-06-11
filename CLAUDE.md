# CLAUDE.md

## 프로젝트 개요

자동차 부품 조립 시뮬레이터. Gradle + JUnit 5 기반 Java 콘솔 애플리케이션.

## 빌드 및 실행

```bash
gradlew.bat test          # 테스트 실행
gradlew.bat compileJava   # 컴파일
java -cp build/classes/java/main Assemble  # 실행
```

## 코드 구조

| 클래스 | 역할 |
|--------|------|
| `Assemble` | `main()` — `Step` enum으로 흐름 제어, 부품 선택 시 `CarSpec` 갱신 |
| `Step` | 조립 단계 enum — `CAR_TYPE`, `ENGINE`, `BRAKE`, `STEERING`, `RUN_TEST` + `previous()` |
| `CarSpec` | 조립 상태 보유 — `CarType`, `Engine`, `BrakeSystem`, `SteeringSystem` 필드 (nullable) |
| `Validator` | 호환 규칙 단일 관리 — `isValid(CarSpec)` / `getFailReason(CarSpec)` |
| `Menu` | 메뉴 출력 + 입력 범위 검증 — `Step` 타입 수신, `enum.values()` 순회로 동적 생성 |
| `enums/*` | `CarType`, `Engine`, `BrakeSystem`, `SteeringSystem` — `code`, `displayName` 보유 |

## 단계 흐름

```
CAR_TYPE → ENGINE → BRAKE → STEERING → RUN_TEST
```
- `0` 입력 시 `step.previous()`로 이전 단계 이동 (`CAR_TYPE` 제외)
- `RUN_TEST`에서 `0` 입력 시 `spec = new CarSpec()` 초기화 후 `CAR_TYPE`으로 복귀

## 호환 규칙 (Validator.getFailReason)

규칙은 순서대로 평가되며 첫 번째로 매칭된 사유를 반환합니다.

| 우선순위 | 조건 | 사유 |
|---------|------|------|
| 1 | 필드 null 존재 | 모든 부품을 선택해야 합니다 |
| 2 | 고장난 엔진 | 고장난 엔진은 사용 불가 |
| 3 | Sedan + CONTINENTAL | Sedan에는 Continental제동장치 사용 불가 |
| 4 | SUV + TOYOTA | SUV에는 TOYOTA엔진 사용 불가 |
| 5 | Truck + WIA | Truck에는 WIA엔진 사용 불가 |
| 6 | Truck + MANDO | Truck에는 Mando제동장치 사용 불가 |
| 7 | BOSCH 제동 + 非BOSCH 조향 | Bosch제동장치에는 Bosch조향장치 이외 사용 불가 |

## 확장 가이드

- **새 차량 타입/부품 추가**: 해당 enum에 `(code, displayName)` 항목 추가 → 메뉴·범위 검증 자동 반영
- **호환 규칙 추가**: `Validator.getFailReason()`에 조건과 메시지 추가, `ValidatorTest`에 검증 케이스 추가
- **새 조립 단계 추가**: `Step` enum에 항목 추가 → `Menu.show()`, `Menu.isValidRange()`, `Assemble.main()` switch에 case 추가

## 주의 사항

- `CarSpec` 필드는 모두 nullable — `Validator`가 null 가드를 첫 번째 규칙으로 처리
- ANSI 이스케이프 코드(`\033[H\033[2J`)로 화면을 지우므로 Windows 구버전 콘솔에서 동작하지 않을 수 있음
