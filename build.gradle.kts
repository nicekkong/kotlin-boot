import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.10"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"
    kotlin ("plugin.noarg") version "1.5.21"
    kotlin("kapt") version "1.5.10" // java annotation Process for Kotlin

}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
    annotation("org.apache.ibatis.annotations.Mapper")

}

noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
    annotation("com.nicekkong.kotlinboot.component.NoArgs")
    annotation("org.apache.ibatis.annotations.Mapper")
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

    // MyBatis
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.2")

    // ModelMapper
    implementation("org.modelmapper:modelmapper:3.1.0")

    // mapStruct
    implementation("org.mapstruct:mapstruct:1.5.2.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.2.Final")
//    implementation("org.mapstruct:mapstruct-processor:1.5.2.Final")

    // 지연로딩 되는 객체의 프로퍼티 직렬
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5:2.9.8")

    // KLogger
    implementation("io.github.microutils:kotlin-logging-jvm:2.0.6")

    // Humanize
    implementation("com.github.mfornos:humanize-slim:1.2.2")

    // HttpClient
    implementation("org.apache.httpcomponents:httpclient:4.5.13")


    compileOnly("org.projectlombok:lombok")
    // runtimeOnly("com.h2database:h2")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client:2.7.4")

    annotationProcessor("org.projectlombok:lombok")

    // test module
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-runner-junit5:5.3.1")
    testImplementation("io.kotest:kotest-assertions-core:5.3.1")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.1")
    testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:2.2.2")
    testImplementation("io.mockk:mockk:1.12.4")
    testImplementation("com.ninja-squad:springmockk:3.1.1")



    kaptTest("org.mapstruct:mapstruct-processor:1.5.2.Final")
}

kapt {
    arguments {
        // Set Mapstruct Configuration options here
        // https://kotlinlang.org/docs/reference/kapt.html#annotation-processor-arguments
        // https://mapstruct.org/documentation/stable/reference/html/#configuration-options
         arg("mapstruct.defaultComponentModel", "spring")
    }
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

