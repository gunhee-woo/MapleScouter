# 아키텍처 구조

## 🏗 Clean Architecture & MVVM
MapleScouter 프로젝트는 관심사 분리와 테스트 용이성을 위해 Clean Architecture를 기반으로 레이어를 분리했습니다.

### 1. Domain Layer (`:domain`)
*   **역할:** 비즈니스 로직의 핵심이며, 다른 레이어에 의존하지 않는 순수 Kotlin 모듈입니다.
*   **포함 요소:**
    *   **UseCase:** 개별 비즈니스 로직 단위 (예: `GetCharacterInfoUseCase`)
    *   **Model:** 앱 전체에서 사용되는 순수 데이터 클래스
    *   **Repository Interface:** 데이터 레이어와의 통신을 위한 추상화된 인터페이스

### 2. Data Layer (`:data`)
*   **역할:** 데이터 소스(Remote API, Local DB)로부터 데이터를 가져오고 도메인 모델로 변환합니다.
*   **포함 요소:**
    *   **DTO (Data Transfer Object):** API 응답 형식을 정의 (Moshi 사용)
    *   **Mapper:** DTO를 Domain Model로 변환하는 확장 함수
    *   **Repository Implementation:** Domain Layer의 인터페이스를 실제로 구현
    *   **Remote DataSource:** Retrofit 인터페이스 및 네트워크 통신 로직

### 3. App Layer (`:app`)
*   **역할:** 사용자 인터페이스를 담당하며 사용자 입력을 처리합니다.
*   **포함 요소:**
    *   **UI (Compose):** 선언형 UI 프레임워크인 Jetpack Compose 사용
    *   **ViewModel:** UI 상태 관리 및 UseCase 호출 (Hilt 주입)
    *   **DI (Hilt):** 의존성 주입 설정 및 모듈 관리

### 4. Core Layer (`:core`)
*   **역할:** 모든 모듈에서 공통적으로 사용되는 유틸리티 및 베이스 클래스를 관리합니다. (예: Interceptor, Logger 등)
