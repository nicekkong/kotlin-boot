import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.10"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"
    kotlin ("plugin.noarg") version "1.5.21"
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

group = "com.nicekkong"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // 지연로딩 되는 객체의 프로퍼티 직렬
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5:2.9.8")

    // KLogger
    implementation("io.github.microutils:kotlin-logging-jvm:2.0.6")

    compileOnly("org.projectlombok:lombok")
//    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client:2.7.4")

    annotationProcessor("org.projectlombok:lombok")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("io.kotlintest:kotlintest:2.0.7")
    testImplementation("io.kotest:kotest-runner-junit5:5.3.1")
    testImplementation("io.kotest:kotest-assertions-core:5.3.1")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.1")




}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Spring 2.6.8 이하에서 KoTest 사용시, kotlin-coroutines 버전이 1.5.2를 지정되어 오류 발생
//https://velog.io/@dokkabei97/Kotest-ClassNotFoundException
extra["kotlin-coroutines.version"] = "1.6.1"

