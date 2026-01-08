# Repository Guidelines

## Project Structure & Module Organization
- Gradle 멀티모듈 구조이며 루트에 `account`, `transfer`, `common`, `aggregate` 모듈이 있습니다.
- 도메인 모듈은 `model`, `infrastructure`, `service`, `exception`, `api`, `repository-{type}`, `application-{type}`로 구성합니다.
- `repository-{type}`은 `jpa|jdbc|mongo` 등 저장 방식에 따라 분기합니다.
- `application-{type}`은 실행/조립 모듈로 `api|batch` 등으로 분기합니다.
- 각 모듈은 보통 `src/main/java`, `src/test/java`를 사용하며 통합 테스트는 `src/integrationTest/java`에 둡니다.
- 모듈 목록은 `settings.gradle.kts`에서 관리됩니다.

## Build, Test, and Development Commands
- `./gradlew build`: 전체 모듈 컴파일 및 테스트 실행.
- `./gradlew test`: 단위 테스트 실행(JUnit Jupiter).
- `./gradlew integrationTest`: 통합 테스트 실행.
- `./gradlew check`: `test` 이후 `integrationTest`까지 포함한 검증 작업.
- `./gradlew spotlessApply`: Google Java Format 기반 포맷팅 적용.
- 애플리케이션 실행은 `./gradlew :account:application-api:bootRun` 형태입니다.

## Coding Style & Naming Conventions
- Kotlin DSL 기반 Gradle 설정(`build.gradle.kts`).
- Java 코드는 Google Java Format을 따르며 `spotless`로 강제됩니다.
- 들여쓰기는 4칸 스페이스를 사용합니다.
- 패키지 소문자, 클래스 PascalCase를 유지합니다.
- 기술 이름을 클래스명에 포함하지 않습니다(Jpa, Http, Kafka, Web 등 금지).
- 역할과 책임이 이름에 드러나도록 하고, 동일 책임은 동일 접미사를 사용합니다.

### Core 네이밍
- `model`: Aggregate Root는 `Domain`, 구성 요소는 `DomainIdentity`/`DomainProps`, 값 객체는 `XXXValue`/`XXXAmount`/`XXXPolicy`.
- `service`: 유스케이스 인터페이스는 `DomainCreateUseCase`/`DomainReadUseCase`/`DomainUpdateUseCase`/`DomainQueryUseCase`, 구현체는 `DomainService`/`DomainQueryService`.
- `exception`: 모든 비즈니스 예외는 `BusinessException`을 상속하며 `DomainNotFoundException` 등 의미 중심 네이밍.
- `infrastructure`: 포트는 `DomainRepository`/`DomainEventPublisher`/`DomainExternalClient` 등으로 정의.

### Inbound Adapter 네이밍
- `api`: `DomainApiController`, `DomainApiRequest`, `DomainApiResponse`.
- `api-operation`: `DomainOperationApiController`.
- `api-internal`: `DomainInternalApiController`, `DomainInternalApiRequest`, `DomainInternalApiResponse`.
- `api-internal-client`: `DomainInternalApiClient`, `DomainInternalApiDto`.
- `in-port-internal`: `DomainApi`, `DomainInternalApi`.

### Outbound Adapter 네이밍
- `repository-{type}`: 구현체 `DomainRepositoryImpl`, 기술 리포지토리 `DomainJpaRepository`, 영속 엔티티 `DomainJpaEntity`.

### Application 네이밍
- `application-{type}`: `DomainApiApplication`, `DomainBatchApplication` 등으로 구성하고 비즈니스 로직은 두지 않습니다.

## Dependency Rules
- 도메인 간 직접 의존은 `model -> model` 수준에서만 제한적으로 허용합니다.
- 구현 기술(JPA, Web, MQ, Batch 등)은 구현 모듈에만 둡니다.
- Core 모듈은 구현 모듈에 의존하지 않습니다.

### Core 의존
- `model`: `common:model`만 의존, 나머지 모듈 의존 금지.
- `infrastructure`: `{domain}:model`만 의존, repository/api/application 의존 금지.
- `service`: `{domain}:model`, `{domain}:infrastructure`, `{domain}:exception`만 의존.
- `exception`: `common:exception`만 의존, 기술 예외 직접 의존 금지.

### Adapter 의존
- `api`: `common:api`, `{domain}:service`, `{domain}:exception`만 의존, repository 직접 의존 금지.
- `repository-{type}`: `common:repository-{type}`, `{domain}:infrastructure`만 의존.
- `application-{type}`: `common:application-{type}`, `{domain}:repository-{type}` 의존, 비즈니스 로직 금지.

## Gradle Exposure Rules
- `api`: 외부 모듈이 계약/타입을 알아야 할 때 사용.
- `implementation`: 내부 구현 세부사항에 사용.

## Testing Guidelines
- 단위 테스트는 `*Test` 클래스 네이밍을 권장합니다.
- 통합 테스트는 `src/integrationTest/java`에 두고 `integrationTest` 태스크로 실행합니다.
- 기본 테스트 프레임워크는 JUnit Jupiter, 통합 테스트는 Testcontainers를 사용합니다.

## Commit & Pull Request Guidelines
- 권장 메시지: 명령형 한 줄 요약 + 필요 시 상세 설명.
- PR에는 변경 요약, 영향 모듈, 테스트 결과를 포함하는 것을 권장합니다.
