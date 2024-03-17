---
# 로컬 환경에서 모든 Spring Boot 어플리케이션 및 테스트에 적용할 설정들
spring.profiles.active: local
spring.profiles.include:
  - local
#  - aws:default  # AWS: 기본값으로 LocalStack을 사용. 실제 AWS를 대상으로 테스트하려면 주석 해제
#  - db:dev  # 기본값으로 local DB를 사용. dev/beta DB를 대상으로 테스트하려면 주석 해제

# 실제 AWS를 대상으로 테스트하려면 설정
# cloud.aws.sdk-system-setting.AWS_PROFILE: n10-dev-oneid


#spring.jpa.properties:
#  "hibernate.format_sql": false

logging.level:
  com.moin.support.LocalConfigOverrideInitializer: DEBUG # Placeholder

  # 쿼리 로깅 사용시 아래 값 변경
  #org.hibernate.SQL: DEBUG
  #org.hibernate.orm.jdbc.bind: TRACE
  #org.hibernate.engine.transaction.internal.TransactionImpl: DEBUG
