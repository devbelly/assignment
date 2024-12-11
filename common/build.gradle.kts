tasks.getByName("bootJar") {
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}

dependencies {
    implementation("ch.qos.logback:logback-classic:1.4.1")
    implementation("org.slf4j:slf4j-api:2.0.3")
}