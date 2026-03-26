# MapleScouter - Project Documentation

메이플스토리 캐릭터 정보 조회 및 스카우터 서비스입니다.

## 📚 주요 문서

프로젝트의 상세한 기술 스택과 아키텍처에 대한 내용은 아래 문서를 참고해주세요.

*   [기술 스택 및 선택 이유 (docs/tech-stack.md)](docs/tech-stack.md)
*   [아키텍처 구조 (docs/architecture.md)](docs/architecture.md)

## 🚀 시작하기

### API Key 설정
`local.properties` 파일에 넥슨 Open API Key를 추가해야 합니다.

```properties
API_KEY=your_nexon_api_key_here
```

## 🏗 프로젝트 구조

*   **`:domain`**: 순수 Kotlin 비즈니스 로직
*   **`:data`**: API 통신 및 데이터 처리
*   **`:app`**: Compose UI 및 ViewModel
*   **`:core`**: 공통 유틸리티 및 인터셉터
