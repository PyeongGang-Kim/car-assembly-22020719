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
| `Assemble` | `main()` — 단계(step) 변수로 흐름 제어, 부품 선택 시 `CarSpec` 갱신 |
| `CarSpec` | 조립 상태 보유 — `CarType`, `Engine`, `BrakeSystem`, `SteeringSystem` 필드 |
| `Validator` | 호환 규칙 단일 관리 — `isValid(CarSpec)` / `getFailReason(CarSpec)` |
| `Menu` | 메뉴 출력 + 입력 범위 검증 — `enum.values()` 순회로 동적 생성 |
| `enums/*` | `CarType`, `Engine`, `BrakeSystem`, `SteeringSystem` — `code`, `displayName` 보유 |

## 단계 흐름

```
CAR_TYPE → ENGINE → BRAKE → STEERING → RUN_TEST
```
- `0` 입력 시 이전 단계로 이동 (`CAR_TYPE` 단계 제외)
- `RUN_TEST`에서 `0` 입력 시 `CAR_TYPE`으로 초기화

## 호환 규칙 (Validator)

**차량 타입별 제약**

| 차량 타입 | 사용 불가 부품 |
|-----------|---------------|
| Sedan | CONTINENTAL 제동장치 |
| SUV | TOYOTA 엔진 |
| Truck | WIA 엔진 |
| Truck | MANDO 제동장치 |

**부품 간 제약**

| 부품 | 조건 |
|------|------|
| BOSCH 제동장치 | BOSCH 조향장치만 함께 사용 가능 |

## 확장 가이드

- **새 차량 타입/부품 추가**: 해당 enum에 `(code, displayName)` 항목 추가 → 메뉴·범위 검증 자동 반영
- **호환 규칙 추가**: `Validator.getFailReason()`에 조건과 메시지 추가
- **규칙 변경 후**: `ValidatorTest` 실행으로 회귀 확인

## 주의 사항

- `Engine.BROKEN`은 `Validator`를 통과하고 `runProducedCar()`에서 별도 처리됨
- ANSI 이스케이프 코드(`\033[H\033[2J`)로 화면을 지우므로 Windows 구버전 콘솔에서 동작하지 않을 수 있음
