/**
 * java-base 플러그인 설정.
 *
 * <p>Kotlin, Java등 자바 기반 언어의 "공통" 설정</p>
 *
 * @see org.gradle.api.plugins.JavaBasePlugin
 */

plugins {
    id("zelda.base")
    id("zelda.repositories")
    `java-base` // 소스가 없는 프로젝트의 경우
}
