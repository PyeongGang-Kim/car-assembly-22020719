# AssemblyCar

자동차 부품을 단계적으로 선택하여 조립하고, 완성된 차량의 동작 여부를 확인하는 Java 콘솔 시뮬레이터입니다.

## 개요

사용자는 차량 타입 → 엔진 → 제동장치 → 조향장치 순서로 부품을 선택합니다. 조립 완료 후 `RUN` 또는 `Test` 동작을 통해 부품 조합의 유효성을 확인할 수 있습니다.

## 실행 방법

### 빌드 및 실행

```bash
# 컴파일
javac java/Assemble.java -d out/

# 실행
java -cp out Assemble
```

### 종료

프롬프트에 `exit` 입력 시 프로그램이 종료됩니다.

## 조립 단계

| 단계 | 항목 | 선택지 |
|------|------|--------|
| 1 | 차량 타입 | Sedan, SUV, Truck |
| 2 | 엔진 | GM, TOYOTA, WIA, 고장난 엔진 |
| 3 | 제동장치 | MANDO, CONTINENTAL, BOSCH |
| 4 | 조향장치 | BOSCH, MOBIS |

- 각 단계에서 `0`을 입력하면 이전 단계로 돌아갑니다 (차량 타입 선택 단계 제외).

## 부품 호환 규칙

아래 조합은 유효하지 않으며 `RUN` 시 동작하지 않고 `Test` 시 FAIL 판정을 받습니다.

**차량 타입별 제약**

| 차량 타입 | 사용 불가 부품 |
|-----------|---------------|
| Sedan | Continental 제동장치 |
| SUV | TOYOTA 엔진 |
| Truck | WIA 엔진 |
| Truck | MANDO 제동장치 |

**부품 간 제약**

| 부품 | 조건 |
|------|------|
| BOSCH 제동장치 | BOSCH 조향장치만 함께 사용 가능 |

## 완성 후 동작

- **RUN**: 부품 조합이 유효하면 차량 사양을 출력하며 동작. 고장난 엔진 선택 시 별도 메시지 출력.
- **Test**: 부품 조합의 유효성을 검증하여 `PASS` 또는 `FAIL` 결과와 실패 사유를 출력.

## 프로젝트 구조

```
assemblyCar-main/
├── java/
│   └── Assemble.java       # 메인 소스 파일
└── out/
    └── production/
        └── assemblyCar-main/
            └── Assemble.class  # 컴파일 결과물
```
