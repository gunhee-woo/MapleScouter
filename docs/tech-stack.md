# 기술 스택 및 선택 이유

## 📚 Network & JSON Parsing: Moshi (모시)
GSON 대신 Moshi를 사용하는 주요 이유는 다음과 같습니다.

### 1. Kotlin 최적화 (Null Safety)
*   Kotlin의 Nullable/Non-nullable 타입을 완벽하게 지원합니다.
*   `val name: String` 필드에 JSON `null`이 들어오면 파싱 단계에서 에러를 발생시켜 런타임 Crash를 방지합니다.

### 2. 컴파일 타임 코드 생성
*   `@JsonClass(generateAdapter = true)`를 사용하여 컴파일 시점에 어댑터를 생성합니다. 
*   런타임 리플렉션(Reflection)을 사용하지 않아 실행 속도가 빠르고 배터리 효율이 좋습니다.

### 3. 명확한 디버깅
*   JSON 구조가 맞지 않을 때 어떤 필드, 어떤 계층에서 에러가 났는지 상세하게 알려줍니다.

### 4. Modern & Lightweight
*   OkHttp와 Retrofit 제작사(Square)에서 만든 라이브러리로, 가장 현대적이며 상호 운용성이 뛰어납니다.

---

## 🛠 컴파일러 경고 해결 (`-Xannotation-default-target=param-property`)
Moshi 사용 시 발생하는 어노테이션 타겟 관련 경고를 해결하기 위해 `data/build.gradle.kts`에 다음 옵션을 추가했습니다.

```kotlin
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xannotation-default-target=param-property"
    }
}
```

이 옵션은 `@Json` 어노테이션이 생성자 파라미터와 클래스 필드 모두에 올바르게 적용되도록 보장합니다.
