plugins {
    kotlin("plugin.jpa") version "1.9.25"
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    compileOnly(project(":domain"))
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

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