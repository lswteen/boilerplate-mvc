#Moin Boilerplate Mvc With Springboot 3.2.x Java 17

---
## 개발 진행시 이슈사항
```text
- 회원 Spring security Springboot 2.7.x 버전에서 Springboot 3.2.x 버전이상으로 변경하면서 변경된부분 파악에 대한 시간소요
참고링크 : https://angryfullstack.tistory.com/58

- SecurityConfig Syntax 에전방식으로 되어 경고가있어서 리펙토링필요
- 보일러프레이트 재사용가능한 프로젝트를 해보겠다는 마음으로 시작하다보니 프로젝트 초기설정시간소요
- Gradle MultiProject 를기반으로 진행하다보니 이부분도 시간소요
- Gradle 공식홈페이지 에서 제공하는 코드 스타터를 사용하면 buildSrc, gradle 2개로 나눠서 생성되지만 한개로 합치는 작업진행시 시간소요
- gradle kotlin으로 진행 시간소요
```

---
## 프로젝트 구성 설명 

### 보일러플레이트 MVC 레이어드 아키텍처 구성
참고링크 : https://angryfullstack.tistory.com/53  
모든 모듈은 1단계만 접근 되며 절대 2단계 이상 접근되지 않는다는 조건으로 구성  
DTO, Model, Entity 데이터 구간은 mapstruct 이용 처리
 
### 네이밍 규칙
```text
API DTO : API 요청객체 postfix : Request, Response 정의
데이터모델에는 사용하지 않도록 주의

Model : 의미 그대로 사용 (recode 불변객체 사용)

Entity : postfix Entity 사용하며 되도록 주석 필수
데이터 날짜형식 : Instant 사용
```

### API 모듈  
```text
user(회원), transfer(송금,견적서), common(에러처리) 및 에러핸들링처리, security 
API 모듈 각 도메인 모듈에 대한 바운디드 컨텍스트를 에그리커트(Aggregate) 한 Service 로 처리 하며 
postfix "AppService" 네이밍 규칙 사용 
```
