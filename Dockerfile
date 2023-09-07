# 자바 버전 17로 설정
FROM openjdk:17-alpine

# JAR_FILE 이란 변수로 jar파일 설정
ARG JAR_FILE=/build/libs/*.jar

# 위에 명시한 파일 이미지에 복사
COPY ${JAR_FILE} /app.jar

# 8080포트 접근 허용 설정
EXPOSE 8080

# 컨테이너 올릴때 명령문
CMD ["java", "-jar", "/app.jar"]