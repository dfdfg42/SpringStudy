# 스프링 공부 리포 모노레포 통합 설계

작성일: 2026-09-16
대상 계정: github.com/dfdfg42
선행 작업: woowacourse-precourse 통합 (같은 날, 같은 방식)

## 목표

스프링 공부용 리포 5개를 `dfdfg42/spring-study` 모노레포 하나로 합치고 원본 리포는 삭제한다. 커밋 히스토리는 전부 보존하고 폴더 단위로 볼 수 있어야 한다.

## 범위

5개 모두 본인 리포(fork 아님), 브랜치 하나씩. 전부 경로 재작성 후 머지로 히스토리째 이관한다.

| 원본 리포 | 브랜치 | 커밋 | 공개 | 폴더 (camelCase) | 주제 |
|---|---|---|---|---|---|
| Spring-Login-Study | master | 1 | public | springLoginStudy | 스프링 로그인 스터디 (OAuth2, H2) |
| CSEC-site | master | 11 | public | csecSite | 스프링 게시판 만들기 |
| jdbc | main | 4 | private | jdbc | JDBC 연습 |
| spring-mvc1-practice | main | 16 | private | springMvc1Practice | 스프링 MVC 1편 연습 |
| spring-jpa-practice | master | 2 | private | springJpaPractice | JPA 연습 (jpashop) |

폴더 이름은 사용자 요청으로 camelCase 통일. 리포 이름은 기존 woowacourse-precourse와 맞춰 kebab-case.

### 비밀 정보 점검 (2026-09-16)

5개 리포의 전체 히스토리에서 설정 파일(application*.properties/yml)을 모두 확인했다. 로컬 H2 설정(비번 없음 또는 `1234`), 주석 처리된 로컬 MySQL `root1234`, OAuth 키는 "Environment Variables로 등록" 문구뿐. 실제 자격 증명 없음. 비공개 3개를 공개 모노레포에 합쳐도 문제 없다.

## 결과물 구조

```
spring-study/                 (public)
├── README.md                 표: 폴더, 주제, 커밋 수, 마지막 작업일, 원본 리포명
├── .gitignore                루트용 (.idea/ 등)
├── docs/2026-09-16-spring-study-monorepo-design.md
├── springLoginStudy/
├── csecSite/
├── jdbc/
├── springMvc1Practice/
└── springJpaPractice/
```

- 각 폴더는 자체 gradlew, settings.gradle, .gitignore 유지. 루트 빌드 없음.
- 로컬 클론: `C:\Users\dfdfg\source\spring-study`
- 임시 작업 폴더: scratchpad `work/`. 끝나면 삭제.

## 이관 방식

woowacourse-precourse와 동일한 `import.sh` 재사용. 폴더명과 원본 URL이 다르므로 3번째 인자로 URL을 넘긴다.

1. `work/<폴더>`에 원본을 `--single-branch` 클론.
2. `git filter-branch --index-filter`로 모든 커밋의 경로 앞에 `<폴더>/` 붙임.
3. 모노레포에 `git merge --allow-unrelated-histories --no-ff`.
4. 트리 해시 일치 확인.

## 절차

1. `gh repo create dfdfg42/spring-study --public`, 클론, README/.gitignore/docs 첫 커밋.
2. 5개를 순서대로 import.sh 로 이관.
3. README 표 채우고 push.
4. work/ 삭제는 검증 후.

## 검증 (삭제 전 필수)

1. 5개 각각: 원본 최신 SHA = 이관 시점 SHA, 원본 트리 해시 = 모노레포 `HEAD:<폴더>` 트리 해시.
2. `git rev-list --count HEAD -- <폴더>` ≥ 원본 커밋 수.
3. 원격 main = 로컬 HEAD, 워킹트리 클린.

## 삭제

- 대상 5개: Spring-Login-Study, CSEC-site, jdbc, spring-mvc1-practice, spring-jpa-practice
- `gh repo delete dfdfg42/<이름> --yes`. 실행 직전 사용자 명시 확인.
- fork가 아니므로 GitHub에서 90일 내 복구 가능.

## 실패 대응

woowacourse-precourse 설계와 동일. 재작성은 임시 클론에서만, 머지 실패 시 `git reset --hard`, 검증 실패 시 삭제 안 함.
