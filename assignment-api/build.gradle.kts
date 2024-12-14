tasks.getByName("bootJar"){
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":storage"))
    implementation(project(":common"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("jakarta.transaction:jakarta.transaction-api:2.0.1")

    implementation("org.springframework.boot:spring-boot-starter-aop")

    //transaction
    implementation("org.springframework.boot:spring-boot-starter-jdbc:3.4.0")

    compileOnly("io.jsonwebtoken:jjwt-api:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")
}