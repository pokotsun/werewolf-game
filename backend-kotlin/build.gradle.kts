import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.diffplug.spotless") version "6.23.0"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

sourceSets {
    // Generated は自動生成コードの集合
    val generatedJava by creating {
        java.srcDir("src/generated/java")
    }
    val generatedKotlin by creating {
        kotlin.srcDir("src/generated/kotlin")
    }

    main {
        java {
            srcDirs(generatedJava.allJava.srcDirs)
        }
        kotlin {
            srcDirs(generatedKotlin.kotlin.srcDirs)
        }
    }
}

dependencyManagement {
    imports {
        mavenBom("io.kotest:kotest-bom:5.8.0")
        mavenBom("org.jetbrains.exposed:exposed-bom:0.58.0")
        mavenBom("org.testcontainers:testcontainers-bom:1.20.4")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.data:spring-data-rest-webmvc")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("org.postgresql:postgresql:42.7.3")

    // exposed 用の依存関係
    implementation("org.jetbrains.exposed:exposed-core")
    implementation("org.jetbrains.exposed:exposed-dao")
    implementation("org.jetbrains.exposed:exposed-jdbc")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime")

    implementation("org.jetbrains.exposed:exposed-json")
    implementation("org.jetbrains.exposed:exposed-spring-boot-starter")

    // grpc 用の依存関係
    implementation("com.google.protobuf:protobuf-java:4.29.3")
    val grpcVersion = "1.69.0"
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("io.grpc:grpc-stub:$grpcVersion")
    implementation("io.grpc:grpc-netty-shaded:$grpcVersion")
    implementation("net.devh:grpc-spring-boot-starter:3.1.0.RELEASE")
    implementation("javax.annotation:javax.annotation-api:1.3.2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("io.kotest:kotest-runner-junit5")
    testImplementation("io.kotest:kotest-framework-datatest")
    testImplementation("io.kotest:kotest-assertions-json")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
    testImplementation("com.ninja-squad:springmockk:4.0.2")

    // postgresql testcontainers
    testImplementation("org.testcontainers:postgresql")

    testImplementation("org.instancio:instancio-junit:5.4.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// spotless フォーマッタ
configure<SpotlessExtension> {
    val ktlintVersion = "0.50.0"
    kotlin {
        targetExclude("**/generated/**/*.kt")
        ktlint(ktlintVersion)
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint(ktlintVersion)
    }
    format("misc") {
        target("*.md", "*.gitignore", "**/*.yml", "**/*.yaml")
        trimTrailingWhitespace()
        endWithNewline()
    }
}
