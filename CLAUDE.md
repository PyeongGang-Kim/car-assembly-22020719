# CLAUDE.md

## 프로젝트 개요

자동차 부품 조립 시뮬레이터. 단일 Java 파일(`java/Assemble.java`)로 구성된 콘솔 애플리케이션.

## 빌드 및 실행

```bash
javac java/Assemble.java -d out/
java -cp out Assemble
```

## 코드 구조

- **진입점**: `main()` — `while(true)` 루프로 단계(step) 변수를 이동하며 메뉴를 순환
- **상태 저장**: `stack[5]` 정수 배열 — 각 인덱스가 단계(CarType=0, Engine=1, Brake=2, Steering=3)에 대응
- **단계 흐름**: `CarType_Q → Engine_Q → BrakeSystem_Q → SteeringSystem_Q → Run_Test`
- **유효성 검증**: `isValidCheck()` / `testProducedCar()` — 동일한 호환 규칙을 각각 boolean 반환과 메시지 출력으로 중복 구현

## 호환 규칙 (isValidCheck / testProducedCar)

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

## 주의 사항

- `isValidCheck()`와 `testProducedCar()`는 같은 규칙을 두 번 구현하고 있음 — 규칙 변경 시 두 메서드를 모두 수정해야 함
- `stack` 배열은 static 전역 변수이므로 처음 화면으로 돌아가도 이전 선택 값이 유지됨 (새 선택으로 덮어쓰여짐)
- 엔진 옵션 4번(고장난 엔진)은 `isValidCheck()`에서 검사하지 않고 `runProducedCar()`에서 별도 처리
- ANSI 이스케이프 코드(`\033[H\033[2J`)로 화면을 지우므로 Windows 구버전 콘솔에서 동작하지 않을 수 있음
