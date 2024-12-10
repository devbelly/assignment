tasks.getByName("bootJar"){
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":domain"))
    runtimeOnly(project(":storage"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}