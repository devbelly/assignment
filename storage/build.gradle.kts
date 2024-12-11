tasks.getByName("bootJar"){
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}

plugins {
    kotlin("plugin.jpa") version "1.9.25"
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    implementation(project(":common"))
    api(project(":domain"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    // kotlin-jdsl
    implementation("com.linecorp.kotlin-jdsl:jpql-dsl:3.3.1")
    implementation("com.linecorp.kotlin-jdsl:jpql-render:3.3.1")
    implementation("com.linecorp.kotlin-jdsl:spring-data-jpa-support:3.3.1")

    // database
    runtimeOnly("com.mysql:mysql-connector-j")
}

repositories {
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
}