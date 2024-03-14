# 빌드용 이미지
FROM amazoncorretto:17 as build

# 작업 디렉토리 설정
WORKDIR /build

# 프로젝트 파일 복사
COPY . .

# 빌드 이미지 애플리케이션 빌드
RUN gradle clean build -x test --parallel --continue > /dev/null 2>&1 || true

## APP
FROM amazoncorretto:17
WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=build /build/api/build/libs/api.jar app.jar

# 포트 열기
EXPOSE 8888

# 애플리케이션 실행
CMD ["java", "-jar", "app.jar"]
