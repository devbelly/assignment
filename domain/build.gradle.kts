tasks.getByName("bootJar"){
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}

dependencies{
    compileOnly("io.jsonwebtoken:jjwt-api:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")
}