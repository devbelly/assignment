tasks.getByName("bootJar"){
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}

dependencies {
    compileOnly("org.springframework:spring-context")
}